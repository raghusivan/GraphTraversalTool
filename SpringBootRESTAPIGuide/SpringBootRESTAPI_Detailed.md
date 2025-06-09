# Detailed Spring Boot REST API Interview Questions and Answers

The following questions provide in-depth explanations with code examples for building REST APIs.

## 1. How do you create a custom exception handler?
Use `@ControllerAdvice` with `@ExceptionHandler` methods to return structured error responses.

```java
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Error> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Error(ex.getMessage()));
    }
}
```

## 2. What is the purpose of HATEOAS?
Hypermedia as the Engine of Application State (HATEOAS) enriches responses with links that guide the client on available actions.

## 3. How do you document APIs with OpenAPI/Swagger?
Add the `springdoc-openapi` dependency and annotate controllers with `@Operation` and `@Parameter` to generate interactive documentation.
