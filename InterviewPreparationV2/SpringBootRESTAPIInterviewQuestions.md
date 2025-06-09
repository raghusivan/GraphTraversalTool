# Spring Boot REST API Interview Questions

This document provides one hundred interview questions focused on building RESTful APIs with Spring Boot. Each question is followed by a detailed answer and relevant code snippets. Diagrams are included where helpful to visualize concepts.

## Questions and Answers

1. **What is REST and how does Spring Boot help implement it?**

   REST (Representational State Transfer) is an architectural style for designing networked applications. Spring Boot simplifies REST service creation through features like auto-configuration, embedded servers, and starter dependencies. With annotations such as `@RestController` and `@RequestMapping`, you can expose HTTP endpoints without extensive configuration.

2. **Explain the difference between `@RestController` and `@Controller`.**

   `@Controller` is typically used with web pages and returns view names, while `@RestController` combines `@Controller` and `@ResponseBody` to indicate that the return value should be serialized directly to the HTTP response body as JSON or XML.

3. **How do you define a simple GET endpoint in Spring Boot?**

   ```java
   @RestController
   public class GreetingController {
       @GetMapping("/greet")
       public String greet() {
           return "Hello";
       }
   }
   ```
   This exposes `/greet` to HTTP GET requests and returns the string "Hello" as the response body.

4. **What are path variables and query parameters?**

   Path variables are parts of the URL that capture values, e.g. `/users/{id}`. Query parameters follow the `?` in the URL, e.g. `/users?id=123`. Spring Boot binds them using `@PathVariable` and `@RequestParam` annotations.

5. **How do you handle POST requests with JSON payloads?**

   Use `@PostMapping` and annotate a method parameter with `@RequestBody` to map the request body to a Java object.
   ```java
   @PostMapping("/users")
   public User create(@RequestBody User user) {
       return userService.save(user);
   }
   ```

6. **Describe the typical architecture of a Spring Boot REST API.**

   A common layout separates layers into controllers, services, and repositories. The controller handles HTTP requests, the service layer implements business logic, and the repository layer interacts with the database.
   ```
   [Client] -> [Controller] -> [Service] -> [Repository]
   ```

7. **What is Spring Data REST?**

   Spring Data REST automatically exports Spring Data repositories as REST endpoints. It follows HATEOAS principles, generating links to related resources.

8. **How do you document REST APIs in Spring Boot?**

   Tools like SpringDoc or Swagger (OpenAPI) scan your controllers and generate interactive API documentation. Add the dependency `springdoc-openapi-ui` and access `/swagger-ui.html`.

9. **Explain the purpose of `@CrossOrigin`.**

   `@CrossOrigin` enables Cross-Origin Resource Sharing (CORS) for specified domains or all domains. It's needed when a frontend application on a different domain calls your API.

10. **How do you return custom HTTP status codes?**

   Return a `ResponseEntity` and specify the status:
   ```java
   @GetMapping("/resource/{id}")
   public ResponseEntity<MyResource> get(@PathVariable Long id) {
       return ResponseEntity.status(HttpStatus.CREATED).body(resource);
   }
   ```

11. **What is HATEOAS and how does Spring support it?**

    HATEOAS (Hypermedia As The Engine Of Application State) provides clients with links to navigate resources dynamically. Spring HATEOAS simplifies link creation using `EntityModel`, `CollectionModel`, and link builders to construct hypermedia-driven APIs.

12. **How can you handle validation for incoming JSON data?**

    Use Bean Validation annotations in your DTOs and the `@Valid` annotation in your controller method parameters. If validation fails, Spring Boot automatically responds with a `400 Bad Request` containing details about the errors.

13. **Describe how to use `ResponseEntityExceptionHandler` to customize error responses.**

    Extend `ResponseEntityExceptionHandler` and override methods such as `handleMethodArgumentNotValid`. Register the class with `@ControllerAdvice` to intercept exceptions and return formatted error payloads.

14. **How do you implement pagination in a REST API?**

    Accept `page` and `size` as request parameters and return a `Page` object from Spring Data. The `Page` includes content and metadata like total pages and number of elements.

15. **What annotations define the HTTP method in Spring MVC?**

    `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`, and `@PatchMapping` are specialized forms of `@RequestMapping` for common HTTP verbs.

