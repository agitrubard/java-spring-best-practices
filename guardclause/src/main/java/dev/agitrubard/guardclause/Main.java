package dev.agitrubard.guardclause;

import java.util.List;

/**
 * A class demonstrating the difference between using guard clauses and nested if-else statements
 * for validating user input in Java.
 *
 * <h2>Purpose</h2>
 * This class provides two methods:
 * <ul>
 *     <li>{@link #processUserWithGuardClause(String, Integer, List)}: Uses guard clauses to validate user input.</li>
 *     <li>{@link #processUserWithNestedIfElse(String, Integer, List)}: Uses nested if-else statements for validation.</li>
 * </ul>
 * Both methods achieve the same functionality but differ significantly in terms of readability, maintainability, and
 * overall code quality.
 *
 * <h2>Comparison: Guard Clauses vs. Nested If-Else</h2>
 * <b>Guard Clauses:</b>
 * <ul>
 *     <li><b>Readability:</b> Guard clauses make the code easier to read by separating validation checks
 *         into clear, independent blocks. Each check is concise and exits the method early if a condition is violated.</li>
 *     <li><b>Maintainability:</b> Adding or modifying validation rules is straightforward since each rule is isolated.
 *         You can easily insert, remove, or change conditions without affecting unrelated logic.</li>
 *     <li><b>Reduced Complexity:</b> Guard clauses prevent deeply nested structures, making it easier to understand
 *         the overall flow of the method.</li>
 *     <li><b>Failure Early:</b> Each invalid condition is detected and handled as soon as it is encountered, improving
 *         the clarity of failure points.</li>
 * </ul>
 *
 * <b>Nested If-Else Statements:</b>
 * <ul>
 *     <li><b>Poor Readability:</b> Deeply nested structures make the code harder to follow. Readers must keep track
 *         of multiple conditions simultaneously.</li>
 *     <li><b>Maintainability Challenges:</b> Adding or changing validation rules requires careful restructuring of
 *         nested conditions, increasing the risk of introducing bugs.</li>
 *     <li><b>High Cognitive Load:</b> Developers need to mentally track multiple layers of conditions,
 *         which increases the complexity of understanding and debugging the code.</li>
 *     <li><b>Unnecessary Indentation:</b> Multiple levels of indentation make the code harder to scan and less aesthetically pleasing.</li>
 * </ul>
 *
 * <h2>Recommendation</h2>
 * <p>
 * For most scenarios, using guard clauses is the preferred approach due to its simplicity, clarity, and ease of maintenance.
 * While nested if-else statements can work for small or simple logic, they quickly become unmanageable as the number of
 * conditions increases.
 * </p>
 */
public class Main {

    public static void main(String[] args) {
        String username = "agitrubard";
        Integer age = 25;
        List<String> roles = List.of("admin");

        processUserWithGuardClause(username, age, roles);

        processUserWithNestedIfElse(username, age, roles);
    }


    /**
     * Processes user input using guard clauses to validate input parameters.
     *
     * <p>This method uses a series of guard clauses to ensure that the input parameters
     * meet the required criteria. If any condition is violated, a {@link RuntimeException}
     * is thrown immediately, avoiding deeply nested logic and improving code readability.</p>
     *
     * <b>Validation rules:</b>
     * <ul>
     *     <li>Username must not be null or empty.</li>
     *     <li>Age must not be null and must be greater than or equal to 18.</li>
     *     <li>Roles list must not be null or empty.</li>
     * </ul>
     *
     * @param username the username of the user; must not be null or empty.
     * @param age      the age of the user; must not be null and must be greater than or equal to 18.
     * @param roles    the list of roles assigned to the user; must not be null or empty.
     * @throws RuntimeException if any of the validation rules are violated.
     */
    public static void processUserWithGuardClause(String username, Integer age, List<String> roles) {

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


    /**
     * Processes user input using nested if-else statements to validate input parameters.
     *
     * <p>This method uses a series of nested if-else statements to validate the input parameters.
     * While functionally equivalent to the guard clause method, this approach results in deeply nested
     * logic, which can make the code harder to read and maintain.</p>
     *
     * <b>Validation rules:</b>
     * <ul>
     *     <li>Username must not be null or empty.</li>
     *     <li>Age must not be null and must be greater than or equal to 18.</li>
     *     <li>Roles list must not be null or empty.</li>
     * </ul>
     *
     * @param username the username of the user; must not be null or empty.
     * @param age      the age of the user; must not be null and must be greater than or equal to 18.
     * @param roles    the list of roles assigned to the user; must not be null or empty.
     * @throws RuntimeException if any of the validation rules are violated.
     */
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

}
