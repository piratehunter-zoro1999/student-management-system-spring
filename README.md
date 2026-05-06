# Student Management System (Spring Boot)

This is a backend REST API project where I converted my console-based student management system into a structured Spring Boot application by gradually improving the architecture and adding real backend features.

## Features

### Student APIs
- Add student
- Get all students
- Get student by ID
- Update student
- Delete student

### Additional Functionalities
- Search students by name
- Pagination support using Page & Pageable
- Structured API responses (`status`, `message`, `data`)
- DTO-based request and response handling
- Reusable mapper layer for DTO ↔ Entity conversion
- Validation for request inputs
- Global exception handling
- JWT-based authentication
- Protected APIs using Bearer Token
- MySQL database integration

## Authentication

### Login Endpoint
POST /auth/login

### Sample Request
```json
{
  "username": "admin",
  "password": "1234"
}
```

### Sample Response
```json
{
  "token": "jwt_token_here"
}
```

### Protected APIs
All student APIs require JWT authentication using:

Authorization: Bearer <token>

## Tech Stack
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- JWT (jjwt)
- Maven
- Postman

## Project Structure
- controller
- dto
- exception
- mapper
- model
- repository
- security
- service

## API Testing
APIs were tested using Postman with:
- JSON request bodies
- JWT Bearer Token authentication
- Pagination query parameters

## Learning Highlights
This project helped me understand:
- REST API design
- Layered backend architecture
- DTO pattern
- JWT authentication flow
- Database integration using JPA
- Pagination with Page & Pageable
- Validation and exception handling
- Clean code and reusable methods

## Future Improvements
- Spring Security integration
- Role-based authorization
- Refresh tokens
- Unit testing
- Swagger/OpenAPI documentation

## How to Run
- Configure MySQL in `application.properties`
- Run `StudentManagementSystemApplication.java`
- Use Postman to test APIs