16. **Explain the difference between `@RequestBody` and `@ResponseBody`.**

    `@RequestBody` tells Spring to map the HTTP request body to a Java object using message converters. `@ResponseBody` (implied by `@RestController`) instructs Spring to serialize the return value to the response body.

17. **How can you version REST APIs in Spring Boot?**

    Common approaches include path versioning (`/v1/users`), request parameter versioning (`?version=1`), or header-based versioning. Implementing custom logic in a filter or controller allows you to switch based on the version value.

18. **What is the role of `HttpMessageConverter`?**

    `HttpMessageConverter` converts HTTP requests and responses to and from Java objects. Spring Boot automatically registers converters for JSON, XML, and other formats based on the classpath.

19. **Describe how to secure a REST API with Spring Security.**

    Add the Spring Security starter, create a `SecurityFilterChain`, and configure HTTP security to require authentication for specific endpoints. Use JWT or OAuth2 for token-based authentication when stateless APIs are required.

20. **How do you write integration tests for REST endpoints?**

    Use `@SpringBootTest` with `@AutoConfigureMockMvc` and test the controller using `MockMvc`. You can perform requests, assert status codes, and validate JSON responses.

21. **How does caching work in Spring Boot REST services?**

    Enable caching with `@EnableCaching` and annotate methods with `@Cacheable`. Spring Boot supports various cache providers such as Ehcache or Redis to store previously computed results and reduce processing.

22. **Explain what `@RestControllerAdvice` is used for.**

    `@RestControllerAdvice` combines `@ControllerAdvice` and `@ResponseBody`. It's typically used to handle exceptions globally for REST controllers and return JSON error responses.

23. **Describe the difference between synchronous and asynchronous request handling.**

    Synchronous endpoints return a response immediately, blocking the thread. Asynchronous handling uses `DeferredResult` or `WebAsyncTask` to release the request thread while the task continues, enabling higher scalability.

24. **How can you stream large data sets to a client?**

    Use `ResponseBodyEmitter` or `StreamingResponseBody` to send data chunks as they are generated. This is helpful for large downloads without buffering the entire response in memory.

25. **What is a `Resource` in Spring and how is it used?**

    A `Resource` represents external content such as files or classpath resources. Controllers can return `Resource` objects to allow clients to download files.

26. **Explain how to configure JSON serialization in Spring Boot.**

    Spring Boot uses Jackson by default. You can customize serialization via `application.properties` or by defining a `Jackson2ObjectMapperBuilder` bean to control naming strategies, date formats, and more.

27. **How do you handle file uploads in a REST API?**

    Use `MultipartFile` as a parameter in your controller and configure `MultipartResolver` if necessary. The uploaded file can then be stored or processed.

28. **What is the difference between `@JsonIgnoreProperties` and `@JsonIgnore`?**

    `@JsonIgnore` excludes a single field from serialization and deserialization. `@JsonIgnoreProperties` allows you to ignore multiple fields or unknown properties at the class level.

29. **How do you manage API errors in a consistent format?**

    Implement a global exception handler using `@RestControllerAdvice` to catch exceptions and return a standardized error body with fields like timestamp, message, and details.

30. **Describe the process of logging incoming requests and responses.**

    Implement a servlet filter or use Spring Boot's `CommonsRequestLoggingFilter`. For more advanced logging, integrate libraries like Logbook to log bodies and headers conditionally.

31. **What is a DTO and why is it used in REST APIs?**

    A Data Transfer Object (DTO) is a simplified object used to transport data between layers. DTOs decouple API models from persistence models and help control JSON serialization.

32. **How do you return different representations (JSON vs XML) from the same endpoint?**

    Configure content negotiation by adding the appropriate message converters. Clients specify the desired format via the `Accept` header.

33. **Explain how to handle exceptions thrown from service methods.**

    Catch service exceptions in a `@RestControllerAdvice` class and convert them to meaningful HTTP responses, such as 404 for not found or 409 for conflicts.

34. **What is OpenAPI and how does it integrate with Spring Boot?**

    OpenAPI is a specification for describing REST APIs. Libraries like SpringDoc automatically generate an OpenAPI description based on your controllers, allowing tools to create documentation and client code.

35. **Describe the use of `@RequestHeader`.**

    `@RequestHeader` binds a method parameter to a specific header value from the HTTP request. This is useful for processing custom headers like tokens or correlation IDs.

36. **How do you enforce HTTPS in Spring Boot?**

    Configure `server.ssl.*` properties and redirect HTTP requests to HTTPS using `TomcatServletWebServerFactory` or a reverse proxy such as Nginx.

