<div align="center">

<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=700&size=28&pause=1000&color=00D9FF&center=true&vCenter=true&width=600&lines=SmartDesk+%F0%9F%96%A5%EF%B8%8F;Helpdesk+Ticketing+System;Built+with+Java+%26+Spring+Boot" alt="Typing SVG" />

<br/>

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring%20Security-JWT-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)](https://spring.io/projects/spring-security)
[![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![H2 Database](https://img.shields.io/badge/H2-Database-0000BB?style=for-the-badge&logo=h2&logoColor=white)](https://www.h2database.com/)
[![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Render](https://img.shields.io/badge/Deployed%20on-Render-46E3B7?style=for-the-badge&logo=render&logoColor=white)](https://render.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)](./LICENSE)

<br/>

> **A production-ready Helpdesk Ticketing System** backend built with Java 21 & Spring Boot 3.x —
> featuring JWT authentication, RBAC, full ticket lifecycle management, Docker support, and a custom dark-themed frontend.

<br/>

[🚀 Live Demo](#-live-demo) · [📡 API Docs](#-api-endpoints) · [🐳 Docker](#-docker-deployment) · [🤝 Connect](#-author)

---

</div>

## 📌 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Tech Stack](#️-tech-stack)
- [System Architecture](#️-system-architecture)
- [Project Structure](#-project-structure)
- [API Reference](#-api-endpoints)
- [Getting Started](#-getting-started)
- [Docker Deployment](#-docker-deployment)
- [Live Demo](#-live-demo)
- [Roadmap](#-roadmap)
- [Author](#-author)

---

## 🔍 Overview

**SmartDesk** is an enterprise-grade **REST API backend** for managing IT helpdesk support tickets. It solves the core challenge of structured ticket tracking — from the moment an issue is raised to its final resolution — with fine-grained access control across multiple user roles.

Whether you're integrating this into an existing enterprise system or building a new support portal on top of it, SmartDesk provides a **robust, secure, and scalable foundation**.

**Who is it for?**
- 🏢 **Enterprises** needing an internal IT helpdesk system
- 👨‍💻 **Developers** looking for a reference Spring Boot REST API with security
- 🎓 **Students & Learners** studying JWT auth, RBAC, and Spring Boot best practices

---

## ✨ Features

<table>
<tr>
<td>

**🔐 Security**
- JWT-based stateless authentication
- BCrypt password hashing
- Role-Based Access Control (RBAC)
- Per-request token validation via filter chain

</td>
<td>

**🎫 Ticket Management**
- Full lifecycle: `OPEN → IN_PROGRESS → RESOLVED → CLOSED`
- Priority levels: `LOW`, `MEDIUM`, `HIGH`
- Ticket assignment to agents
- Timestamped audit trail

</td>
</tr>
<tr>
<td>

**👥 User Roles**
- `USER` — create & view own tickets
- `AGENT` — manage & update all tickets
- `ADMIN` — full system access + admin panel

</td>
<td>

**⚙️ Infrastructure**
- Docker multi-stage containerized build
- H2 file-mode database (persists across restarts)
- Global exception handling with clean error responses
- Deployed & live on Render

</td>
</tr>
</table>

---

## 🛠️ Tech Stack

| Layer | Technology | Purpose |
|:------|:-----------|:--------|
| **Language** | Java 21 (LTS) | Core application logic |
| **Framework** | Spring Boot 3.x | REST API, IoC, auto-configuration |
| **Security** | Spring Security + JWT (JJWT) | Authentication & authorization |
| **ORM** | Spring Data JPA + Hibernate | Database abstraction |
| **Database** | H2 (File Mode) | Persistent embedded storage |
| **Build** | Maven | Dependency management & build |
| **Container** | Docker (Multi-stage) | Portable deployment |
| **Deployment** | Render | Cloud hosting |
| **Testing** | Postman | API testing & documentation |
| **Version Control** | Git + GitHub | Source management |

---

## 🏗️ System Architecture

```
╔══════════════════════════════════════════════════════════════╗
║                         CLIENT LAYER                        ║
║           Postman  ·  Browser  ·  Frontend (SmartDesk.html) ║
╚══════════════════════════════╦═══════════════════════════════╝
                               ║  HTTPS / REST
                               ▼
╔══════════════════════════════════════════════════════════════╗
║                      SPRING BOOT APPLICATION                ║
║                                                              ║
║  ┌────────────────────────────────────────────────────────┐  ║
║  │               🔐 Spring Security Layer                 │  ║
║  │                                                        │  ║
║  │   JwtAuthFilter  ──→  validates Bearer Token          │  ║
║  │   SecurityConfig ──→  configures route permissions    │  ║
║  └──────────────────────────┬─────────────────────────────┘  ║
║                             │                                ║
║  ┌──────────────────────────▼─────────────────────────────┐  ║
║  │                  🎮 Controller Layer                   │  ║
║  │                                                        │  ║
║  │   AuthController    ──→  /api/auth/**                 │  ║
║  │   TicketController  ──→  /api/tickets/**              │  ║
║  └──────────────────────────┬─────────────────────────────┘  ║
║                             │                                ║
║  ┌──────────────────────────▼─────────────────────────────┐  ║
║  │                  ⚙️  Service Layer                     │  ║
║  │                                                        │  ║
║  │   AuthService    ──→  register, login, JWT ops        │  ║
║  │   TicketService  ──→  CRUD, lifecycle transitions     │  ║
║  └──────────────────────────┬─────────────────────────────┘  ║
║                             │                                ║
║  ┌──────────────────────────▼─────────────────────────────┐  ║
║  │                🗄️  Repository Layer                    │  ║
║  │                                                        │  ║
║  │   UserRepository    ──→  Spring Data JPA              │  ║
║  │   TicketRepository  ──→  Spring Data JPA              │  ║
║  └──────────────────────────┬─────────────────────────────┘  ║
║                             │                                ║
║  ┌──────────────────────────▼─────────────────────────────┐  ║
║  │                  💾 H2 Database                        │  ║
║  │                                                        │  ║
║  │   Mode     : File (persists across restarts)          │  ║
║  │   Path     : /app/data/smartdeskdb                    │  ║
║  │   Tables   : users, tickets                           │  ║
║  └────────────────────────────────────────────────────────┘  ║
╚══════════════════════════════════════════════════════════════╝
```

### 🔐 JWT Authentication Flow

```
┌──────────┐     POST /register      ┌─────────────┐     Save     ┌──────────┐
│  Client  │ ──────────────────────▶ │  AuthCtrl   │ ──────────▶  │    DB    │
└──────────┘                         └──────┬──────┘              └──────────┘
                                            │ Return JWT Token
                                            ▼
┌──────────┐     POST /login         ┌─────────────┐   Validate   ┌──────────┐
│  Client  │ ──────────────────────▶ │  AuthCtrl   │ ──────────▶  │    DB    │
└──────────┘                         └──────┬──────┘              └──────────┘
                                            │ Return JWT Token
                                            ▼
┌──────────┐  Bearer Token in Header  ┌────────────┐  Authorized  ┌──────────┐
│  Client  │ ────────────────────────▶│ JwtFilter  │ ──────────▶  │ Resource │
└──────────┘                          └────────────┘              └──────────┘
```

### 🎫 Ticket Lifecycle

```
  ╔════════╗    Agent picks up    ╔═════════════╗   Issue fixed   ╔══════════╗   Verified   ╔════════╗
  ║  OPEN  ║ ─────────────────▶  ║ IN_PROGRESS ║ ─────────────▶ ║ RESOLVED ║ ──────────▶ ║ CLOSED ║
  ╚════════╝                     ╚═════════════╝                 ╚══════════╝             ╚════════╝
```

### 👥 Role Permission Matrix

| Action | USER | AGENT | ADMIN |
|:-------|:----:|:-----:|:-----:|
| Register / Login | ✅ | ✅ | ✅ |
| Create Ticket | ✅ | ✅ | ✅ |
| View Own Tickets | ✅ | ✅ | ✅ |
| View All Tickets | ❌ | ✅ | ✅ |
| Update Ticket Status | ❌ | ✅ | ✅ |
| Delete Ticket | ❌ | ❌ | ✅ |
| Admin Panel Access | ❌ | ❌ | ✅ |

---

## 📁 Project Structure

```
smartdesk/
├── 📂 src/
│   └── 📂 main/
│       ├── 📂 java/com/smartdesk/smartdesk/
│       │   ├── 📂 controller/
│       │   │   ├── AuthController.java          # Register & Login endpoints
│       │   │   └── TicketController.java        # Ticket CRUD endpoints
│       │   │
│       │   ├── 📂 service/
│       │   │   ├── AuthService.java             # Auth logic, JWT generation
│       │   │   └── TicketService.java           # Ticket CRUD & lifecycle
│       │   │
│       │   ├── 📂 repository/
│       │   │   ├── UserRepository.java          # JPA User queries
│       │   │   └── TicketRepository.java        # JPA Ticket queries
│       │   │
│       │   ├── 📂 model/
│       │   │   ├── User.java                    # User JPA entity
│       │   │   └── Ticket.java                  # Ticket JPA entity
│       │   │
│       │   ├── 📂 dto/
│       │   │   ├── RegisterRequest.java         # Register request DTO
│       │   │   ├── LoginRequest.java            # Login request DTO
│       │   │   └── TicketRequest.java           # Create/update ticket DTO
│       │   │
│       │   ├── 📂 security/
│       │   │   ├── SecurityConfig.java          # Security config & route rules
│       │   │   ├── JwtService.java              # JWT generate & validate
│       │   │   ├── JwtAuthFilter.java           # Per-request JWT filter
│       │   │   └── CustomUserDetailsService.java# Load user from DB
│       │   │
│       │   └── 📂 exception/
│       │       └── GlobalExceptionHandler.java  # Centralized error handling
│       │
│       └── 📂 resources/
│           ├── application.properties           # App configuration
│           └── 📂 static/
│               └── SmartDesk.html               # Dark-themed frontend UI
│
├── 🐳 Dockerfile                                # Multi-stage Docker build
├── 📄 pom.xml                                   # Maven dependencies
└── 📖 README.md
```

---

## 📡 API Endpoints

### 🔓 Auth Endpoints — Public (No token required)

| Method | Endpoint | Description | Request Body |
|:------:|:---------|:------------|:-------------|
| `POST` | `/api/auth/register` | Register a new user | `name`, `email`, `password`, `role` |
| `POST` | `/api/auth/login` | Login and receive JWT | `email`, `password` |

### 🔒 Ticket Endpoints — Protected (Bearer Token required)

| Method | Endpoint | Description | Allowed Roles |
|:------:|:---------|:------------|:--------------|
| `GET` | `/api/tickets` | Get all tickets | USER · AGENT · ADMIN |
| `POST` | `/api/tickets` | Create a new ticket | USER |
| `GET` | `/api/tickets/{id}` | Get ticket by ID | USER · AGENT · ADMIN |
| `PUT` | `/api/tickets/{id}` | Update ticket status | AGENT · ADMIN |
| `DELETE` | `/api/tickets/{id}` | Delete a ticket | ADMIN |

---

### 📝 Request & Response Examples

<details>
<summary><strong>📌 Register a new user</strong></summary>

```http
POST /api/auth/register
Content-Type: application/json

{
  "name": "Karan Awala",
  "email": "karan@gmail.com",
  "password": "karan123",
  "role": "USER"
}
```

**Response `200 OK`**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

</details>

<details>
<summary><strong>📌 Login</strong></summary>

```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "karan@gmail.com",
  "password": "karan123"
}
```

**Response `200 OK`**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

</details>

<details>
<summary><strong>📌 Create a ticket</strong></summary>

```http
POST /api/tickets
Authorization: Bearer <your_jwt_token>
Content-Type: application/json

{
  "title": "Login issue",
  "description": "Unable to login to the system",
  "priority": "HIGH"
}
```

**Response `201 Created`**
```json
{
  "id": 1,
  "title": "Login issue",
  "description": "Unable to login to the system",
  "priority": "HIGH",
  "status": "OPEN",
  "createdBy": "Karan Awala",
  "assignedTo": "Unassigned",
  "createdAt": "2026-03-15T18:30:05",
  "updatedAt": "2026-03-15T18:30:05"
}
```

</details>

<details>
<summary><strong>📌 Update ticket status (AGENT / ADMIN only)</strong></summary>

```http
PUT /api/tickets/1
Authorization: Bearer <agent_or_admin_jwt_token>
Content-Type: application/json

{
  "status": "IN_PROGRESS",
  "assignedTo": "Agent Smith"
}
```

**Response `200 OK`**
```json
{
  "id": 1,
  "title": "Login issue",
  "status": "IN_PROGRESS",
  "assignedTo": "Agent Smith",
  "updatedAt": "2026-03-15T19:00:00"
}
```

</details>

---

## 🚀 Getting Started

### ✅ Prerequisites

- **Java 21+** — [Download](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.8+** — [Download](https://maven.apache.org/download.cgi)
- **Git** — [Download](https://git-scm.com/)

### 🖥️ Run Locally

```bash
# 1. Clone the repository
git clone https://github.com/karanaawla1/SmartDesk-Helpdesk-System.git

# 2. Navigate into the project
cd SmartDesk-Helpdesk-System/smartdesk

# 3. Build the project (skip tests for faster build)
./mvnw clean package -DskipTests

# 4. Run the application
java -jar target/*.jar
```

The app will be live at:

| Service | URL |
|:--------|:----|
| 🌐 API Base | `http://localhost:8080` |
| 🖥️ Frontend UI | `http://localhost:8080/SmartDesk.html` |
| 🗄️ H2 Console | `http://localhost:8080/h2-console` |

### ⚙️ Configuration

Key settings in `src/main/resources/application.properties`:

```properties
# Server
server.port=8080

# H2 Database (File Mode)
spring.datasource.url=jdbc:h2:file:/app/data/smartdeskdb
spring.h2.console.enabled=true

# JWT
jwt.secret=your_secret_key_here
jwt.expiration=86400000   # 24 hours in ms
```

---

## 🐳 Docker Deployment

### Build & Run

```bash
# Build the Docker image
docker build -t smartdesk:latest .

# Run the container
docker run -d \
  -p 8080:8080 \
  --name smartdesk-app \
  smartdesk:latest
```

### Docker Compose (optional)

```yaml
version: '3.8'
services:
  smartdesk:
    build: .
    ports:
      - "8080:8080"
    volumes:
      - smartdesk-data:/app/data
    restart: unless-stopped

volumes:
  smartdesk-data:
```

```bash
docker-compose up -d
```

> 💡 The multi-stage Dockerfile keeps the final image lean by separating the build stage (Maven + JDK) from the runtime stage (JRE only).

---

## 🌐 Live Demo

> ☁️ Deployed on **Render** — accessible 24/7

| Resource | Link |
|:---------|:-----|
| 🔗 API Base URL | `https://smartdesk-xxxx.onrender.com` |
| 🖥️ Frontend UI | `https://smartdesk-xxxx.onrender.com/SmartDesk.html` |
| 📬 Postman Collection | *(Add your Postman public link here)* |

> ⚠️ **Note:** Free-tier Render instances spin down after inactivity. The first request may take 30–60 seconds to wake up.

---

## 🗺️ Roadmap

- [x] JWT Authentication & RBAC
- [x] Full ticket lifecycle management
- [x] Docker containerization
- [x] Render deployment
- [ ] 🔜 Ticket comments & activity log
- [ ] 🔜 Email notifications on ticket status change
- [ ] 🔜 Pagination & filtering for ticket listings
- [ ] 🔜 Migrate to PostgreSQL for production
- [ ] 🔜 Unit & integration test coverage (JUnit 5 + Mockito)
- [ ] 🔜 Swagger / OpenAPI documentation
- [ ] 🔜 Refresh token support

---

## 👨‍💻 Author

<div align="center">

**Karan Awala**
*Java Backend Developer*

[![Email](https://img.shields.io/badge/Email-karanaawla07%40gmail.com-EA4335?style=for-the-badge&logo=gmail&logoColor=white)](mailto:karanaawla07@gmail.com)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-karanaawla-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/karanaawla)
[![GitHub](https://img.shields.io/badge/GitHub-karanaawla1-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/karanaawla1)

</div>

---

## 📄 License

This project is licensed under the **MIT License** — see the [LICENSE](./LICENSE) file for details.

---

<div align="center">

**If SmartDesk helped you, consider giving it a ⭐ on GitHub!**

*Built with ☕ Java & 💚 Spring Boot by Karan Awala*

</div>
