<div align="center">

<!-- Animated Banner -->
<img src="https://capsule-render.vercel.app/api?type=waving&color=0:0d1117,50:00d9ff,100:0d1117&height=200&section=header&text=SmartDesk&fontSize=70&fontColor=ffffff&fontAlignY=38&desc=Enterprise%20Helpdesk%20Ticketing%20System&descAlignY=58&descSize=20&animation=fadeIn" alt="SmartDesk Banner" width="100%"/>

<br/>

<!-- Typing Animation -->
<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=600&size=18&duration=3000&pause=800&color=00D9FF&center=true&vCenter=true&multiline=true&width=700&height=70&lines=🔐+JWT+Auth+%7C+RBAC+%7C+Full+Ticket+Lifecycle;🐳+Docker+%7C+Spring+Boot+3.x+%7C+Java+21+%7C+Deployed+on+Render" alt="Typing SVG" />

<br/><br/>

<!-- Core Badges -->
[![Java](https://img.shields.io/badge/Java-21_LTS-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring_Security-JWT-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)](https://spring.io/projects/spring-security)
[![Docker](https://img.shields.io/badge/Docker-Multi--Stage-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)

<!-- Status Badges -->
[![Render](https://img.shields.io/badge/Deployed-Render-46E3B7?style=for-the-badge&logo=render&logoColor=white)](https://render.com/)
[![REST API](https://img.shields.io/badge/API-RESTful-ff6b35?style=for-the-badge&logo=fastapi&logoColor=white)]()
[![H2](https://img.shields.io/badge/Database-H2_File_Mode-0000BB?style=for-the-badge&logo=databricks&logoColor=white)](https://www.h2database.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)](./LICENSE)
[![PRs Welcome](https://img.shields.io/badge/PRs-Welcome-brightgreen?style=for-the-badge&logo=git&logoColor=white)](./CONTRIBUTING.md)

<br/>

**[🚀 Live Demo](#-live-demo)** &nbsp;•&nbsp;
**[📡 API Reference](#-api-reference)** &nbsp;•&nbsp;
**[🐳 Docker](#-docker-deployment)** &nbsp;•&nbsp;
**[🔐 Security](#-security-deep-dive)** &nbsp;•&nbsp;
**[🗺️ Roadmap](#️-roadmap)** &nbsp;•&nbsp;
**[🤝 Contribute](#-contributing)**

<br/>

> **SmartDesk** is a production-ready, enterprise-grade **Helpdesk Ticketing REST API** built on **Java 21 + Spring Boot 3.x**.
> Stateless JWT authentication, fine-grained RBAC, a complete ticket lifecycle engine, and Docker-first deployment —
> all in a clean, layered architecture designed for real-world scale.

</div>

---

## 📌 Table of Contents

<details open>
<summary>Click to expand / collapse</summary>

- [🔍 Overview](#-overview)
- [✨ Feature Highlights](#-feature-highlights)
- [🛠️ Tech Stack](#️-tech-stack)
- [🏗️ Architecture](#️-architecture)
  - [System Layers](#system-layers)
  - [JWT Auth Flow — Sequence Diagram](#-jwt-authentication-flow)
  - [Ticket Lifecycle State Machine](#-ticket-lifecycle-state-machine)
  - [Role × Permission Matrix](#-role--permission-matrix)
- [🗄️ Database Schema](#️-database-schema)
- [📁 Project Structure](#-project-structure)
- [📡 API Reference](#-api-reference)
  - [Auth Endpoints](#-auth-endpoints--public)
  - [Ticket Endpoints](#-ticket-endpoints--protected)
  - [Request & Response Examples](#-request--response-examples)
  - [Error Codes Reference](#️-error-codes-reference)
- [🔐 Security Deep Dive](#-security-deep-dive)
- [⚙️ Configuration Reference](#️-configuration-reference)
- [🚀 Getting Started](#-getting-started)
- [🐳 Docker Deployment](#-docker-deployment)
- [🌐 Live Demo](#-live-demo)
- [🗺️ Roadmap](#️-roadmap)
- [🤝 Contributing](#-contributing)
- [❓ FAQ](#-faq)
- [👨‍💻 Author](#-author)
- [📄 License](#-license)

</details>

---

## 🔍 Overview

<table>
<tr>
<td width="60%">

**SmartDesk** solves a core enterprise problem: structured, trackable IT support — from the moment a user raises an issue to its final verified resolution — with strict role enforcement throughout.

Built as a **pure backend REST API**, SmartDesk is designed to be consumed by any frontend framework (React, Angular, Vue) or integrated into existing enterprise systems. The bundled `SmartDesk.html` provides a ready-to-use dark-themed dashboard UI out of the box.

**Key Design Decisions:**
- **Stateless auth** via JWT — no server-side session storage
- **Filter-chain security** — every request validated at the Spring Security layer before reaching controllers
- **Layered architecture** — Controller → Service → Repository — clean separation of concerns
- **File-mode H2** — data persists across restarts without PostgreSQL overhead for early-stage deployment

</td>
<td width="40%" align="center">

**Who is SmartDesk for?**

| Audience | Use Case |
|:---------|:---------|
| 🏢 **Enterprise** | Internal IT helpdesk system |
| 🔌 **Integrators** | Backend for existing portals |
| 👨‍💻 **Developers** | Spring Boot security reference |
| 🎓 **Learners** | JWT + RBAC implementation guide |

</td>
</tr>
</table>

---

## ✨ Feature Highlights

<table>
<tr>
<td valign="top" width="50%">

### 🔐 Authentication & Security
- Stateless **JWT Bearer Token** authentication
- **BCrypt** password hashing (strength: 10)
- Per-request token validation via `JwtAuthFilter`
- Role extraction directly from JWT claims
- **Global exception handler** — no stack traces exposed to clients

</td>
<td valign="top" width="50%">

### 🎫 Ticket Engine
- **4-stage lifecycle:** `OPEN → IN_PROGRESS → RESOLVED → CLOSED`
- Priority tiers: `LOW` · `MEDIUM` · `HIGH`
- Ticket assignment to specific agents
- Full **timestamps** on create & update
- Immutable `createdBy` field — audit-safe

</td>
</tr>
<tr>
<td valign="top" width="50%">

### 👥 Role-Based Access Control
- **3 roles:** `USER`, `AGENT`, `ADMIN`
- Route-level permission enforcement via `SecurityConfig`
- Role stored in DB + encoded in JWT
- Extensible for additional roles (e.g., `SUPERVISOR`)

</td>
<td valign="top" width="50%">

### 🐳 DevOps & Infrastructure
- **Multi-stage Docker build** — lean production image
- **H2 File Mode** — zero-config persistence
- Deployed on **Render** (Cloud PaaS)
- Docker Compose ready for local multi-service setup
- Environment-variable-driven configuration

</td>
</tr>
</table>

---

## 🛠️ Tech Stack

<div align="center">

| Layer | Technology | Version | Role |
|:------|:-----------|:-------:|:-----|
| ![Java](https://img.shields.io/badge/-Java-ED8B00?logo=openjdk&logoColor=white&style=flat-square) **Language** | Java (OpenJDK) | `21 LTS` | Core application runtime |
| ![Spring Boot](https://img.shields.io/badge/-Spring_Boot-6DB33F?logo=springboot&logoColor=white&style=flat-square) **Framework** | Spring Boot | `3.x` | REST API, IoC, auto-config |
| ![Security](https://img.shields.io/badge/-Spring_Security-6DB33F?logo=springsecurity&logoColor=white&style=flat-square) **Security** | Spring Security + JJWT | `6.x / 0.12.x` | Auth & authorization |
| ![JPA](https://img.shields.io/badge/-Hibernate-59666C?logo=hibernate&logoColor=white&style=flat-square) **ORM** | Spring Data JPA + Hibernate | `3.x` | DB abstraction layer |
| ![H2](https://img.shields.io/badge/-H2_Database-0000BB?logo=databricks&logoColor=white&style=flat-square) **Database** | H2 (File Mode) | `2.x` | Persistent embedded DB |
| ![Maven](https://img.shields.io/badge/-Maven-C71A36?logo=apachemaven&logoColor=white&style=flat-square) **Build** | Apache Maven | `3.8+` | Dependency mgmt & build |
| ![Docker](https://img.shields.io/badge/-Docker-2496ED?logo=docker&logoColor=white&style=flat-square) **Container** | Docker | `24+` | Multi-stage containerization |
| ![Render](https://img.shields.io/badge/-Render-46E3B7?logo=render&logoColor=white&style=flat-square) **Cloud** | Render | — | Cloud PaaS deployment |
| ![Postman](https://img.shields.io/badge/-Postman-FF6C37?logo=postman&logoColor=white&style=flat-square) **Testing** | Postman | — | API testing & collections |
| ![Git](https://img.shields.io/badge/-GitHub-181717?logo=github&logoColor=white&style=flat-square) **VCS** | Git + GitHub | — | Version control & hosting |

</div>

---

## 🏗️ Architecture

### System Layers

```
╔══════════════════════════════════════════════════════════════════════╗
║                          CLIENT  LAYER                              ║
║      Browser · Postman · Mobile App · SmartDesk.html (SPA)          ║
╚══════════════════════════════╤═══════════════════════════════════════╝
                               │   HTTPS  ·  JSON  ·  Bearer Token
                               ▼
╔══════════════════════════════════════════════════════════════════════╗
║                      SPRING BOOT APPLICATION                        ║
║                                                                      ║
║  ════════════════════════ SECURITY WALL ════════════════════════     ║
║  ║                                                             ║     ║
║  ║  ┌─────────────────────────────────────────────────────┐   ║     ║
║  ║  │   JwtAuthFilter  (OncePerRequestFilter)             │   ║     ║
║  ║  │                                                     │   ║     ║
║  ║  │   1. Extract "Authorization: Bearer <token>"        │   ║     ║
║  ║  │   2. JwtService.validateToken(token)                │   ║     ║
║  ║  │   3. Set Authentication in SecurityContext          │   ║     ║
║  ║  │   4. Chain → Controller  OR  401 Unauthorized       │   ║     ║
║  ║  └─────────────────────────────────────────────────────┘   ║     ║
║  ║                                                             ║     ║
║  ║  ┌─────────────────────────────────────────────────────┐   ║     ║
║  ║  │   SecurityConfig  (Route-level authorization)       │   ║     ║
║  ║  │                                                     │   ║     ║
║  ║  │   /api/auth/**      → PUBLIC  (no token needed)     │   ║     ║
║  ║  │   /api/tickets POST → USER, AGENT, ADMIN            │   ║     ║
║  ║  │   /api/tickets PUT  → AGENT, ADMIN only             │   ║     ║
║  ║  │   /api/tickets DEL  → ADMIN only                    │   ║     ║
║  ║  └─────────────────────────────────────────────────────┘   ║     ║
║  ════════════════════════════════════════════════════════════  ║     ║
║                                                                      ║
║  ┌────────────────────────────────────────────────────────────────┐  ║
║  │                   CONTROLLER  LAYER                            │  ║
║  │   @RestController  ·  @RequestMapping  ·  @PreAuthorize        │  ║
║  │                                                                │  ║
║  │      AuthController     →   /api/auth/**                      │  ║
║  │      TicketController   →   /api/tickets/**                   │  ║
║  └──────────────────────────────┬─────────────────────────────────┘  ║
║                                 │  DTOs (validated input/output)     ║
║  ┌──────────────────────────────▼─────────────────────────────────┐  ║
║  │                    SERVICE  LAYER                              │  ║
║  │   @Service  ·  Business logic  ·  @Transactional               │  ║
║  │                                                                │  ║
║  │      AuthService      →  register · login · JWT ops           │  ║
║  │      TicketService    →  CRUD · lifecycle state transitions    │  ║
║  └──────────────────────────────┬─────────────────────────────────┘  ║
║                                 │  Entity objects                    ║
║  ┌──────────────────────────────▼─────────────────────────────────┐  ║
║  │                  REPOSITORY  LAYER                             │  ║
║  │   @Repository  ·  Spring Data JPA  ·  Hibernate               │  ║
║  │                                                                │  ║
║  │      UserRepository    →  findByEmail, existsByEmail           │  ║
║  │      TicketRepository  →  findByCreatedBy, findAll             │  ║
║  └──────────────────────────────┬─────────────────────────────────┘  ║
║                                 │  SQL / JPQL                        ║
║  ┌──────────────────────────────▼─────────────────────────────────┐  ║
║  │                   H2  DATABASE  (File Mode)                    │  ║
║  │                                                                │  ║
║  │   📁 /app/data/smartdeskdb.mv.db  (persists across restarts)  │  ║
║  │   Tables:  users  ·  tickets                                  │  ║
║  └────────────────────────────────────────────────────────────────┘  ║
╚══════════════════════════════════════════════════════════════════════╝
```

---

### 🔐 JWT Authentication Flow

```
  ┌──────────┐         ┌───────────────┐       ┌─────────────┐       ┌──────────┐
  │  Client  │         │ AuthController │       │  AuthService │       │    DB    │
  └────┬─────┘         └───────┬───────┘       └──────┬──────┘       └────┬─────┘
       │                       │                       │                   │
       │  POST /register       │                       │                   │
       │  {name,email,pw,role} │                       │                   │
       │──────────────────────▶│                       │                   │
       │                       │  register(request)    │                   │
       │                       │──────────────────────▶│                   │
       │                       │                       │  BCrypt.hash(pw)  │
       │                       │                       │  save(user)       │
       │                       │                       │──────────────────▶│
       │                       │                       │◀── saved ─────────│
       │                       │                       │  generateJwt()    │
       │◀─────────────────────────────────────────────│                   │
       │  { "token": "eyJ..." }│                       │                   │
       │                       │                       │                   │
       │  POST /login          │                       │                   │
       │  { email, password }  │                       │                   │
       │──────────────────────▶│                       │                   │
       │                       │  login(request)       │                   │
       │                       │──────────────────────▶│                   │
       │                       │                       │  findByEmail()    │
       │                       │                       │──────────────────▶│
       │                       │                       │◀── User ──────────│
       │                       │                       │  BCrypt.matches() │
       │                       │                       │  generateJwt()    │
       │◀─────────────────────────────────────────────│                   │
       │  { "token": "eyJ..." }│                       │                   │
       │                       │                       │                   │
       │  GET /api/tickets     │                       │                   │
       │  Authorization: Bearer eyJ...                 │                   │
       │──────────────────────────────────────────────────────────────────▶
       │                       │                       │                   │
       │          ┌──────────────────────────┐         │                   │
       │          │     JwtAuthFilter         │         │                   │
       │          │  1. extractToken(header)  │         │                   │
       │          │  2. validateToken(jwt)    │         │                   │
       │          │  3. setAuthentication()   │         │                   │
       │          └──────────────────────────┘         │                   │
       │                       │                       │                   │
       │◀───────────────────── 200 OK · Ticket List ───────────────────────
```

---

### 🎫 Ticket Lifecycle State Machine

```
                    ┌──────────────────────────────────────────┐
                    │        TICKET  STATE  MACHINE             │
                    └──────────────────────────────────────────┘

         Created by USER
               │
               ▼
    ┌──────────────────┐
    │      OPEN         │  ← Initial state on ticket creation
    │  Priority: set    │    assignedTo: "Unassigned"
    └────────┬──────────┘
             │  AGENT / ADMIN picks up ticket
             ▼
    ┌──────────────────┐
    │   IN_PROGRESS     │  ← Agent assigned, work underway
    │  Assigned: Agent  │
    └────────┬──────────┘
             │  Issue fixed by AGENT         ┌─────────────────────┐
             ▼                               │  Can reopen if issue │
    ┌──────────────────┐ ◀─────────────────── │  is not actually     │
    │    RESOLVED       │                     │  fixed              │
    │  Fix applied      │                     └─────────────────────┘
    └────────┬──────────┘
             │  Verified by USER / ADMIN
             ▼
    ┌──────────────────┐
    │     CLOSED        │  ← Terminal state · Immutable · Archived
    └──────────────────┘

  ┌──────────────────────────────────────────────────────────────┐
  │ Valid Transitions                    Allowed Roles           │
  │                                                              │
  │  OPEN        →  IN_PROGRESS          AGENT · ADMIN           │
  │  IN_PROGRESS →  RESOLVED             AGENT · ADMIN           │
  │  RESOLVED    →  IN_PROGRESS  (reopen) AGENT · ADMIN          │
  │  RESOLVED    →  CLOSED                ADMIN                  │
  └──────────────────────────────────────────────────────────────┘
```

---

### 👥 Role × Permission Matrix

<div align="center">

| Permission | `USER` | `AGENT` | `ADMIN` |
|:-----------|:------:|:-------:|:-------:|
| Register & Login | ✅ | ✅ | ✅ |
| Create Ticket | ✅ | ✅ | ✅ |
| View Own Tickets | ✅ | ✅ | ✅ |
| View All Tickets | ❌ | ✅ | ✅ |
| Update Ticket Status | ❌ | ✅ | ✅ |
| Assign Ticket to Agent | ❌ | ✅ | ✅ |
| Delete Any Ticket | ❌ | ❌ | ✅ |
| Access Admin Panel | ❌ | ❌ | ✅ |
| Access H2 Console | ❌ | ❌ | ✅ |

</div>

---

## 🗄️ Database Schema

```
┌──────────────────────────────────┐         ┌─────────────────────────────────────────┐
│            users                 │         │                 tickets                  │
├──────────────────────────────────┤         ├─────────────────────────────────────────┤
│ 🔑 id           BIGINT  PK  AI   │         │ 🔑 id           BIGINT   PK  AI          │
│    name         VARCHAR(100)     │   1:N   │    title        VARCHAR(255)  NOT NULL   │
│    email        VARCHAR(150)  UQ │────────▶│    description  TEXT                    │
│    password     VARCHAR(255)     │         │    priority     ENUM(LOW, MEDIUM, HIGH)  │
│    role         ENUM(USER,...)   │         │    status       ENUM(OPEN, IN_PROGRESS,  │
│    created_at   TIMESTAMP        │         │                      RESOLVED, CLOSED)   │
└──────────────────────────────────┘         │    created_by   VARCHAR(100)             │
                                             │    assigned_to  VARCHAR(100)             │
                                             │    created_at   TIMESTAMP                │
                                             │    updated_at   TIMESTAMP                │
                                             └─────────────────────────────────────────┘

  ENUMS
  ─────
  Role     :  USER  |  AGENT  |  ADMIN
  Priority :  LOW   |  MEDIUM |  HIGH
  Status   :  OPEN  |  IN_PROGRESS  |  RESOLVED  |  CLOSED
```

---

## 📁 Project Structure

```
SmartDesk-Helpdesk-System/
└── smartdesk/
    │
    ├── 📂 src/main/java/com/smartdesk/smartdesk/
    │   │
    │   ├── 📂 controller/                         ← HTTP layer — routes & request mapping only
    │   │   ├── AuthController.java                # POST /register  POST /login
    │   │   └── TicketController.java              # GET / POST / PUT / DELETE /tickets
    │   │
    │   ├── 📂 service/                            ← Business logic & orchestration
    │   │   ├── AuthService.java                   # Register, login, BCrypt, JWT generation
    │   │   └── TicketService.java                 # CRUD + lifecycle state transitions
    │   │
    │   ├── 📂 repository/                         ← Data access — Spring Data JPA interfaces
    │   │   ├── UserRepository.java                # findByEmail(), existsByEmail()
    │   │   └── TicketRepository.java              # findAll(), findByCreatedBy()
    │   │
    │   ├── 📂 model/                              ← JPA entities (map to DB tables)
    │   │   ├── User.java                          # @Entity: id, name, email, password, role
    │   │   └── Ticket.java                        # @Entity: id, title, status, priority, ...
    │   │
    │   ├── 📂 dto/                                ← Data Transfer Objects (API contract)
    │   │   ├── RegisterRequest.java               # Inbound: name, email, password, role
    │   │   ├── LoginRequest.java                  # Inbound: email, password
    │   │   └── TicketRequest.java                 # Inbound: title, description, priority
    │   │
    │   ├── 📂 security/                           ← Spring Security configuration
    │   │   ├── SecurityConfig.java                # Filter chain, route rules, CORS, session
    │   │   ├── JwtService.java                    # generateToken(), validateToken(), extractClaims()
    │   │   ├── JwtAuthFilter.java                 # OncePerRequestFilter — validates every request
    │   │   └── CustomUserDetailsService.java      # loadUserByUsername() — DB lookup for auth
    │   │
    │   └── 📂 exception/
    │       └── GlobalExceptionHandler.java        # @ControllerAdvice — unified error responses
    │
    ├── 📂 src/main/resources/
    │   ├── application.properties                 # Server, DB, JWT configuration
    │   └── 📂 static/
    │       └── SmartDesk.html                     # Dark-themed frontend dashboard SPA
    │
    ├── 🐳 Dockerfile                              # Multi-stage: build (JDK) → run (JRE only)
    ├── 📋 docker-compose.yml                      # Local multi-service orchestration
    ├── 📄 pom.xml                                 # Maven dependencies & build plugins
    └── 📖 README.md
```

---

## 📡 API Reference

### 🔓 Auth Endpoints — Public

| Method | Endpoint | Description | Auth Required |
|:------:|:---------|:------------|:-------------:|
| `POST` | `/api/auth/register` | Register a new user account | ❌ |
| `POST` | `/api/auth/login` | Authenticate and receive JWT | ❌ |

### 🔒 Ticket Endpoints — Protected

| Method | Endpoint | Description | Minimum Role |
|:------:|:---------|:------------|:------------:|
| `GET` | `/api/tickets` | Fetch all tickets | `USER` |
| `POST` | `/api/tickets` | Create a new ticket | `USER` |
| `GET` | `/api/tickets/{id}` | Fetch ticket by ID | `USER` |
| `PUT` | `/api/tickets/{id}` | Update status / assignment | `AGENT` |
| `DELETE` | `/api/tickets/{id}` | Permanently delete ticket | `ADMIN` |

---

### 📝 Request & Response Examples

<details>
<summary><strong>📌 POST /api/auth/register — Register a new user</strong></summary>

```http
POST /api/auth/register
Content-Type: application/json
```

```json
{
  "name": "Karan Awala",
  "email": "karan@gmail.com",
  "password": "karan123",
  "role": "USER"
}
```

**✅ 200 OK**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJrYXJhbkBnbWFpbC5jb20iLCJyb2xlIjoiVVNFUiIsImlhdCI6MTc0MiwiZXhwIjoxNzQyfQ.xxxx"
}
```

**❌ 409 Conflict** *(email already registered)*
```json
{
  "timestamp": "2026-03-17T10:00:00",
  "status": 409,
  "error": "Conflict",
  "message": "Email already in use"
}
```

</details>

<details>
<summary><strong>📌 POST /api/auth/login — Login</strong></summary>

```http
POST /api/auth/login
Content-Type: application/json
```

```json
{
  "email": "karan@gmail.com",
  "password": "karan123"
}
```

**✅ 200 OK**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

**❌ 401 Unauthorized** *(wrong credentials)*
```json
{
  "timestamp": "2026-03-17T10:05:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "Invalid email or password"
}
```

</details>

<details>
<summary><strong>📌 POST /api/tickets — Create a ticket</strong></summary>

```http
POST /api/tickets
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
Content-Type: application/json
```

```json
{
  "title": "Cannot login to internal portal",
  "description": "Getting 403 error after AD password reset. Tried clearing cookies — issue persists on all browsers.",
  "priority": "HIGH"
}
```

**✅ 201 Created**
```json
{
  "id": 7,
  "title": "Cannot login to internal portal",
  "description": "Getting 403 error after AD password reset...",
  "priority": "HIGH",
  "status": "OPEN",
  "createdBy": "Karan Awala",
  "assignedTo": "Unassigned",
  "createdAt": "2026-03-17T10:30:00",
  "updatedAt": "2026-03-17T10:30:00"
}
```

</details>

<details>
<summary><strong>📌 PUT /api/tickets/{id} — Update ticket (AGENT / ADMIN)</strong></summary>

```http
PUT /api/tickets/7
Authorization: Bearer <agent_jwt_token>
Content-Type: application/json
```

```json
{
  "status": "IN_PROGRESS",
  "assignedTo": "Agent Smith"
}
```

**✅ 200 OK**
```json
{
  "id": 7,
  "title": "Cannot login to internal portal",
  "status": "IN_PROGRESS",
  "assignedTo": "Agent Smith",
  "updatedAt": "2026-03-17T11:00:00"
}
```

**❌ 403 Forbidden** *(USER role attempting update)*
```json
{
  "timestamp": "2026-03-17T11:01:00",
  "status": 403,
  "error": "Forbidden",
  "message": "Access denied — insufficient role"
}
```

</details>

<details>
<summary><strong>📌 DELETE /api/tickets/{id} — Delete ticket (ADMIN only)</strong></summary>

```http
DELETE /api/tickets/7
Authorization: Bearer <admin_jwt_token>
```

**✅ 204 No Content**

**❌ 404 Not Found**
```json
{
  "timestamp": "2026-03-17T11:10:00",
  "status": 404,
  "error": "Not Found",
  "message": "Ticket with id 7 not found"
}
```

</details>

---

### ⚠️ Error Codes Reference

| HTTP Status | Error | When it occurs |
|:-----------:|:------|:---------------|
| `400` | Bad Request | Missing required fields, validation failure |
| `401` | Unauthorized | Missing, malformed, or expired JWT token |
| `403` | Forbidden | Valid token but insufficient role for the route |
| `404` | Not Found | Ticket or User ID does not exist |
| `409` | Conflict | Email already registered |
| `500` | Internal Server Error | Unexpected server-side failure |

All errors share this consistent JSON envelope:

```json
{
  "timestamp": "2026-03-17T10:00:00",
  "status": 4xx,
  "error": "Error Type",
  "message": "Human-readable description of the issue"
}
```

---

## 🔐 Security Deep Dive

### JWT Token Anatomy

```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9          ← HEADER  (Base64-encoded)
.
eyJzdWIiOiJrYXJhbkBnbWFpbC5jb20iLCJyb2xlIjoiVVNFUiIsImlhdCI6MTc0MiwiZXhwIjoxNzQyfQ
                                                ← PAYLOAD (Base64-encoded)
.
HMACSHA256(header + "." + payload, SECRET_KEY)
                                                ← SIGNATURE (validates integrity)

─────────────────────────────────────────────────
DECODED PAYLOAD CLAIMS:
  sub   → "karan@gmail.com"      ← Subject (user identifier)
  role  → "USER"                 ← Role for RBAC decisions
  iat   → 1742060000             ← Issued At (Unix timestamp)
  exp   → 1742146400             ← Expiry  (iat + 86400 = 24h later)
─────────────────────────────────────────────────
```

### Security Filter Chain (per request)

```
Incoming Request
       │
       ▼
 ┌──────────────────────────────────────────────────────────┐
 │  JwtAuthFilter  extends OncePerRequestFilter             │
 │                                                          │
 │  Step 1: getHeader("Authorization")                      │
 │          └── starts with "Bearer "?                      │
 │               NO  ──▶ chain.doFilter() (no auth set)     │
 │               YES ──▶ extract raw token                  │
 │                                                          │
 │  Step 2: jwtService.extractEmail(token)                  │
 │          └── SecurityContext already authenticated?      │
 │               YES ──▶ skip (this request already done)   │
 │               NO  ──▶ continue                           │
 │                                                          │
 │  Step 3: userDetailsService.loadUserByUsername(email)    │
 │          └── user not found? ──▶ 401                     │
 │                                                          │
 │  Step 4: jwtService.isTokenValid(token, userDetails)     │
 │          └── INVALID (expired / tampered) ──▶ 401        │
 │               VALID ──▶ build UsernamePasswordAuthToken  │
 │                         set in SecurityContextHolder     │
 └──────────────────────────────────────────────────────────┘
       │  Authentication set in context
       ▼
 SecurityConfig checks route permission (ROLE check)
       │
       ▼
 Controller → Service → Repository → Response ✅
```

### Security Best Practices Applied

| Practice | Implementation |
|:---------|:---------------|
| Password hashing | BCrypt, cost factor 10 |
| Token signing algorithm | HMAC-SHA256 |
| Configurable secret | `jwt.secret` via properties / env var |
| Token expiry | 24h (configurable via `jwt.expiration`) |
| No sensitive data in JWT | Only email + role in payload |
| Route protection | Spring Security filter chain (not annotation-only) |
| Consistent error messages | Generic messages — prevents user enumeration |
| Stateless sessions | `SessionCreationPolicy.STATELESS` |

---

## ⚙️ Configuration Reference

**`src/main/resources/application.properties`**

```properties
# ─────────────────────────────────────────────
#  SERVER
# ─────────────────────────────────────────────
server.port=8080

# ─────────────────────────────────────────────
#  H2 DATABASE  (File Mode — persists across restarts)
# ─────────────────────────────────────────────
spring.datasource.url=jdbc:h2:file:/app/data/smartdeskdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# ─────────────────────────────────────────────
#  JWT
# ─────────────────────────────────────────────
# ⚠️  Use a strong 256-bit secret in production — never commit to git!
jwt.secret=your-super-secret-256-bit-key-change-this
jwt.expiration=86400000          # 24 hours in milliseconds
```

---

## 🚀 Getting Started

### ✅ Prerequisites

| Tool | Min Version | Download |
|:-----|:-----------:|:---------|
| Java (OpenJDK) | **21** | [adoptium.net](https://adoptium.net/) |
| Maven | **3.8** | [maven.apache.org](https://maven.apache.org/download.cgi) |
| Git | any | [git-scm.com](https://git-scm.com/) |
| Docker *(optional)* | **24** | [docker.com](https://www.docker.com/) |

### 🖥️ Local Setup

```bash
# 1. Clone the repository
git clone https://github.com/karanaawla1/SmartDesk-Helpdesk-System.git

# 2. Navigate into the project root
cd SmartDesk-Helpdesk-System/smartdesk

# 3. Build — skipping tests for a faster first run
./mvnw clean package -DskipTests
# Windows: mvnw.cmd clean package -DskipTests

# 4. Run the application
java -jar target/*.jar
```

The app is now live at:

| Service | URL |
|:--------|:----|
| 🌐 **API Base** | `http://localhost:8080` |
| 🖥️ **Frontend UI** | `http://localhost:8080/SmartDesk.html` |
| 🗄️ **H2 Console** | `http://localhost:8080/h2-console` |

### 🔑 Environment Variables

Override any default via environment variables for Docker / cloud deployments:

| Variable | Default | Description |
|:---------|:--------|:------------|
| `SERVER_PORT` | `8080` | HTTP listener port |
| `JWT_SECRET` | *(weak default)* | Signing secret — **use 256-bit key in prod** |
| `JWT_EXPIRATION` | `86400000` | Token TTL in ms (default 24h) |
| `SPRING_DATASOURCE_URL` | H2 file path | Override with PostgreSQL JDBC URL in prod |
| `SPRING_JPA_SHOW_SQL` | `false` | Set `true` for SQL query debugging |

---

## 🐳 Docker Deployment

### Build & Run

```bash
# Build the Docker image (multi-stage — final image uses JRE, not full JDK)
docker build -t smartdesk:latest .

# Run with persistent volume for H2 data
docker run -d \
  --name smartdesk-app \
  -p 8080:8080 \
  -v smartdesk-data:/app/data \
  -e JWT_SECRET=your-super-secret-256-bit-key \
  smartdesk:latest

# Tail logs
docker logs -f smartdesk-app

# Stop & remove
docker stop smartdesk-app && docker rm smartdesk-app
```

### Docker Compose

```yaml
# docker-compose.yml
version: '3.8'

services:
  smartdesk:
    build: .
    image: smartdesk:latest
    container_name: smartdesk-app
    ports:
      - "8080:8080"
    environment:
      - JWT_SECRET=${JWT_SECRET}
      - JWT_EXPIRATION=86400000
      - SPRING_JPA_SHOW_SQL=false
    volumes:
      - smartdesk-data:/app/data
    restart: unless-stopped
    healthcheck:
      test: ["CMD", "curl", "-f", "http://localhost:8080/actuator/health"]
      interval: 30s
      timeout: 10s
      retries: 3

volumes:
  smartdesk-data:
    driver: local
```

```bash
# Start in background
docker-compose up -d

# Real-time logs
docker-compose logs -f

# Stop and tear down
docker-compose down
```

### Why Multi-Stage?

```
Stage 1 — BUILD (eclipse-temurin:21-jdk-alpine)
   ├── Full JDK installed
   ├── Maven downloads all dependencies
   ├── Compiles + packages → app.jar
   └── This entire stage is DISCARDED after build

Stage 2 — RUNTIME (eclipse-temurin:21-jre-alpine)
   ├── Lean JRE only — no compiler, no Maven, no sources
   ├── COPY app.jar from Stage 1
   └── Final image ≈ 180MB  (vs ≈ 400MB with JDK)
```

> **Result:** A smaller, faster, and more secure production image.

---

## 🌐 Live Demo

<div align="center">

> ☁️ **Deployed on Render** — Cloud PaaS

| Resource | Link |
|:---------|:-----|
| 🔗 **API Base URL** | `https://smartdesk-xxxx.onrender.com` |
| 🖥️ **Frontend UI** | `https://smartdesk-xxxx.onrender.com/SmartDesk.html` |
| 🗄️ **H2 Console** | `https://smartdesk-xxxx.onrender.com/h2-console` |
| 📬 **Postman Collection** | *(Add your public Postman link here)* |

> ⚠️ **Cold Start:** Free-tier Render instances spin down after 15 min of inactivity.
> First request after idle may take **30–60 seconds** — this is a platform limitation, not an app bug.

</div>

---

## 🗺️ Roadmap

### ✅ v1.0 — Completed
- [x] JWT-based stateless authentication
- [x] BCrypt password hashing
- [x] RBAC — USER / AGENT / ADMIN
- [x] Full ticket lifecycle OPEN → CLOSED
- [x] Global exception handler with clean error JSON
- [x] H2 File-mode persistence
- [x] Dark-themed frontend dashboard
- [x] Docker multi-stage containerization
- [x] Render cloud deployment

### 🔜 v1.1 — Next Release
- [ ] **Ticket Comments** — threaded discussion per ticket
- [ ] **Activity Log** — audit trail of every status change with actor + timestamp
- [ ] **Email Notifications** — JavaMail / SendGrid on status transitions
- [ ] **Pagination & Filtering** — `?status=OPEN&priority=HIGH&page=0&size=10`
- [ ] **Swagger / OpenAPI 3.0** — interactive API documentation at `/swagger-ui`
- [ ] **Refresh Token** — short-lived access + long-lived refresh token pair

### 🔮 v2.0 — Future Vision
- [ ] **PostgreSQL migration** — production-grade relational DB via Fly.io or Railway
- [ ] **Unit & Integration Tests** — JUnit 5 + Mockito + Testcontainers (80%+ coverage)
- [ ] **Spring Boot Actuator** — health checks, metrics, `/actuator/health`
- [ ] **SLA / Escalation Engine** — auto-escalate tickets past SLA deadline
- [ ] **File Attachments** — screenshots/logs per ticket stored on S3 / MinIO
- [ ] **WebSocket** — real-time ticket status push notifications
- [ ] **Multi-tenancy** — fully isolated data per organization

---

## 🤝 Contributing

Contributions, bug reports, and feature requests are welcome!

```bash
# 1. Fork the repository on GitHub

# 2. Create a feature branch
git checkout -b feature/your-feature-name

# 3. Make changes & commit with a clear message
git commit -m "feat: add ticket comment functionality"

# 4. Push to your fork
git push origin feature/your-feature-name

# 5. Open a Pull Request targeting main
```

**Commit convention:**

| Prefix | Purpose |
|:-------|:--------|
| `feat:` | New feature |
| `fix:` | Bug fix |
| `docs:` | Documentation only |
| `refactor:` | Code restructuring (no behavior change) |
| `test:` | Adding or updating tests |
| `chore:` | Build tooling, CI, config |

> 💡 For major changes, please open an **issue first** to discuss the approach before submitting a PR.

---

## ❓ FAQ

<details>
<summary><strong>Why H2 instead of PostgreSQL?</strong></summary>

H2 in file mode provides zero-configuration persistence — no separate DB server, no Docker dependency, instant setup. It's ideal for demo and early-stage deployments. Migrating to PostgreSQL requires only changing `spring.datasource.url` in `application.properties`. It's explicitly on the v2.0 roadmap.

</details>

<details>
<summary><strong>How do I test the API?</strong></summary>

Import the Postman collection (see Live Demo section), or use any HTTP client (curl, Insomnia). Workflow:
1. `POST /api/auth/register` to create a user
2. Copy the JWT from the response
3. Add `Authorization: Bearer <token>` header to all `/api/tickets/**` requests

</details>

<details>
<summary><strong>Can I use this as a base for my own project?</strong></summary>

Yes — SmartDesk is MIT licensed. Fork it, extend it, ship it. If you build something on top, a GitHub star or a shoutout is always appreciated!

</details>

<details>
<summary><strong>JWT tokens expire — what do I do?</strong></summary>

The default TTL is 24 hours (configurable via `jwt.expiration` in ms). Simply re-login after expiry to get a fresh token. Refresh token support is planned for v1.1.

</details>

<details>
<summary><strong>The live demo is slow on first load — is something broken?</strong></summary>

No — this is expected on Render's free tier. Instances spin down after 15 minutes of inactivity and take ~60 seconds to cold-start. All subsequent requests are fast.

</details>

<details>
<summary><strong>How do I switch to PostgreSQL for production?</strong></summary>

1. Add `spring-boot-starter-data-jpa` + PostgreSQL driver to `pom.xml`
2. Update `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://host:5432/smartdesk
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```
3. Remove H2 dependency — done.

</details>

---

## 👨‍💻 Author

<div align="center">

**Karan Awala**
*Java Backend Developer · Spring Boot · Cloud-Native*

<br/>

[![Email](https://img.shields.io/badge/Email-karanaawla07%40gmail.com-EA4335?style=for-the-badge&logo=gmail&logoColor=white)](mailto:karanaawla07@gmail.com)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-karanaawla-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/karanaawla)
[![GitHub](https://img.shields.io/badge/GitHub-karanaawla1-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/karanaawla1)

<br/>

<img src="https://github-readme-stats.vercel.app/api?username=karanaawla1&show_icons=true&theme=tokyonight&hide_border=true&count_private=true" height="150" alt="GitHub Stats"/>
&nbsp;&nbsp;
<img src="https://github-readme-stats.vercel.app/api/top-langs/?username=karanaawla1&layout=compact&theme=tokyonight&hide_border=true" height="150" alt="Top Languages"/>

<br/>

<img src="https://github-readme-streak-stats.herokuapp.com/?user=karanaawla1&theme=tokyonight&hide_border=true" height="150" alt="GitHub Streak"/>

</div>

---

## 📄 License

```
MIT License  —  Copyright (c) 2026 Karan Awala

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```

Full text: [LICENSE](./LICENSE)

---

<!-- Footer -->
<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:0d1117,50:00d9ff,100:0d1117&height=120&section=footer" width="100%"/>

**⭐ If SmartDesk was useful or inspiring — a GitHub star makes a real difference!**

*Crafted with ☕ Java & 💚 Spring Boot by [Karan Awala](https://github.com/karanaawla1)*

![Visitor Count](https://komarev.com/ghpvc/?username=karanaawla1&label=Profile+Views&color=00d9ff&style=flat-square)

</div>