37. **What is the difference between `@Component`, `@Service`, and `@Repository`?**

    All are stereotypes for Spring-managed beans. `@Service` expresses business logic, `@Repository` applies to persistence-layer classes, and `@Component` is a generic stereotype when others do not fit.

38. **How do you implement rate limiting?**

    You can use libraries like Bucket4j or a gateway solution such as Spring Cloud Gateway to limit how many requests a client can perform within a time window.

39. **What is the purpose of `@Validated` on a controller?**

    `@Validated` enables group-based validation using Bean Validation. It allows selective validation of fields depending on the validation group provided.

40. **Explain how you can test REST controllers without starting the server.**

    Use `MockMvc` or `WebTestClient` in combination with `@WebMvcTest` to test controllers in isolation from the web server.

41. **How do you implement filtering or searching with Spring Data?**

    Expose repository methods like `findByLastName` or use `JpaSpecificationExecutor` for complex criteria. For dynamic queries, use QueryDSL or Specifications.

42. **What is the difference between eager and lazy loading for REST data?**

    When returning entities directly, lazy-loaded associations might trigger additional queries during serialization. Mapping to DTOs avoids unwanted lazy loads and controls which data is exposed.

43. **How do you set up global CORS configuration?**

    Extend `WebMvcConfigurer` and override `addCorsMappings` to configure allowed origins, headers, and methods for all routes.

44. **Describe the role of `@JsonView`.**

    `@JsonView` allows you to control which fields of an object are serialized based on different views. This is helpful when the same object must be represented differently for various clients.

45. **How can you implement custom authentication for an API?**

    Implement a `OncePerRequestFilter` that extracts credentials from the request and authenticates against your user service. Then set the authentication in the SecurityContext.

46. **What libraries can you use to consume other REST services from a Spring Boot application?**

    `RestTemplate` (legacy) and `WebClient` (reactive) are common. Spring Cloud OpenFeign provides a declarative HTTP client with integrated load balancing.

47. **When would you choose reactive WebFlux over traditional Spring MVC?**

    WebFlux is suitable for highly concurrent, non-blocking applications requiring scalability with fewer threads. It uses Project Reactor types like `Mono` and `Flux`.

48. **How do you return a file for download from a REST endpoint?**

    Return a `ResponseEntity<Resource>` and set the `Content-Disposition` header to `attachment; filename=...`. Use `ByteArrayResource` or `InputStreamResource` for the file data.

49. **Explain the role of `@RestClientTest`.**

    `@RestClientTest` is used to test REST client components like `RestTemplate` or `WebClient`. It auto-configures the necessary beans and allows mocking of remote servers via `MockRestServiceServer`.

50. **Provide a simple sequence diagram for a REST call in Spring Boot.**

```
Client -> Controller -> Service -> Repository -> Database
Client <- Controller <- Service <- Repository <- Database
```

51. **How do you implement conditional requests using ETags?**

    Generate an ETag hash of the resource representation and return it in the `ETag` header. When the client sends `If-None-Match`, compare the value and return `304 Not Modified` if unchanged.

52. **What is content negotiation and how does Spring handle it?**

    Content negotiation selects the response format based on the `Accept` header, URL extension, or request parameter. `ContentNegotiationConfigurer` customizes how Spring chooses between JSON, XML, or other formats.

53. **Explain how to configure global exception handling for 404 errors.**

    Implement a `@ControllerAdvice` class with a method annotated `@ExceptionHandler(NoHandlerFoundException.class)` and return a custom error response with a 404 status.

54. **What is the difference between `@RequestParam` and `@ModelAttribute`?**

    `@RequestParam` binds a single query parameter or form field, while `@ModelAttribute` binds multiple form fields to a Java object. `@ModelAttribute` is commonly used in form-based web apps but can be used in APIs as well.

55. **How do you enable method-level security for REST APIs?**

    Add `@EnableMethodSecurity` (or `@EnableGlobalMethodSecurity` in older versions) and annotate service methods with `@PreAuthorize` or `@PostAuthorize` to enforce access control based on roles.

56. **Describe the purpose of `@RestController`'s `value` attribute.**

    The `value` attribute defines a base path for all endpoints in the controller, helping organize URIs under a common prefix.

