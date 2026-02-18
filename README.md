# Binary Calculator Web Application

A Spring Boot web application that performs binary arithmetic operations including addition, multiplication, AND, and OR on binary numbers.

## Overview

This application provides both a web interface and REST API for performing binary calculations. It features an interactive calculator built with Thymeleaf templates and comprehensive test coverage using Spring MockMvc.

## Features

- **Binary Operations:** Addition, multiplication, bitwise AND, and bitwise OR
- **Web Interface:** Interactive calculator with HTML/JavaScript frontend
- **REST API:** JSON and plain-text endpoints for programmatic access
- **Comprehensive Testing:** Unit and integration tests for all controllers and binary operations

## Tech Stack

| Dependency | Version | Purpose |
|---|---|---|
| Java | 20 | Language |
| Spring Boot | 2.7.18 | Application framework |
| Spring Web | — | Web/REST layer |
| Spring Thymeleaf | — | Server-side templating |
| JUnit | 4 | Testing framework |

## Project Structure

```
src/
├── main/
│   ├── java/com/ontariotechu/sofe3980u/
│   │   ├── Binary.java                  # Core binary arithmetic logic
│   │   ├── BinaryController.java        # Web interface controller
│   │   └── BinaryAPIController.java     # REST API controller
│   └── resources/
│       └── templates/                   # Thymeleaf HTML templates
└── test/
    └── java/com/ontariotechu/sofe3980u/
        ├── BinaryControllerTest.java
        ├── BinaryAPIControllerTest.java
        ├── HelloControllerTest.java
        └── HelloAPIControllerTest.java
```

## Getting Started

### Prerequisites

- Java 20+
- Maven 3.6+

### Build

```bash
mvn clean package
```

### Run

```bash
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`.

### Run Tests

```bash
mvn test
```

## Usage

### Web Interface

Navigate to `http://localhost:8080/` to access the binary calculator. Enter binary numbers (0s and 1s), select an operation, and submit.

Supported operations: `+` (Add), `*` (Multiply), `&` (AND), `|` (OR)

### REST API

#### Binary Operations

| Endpoint | Example | Response |
|---|---|---|
| `GET /add` | `/add?operand1=111&operand2=1010` | Result as string |
| `GET /add_json` | `/add_json?operand1=111&operand2=1010` | Result as JSON |
| `GET /multiply` | `/multiply?operand1=101&operand2=10` | Result as string |
| `GET /multiply_json` | `/multiply_json?operand1=101&operand2=10` | Result as JSON |
| `GET /and` | `/and?operand1=1101&operand2=1011` | Result as string |
| `GET /and_json` | `/and_json?operand1=1101&operand2=1011` | Result as JSON |
| `GET /or` | `/or?operand1=1101&operand2=0011` | Result as string |
| `GET /or_json` | `/or_json?operand1=1101&operand2=0011` | Result as JSON |

#### Hello Endpoints

| Endpoint | Example | Response |
|---|---|---|
| `GET /hello` | `/hello?name=John` | Hello page (defaults to "World") |
| `GET /helloAPI` | `/helloAPI?name=John` | `Hello John!` as plain text |

#### Email API

| Endpoint | Example | Response |
|---|---|---|
| `GET /emailAPI` | `/emailAPI?fname=Jack&lname=Sparrow` | Suggested email as JSON |

## Core Classes

### `Binary.java`

Implements all binary arithmetic logic:

- `add(Binary, Binary)` — Binary addition
- `multiply(Binary, Binary)` — Binary multiplication via repeated addition
- `and(Binary, Binary)` — Bitwise AND
- `or(Binary, Binary)` — Bitwise OR

### `BinaryController.java`

Handles requests for the web interface and renders Thymeleaf HTML templates.

### `BinaryAPIController.java`

Provides REST API endpoints returning results as plain strings or JSON objects.

## Testing

All test classes are located in `src/test/java/com/ontariotechu/sofe3980u/` and use Spring MockMvc for integration testing:

- `BinaryControllerTest.java` — Web controller tests
- `BinaryAPIControllerTest.java` — API controller tests
- `HelloControllerTest.java` — Hello web controller tests
- `HelloAPIControllerTest.java` — Hello API tests

## License

This is an educational project for **Ontario Tech University** — SOFE 3980U (Software Quality).
