# SmartDesk — Helpdesk Ticketing System

A backend REST API for an enterprise helpdesk ticketing system, built with Java 21 and Spring Boot 3. It handles user authentication, role-based access control, and a full ticket lifecycle from creation to closure.

**Live demo:** `https://smartdesk-xxxx.onrender.com`
**Frontend UI:** `https://smartdesk-xxxx.onrender.com/SmartDesk.html`

> Note: the free-tier Render instance spins down after 15 minutes of inactivity, so the first request after a while can take 30-60 seconds to respond. That's a hosting limitation, not a bug.

---

## What it does

SmartDesk lets users raise support tickets, agents pick them up and work through them, and admins oversee the whole system. It's a pure backend API — no server-rendered pages — so it can be consumed by any frontend, though a simple dashboard UI (`SmartDesk.html`) is included for convenience.

Three roles are supported: `USER`, `AGENT`, and `ADMIN`, each with different permissions on what they can view, update, or delete.

## Tech stack

- **Language:** Java 21
- **Framework:** Spring Boot 3
- **Security:** Spring Security with JWT-based authentication
- **Database:** H2 (file mode, so data persists across restarts without needing a separate DB server)
- **ORM:** Spring Data JPA / Hibernate
- **Build tool:** Maven
- **Containerization:** Docker (multi-stage build)
- **Deployment:** Render
- **Testing:** Postman

## Architecture

The app follows a standard layered structure:

```
Controller → Service → Repository → Database
```

Requests first pass through a security filter (`JwtAuthFilter`), which checks the JWT in the `Authorization` header before anything reaches the controllers. Once authenticated, routes are further restricted by role — for example, only agents and admins can update ticket status, and only admins can delete tickets.

**Key design decisions:**
- **JWT over sessions** — the API is stateless, so no session data is stored server-side. This keeps it easier to scale horizontally later.
- **H2 in file mode for now** — avoids the overhead of running a separate database during early development. Switching to PostgreSQL later only requires changing the datasource URL (see FAQ below).
- **Layered architecture** — keeps HTTP handling, business logic, and data access separate, which makes the codebase easier to test and extend.

## Ticket lifecycle

Tickets move through four states:

```
OPEN → IN_PROGRESS → RESOLVED → CLOSED
```

- A ticket starts as `OPEN` when a user creates it.
- An agent or admin can move it to `IN_PROGRESS` once they start working on it.
- Once fixed, it goes to `RESOLVED` — and can be reopened (back to `IN_PROGRESS`) if the issue isn't actually fixed.
- Only an admin can mark it `CLOSED`, which is the final, immutable state.

## Roles and permissions

| Action | User | Agent | Admin |
|---|:---:|:---:|:---:|
| Register / Login | ✓ | ✓ | ✓ |
| Create ticket | ✓ | ✓ | ✓ |
| View own tickets | ✓ | ✓ | ✓ |
| View all tickets | – | ✓ | ✓ |
| Update ticket status | – | ✓ | ✓ |
| Assign ticket to agent | – | ✓ | ✓ |
| Delete ticket | – | – | ✓ |
| Access H2 console | – | – | ✓ |

## Database schema

Two main tables:

**users** — `id`, `name`, `email`, `password` (hashed), `role`, `created_at`
**tickets** — `id`, `title`, `description`, `priority`, `status`, `created_by`, `assigned_to`, `created_at`, `updated_at`

Each ticket is linked to the user who created it. Priority is one of `LOW` / `MEDIUM` / `HIGH`.

## API endpoints

**Auth (public)**

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Create a new account |
| POST | `/api/auth/login` | Log in and receive a JWT |

**Tickets (require a valid token)**

| Method | Endpoint | Description | Minimum role |
|---|---|---|---|
| GET | `/api/tickets` | List tickets | User |
| POST | `/api/tickets` | Create a ticket | User |
| GET | `/api/tickets/{id}` | Get a single ticket | User |
| PUT | `/api/tickets/{id}` | Update status / assignment | Agent |
| DELETE | `/api/tickets/{id}` | Delete a ticket | Admin |

### Example: creating a ticket

