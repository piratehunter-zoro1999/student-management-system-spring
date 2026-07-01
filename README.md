# 🎓 Student Management System (Spring Boot)

A backend REST API project built using **Spring Boot** to manage student records while learning real-world backend development concepts. The project started as a console-based Java application and was gradually transformed into a layered Spring Boot application with authentication, authorization, validation, and secure REST APIs.

---

# 🚀 Features

## Student Management

- Add Student
- Get All Students
- Get Student by ID
- Update Student
- Delete Student
- Search Students by Name
- Pagination Support

---

## Authentication & Authorization

- JWT-based Authentication
- User Registration API
- Login API
- Current Authenticated User API (`/auth/me`)
- BCrypt Password Hashing
- Role-Based Authorization
- Role Hierarchy
- Custom JWT Authentication Filter
- Stateless Authentication
- Protected APIs using Bearer Token

---

## Security

- Spring Security Integration
- Custom Authentication Entry Point (401)
- Custom Access Denied Handler (403)
- JWT Role Claims
- Password Encryption using BCrypt

---

## Validation & Exception Handling

- Request Validation using Jakarta Validation
- Global Exception Handling
- Custom Exception Classes
- Consistent JSON Response Structure
- Proper HTTP Status Codes

| Status | Meaning |
|---------|---------|
| 200 | Success |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Resource Not Found |
| 409 | Conflict |

---

# 🛠 Tech Stack

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- JWT (jjwt)
- Maven
- Postman

---

# 🏗 Project Architecture

```
Client
    │
    ▼
Security Filter Chain
    │
    ▼
JWT Authentication Filter
    │
    ▼
Controller
    │
    ▼
Service
    │
    ▼
Repository
    │
    ▼
MySQL Database
```

---

# 📁 Project Structure

```
src
└── main
    └── java
        ├── config
        ├── controller
        ├── dto
        ├── exception
        ├── mapper
        ├── model
        ├── repository
        ├── security
        └── service
```

---

# 🔑 Authentication APIs

## Register

```
POST /auth/register
```

Sample Request

```json
{
  "username": "john",
  "password": "1234"
}
```

---

## Login

```
POST /auth/login
```

Sample Request

```json
{
  "username": "admin",
  "password": "1234"
}
```

---

## Current User

```
GET /auth/me
```

Requires:

```
Authorization: Bearer <JWT_TOKEN>
```

---

# 📚 Student APIs

| Method | Endpoint | Access |
|---------|----------|--------|
| POST | /students | ADMIN |
| GET | /students | Authenticated |
| GET | /students/{id} | Authenticated |
| PUT | /students/{id} | ADMIN |
| DELETE | /students/{id} | ADMIN |

> *(Update this table if your access rules differ.)*

---

# 📮 API Testing

All APIs were tested using **Postman** with:

- JSON Request Bodies
- JWT Bearer Authentication
- Pagination Query Parameters
- Role-Based Authorization Testing
- Authentication & Authorization Error Scenarios

---

# 📖 Learning Highlights

This project helped me understand:

- Layered Architecture
- REST API Design
- DTO Pattern
- Mapper Pattern
- Spring Data JPA
- JWT Authentication
- Spring Security
- Security Filter Chain
- SecurityContext
- Role-Based Authorization
- BCrypt Password Hashing
- Global Exception Handling
- Request Validation
- Pagination
- Clean Code Practices

---

# 🚀 Future Improvements

- Refresh Token Authentication
- Swagger / OpenAPI Documentation
- Unit & Integration Testing
- Docker Support
- CI/CD Pipeline
- Logging with SLF4J
- API Rate Limiting
- Deployment (Render / Railway / AWS)

---

# ▶️ How to Run

1. Clone the repository

```bash
git clone <your-repository-url>
```

2. Configure MySQL credentials inside:

```
application.properties
```

3. Run

```
StudentManagementSystemApplication.java
```

4. Test APIs using Postman.

---

# 📌 Project Status

✅ Authentication Complete

✅ Authorization Complete

✅ Student CRUD Complete

✅ Spring Security Integrated

🚧 More backend features coming soon...