57. **How do you use Spring Boot profiles to manage API configurations?**

    Define different property files like `application-dev.properties` or `application-prod.properties`. Activate profiles via the `spring.profiles.active` property or environment variable to switch configurations such as logging or datasource settings.

58. **What is the role of `@JsonInclude`?**

    `@JsonInclude` controls which properties are serialized. For example, `@JsonInclude(JsonInclude.Include.NON_NULL)` omits null values from the JSON output.

59. **Explain the difference between `HttpServletRequest` and `ServerHttpRequest`.**

    `HttpServletRequest` is the servlet-based request object, while `ServerHttpRequest` is part of Spring Web Reactive. Choose based on whether you use Spring MVC or WebFlux.

60. **How do you configure a custom media type?**

    Register a custom `MediaType` and add a converter that supports it. Use `produces` in your `@RequestMapping` to expose endpoints returning the new type.

61. **What is HAL and how does it relate to Spring HATEOAS?**

    HAL (Hypertext Application Language) is a JSON or XML standard for representing resources with links. Spring HATEOAS can serialize responses in HAL format out of the box.

62. **Describe the use of interceptors in Spring MVC.**

    Implement `HandlerInterceptor` to intercept incoming HTTP requests before they reach the controller. This is useful for logging, authentication, or modifying requests.

63. **How can you customize Jackson's ObjectMapper globally?**

    Define a `@Bean` of type `Jackson2ObjectMapperBuilder` or customize the `ObjectMapper` directly via `@Bean`. Spring Boot will use this mapper for all JSON serialization.

64. **Explain CORS preflight requests.**

    Browsers send an OPTIONS request with headers like `Access-Control-Request-Method` to check if the server allows a cross-origin request. Spring must respond with appropriate CORS headers to permit the actual request.

65. **How do you configure connection pooling for REST clients?**

    When using `RestTemplate`, supply a custom `HttpClient` from Apache HttpClient or OkHttp with a pooling connection manager to reuse connections efficiently.

66. **What is the benefit of using DTO mapping libraries like MapStruct?**

    MapStruct automatically generates code for mapping between entity classes and DTOs, reducing boilerplate and potential errors compared to manual setters.

67. **How can you delay initialization of a bean until it is needed?**

    Use `@Lazy` on the bean definition. For REST controllers, this might be applied to service dependencies if they should only initialize when first used.

68. **Explain the difference between synchronous and reactive REST clients.**

    `RestTemplate` executes requests synchronously, blocking until a response is received. `WebClient` can work asynchronously and supports reactive streams for non-blocking I/O.

69. **How do you handle file download progress in Spring Boot?**

    Implement a custom `StreamingResponseBody` that writes the file to the output stream and updates a progress listener that the client polls or receives via WebSocket.

70. **Provide an ASCII diagram of the layered architecture for a Spring REST service.**

```
[Client] -> [Controller] -> [Service] -> [Repository] -> [Database]
```

71. **How do you handle timeouts for outbound REST calls?**

    Configure timeouts on the HTTP client used by `RestTemplate` or `WebClient` to avoid hanging requests. For example, with Apache HttpClient you can set connection and socket timeouts.

72. **Explain how to return a custom error code when validation fails.**

    Customize `MethodArgumentNotValidException` handling in a `@RestControllerAdvice` and set a unique error code in the response body for client-side processing.

73. **What is server-sent events (SSE) and how does Spring Boot implement it?**

    SSE allows the server to push events to the browser over HTTP. Use `SseEmitter` in Spring MVC or `Flux` in WebFlux to stream events to the client.

74. **How do you manage API documentation for multiple versions?**

    Create separate OpenAPI groups or documentation endpoints for each version, so `/v1/api-docs` and `/v2/api-docs` show the appropriate endpoints.

75. **Describe how to implement global response wrapping.**

    Use `ResponseBodyAdvice` to intercept responses and wrap them in a standard envelope, such as `{ "data": ..., "status": ... }`.

76. **What is a correlation ID and why is it useful?**

    A correlation ID is a unique value added to each request to trace it across multiple services. Use a filter to generate or propagate the ID and include it in logs.

77. **How do you enforce request size limits?**

    Configure `spring.servlet.multipart.max-request-size` for file uploads or set server properties like `server.tomcat.max-http-form-post-size` to restrict payload sizes.

78. **Explain the role of `@ExceptionHandler` in a controller.**

    `@ExceptionHandler` on a controller method catches specific exceptions thrown by that controller and returns a custom response. It's useful for handling local errors without global advice.