```http
POST /api/tickets
Authorization: Bearer <token>
Content-Type: application/json

{
  "title": "Cannot login to internal portal",
  "description": "Getting a 403 error after resetting my password.",
  "priority": "HIGH"
}
```

Response:

```json
{
  "id": 7,
  "title": "Cannot login to internal portal",
  "status": "OPEN",
  "createdBy": "Karan Awala",
  "assignedTo": "Unassigned",
  "createdAt": "2026-03-17T10:30:00"
}
```

### Error responses

All errors follow the same shape:

```json
{
  "timestamp": "2026-03-17T10:00:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "Invalid email or password"
}
```

Common status codes: `400` (bad input), `401` (missing/invalid token), `403` (valid token, wrong role), `404` (not found), `409` (email already registered), `500` (server error).

## Security notes

- Passwords are hashed with BCrypt before storage.
- JWTs are signed with HMAC-SHA256 and expire after 24 hours by default (configurable).
- Tokens only carry the user's email and role — nothing sensitive.
- Every request is checked against the security filter before reaching a controller, not just at the annotation level.

## Project structure

```
smartdesk/
├── controller/     → handles HTTP requests (AuthController, TicketController)
├── service/        → business logic (AuthService, TicketService)
├── repository/     → database access via Spring Data JPA
├── model/          → JPA entities (User, Ticket)
├── dto/            → request/response objects
├── security/       → JWT filter, security config, user details service
└── exception/      → centralized error handling
```

## Running it locally

**Requirements:** Java 21, Maven 3.8+, Git (Docker optional)

```bash
git clone https://github.com/karanaawla1/SmartDesk-Helpdesk-System.git
cd SmartDesk-Helpdesk-System/smartdesk

./mvnw clean package -DskipTests
java -jar target/*.jar
```

The app runs at `http://localhost:8080`. The dashboard UI is at `http://localhost:8080/SmartDesk.html`, and the H2 console (if you need to inspect the DB directly) is at `http://localhost:8080/h2-console`.

### Environment variables

| Variable | Default | Purpose |
|---|---|---|
| `SERVER_PORT` | `8080` | Port the app listens on |
| `JWT_SECRET` | (weak placeholder) | Signing key — use a strong 256-bit key in production |
| `JWT_EXPIRATION` | `86400000` | Token lifetime in ms (default 24h) |
| `SPRING_DATASOURCE_URL` | H2 file path | Point this at PostgreSQL for production |

## Running with Docker

```bash
docker build -t smartdesk:latest .

docker run -d \
  --name smartdesk-app \
  -p 8080:8080 \
  -v smartdesk-data:/app/data \
  -e JWT_SECRET=your-secret-key \
  smartdesk:latest
```

Or with Docker Compose:

```bash
docker-compose up -d
```

The Dockerfile uses a multi-stage build — dependencies and compilation happen in a JDK-based stage, but the final image only ships the JRE, which keeps it lean (roughly 180MB vs 400MB+ if the JDK were included).

## What's next

Planned for the next iteration:
- Comments on tickets
- An activity/audit log for status changes
- Email notifications on ticket updates
- Pagination and filtering on the tickets list
- Swagger/OpenAPI docs

Longer term: migrating from H2 to PostgreSQL for production use, adding proper unit and integration tests, and support for real-time updates via WebSockets.

## FAQ

**Why H2 instead of PostgreSQL right now?**
It avoids needing a separate database server during early development — everything just works out of the box. Moving to PostgreSQL later is a small change: update the `spring.datasource.url` and related properties in `application.properties`, add the PostgreSQL driver dependency, and remove the H2 one.

**How do I test the API?**
Register a user via `/api/auth/register`, grab the JWT from the response, and pass it as a `Bearer` token in the `Authorization` header for any ticket-related request. Postman works well for this.

**Can I build on top of this?**
Yes — it's MIT licensed.

## Author

**Karan Awala**
[karanaawla07@gmail.com](mailto:karanaawla07@gmail.com) · [GitHub](https://github.com/karanaawla1) · [LinkedIn](https://linkedin.com/in/karanaawla)

## License

MIT License — see [LICENSE](./LICENSE) for details.
