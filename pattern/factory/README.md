# Java Spring Best Practices: Factory Pattern for Authentications

This module demonstrates the implementation of the Factory Pattern in a Spring Boot application for handling different
types of authentications.

📌 Overview

This module is designed to demonstrate best practices for implementing the Factory Pattern in a Java Spring application.
The Factory Pattern is used to create objects without specifying the exact class of object that will be created,
promoting loose coupling and extensibility.

Key Features:

- **Encapsulation:** Hide the creation logic and create objects through a common interface.
- **Separation of Concerns:** Clean and maintainable code.
- **Dependency Injection:** Uses Spring’s DI to inject dependencies and promote immutability.

---

## 🏛️ Module Structure

```plaintext
src/
└── main/
    └── java/
        └── dev/
            └── agitrubard/
                └── factory/
                    ├── controller/
                    │   └── AuthController.java
                    ├── model/
                    │   ├── entity/
                    │   │   └── UserEntity.java
                    │   ├── enums/
                    │   │   └── TwoFactorAuthenticationType.java
                    │   └── request/
                    │       ├── LoginRequest.java
                    │       └── RegisterRequest.java
                    ├── repository/
                    │   ├── UserRepository.java
                    │   └── impl/
                    │       └── UserRepositoryImpl.java
                    └── service/
                        ├── LoginService.java
                        ├── RegisterService.java
                        ├── TwoFactorAuthenticationService.java
                        ├── TwoFactorAuthenticationServiceFactory.java
                        └── impl/
                            ├── EmailAuthenticationServiceImpl.java
                            ├── LoginServiceImpl.java
                            ├── PassKeyAuthenticationServiceImpl.java
                            ├── RegisterServiceImpl.java
                            ├── SmsAuthenticationServiceImpl.java
                            └── TwoFactorAuthenticationServiceFactoryImpl.java
```

---

## 🚀 Getting Started

### Prerequisites

- **Java 21**
- **Maven 3.x.x**
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

### Supported Authentication Types

- `PASSKEY`
- `EMAIL`
- `SMS`

### 1. Registration Flow

To register a user, make a `POST` request to `/api/v1/auth/register` with the following JSON payload:

```json
{
  "username": "agitrubard",
  "password": "1234",
  "emailAddress": "agitrubard@software.eng",
  "phoneNumber": "1234567890",
  "twoFactorAuthenticationType": "PASSKEY"
}
```

#### Example Response

```json
"User authenticating via Passkey..."
```

### 2. Login Flow

To login a user, make a `POST` request to `/api/v1/auth/login` with the following JSON payload:

```json
{
  "username": "agitrubard",
  "password": "1234"
}
```

#### Example Response

```json
"User authenticating via Passkey..."
```

---

## ⚙️ Factory Pattern Explained

The **Factory Pattern** is a creational design pattern that provides an interface for creating objects in a superclass,
but allows subclasses to alter the type of objects that will be created. This pattern promotes flexibility and decouples
the client code from the specific classes that implement the creation process.

### How It's Applied

In this project, the `TwoFactorAuthenticationServiceFactory` is responsible for creating the
appropriate `TwoFactorAuthenticationService`
implementation based on the `TwoFactorAuthenticationType`. Each authentication type (Passkey, Email, SMS) has its own
implementation of
the `TwoFactorAuthenticationService` interface:

- **PassKeyAuthenticationServiceImpl:** Handles Passkey authentications.
- **EmailAuthenticationServiceImpl:** Handles Email authentications.
- **SmsAuthenticationServiceImpl:** Handles SMS authentications.

The `LoginService` and The `RegisterService` uses the `TwoFactorAuthenticationService` implementations based on the
authentication type specified in
the request.

### Benefits

- **Decoupling:** The client code is not tightly coupled to the concrete classes of the services.
- **Maintainability:** New authentication types can be added without changing existing client code.
- **Flexibility:** Allows runtime decision-making for the creation of objects.

### Class Diagram

```plaintext
┌─────────────────────────────────────────┐    ┌─────────────────────────────────────────┐
│ RegisterService                         │    │ LoginService                            │
├─────────────────────────────────────────┤    ├─────────────────────────────────────────┤
│ + register(RegisterRequest): String     │    │ + login(LoginRequest): String           │
└───────────────────┬─────────────────────┘    └────────────────────┬────────────────────┘
┌───────────────────┴─────────────────────┐    ┌────────────────────┴────────────────────┐
│ RegisterServiceImpl                     │    │ LoginServiceImpl                        │
├─────────────────────────────────────────┤    ├─────────────────────────────────────────┤
│ - twoFactorAuthenticationServiceFactory │    │ - twoFactorAuthenticationServiceFactory │
├─────────────────────────────────────────┤    ├─────────────────────────────────────────┤
│ + register(RegisterRequest): String     │    │ + login(LoginRequest): String           │
└──────────────────┬──────────────────────┘    └───────────────┬─────────────────────────┘
                   │                                           │
                   └─────────────┐             ┌───────────────┘
                                 │             │
        ┌────────────────────────┴─────────────┴────────────────────────────────┐
        │ TwoFactorAuthenticationServiceFactory                                 │
        ├───────────────────────────────────────────────────────────────────────┤
        │ + create(TwoFactorAuthenticationType): TwoFactorAuthenticationService │
        └───────────────────────────────┬───────────────────────────────────────┘
        ┌───────────────────────────────┴───────────────────────────────────────┐
        │ TwoFactorAuthenticationServiceFactoryImpl                             │
        ├───────────────────────────────────────────────────────────────────────┤
        │ + create(TwoFactorAuthenticationType): TwoFactorAuthenticationService │
        └────────────────────────┬────────┬────────┬────────────────────────────┘
                                 │        │        │
                ┌────────────────┘        │        └────────────────────────────────────────┐
                │                         └─────────────┐                                   │
                │                                       │                                   │
┌───────────────┴──────────────────┐   ┌────────────────┴───────────────┐   ┌───────────────┴──────────────┐
│ PassKeyAuthenticationServiceImpl │   │ EmailAuthenticationServiceImpl │   │ SmsAuthenticationServiceImpl │
├──────────────────────────────────┤   ├────────────────────────────────┤   ├──────────────────────────────┤
│ + authenticate(): String         │   │ + authenticate(): String       │   │ + authenticate(): String     │
└──────────────────────────────────┘   └────────────────────────────────┘   └──────────────────────────────┘
```