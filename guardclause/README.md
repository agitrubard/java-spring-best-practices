# Java Best Practices: Guard Clauses 🆚 Nested If-Else Statements

This module demonstrates the difference between using **Guard Clauses** and **Nested If-Else Statements** for validating
user input in Java. It highlights the advantages of guard clauses in improving code readability, maintainability, and
reducing complexity compared to nested conditional logic.

---

## 📌 Overview

Validation is a critical part of any application to ensure the correctness of input data. Two common approaches for
implementing validation are:

- **Guard Clauses**: Early exit conditions to handle invalid input.
- **Nested If-Else Statements**: Layered conditions to check and handle input step by step.

This module compares the two methods in terms of:

- **Readability**
- **Maintainability**
- **Complexity**

Both approaches validate the same input criteria:

1. **Username** must not be `null` or empty.
2. **Age** must not be `null` and must be greater than or equal to 18.
3. **Roles** must not be `null` or empty.

---

## 🚀 Key Features

### **Guard Clauses**

- **Readability:** Each condition is handled independently, with immediate feedback for invalid inputs.
- **Maintainability:** Adding, removing, or updating validation rules is straightforward.
- **Reduced Complexity:** Avoids deeply nested structures, making the code easier to follow.
- **Failure Early:** Stops processing as soon as an invalid condition is found.

### **Nested If-Else Statements**

- **Poor Readability:** Deeply nested structures make the code harder to scan and understand.
- **Difficult Maintenance:** Adding or updating validation rules requires careful restructuring.
- **High Complexity:** Increases the cognitive load on developers to understand the logic flow.
- **Unnecessary Indentation:** Excessive nesting reduces code clarity.

---

## 🛠️ Example Code

### **Guard Clauses Implementation**

```java
public static void processUserWithClauseGuard(String username, Integer age, List<String> roles) {

    if (username == null) {
        throw new RuntimeException("Username cannot be null");
    }

    if (username.isEmpty()) {
        throw new RuntimeException("Username cannot be empty");
    }

    if (age == null) {
        throw new RuntimeException("Age cannot be null");
    }

    if (age < 18) {
        throw new RuntimeException("Age must be greater than 18");
    }

    if (roles == null || roles.isEmpty()) {
        throw new RuntimeException("Roles cannot be empty");
    }

    System.out.println("Process user with clause guard...");
}
```

### **Nested If-Else Statements Implementation**

```java
public static void processUserWithNestedIfElse(String username, Integer age, List<String> roles) {

    if (username != null) {
        if (!username.isEmpty()) {
            if (age != null) {
                if (age >= 18) {
                    if (roles != null && !roles.isEmpty()) {
                        System.out.println("Process user with nested if-else...");
                    } else {
                        throw new RuntimeException("Roles cannot be empty");
                    }
                } else {
                    throw new RuntimeException("Age must be greater than 18");
                }
            } else {
                throw new RuntimeException("Age cannot be null");
            }
        } else {
            throw new RuntimeException("Username cannot be empty");
        }
    } else {
        throw new RuntimeException("Username cannot be null");
    }
}
```

---

### 📄 Comparison: Guard Clauses vs. Nested If-Else

| Feature             | Guard Clauses                      | Nested If-Else                            |
|---------------------|------------------------------------|-------------------------------------------|
| **Readability**     | Clear, concise, and easy to follow | Hard to read due to deep nesting          |
| **Maintainability** | Easy to modify                     | Difficult to update or restructure        |
| **Complexity**      | Low                                | High, especially with multiple conditions |
| **Error Handling**  | Immediate                          | Delayed due to layered conditions         |
| **Indentation**     | Minimal                            | Excessive                                 |

---

### 🏁 Conclusion

For most scenarios, **Guard Clauses** are the recommended approach due to their:

- **Simplicity:** Early exit for invalid conditions avoids unnecessary complexity.
- **Clarity:** Each condition is isolated, making the code easier to read and understand.
- **Maintainability:** New rules can be added or existing ones updated without impacting unrelated logic.

While **Nested If-Else Statements** can work for simple cases, they quickly become unmanageable as conditions grow in
number and complexity. Opting for guard clauses leads to cleaner, more maintainable, and more professional code.

---

Feel free to extend this module with your own examples or additional comparisons! Let me know if you have any feedback
or questions.
