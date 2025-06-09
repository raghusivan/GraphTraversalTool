# Java Interview Questions

These questions highlight concepts relevant for building back-end services in Java.

## Questions and Answers

### 1. What advantages does Spring Boot provide for microservices?
**Answer:** Spring Boot offers auto-configuration, embedded servers, and a large ecosystem of starters that simplify setup. It reduces boilerplate and accelerates development, making it well suited for microservices.

### 2. How does Java handle memory management?
**Answer:** Java uses automatic garbage collection. Objects on the heap are reclaimed when no longer referenced. Developers need to avoid memory leaks by releasing resources and using profiling tools to monitor usage.

### 3. Explain the difference between checked and unchecked exceptions.
**Answer:** Checked exceptions must be declared or handled in code, representing recoverable conditions. Unchecked exceptions (RuntimeException and its subclasses) do not require explicit handling and are often programming errors.

### 4. Describe how you can build RESTful APIs in Java.
**Answer:** Frameworks like Spring MVC or Jakarta RESTful Web Services allow you to define controllers or resources, map HTTP verbs to methods, and produce JSON or XML responses. Annotations handle routing and dependency injection facilitates testability.

### 5. What tools do you use for unit testing in Java?
**Answer:** JUnit and Mockito are common. JUnit defines test cases and assertions, while Mockito provides mocks for dependencies so tests can run in isolation.
