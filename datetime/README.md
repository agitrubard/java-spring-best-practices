# Java Spring Best Practices: Handling Date and Time with LocalDate, LocalTime, and LocalDateTime

This module demonstrates the use of `java.time.LocalDate`, `java.time.LocalTime`, and `java.time.LocalDateTime` in a Spring Boot application for managing events. Additionally, it incorporates a use case that dynamically determines if an event has been published based on the current `LocalDateTime`.

---

## 📌 Overview

In this module, we focus on how to handle various date and time representations using Java’s `LocalDate`, `LocalTime`, and `LocalDateTime` classes, and how to implement business logic based on time conditions (e.g., checking if an event has been published). These classes, part of the Java 8 `java.time` package, offer a robust, intuitive, and immutable approach to working with date and time data.

### Key Features:

- **Immutability:** These classes are immutable, which means once they are created, their value cannot be changed. This helps avoid side effects and ensures thread safety in multi-threaded environments.
- **Type Safety:** Using `LocalDate`, `LocalTime`, and `LocalDateTime` ensures that the date and time data is strongly typed, preventing issues with improperly formatted `String` values.
- **Simplified Logic:** Java’s time package provides built-in methods for comparing and manipulating dates and times, making business logic, like checking if an event is published, straightforward.

---

## 🏛️ **Module Structure**

```plaintext
src/
└── main/
    └── java/
        └── dev/
            └── agitrubard/
                └── datetime/
                    ├── controller/
                    │   └── EventController.java
                    ├── model/
                    │   ├── entity/
                    │   │   └── EventEntity.java
                    │   ├── request/
                    │   │   └── EventCreateRequest.java
                    │   └── response/
                    │       └── EventResponse.java
                    ├── repository/
                    │   └── impl/
                    │       └── EventRepositoryImpl.java
                    └── service/
                        └── impl/
                            └── EventServiceImpl.java
```

---

## 🚀 **Getting Started**

### **Prerequisites**

- **Java 21**
- **Maven 3.6.x**
- **Spring Boot 3.x.x**

### **Installation**

1. **Clone the repository:**

```bash
git clone https://github.com/agitrubard/java-spring-best-practices.git
cd java-spring-best-practices
```

2. **Build the project:**

```bash
mvn clean install
```

3. **Run the application:**

```bash
mvn spring-boot:run
```

---

## 📄 **Usage**

### Creating an Event with Time Logic

To create an event, make a `POST` request to `/events` with the following JSON payload:

```json
{
  "name": "Some Event",
  "date": "2025-09-23",
  "time": "20:00",
  "publishAt": "2024-09-23T00:00:00"
}
```

In the backend, the application checks whether the `publishAt` date is in the past, and marks the event as published or unpublished based on this logic. If `publishAt` is `null`, the event is automatically considered published.

**Example Logic in Event Creation:**
```java
@Override
public void create(EventCreateRequest createRequest) {
    
    boolean isPublished = Optional.ofNullable(createRequest.getPublishAt())
            .map(publishAt -> publishAt.isBefore(LocalDateTime.now()))
            .orElse(true);

    EventEntity eventEntity = new EventEntity(
            createRequest.getName(),
            createRequest.getDate(),
            createRequest.getTime(),
            createRequest.getPublishAt(),
            isPublished
    );

    eventRepository.save(eventEntity);
}
```

### Example Request (POST /events):

```bash
POST /events
Content-Type: application/json
{
  "name": "Some Event",
  "date": "2025-09-23",
  "time": "20:00",
  "publishAt": "2024-09-23T00:00:00"
}
```

### Retrieving Events

To retrieve all events, send a `GET` request to `/events`:

```bash
GET /events
```

Sample Response:

```json
[
  {
    "id": 1,
    "name": "Some Event",
    "date": "2025-09-23",
    "time": "20:00",
    "publishAt": "2024-09-23T00:00:00",
    "isPublished": true,
    "createdAt": "2024-09-21T09:52:20.12342"
  }
]
```

### Retrieving an Event by ID

To retrieve a specific event by ID, use the following endpoint:

```bash
GET /event/{id}
```

---

## ⚙️ **Why Use `LocalDate`, `LocalTime`, and `LocalDateTime`?**

### **`java.time.LocalDate`**
- Represents a date (year, month, day) without time or time zone.
- Ideal for representing specific days like holidays, deadlines, or event dates.

### **`java.time.LocalTime`**
- Represents time (hour, minute, second) without a date or time zone.
- Perfect for representing a specific time of day (e.g., the start time of a meeting).

### **`java.time.LocalDateTime`**
- Represents a date and time without time zone information.
- Useful for scenarios where both date and time are needed, like when an event should be published.

### **Advantages in a Real-World Application**

- **Built-in Date Logic**: The `LocalDateTime` object provides methods like `isBefore()` and `isAfter()` that make it easy to perform checks based on the current date and time.
- **Consistency**: Using `LocalDate`, `LocalTime`, and `LocalDateTime` ensures consistency and eliminates the need for manual date formatting or parsing.
- **Simplified Business Logic**: By utilizing these classes, it’s easy to implement logic based on dates, such as automatically marking an event as published if its `publishAt` date is in the past.

---

### Class Diagram

```plaintext
┌───────────────────────┐
│ EventController       │
├───────────────────────┤
│ + findAll()           │
│ + findById()          │
│ + create()            │
└───────────┬───────────┘
            │
            │
┌───────────┴───────────┐
│ EventService          │
├───────────────────────┤
│ + findAll()           │
│ + findById()          │
│ + create()            │
└───────────┬───────────┘
┌───────────┴───────────┐
│ EventServiceImpl      │
├───────────────────────┤
│ + findAll()           │
│ + findById()          │
│ + create()            │
└───────────┬───────────┘
            │
            │
┌───────────┴───────────┐
│ EventRepository       │
├───────────────────────┤
│ + findAll()           │
│ + findById()          │
│ + save()              │
└───────────┬───────────┘
┌───────────┴───────────┐
│ EventRepositoryImpl   │
├───────────────────────┤
│ + findAll()           │
│ + findById()          │
│ + save()              │
└───────────────────────┘
```

---

## **Conclusion**

By using `LocalDate`, `LocalTime`, and `LocalDateTime`, we ensure that our application handles date and time information efficiently, and implements logic like determining whether an event is published based on the current time. This simplifies both coding and maintaining business rules around time-based logic.

**Key Takeaways**:
- No need for custom formatting—Java’s date and time objects provide everything we need out of the box.
- Built-in methods for time comparisons, such as determining whether an event should be published based on `LocalDateTime`.
- Immutability and type safety are maintained, ensuring that your code is secure and easy to understand.

---

This README now highlights the time-based logic you've added while continuing to emphasize the use of `LocalDate`, `LocalTime`, and `LocalDateTime` effectively. Let me know if you need further adjustments!