# QuizApp Microservices Project

A full-featured **Spring Boot Microservices** project demonstrating scalable architecture including:

- Microservice communication with **Feign**
- **JWT Authentication** and **Spring Security**
- API protection with **API Gateway**
- Service discovery using **Eureka Server**
- Input validation, global exception handling
- RESTful API design principles

---

## Project Structure
QuizApp-Microservices/

- APIGateway/ # Entry point for all client requests
- Auth-Service/ # Auth, registration, JWT token generation
- QuizService/ # Quiz logic (create, assign, score)
- QuestionService/ # Question logic (CRUD, random fetch)
- ServiceRegistry/ # Eureka Server for service discovery

---

## Authentication Flow (JWT)

- `/auth/register`: Register a new user
- `/auth/token`: Authenticate user and return JWT token
- All secured requests require:
  Authorization: Bearer <token>
- API Gateway filters requests using a custom **JWTFilter** before routing

---

## Tech Stack

| Layer | Tech |
|-------|------|
| Language | Java 21 |
| Framework | Spring Boot |
| Architecture | Microservices |
| Service Discovery | Eureka Server |
| Routing | Spring Cloud Gateway |
| Security | Spring Security + JWT |
| Validation | Jakarta Bean Validation |
| Communication | OpenFeign (REST) |
| Build Tool | Maven |
| HTTP Tool | Postman |

---

## Features

- Login & Registration with password encryption
- Token-based stateless authentication
- Request filtering and routing via API Gateway
- Microservices separated by responsibility
- Load balancing with multiple service instances
- Service-to-service communication with Feign
- DTO validation with `@NotBlank`, `@Email`, etc.
- Global exception handling (`@ControllerAdvice`)

---

