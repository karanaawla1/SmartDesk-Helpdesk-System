# 🖥️ SmartDesk – Helpdesk Ticketing System

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=flat-square&logo=springboot)
![Spring Security](https://img.shields.io/badge/Spring%20Security-JWT-blue?style=flat-square&logo=springsecurity)
![Docker](https://img.shields.io/badge/Docker-Containerized-blue?style=flat-square&logo=docker)
![H2](https://img.shields.io/badge/Database-H2-lightblue?style=flat-square)
![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)

> A production-ready **Helpdesk Ticketing System** backend built with Java & Spring Boot, featuring JWT-based authentication, Role-Based Access Control (RBAC), and a full ticket lifecycle management system.

---

## 📌 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Project Structure](#-project-structure)
- [API Endpoints](#-api-endpoints)
- [Getting Started](#-getting-started)
- [Docker Deployment](#-docker-deployment)
- [Live Demo](#-live-demo)
- [Author](#-author)

---

## 🔍 Overview

SmartDesk is a **backend REST API** for managing helpdesk support tickets in an enterprise environment. It supports user registration, JWT-based login, ticket creation, and a complete ticket lifecycle from `OPEN` to `CLOSED`. The system implements role-based access so that `USER`, `AGENT`, and `ADMIN` roles have different levels of access.

---

## ✨ Features

- ✅ **User Registration & Login** with JWT Authentication
- ✅ **Role-Based Access Control (RBAC)** — USER / AGENT / ADMIN
- ✅ **Full Ticket Lifecycle** — OPEN → IN_PROGRESS → RESOLVED → CLOSED
- ✅ **RESTful APIs** with JSON request/response
- ✅ **Exception Handling** with meaningful error responses
- ✅ **H2 File-Mode Database** — data persists across restarts
- ✅ **Docker Support** — containerized with multi-stage build
- ✅ **Custom Frontend UI** — dark-themed dashboard (SmartDesk.html)
- ✅ **Deployed on Render** — live and accessible

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Java 21 |
| Framework | Spring Boot 3.x |
| Security | Spring Security + JWT |
| ORM | Spring Data JPA + Hibernate |
| Database | H2 (File Mode) |
| Build Tool | Maven |
| Containerization | Docker (Multi-stage build) |
| API Testing | Postman |
| Deployment | Render |
| Version Control | Git + GitHub |

---

## 🏗️ Architecture

```
┌─────────────────────────────────────────────────────────┐
│                        CLIENT                           │
│              (Postman / Browser / Frontend)             │
└────────────────────────┬────────────────────────────────┘
                         │  HTTP Request
                         ▼
┌─────────────────────────────────────────────────────────┐
│                   SPRING BOOT APP                       │
│                                                         │
│  ┌──────────────────────────────────────────────────┐   │
│  │              Spring Security Layer               │   │
│  │   JwtAuthFilter → validates Bearer Token        │   │
│  │   SecurityConfig → route permissions             │   │
│  └──────────────────────┬───────────────────────────┘   │
│                         │                               │
│  ┌──────────────────────▼───────────────────────────┐   │
│  │               Controller Layer                   │   │
│  │   AuthController   →  /api/auth/**              │   │
│  │   TicketController →  /api/tickets/**           │   │
│  └──────────────────────┬───────────────────────────┘   │
│                         │                               │
│  ┌──────────────────────▼───────────────────────────┐   │
│  │                Service Layer                     │   │
│  │   AuthService      →  register, login, JWT      │   │
│  │   TicketService    →  CRUD, lifecycle            │   │
│  └──────────────────────┬───────────────────────────┘   │
│                         │                               │
│  ┌──────────────────────▼───────────────────────────┐   │
│  │              Repository Layer                    │   │
│  │   UserRepository   →  Spring Data JPA           │   │
│  │   TicketRepository →  Spring Data JPA           │   │
│  └──────────────────────┬───────────────────────────┘   │
│                         │                               │
│  ┌──────────────────────▼───────────────────────────┐   │
│  │                H2 Database                       │   │
│  │   File Mode: /app/data/smartdeskdb              │   │
│  │   Tables: users, tickets                        │   │
│  └──────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────┘
```

### 🔐 JWT Auth Flow

```
User → POST /api/auth/register → Save to DB → Return JWT Token
User → POST /api/auth/login    → Validate   → Return JWT Token
User → POST /api/tickets       → JwtFilter validates token → Controller → Service → DB
```

### 🎫 Ticket Lifecycle

```
OPEN → IN_PROGRESS → RESOLVED → CLOSED
```

### 👥 Role Permissions

```
USER  → Create tickets, view own tickets
AGENT → View all tickets, update ticket status
ADMIN → Full access — all routes + admin panel
```

---

## 📁 Project Structure

```
smartdesk/
├── src/
│   └── main/
│       ├── java/com/smartdesk/smartdesk/
│       │   ├── controller/
│       │   │   ├── AuthController.java       # Register & Login endpoints
│       │   │   └── TicketController.java     # Ticket CRUD endpoints
│       │   ├── service/
│       │   │   ├── AuthService.java          # Auth business logic
│       │   │   └── TicketService.java        # Ticket business logic
│       │   ├── repository/
│       │   │   ├── UserRepository.java       # JPA User queries
│       │   │   └── TicketRepository.java     # JPA Ticket queries
│       │   ├── model/
│       │   │   ├── User.java                 # User entity
│       │   │   └── Ticket.java               # Ticket entity
│       │   ├── dto/
│       │   │   ├── RegisterRequest.java      # Register DTO
│       │   │   ├── LoginRequest.java         # Login DTO
│       │   │   └── TicketRequest.java        # Ticket DTO
│       │   ├── security/
│       │   │   ├── SecurityConfig.java       # Spring Security config
│       │   │   ├── JwtService.java           # JWT generate & validate
│       │   │   ├── JwtAuthFilter.java        # JWT filter per request
│       │   │   └── CustomUserDetailsService  # Load user from DB
│       │   └── exception/
│       │       └── GlobalExceptionHandler.java
│       └── resources/
│           ├── application.properties        # App configuration
│           └── static/
│               └── SmartDesk.html            # Frontend UI
├── Dockerfile                                # Multi-stage Docker build
├── pom.xml                                   # Maven dependencies
└── README.md
```

---

## 📡 API Endpoints

### 🔐 Auth Endpoints (Public)

| Method | Endpoint | Description | Body |
|--------|----------|-------------|------|
| POST | `/api/auth/register` | Register new user | `name, email, password, role` |
| POST | `/api/auth/login` | Login & get JWT token | `email, password` |

### 🎫 Ticket Endpoints (Protected — Bearer Token required)

| Method | Endpoint | Description | Role |
|--------|----------|-------------|------|
| GET | `/api/tickets` | Get all tickets | USER, AGENT, ADMIN |
| POST | `/api/tickets` | Create new ticket | USER |
| GET | `/api/tickets/{id}` | Get ticket by ID | USER, AGENT, ADMIN |
| PUT | `/api/tickets/{id}` | Update ticket | AGENT, ADMIN |
| DELETE | `/api/tickets/{id}` | Delete ticket | ADMIN |

### 📝 Sample Request — Register

```json
POST /api/auth/register
{
  "name": "Karan Awala",
  "email": "karan@gmail.com",
  "password": "karan123",
  "role": "USER"
}
```

### 📝 Sample Request — Create Ticket

```json
POST /api/tickets
Authorization: Bearer <your_jwt_token>

{
  "title": "Login issue",
  "description": "Unable to login to the system",
  "priority": "HIGH"
}
```

### 📝 Sample Response — Ticket Created

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

---

## 🚀 Getting Started

### Prerequisites

- Java 21+
- Maven 3.8+
- Git

### Run Locally

```bash
# 1. Clone the repo
git clone https://github.com/karanaawla1/SmartDesk-Helpdesk-System.git
cd SmartDesk-Helpdesk-System/smartdesk

# 2. Build the project
./mvnw clean package -DskipTests

# 3. Run the app
java -jar target/*.jar
```

App will start at: `http://localhost:8080`

Frontend UI: `http://localhost:8080/SmartDesk.html`

H2 Console: `http://localhost:8080/h2-console`

---

## 🐳 Docker Deployment

```bash
# Build Docker image
docker build -t smartdesk .

# Run container
docker run -p 8080:8080 smartdesk
```

---

## 🌐 Live Demo

> Deployed on Render

🔗 **Live URL:** `https://smartdesk-xxxx.onrender.com`

🔗 **Frontend:** `https://smartdesk-xxxx.onrender.com/SmartDesk.html`

---

## 👨‍💻 Author

**Karan Awala**

- 📧 Email: karanaawla07@gmail.com
- 💼 LinkedIn: [linkedin.com/in/karanaawla](https://linkedin.com/in/karanaawla)
- 🐙 GitHub: [github.com/karanaawla1](https://github.com/karanaawla1)

---

## 📄 License

This project is licensed under the MIT License.

---

> ⭐ If you found this project helpful, please give it a star on GitHub!
