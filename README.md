# Registration Portal

A complete Spring Boot registration application with a responsive browser interface, persistent storage, server-side validation, duplicate-email protection, tests, Docker support, and CI.

## Features

- Responsive registration form
- Spring Boot REST API
- Bean Validation
- Duplicate email detection
- Persistent H2 database
- Structured API error responses
- Automated service tests
- Docker image
- GitHub Actions CI

## Tech Stack

Java 17 · Spring Boot · Spring Web · Spring Data JPA · H2 · Bean Validation · HTML · CSS · JavaScript · JUnit · Mockito

## Run locally

```bash
mvn spring-boot:run
```

Open:

```text
http://localhost:8080
```

The H2 console is available at:

```text
http://localhost:8080/h2-console
```

## API

### Create registration

```http
POST /api/registrations
Content-Type: application/json
```

```json
{
  "firstName": "Tejaswini",
  "lastName": "Betina",
  "email": "tejaswini@example.com",
  "phone": "313-555-0100",
  "program": "Software Engineering"
}
```

### List registrations

```http
GET /api/registrations
```

## Docker

```bash
docker build -t registration-portal .
docker run -p 8080:8080 registration-portal
```
