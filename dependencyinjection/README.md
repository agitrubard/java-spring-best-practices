# Java Spring Best Practices: Dependency Injection Module

This module demonstrates the best practices for using Dependency Injection in a Spring Boot application.

---

## 📌 Overview

This module illustrates how to effectively use Dependency Injection in a Spring Boot application. Dependency Injection (DI) is a fundamental principle in Spring that promotes loose coupling and enhances testability by injecting dependencies rather than instantiating them directly within the classes.

### Key Features:
- **Constructor Injection:** Preferred method for injecting dependencies, ensuring immutability and making the code easier to test.
- **Separation of Concerns:** Keeps your business logic separate from dependency management.
- **Clean and Maintainable Code:** Promotes cleaner code architecture by adhering to SOLID principles.

---

## 🏛️ Module Structure

```
src/
├── main/
│   ├── java/
│   │   └── dev/
│   │       └── agitrubard/
│   │           └── dependencyinjection/
│   │               ├── controller/
│   │               │   └── SomeController.java
│   │               └── service/
│   │                   ├── SomeService.java
│   │                   └── impl/
│   │                       └── SomeServiceImpl.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── dev/
            └── agitrubard/
                └── dependencyinjection/
                    └── SomeControllerTests.java
```

---

## 🚀 Getting Started

### Prerequisites
- **Java 21**
- **Maven 3.6.x**
- **Spring Boot 3.x.x**

### Installation
**1. Clone the repository:**
```bash
git clone https://github.com/agitrubard/java-spring-best-practices.git
cd java-spring-best-practices
```
**2. Build the project:**
```bash
mvn clean install
```
**3. Run the application:**
```bash
mvn spring-boot:run
```

---

## 📄 Usage

### Asking Something
To ask something, make a `GET` request to `/something/ask`. This endpoint will use the SomeService to respond with a friendly message.

#### Example Request
```bash
curl -X GET http://localhost:8080/something/ask
```

#### Example Response
```json
"Slm, nbr?"
```

---

## 📘 Code Explanation
- **EMAIL**
- **SMS**
- **PUSH**

### Controller
`SomeController` handles the HTTP requests. It uses constructor injection to receive an instance of `SomeService`.

```java
@RestController
@RequestMapping("/something")
class SomeController {

    private final SomeService someService;

    SomeController(SomeService someService) {
        this.someService = someService;
    }

    @GetMapping("/ask")
    ResponseEntity<String> askSomething() {
        String something = someService.askSomething();
        return ResponseEntity.ok(something);
    }

}
```

### Service
`SomeService` is an interface that defines the contract for asking something.

```java
public interface SomeService {

    String askSomething();

}
```

### Service Implementation
`SomeServiceImpl` provides the actual implementation of `SomeService`.

```java
@Service
class SomeServiceImpl implements SomeService {

    @Override
    public String askSomething() {
        return "Slm, nbr?";
    }

}
```

---

## 📙 Dependency Injection Best Practices


- **Use Constructor Injection:** Preferred over field or setter injection as it promotes immutability and ensures that the dependency is provided when the object is created.
- **Avoid Circular Dependencies:** Design your services to avoid circular references, which can lead to complicated dependency graphs and potential application startup issues.
- **Use Interfaces:** Inject interfaces rather than concrete implementations to adhere to the Dependency Inversion Principle and make your code more flexible and testable.
- **Promote Single Responsibility:** Each class should have one responsibility. For example, the controller handles HTTP requests, and the service handles business logic.

### Class Diagram
```plaintext
┌──────────────────────┐
│ SomeController       │
├──────────────────────┤
│ - someService        │
└───────────┬──────────┘
            │
            │
┌───────────┴──────────────┐
│ SomeService              │
├──────────────────────────┤
│ + askSomething(): String │
└───────────┬──────────────┘
            │
            │
┌───────────┴──────────────┐
│ SomeServiceImpl          │
├──────────────────────────┤
│ + askSomething(): String │
└──────────────────────────┘
```

In this diagram:

- `SomeController` depends on `SomeService`.
- `SomeServiceImpl` implements `SomeService`.