79. **How do you return different HTTP responses for the same exception?**

    Provide multiple `@ExceptionHandler` methods for the same exception type with different conditions or check the request data to decide which status code to send.

80. **Describe how Spring Boot integrates with messaging systems in a REST context.**

    Use Spring AMQP or Spring Kafka to publish and consume messages. REST controllers may send a message to a queue as part of handling a request, decoupling asynchronous tasks from the immediate response.

81. **How can you ensure API endpoints are idempotent?**

    Design POST or PUT operations so that repeating the request has the same effect, often by including a unique request ID that is tracked server side.

82. **Explain the use of `@Scheduled` tasks in conjunction with REST APIs.**

    Scheduled tasks can perform periodic maintenance or data updates that the REST API exposes. For example, a scheduled job might refresh caches that endpoints use.

83. **What is the difference between 401 and 403 status codes?**

    401 Unauthorized indicates the user is not authenticated, while 403 Forbidden means the user is authenticated but does not have permission to access the resource.

84. **How do you serve API documentation as static content?**

    Place documentation in the `resources/static` directory or configure Spring MVC to serve HTML and JS assets under `/docs`. This can complement generated Swagger docs.

85. **Describe how to use a RequestBodyAdvice for custom deserialization logic.**

    Implement the `RequestBodyAdvice` interface to intercept and modify request bodies before they are converted to Java objects. This is useful for decryption or input transformation.

86. **How does Spring Boot support API version deprecation warnings?**

    Add custom headers such as `Deprecated: true` or log warnings when deprecated endpoints are called. Documentation should note planned removal dates.

87. **Explain parameterized type references in `RestTemplate`.**

    Use `ParameterizedTypeReference<T>` with `exchange` to receive responses containing parameterized generic types, like a `List<User>`.

88. **How do you enforce JSON schema validation on incoming payloads?**

    Integrate a library such as Everit JSON or networknt JSON Schema to validate the request body against a schema before processing.

89. **What is the benefit of using asynchronous logging for REST APIs?**

    Asynchronous logging frameworks (e.g., Logback AsyncAppender) reduce the overhead of disk or network I/O by decoupling log writing from the request thread.

90. **Provide an ASCII diagram of an authentication flow using JWT.**

```
[Client] --login--> [Auth Server] --token--> [Client] --JWT--> [API]
```

91. **How can you generate API clients automatically?**

    Use tools like OpenAPI Generator or Swagger Codegen to produce client libraries in various languages from your OpenAPI specification.

92. **Explain the role of `Filter` and `OncePerRequestFilter`.**

    `Filter` is a servlet specification concept for intercepting requests before they reach the servlet. `OncePerRequestFilter` ensures a filter executes only once per request, which is useful for authentication or logging.

93. **How do you compress responses in Spring Boot?**

    Enable `server.compression.enabled=true` in application properties. Spring Boot will compress responses using gzip when the client supports it via the `Accept-Encoding` header.

94. **Describe how you handle version negotiation when clients omit the version header.**

    Provide a default version or reject the request with a clear error message. Document the default behavior to avoid confusion for API consumers.

95. **What metrics can you gather with Spring Boot Actuator for REST APIs?**

    Actuator exposes metrics such as request counts, response times, and HTTP status breakdowns through endpoints like `/actuator/metrics/http.server.requests`.

96. **How can you implement request/response logging selectively?**

    Use a custom `HandlerInterceptor` or filter that logs data only for certain paths or based on configuration properties.

97. **Describe an approach for graceful shutdown of a Spring Boot API.**

    Enable graceful shutdown with `server.shutdown=graceful` and configure the `spring.lifecycle.timeout-per-shutdown-phase` property to allow existing requests to finish before closing.

98. **What is API throttling and how do you implement it in Spring?**

    API throttling restricts the rate of requests allowed for each client. Spring Cloud Gateway or filters with an in-memory store like Bucket4j can enforce quotas per API key.

99. **How do you return a CSV file from a Spring Boot endpoint?**

    Set the `Content-Type` to `text/csv`, write the CSV data to the response output stream, and set `Content-Disposition` for download.

100. **Provide an ASCII diagram for a microservice calling another service via REST.**

```
[Client] -> [Service A] -> [Service B] -> [Database]
[Client] <- [Service A] <- [Service B] <- [Database]
```

