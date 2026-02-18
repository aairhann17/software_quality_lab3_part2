# Binary Calculator Web Application

A Spring Boot web application that performs binary arithmetic operations on binary numbers, exposed through both an interactive web interface and a REST API.

**Course:** SOFE 3980U — Software Quality and Project Management | Ontario Tech University  
---

## Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Web Interface](#web-interface)
- [REST API Reference](#rest-api-reference)
- [Core Classes](#core-classes)
- [Testing](#testing)

---

## Overview

This application accepts binary number strings (sequences of `0`s and `1`s) and performs four arithmetic/logical operations:

- **Addition** — binary addition with carry propagation
- **Multiplication** — binary multiplication via repeated addition
- **Bitwise AND** — bit-by-bit logical AND
- **Bitwise OR** — bit-by-bit logical OR (inclusive)

Results are available via a Thymeleaf-rendered HTML calculator page and via REST endpoints that return either plain-text strings or structured JSON responses.

---

## Tech Stack

| Component | Version |
|---|---|
| Java | 20 |
| Spring Boot | 2.7.18 |
| Spring Web | — |
| Spring Thymeleaf | — |
| JUnit | 4 |
| Spring MockMvc | — |

---

## Project Structure

```
software_quality_lab2/
├── README.md
└── BinaryCalculatorWebApp/
    ├── pom.xml
    └── src/
        ├── main/
        │   ├── java/com/ontariotechu/sofe3980u/
        │   │   ├── Binary.java                  # Core binary arithmetic logic
        │   │   ├── BinaryController.java         # Web interface controller (GET + POST /)
        │   │   ├── BinaryAPIController.java      # REST API controller
        │   │   ├── HelloController.java          # Hello web controller (GET /hello)
        │   │   └── HelloAPIController.java       # Hello & Email REST controller
        │   └── resources/
        │       └── templates/
        │           ├── calculator.html           # Calculator input page
        │           ├── result.html               # Calculation result page
        │           └── hello.html                # Hello page
        └── test/
            └── java/com/ontariotechu/sofe3980u/
                ├── BinaryControllerTest.java     # 6 web controller tests
                ├── BinaryAPIControllerTest.java  # 5 REST API tests
                ├── HelloControllerTest.java      # 2 Hello web tests
                └── HelloAPIControllerTest.java   # 6 Hello & Email API tests
```

---

## Getting Started

### Prerequisites

- Java 20+
- Maven 3.6+

### Build

```bash
cd BinaryCalculatorWebApp
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

Expected output: **19 tests, 0 failures, 0 errors — BUILD SUCCESS**

---

## Web Interface

Navigate to `http://localhost:8080/` to use the calculator.

### GET /

Renders the calculator input page.

| Query Parameter | Type | Default | Description |
|---|---|---|---|
| `operand1` | string | `""` | Pre-fills the first operand field and focuses it |

### POST /

Submits a calculation. The form sends three parameters and renders the result page.

| Parameter | Type | Description |
|---|---|---|
| `operand1` | string | First binary number |
| `operator` | string | Operation: `+`, `*`, `&`, or `I` (inclusive OR) |
| `operand2` | string | Second binary number |

**Example operations:**

| operand1 | operator | operand2 | result |
|---|---|---|---|
| `111` | `+` | `111` | `1110` |
| `101` | `*` | `10` | `1010` |
| `1101` | `&` | `1011` | `1001` |
| `1101` | `I` | `0011` | `1111` |

---

## REST API Reference

### Binary Operations

#### `GET /add`
Returns the binary sum of two operands as a plain-text string.

```
GET /add?operand1=111&operand2=1010
→ 10001
```

#### `GET /add_json`
Returns the binary sum as a JSON object with full operation details.

```
GET /add_json?operand1=111&operand2=1010
→ { "operand1": 111, "operand2": 1010, "result": 10001, "operator": "add" }
```

#### `GET /multiply`
Returns the binary product as a plain-text string.

```
GET /multiply?operand1=101&operand2=10
→ 1010
```

#### `GET /and`
Returns the bitwise AND result as a plain-text string.

```
GET /and?operand1=1101&operand2=1011
→ 1001
```

#### `GET /or`
Returns the bitwise OR result as a plain-text string.

```
GET /or?operand1=1101&operand2=0011
→ 1111
```

---

### Hello Endpoints

#### `GET /hello`
Renders the hello Thymeleaf page with a name model attribute.

| Parameter | Default |
|---|---|
| `name` | `"World"` |

```
GET /hello          → renders hello view with name = "World"
GET /hello?name=Doe → renders hello view with name = "Doe"
```

#### `GET /helloAPI`
Returns a plain-text greeting string.

| Parameter | Default |
|---|---|
| `name` | `"World"` |

```
GET /helloAPI           → Hello World!
GET /helloAPI?name=John → Hello John!
```

---

### Email API

#### `GET /emailAPI`
Returns a suggested Ontario Tech email address as a JSON object.

| Parameter | Default |
|---|---|
| `fname` | `"John"` |
| `lname` | `"Doe"` |

```
GET /emailAPI
→ { "name": "John Doe", "suggestedEmail": "John.Doe@OntarioTechU.net" }

GET /emailAPI?fname=Jack
→ { "name": "Jack Doe", "suggestedEmail": "Jack.Doe@OntarioTechU.net" }

GET /emailAPI?lname=Sparrow
→ { "name": "John Sparrow", "suggestedEmail": "John.Sparrow@OntarioTechU.net" }

GET /emailAPI?fname=Jack&lname=Sparrow
→ { "name": "Jack Sparrow", "suggestedEmail": "Jack.Sparrow@OntarioTechU.net" }
```

---

## Core Classes

### `Binary.java`
Holds the binary string value and implements all arithmetic operations as static methods:

- `add(Binary a, Binary b)` — adds two binary numbers with full carry propagation
- `multiply(Binary a, Binary b)` — multiplies two binary numbers via repeated addition
- `and(Binary a, Binary b)` — performs bit-by-bit logical AND
- `or(Binary a, Binary b)` — performs bit-by-bit logical OR (inclusive)

### `BinaryController.java`
Handles the web calculator interface. Responds to `GET /` (renders the input page with optional pre-filled operand and `operand1Focused` flag) and `POST /` (performs the selected operation and renders the result page with the result and original operand1 in the model).

### `BinaryAPIController.java`
Provides REST endpoints for binary operations, returning results as plain-text strings (`/add`, `/multiply`, `/and`, `/or`) or as a JSON object with `operand1`, `operand2`, `result`, and `operator` fields (`/add_json`).

### `HelloController.java`
Handles `GET /hello`, renders the hello Thymeleaf view with a `name` model attribute, defaulting to `"World"` when no parameter is provided.

### `HelloAPIController.java`
Provides `GET /helloAPI` (returns a plain-text greeting, defaulting to `"Hello World!"`) and `GET /emailAPI` (returns a JSON object with `name` and `suggestedEmail` fields using `fname` and `lname` parameters, both defaulting to `"John"` and `"Doe"` respectively).

---

## Testing

All tests use **JUnit 4** with **Spring MockMvc** (`@WebMvcTest`) for integration-level testing of the full request/response pipeline without a running server. Each test class targets a single controller using slice-based loading.

| Test Class | Controller | Tests | What's Verified |
|---|---|---|---|
| `BinaryAPIControllerTest` | `BinaryAPIController` | 5 | Plain-text and JSON response bodies, all four operations |
| `BinaryControllerTest` | `BinaryController` | 6 | View names, model attributes, `operand1Focused` flag, all four POST operations |
| `HelloAPIControllerTest` | `HelloAPIController` | 6 | Greeting strings, JSON `name` and `suggestedEmail` fields, all default/partial/full parameter combinations |
| `HelloControllerTest` | `HelloController` | 2 | View name, `name` model attribute, default value |
| **Total** | | **19** | |

---

## License

Educational project — Ontario Tech University, SOFE 3980U (Software Quality).
