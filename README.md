
---

# 2. Product Management Microservice — `README.md`

```markdown
# Product Management Microservice

Product Management microservice for the Order Management application.

This service manages product-related backend functionality and exposes
REST APIs that can be consumed by other services in the microservices
ecosystem.

## Architecture

```text
                 ┌─────────────────────┐
                 │   Client / API      │
                 └──────────┬──────────┘
                            │
                            ▼
                 ┌─────────────────────┐
                 │ Product Service     │
                 │                     │
                 │ Spring Boot        │
                 │ REST APIs          │
                 │ Spring Data JPA    │
                 └──────────┬──────────┘
                            │
                            ▼
                 ┌─────────────────────┐
                 │    PostgreSQL       │
                 └─────────────────────┘
