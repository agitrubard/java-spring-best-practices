# Java Spring Best Practices: Strategy Pattern for Notifications

This module demonstrates the implementation of the Strategy Pattern in a Spring Boot application for handling different
types of notifications.

---

## 📌 Overview

This module is designed to demonstrate best practices for implementing the **Strategy Pattern** in a Java Spring
application. The Strategy Pattern is used to encapsulate a family of algorithms and allows the algorithm to vary
independently from the clients that use it.

### Key Features:

- **Modular Design:** Easily extend or modify notification types.
- **Separation of Concerns:** Clean and maintainable code.
- **Dependency Injection:** Uses Spring's DI to inject dependencies and promote immutability.

---

## 🏛️ Module Structure

```plaintext
src/
├── main/
│   └── java/
│       └── dev/
│           └── agitrubard/
│               └── strategy/
│                   ├── controller/
│                   │   └── NotificationController.java
│                   ├── model/
│                   │   ├── enums/
│                   │   │   └── NotificationType.java
│                   │   └── request/
│                   │       └── NotificationRequest.java
│                   └── service/
│                       ├── NotificationService.java
│                       └── impl/
│                           ├── EmailNotificationServiceImpl.java
│                           ├── PushNotificationServiceImpl.java
│                           └── SmsNotificationServiceImpl.java
└── test/
    └── java/
        └── dev/
            └── agitrubard/
                └── strategy/
                    └── controller/
                        └── StrategyPatternEndToEndTest.java
```

---

## 🚀 Getting Started

### Prerequisites

- **Java 21**
- **Maven 3.9.6**
- **Spring Boot 3.3.0**

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

### Supported Notification Types

- **EMAIL**
- **SMS**
- **PUSH**

### Sending Notifications

To send a notification, make a `POST` request to `/api/v1/notifications/send` with the following JSON payload:

```json
{
  "to": "user@example.com",
  "type": "EMAIL"
}
```

#### Example Response

```json
"Email notification sent to user@example.com"
```

---

## ⚙️ Strategy Pattern Explained

The **Strategy Pattern** is a behavioral design pattern that enables selecting an algorithm’s behavior at runtime. It
defines a family of algorithms, encapsulates each one, and makes them interchangeable. The strategy pattern lets the
algorithm vary independently from clients that use it.

### How It's Applied

In this project, the `NotificationService` interface represents the strategy interface. Each notification type (Email,
Push, SMS) has its own implementation of this interface:

- **EmailNotificationServiceImpl:** Handles email notifications.
- **PushNotificationServiceImpl:** Handles push notifications.
- **SmsNotificationServiceImpl:** Handles SMS notifications.

The `NotificationController` uses the `NotificationService` implementations based on the notification type specified in
the request.

### Benefits

- **Flexibility:** Easily add new notification types without modifying existing code.
- **Maintainability:** Each notification type is encapsulated in its own class, adhering to the Single Responsibility
  Principle.

### Class Diagram

```plaintext
┌────────────────────────┐
│ NotificationController │
├────────────────────────┤
│ - notificationServices │
└───────────┬────────────┘
            │
            │
┌───────────┴─────────────────────────┐
│ NotificationService                 │
├─────────────────────────────────────┤
│ + getType(): NotificationType       │
│ + send(NotificationRequest): String │
└──────────┬────────┬────────┬────────┘
           │        │        │
           │        │        │
           │        │        │
           │        │        └─────────────────────────────────────────────────────┐
           │        │                                                              │
           │        └─────────────────────────────┐                                │
           │                                      │                                │
           │                                      │                                │
┌──────────┴───────────────────┐   ┌──────────────┴──────────────┐   ┌─────────────┴───────────────┐
│ EmailNotificationServiceImpl │   │ PushNotificationServiceImpl │   │ SmsNotificationServiceImpl  │
├──────────────────────────────┤   ├─────────────────────────────┤   ├─────────────────────────────┤
│ + getType()                  │   │ + getType()                 │   │ + getType()                 │
│ + send(NotificationRequest)  │   │ + send(NotificationRequest) │   │ + send(NotificationRequest) │
└──────────────────────────────┘   └─────────────────────────────┘   └─────────────────────────────┘
```