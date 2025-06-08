# Spring Boot Interview Questions

This document contains one hundred Spring Boot interview questions answered in the STAR (Situation, Task, Action, Result) format. Key topics include configuration, web development, data access, security, and monitoring. Diagrams are used where they help illustrate a concept.

## Questions and Answers

### 1. What is Spring Boot and how does it differ from the Spring Framework?

**Situation**: Developers using the Spring Framework had to write extensive configuration and deploy to an external server.

**Task**: Provide a way to create Spring applications quickly with minimal setup.

**Action**: Spring Boot introduces auto-configuration, embedded servers, and starter dependencies so that a basic project can run with almost no configuration.

**Result**: Projects start faster and require less boilerplate, letting teams focus on business logic.

```
[Developer]--uses-->[Spring Boot]--auto-config-->[Standalone App]
               \
                ->[Traditional Spring]--manual setup-->[WAR deployment]
```

### 2. How do you create a Spring Boot application?

**Situation**: You need a new project with REST endpoints, data access, and testing libraries.

**Task**: Initialize the project with the correct dependencies and build configuration.

**Action**: Use Spring Initializr or the Spring Boot CLI to generate a Maven or Gradle project, then create a main class annotated with `@SpringBootApplication` and run `SpringApplication.run`.

**Result**: A minimal application with embedded Tomcat starts and serves HTTP requests immediately.

### 3. What is auto-configuration in Spring Boot?

**Situation**: Writing configuration for each library (e.g., DataSource, MessageConverters) can be repetitive.

**Task**: Detect which dependencies are on the classpath and create sensible default beans.

**Action**: Auto-configuration classes use `@Conditional` annotations to activate only when certain libraries or properties are present.

**Result**: Most of the plumbing beans (DataSource, MessageConverters, etc.) are created automatically, reducing developer effort.

### 4. How does Spring Boot embed web servers?

**Situation**: Deploying to an external servlet container adds operational overhead.

**Task**: Run a Spring web application as a standalone executable.

**Action**: Spring Boot includes server libraries (Tomcat, Jetty, Undertow) as dependencies. When `SpringApplication.run` executes, it starts the embedded server automatically.

**Result**: The application runs from the command line with `java -jar`, no external server setup is needed.

```
[Spring Boot App] --starts--> [Embedded Tomcat]
```

### 5. Explain the purpose of `application.properties` and `application.yml`.

**Situation**: Applications often have environment-specific settings like ports or database credentials.

**Task**: Externalize configuration to easily switch between environments.

**Action**: Spring Boot reads properties from `application.properties` or YAML format `application.yml`. Values can be overridden per environment using profile-specific files.

**Result**: The same application jar can run in multiple environments simply by supplying different property files.

### 6. What does the `@SpringBootApplication` annotation do?

**Situation**: Several annotations are typically required to enable component scanning and auto-configuration.

**Task**: Provide a single annotation to bootstrap a Spring Boot application.

**Action**: `@SpringBootApplication` combines `@Configuration`, `@ComponentScan`, and `@EnableAutoConfiguration`.

**Result**: The main application class is concise and automatically triggers scanning of components.

### 7. How can you customize the port of the embedded server?

**Situation**: By default Spring Boot starts Tomcat on port 8080.

**Task**: Change the HTTP port to avoid conflicts or comply with environment requirements.

**Action**: Set `server.port` in `application.properties` or pass `--server.port=8081` as a command-line argument.

**Result**: The embedded server listens on the configured port without code changes.

### 8. How do you connect a Spring Boot application to a database?

**Situation**: The application needs to persist data in a relational database.

**Task**: Configure a DataSource and set up JPA repositories.

**Action**: Include `spring-boot-starter-data-jpa` and set properties like `spring.datasource.url`, `username`, and `password`. Spring Boot auto-configures the DataSource and JPA provider.

**Result**: Repository beans are ready to use, and the application can perform CRUD operations on the database.

### 9. What is the `@RestController` annotation used for?

**Situation**: Exposing JSON or XML endpoints typically requires a controller and manual serialization.

**Task**: Simplify REST controller development.

**Action**: Annotate a class with `@RestController`; methods annotated with `@RequestMapping` automatically serialize return values to JSON/XML using Jackson or other converters.

**Result**: Endpoints return data directly without writing `ResponseBody` logic.

### 10. Explain `spring-boot-starter` dependencies.

**Situation**: Managing dozens of library versions manually can cause conflicts.

**Task**: Provide a curated dependency set for common application types.

**Action**: Spring Boot starters (e.g., `spring-boot-starter-web`) bundle compatible versions of libraries such as Spring MVC and Jackson.

**Result**: Build files stay short and consistent across projects.

### 11. What are the differences between `@Component`, `@Service`, and `@Repository`?

**Situation**: You have classes representing generic components, business services, and data access layers.

**Task**: Mark them for component scanning while conveying intent.

**Action**: Use `@Component` for generic beans, `@Service` for service-layer classes, and `@Repository` for persistence classes (which also enables exception translation).

**Result**: The container registers each bean appropriately and developers quickly recognize their roles.

### 12. How do you define a custom startup banner?

**Situation**: Organizations often display ASCII art or environment information on startup.

**Task**: Customize the console banner.

**Action**: Create a `banner.txt` in the `resources` folder with ASCII content and property placeholders (e.g., `${spring-boot.version}`).

**Result**: The banner appears when the application starts, giving a personalized launch message.

### 13. What is the `CommandLineRunner` interface for?

**Situation**: Some initialization logic must run after the context is ready but before serving requests.

**Task**: Execute code once when the application starts.

**Action**: Implement `CommandLineRunner` (or `ApplicationRunner`) and override `run(String... args)`.

**Result**: Spring Boot executes the runner after the application context is loaded.

### 14. Explain the difference between `@Value` and `@ConfigurationProperties`.

**Situation**: External configuration needs to be injected into beans.

**Task**: Bind configuration values in a type-safe manner.

**Action**: Use `@Value` for individual values, or `@ConfigurationProperties` on a class to map entire groups of related properties.

**Result**: Beans receive properly typed configuration with minimal boilerplate.

### 15. How do you create a REST client with `RestTemplate`?

**Situation**: The application must call another HTTP service.

**Task**: Issue GET, POST, and other HTTP requests programmatically.

**Action**: Inject `RestTemplate` or build it using `RestTemplateBuilder`, then call methods like `getForObject` or `postForEntity`.

**Result**: External services are called with ease, and responses are mapped to Java objects.

```
[App] --RestTemplate--> [Remote Service]
```

### 16. What is actuator and why is it useful?

**Situation**: Operations teams need insight into application health and metrics.

**Task**: Expose endpoints for monitoring and managing the application.

**Action**: Include `spring-boot-starter-actuator` to add endpoints such as `/actuator/health`, `/actuator/metrics`, and more.

**Result**: Administrators and monitoring tools can check system health and perform management tasks.

### 17. How do you create a custom endpoint in actuator?

**Situation**: Default endpoints do not expose domain-specific metrics.

**Task**: Provide additional management data.

**Action**: Implement `@Endpoint` or `@RestControllerEndpoint` and define methods annotated with `@ReadOperation`, `@WriteOperation`, etc.

**Result**: Custom metrics or health data appear under `/actuator/myendpoint`.

### 18. What is Spring Boot DevTools?

**Situation**: During development, manual restarts slow down the feedback loop.

**Task**: Automatically reload changes on the fly.

**Action**: Add `spring-boot-devtools`, which monitors the classpath and restarts the application when files change.

**Result**: Development becomes faster as updates are reloaded instantly.

### 19. How do you implement internationalization?

**Situation**: A web application must display messages in multiple languages.

**Task**: Serve locale-specific messages based on user preferences.

**Action**: Create message bundles (`messages_en.properties`, `messages_fr.properties`) and configure `LocaleResolver`. Use `@RequestHeader("Accept-Language")` or other mechanisms to detect locale.

**Result**: The application responds with translated text according to the user's locale.

### 20. Explain how to secure a Spring Boot application with Spring Security.

**Situation**: Sensitive endpoints must be protected from unauthorized access.

**Task**: Implement authentication and authorization.

**Action**: Add `spring-boot-starter-security`, define a `SecurityFilterChain` bean to configure login, password encoding, and access rules.

**Result**: Only authenticated users can access restricted endpoints, improving security.

```
[Client] -> [SecurityFilterChain] -> [Controller]
```

### 21. What is the purpose of `@EnableConfigurationProperties`?

**Situation**: You want to bind external properties to a configuration class.

**Task**: Register that class as a bean so the properties are loaded automatically.

**Action**: Annotate a configuration class with `@EnableConfigurationProperties` or specify the class name within a `@Configuration`.

**Result**: Spring Boot populates the fields from property files at startup.

### 22. How does caching work in Spring Boot?

**Situation**: Repeated method calls that compute data should be cached.

**Task**: Reduce repeated calculations or database calls.

**Action**: Add `spring-boot-starter-cache` and enable caching with `@EnableCaching`. Annotate methods with `@Cacheable` or `@CacheEvict`.

**Result**: Method results are stored in cache stores like Ehcache or Redis, improving performance.

### 23. What is the role of Spring Data JPA?

**Situation**: Writing boilerplate repository implementations is time-consuming.

**Task**: Provide CRUD and query capabilities with minimal code.

**Action**: Extend `JpaRepository` or other Spring Data interfaces. Method names generate queries, and custom queries use `@Query`.

**Result**: Data access code is concise and consistent.

### 24. How do you handle exceptions globally?

**Situation**: Uncaught exceptions produce default error pages that leak information.

**Task**: Return custom error messages for API clients or web users.

**Action**: Use `@ControllerAdvice` with `@ExceptionHandler` methods. Optionally implement `ErrorController` for a global error page.

**Result**: The application returns consistent, safe error responses.

### 25. Explain dependency injection in Spring Boot.

**Situation**: Components depend on one another and should be decoupled.

**Task**: Manage object creation and wiring automatically.

**Action**: Annotate classes with `@Component`, `@Service`, etc., and inject them into other beans via constructor injection or `@Autowired`.

**Result**: The container manages dependencies, promoting modular and testable code.

```
[Controller] --> [Service] --> [Repository]
```

### 26. How do you log in Spring Boot?

**Situation**: Applications need consistent logging across all layers.

**Task**: Provide logging configuration and integration with frameworks like Logback or Log4j2.

**Action**: Use the default Logback setup or add custom configuration via `logback-spring.xml`. Inject `Logger` instances with `LoggerFactory.getLogger`.

**Result**: Log files capture key events, enabling troubleshooting and monitoring.

### 27. What is Spring Profiles?

**Situation**: Different environments (development, staging, production) need different bean configurations.

**Task**: Enable or disable beans based on the active profile.

**Action**: Annotate configuration classes or bean methods with `@Profile("dev")`, `@Profile("prod")`, etc., and set `spring.profiles.active` at runtime.

**Result**: The application loads only the beans relevant to the current environment.

### 28. How do you perform validation in Spring Boot?

**Situation**: Incoming requests should be checked for valid data.

**Task**: Validate fields automatically.

**Action**: Include `spring-boot-starter-validation` and add annotations like `@NotNull`, `@Size`, or `@Email` on DTOs. Use `@Valid` in controller methods.

**Result**: Invalid data triggers `MethodArgumentNotValidException` and returns a meaningful response.

### 29. Explain the role of `application.properties` profiles like `application-dev.properties`.

**Situation**: Each environment needs unique settings while sharing the same jar.

**Task**: Override default properties for a specific profile.

**Action**: Create files such as `application-dev.properties`. Activate the profile with `--spring.profiles.active=dev` or via environment variables.

**Result**: The application picks up environment-specific configuration without code changes.

### 30. What is the difference between `@ComponentScan` and `@SpringBootApplication`?

**Situation**: Component scanning is required to discover beans automatically.

**Task**: Understand how scanning configuration is initiated.

**Action**: `@ComponentScan` explicitly declares packages to scan, whereas `@SpringBootApplication` implicitly triggers scanning of the package where the main class resides and its subpackages.

**Result**: Properly placed classes are detected automatically, or custom packages can be specified.

### 31. How do you perform testing with Spring Boot?

**Situation**: Components should be verified with unit and integration tests.

**Task**: Provide a simple setup for tests that require the Spring context.

**Action**: Use `@SpringBootTest` for full context tests or `@WebMvcTest` for MVC slice testing. Include `spring-boot-starter-test` which brings JUnit, AssertJ, and Mockito.

**Result**: Tests run with minimal configuration and use embedded servers or mocks as needed.

### 32. Explain the role of Spring Boot starters in dependency management.

**Situation**: Projects rely on multiple third-party libraries with varying versions.

**Task**: Provide a curated set of dependencies that work well together.

**Action**: Spring Boot starters declare compatible versions and transitive dependencies. You add one starter to get a full stack of libraries.

**Result**: Less version conflict, easier updates, and streamlined build files.

### 33. What is actuator health endpoint used for?

**Situation**: Operations teams must check if the service is alive and ready.

**Task**: Provide a standardized endpoint for health checks.

**Action**: Access `/actuator/health`; you can extend it with custom indicators.

**Result**: Load balancers and monitoring systems know when to route traffic to or away from the instance.

### 34. How do you override a bean definition provided by auto-configuration?

**Situation**: Auto-configuration might not fit every scenario.

**Task**: Replace the default bean with a custom implementation.

**Action**: Define a bean of the same type in your configuration. Spring Boot favors user-defined beans over auto-configured ones. Alternatively use `spring.main.allow-bean-definition-overriding=true` for duplicates.

**Result**: Your custom bean is used while still benefiting from other auto-configured components.

### 35. What is the role of `ApplicationContext`?

**Situation**: Beans need to be managed with lifecycle callbacks and dependency injection.

**Task**: Provide a container that holds bean definitions and resolves dependencies.

**Action**: Spring Boot creates an `ApplicationContext` which loads configuration, instantiates beans, and wires dependencies.

**Result**: The application operates with fully managed beans.

### 36. How can you override default auto-configuration selectively?

**Situation**: You might want to keep most auto-configurations but disable one that conflicts with custom code.

**Task**: Exclude certain auto-configuration classes.

**Action**: Use `@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})` or set `spring.autoconfigure.exclude` in properties.

**Result**: Only specified auto-configurations are skipped while others remain active.

### 37. What is the purpose of `spring-boot-devtools`?

**Situation**: Frequent code changes require restarts for them to take effect.

**Task**: Speed up development cycles.

**Action**: Adding `spring-boot-devtools` enables automatic restarts and live reload when classpath files change.

**Result**: Developers see changes reflected almost instantly, boosting productivity.

### 38. How do you monitor and manage a Spring Boot application in production?

**Situation**: You need insight into metrics and configuration of a running service.

**Task**: Provide endpoints for health, metrics, and environment details.

**Action**: The actuator module exposes endpoints and can integrate with tools like Prometheus or Spring Boot Admin.

**Result**: System administrators can track performance and troubleshoot issues quickly.

### 39. Explain the difference between `@ResponseBody` and `ResponseEntity`.

**Situation**: A controller returns data and needs control over headers or status codes.

**Task**: Understand when to use direct serialization vs. a full HTTP response object.

**Action**: `@ResponseBody` serializes the return value, whereas `ResponseEntity` lets you specify headers and status along with the body.

**Result**: `ResponseEntity` offers fine-grained control for custom responses, while `@ResponseBody` is simpler for default behavior.

### 40. How do you handle 404 errors with a custom JSON response?

**Situation**: REST clients expect a JSON payload even when a resource is not found.

**Task**: Return a JSON structure instead of the default whitelabel error page.

**Action**: Configure `spring.mvc.throw-exception-if-no-handler-found=true` and create an `@ControllerAdvice` that handles `NoHandlerFoundException` to produce a JSON response.

**Result**: Clients receive a consistent JSON error with status code 404.

### 41. What is a starter parent in Maven and why is it useful?

**Situation**: Managing plugin versions and Maven defaults is tedious.

**Task**: Provide a base POM with sensible defaults.

**Action**: Use `spring-boot-starter-parent` as the parent in your Maven POM. It defines plugin versions and default configuration for common plugins.

**Result**: Your build inherits stable plugin versions and requires fewer explicit definitions.

### 42. How do you access command-line arguments in a Spring Boot application?

**Situation**: A script launches the application with parameters you need to read.

**Task**: Obtain startup arguments programmatically.

**Action**: Implement `ApplicationRunner` or `CommandLineRunner` and inject `ApplicationArguments` to read the values.

**Result**: The application processes command-line options as part of its initialization.

### 43. What is the default embedded container in Spring Boot? Can you change it?

**Situation**: Most Spring Boot web applications run with Tomcat by default.

**Task**: Understand how to use a different server like Jetty or Undertow.

**Action**: Exclude `spring-boot-starter-tomcat` and include `spring-boot-starter-jetty` or `spring-boot-starter-undertow`.

**Result**: The application runs on the chosen server implementation.

### 44. How do you create a custom `ErrorController`?

**Situation**: The default error page doesn't match your API format.

**Task**: Customize the response for errors globally.

**Action**: Implement `ErrorController` or extend `AbstractErrorController` and map `/error` to your handler that returns custom JSON or HTML.

**Result**: All unhandled errors return your standardized message.

### 45. How do you schedule tasks in Spring Boot?

**Situation**: Certain jobs must run on a schedule (e.g., cleanup tasks).

**Task**: Execute a method at fixed intervals or cron expressions.

**Action**: Use `@EnableScheduling` and annotate a method with `@Scheduled` specifying `fixedRate` or `cron`.

**Result**: Spring calls the method automatically based on the schedule.

### 46. What is the difference between `@LoadBalanced` RestTemplate and `@FeignClient`?

**Situation**: A microservice must call other services with client-side load balancing.

**Task**: Choose between using a generic HTTP client or a declarative one.

**Action**: `@LoadBalanced RestTemplate` resolves service names using Ribbon or Spring Cloud LoadBalancer. `@FeignClient` defines an interface where method signatures map to HTTP endpoints.

**Result**: Feign is more declarative; RestTemplate requires manual request creation but may offer finer control.

### 47. How do you implement REST versioning?

**Situation**: Your API evolves over time while maintaining backward compatibility.

**Task**: Provide versioned endpoints or responses.

**Action**: Choose an approach: URL versioning (`/v1/items`), request parameters, headers (`Accept-Version`), or content negotiation.

**Result**: Clients can migrate gradually while new versions are added.

### 48. What does the Actuator `/shutdown` endpoint do?

**Situation**: You may need to stop the application remotely in a controlled manner.

**Task**: Provide a management endpoint that triggers a graceful shutdown.

**Action**: Enable `management.endpoint.shutdown.enabled=true`; issue a POST request to `/actuator/shutdown`.

**Result**: The application context closes and the JVM exits cleanly.

### 49. Explain the difference between `EntityManager` and Spring Data repositories.

**Situation**: Direct JPA access vs. repository abstraction.

**Task**: Choose a data access approach.

**Action**: `EntityManager` allows fine-grained control over JPA operations. Spring Data repositories extend it with ready-made CRUD methods and query derivation from method names.

**Result**: Spring Data reduces boilerplate, while `EntityManager` offers flexibility when needed.

### 50. How do you disable a specific auto-configuration class?

**Situation**: An auto-configured bean conflicts with your custom configuration.

**Task**: Exclude that configuration from running.

**Action**: Use `@SpringBootApplication(exclude = {ClassName.class})` or set the property `spring.autoconfigure.exclude`. Multiple classes can be excluded as an array.

**Result**: The unwanted auto-configuration no longer creates beans.

```
[AutoConfig] --exclude--> [YourConfig]
```

### 51. How can you add custom startup logic before the application fully starts?

**Situation**: You must perform a check or initialization before accepting requests.

**Task**: Run code immediately after the context is created.

**Action**: Implement `ApplicationRunner` or `CommandLineRunner`. For more control, use `SmartLifecycle` or `ApplicationListener` on `ApplicationPreparedEvent`.

**Result**: Your custom logic executes during startup.

### 52. What is the use of `spring.main.allow-bean-definition-overriding`?

**Situation**: Two configuration classes define beans with the same name.

**Task**: Decide whether to allow overriding.

**Action**: Set `spring.main.allow-bean-definition-overriding=true` to permit the later bean definition to replace the existing one.

**Result**: The application uses the last declared bean, enabling customization with caution.

### 53. How do you integrate Swagger/OpenAPI with Spring Boot?

**Situation**: Clients require documentation for each REST endpoint.

**Task**: Generate an interactive API specification.

**Action**: Include `springdoc-openapi-ui`, annotate controllers with OpenAPI annotations, and access `/swagger-ui.html`.

**Result**: An automatically generated UI displays all endpoints and models.

### 54. What does `@RestControllerAdvice` do?

**Situation**: You have multiple controllers and want to handle exceptions globally.

**Task**: Provide cross-cutting exception handling.

**Action**: Annotate a class with `@RestControllerAdvice` and define `@ExceptionHandler` methods.

**Result**: Errors from any controller are processed consistently.

### 55. How do you add custom properties to Spring Boot's environment?

**Situation**: Your application requires custom settings not covered by standard properties.

**Task**: Expose them in a type-safe manner.

**Action**: Create a class with `@ConfigurationProperties(prefix = "my")` and define the fields. Register the class with `@EnableConfigurationProperties`.

**Result**: The custom properties become available as a bean and can be injected anywhere.

### 56. How do you implement file upload with Spring Boot?

**Situation**: Users need to upload files to the server.

**Task**: Accept multipart file requests and store files.

**Action**: Enable `spring.servlet.multipart.enabled=true` (default), then in a controller accept `MultipartFile` arguments.

**Result**: Files are received and processed or stored to disk/cloud.

### 57. What is a `Filter` and how do you register one in Spring Boot?

**Situation**: You need to intercept requests before they reach a controller (e.g., for logging).

**Task**: Configure a servlet filter.

**Action**: Implement `javax.servlet.Filter`, register it as a `@Bean`, or use `@WebFilter` and `@ServletComponentScan`.

**Result**: All matching requests pass through the filter before continuing.

### 58. Explain what happens when you call `SpringApplication.run`.

**Situation**: Starting the application triggers several processes.

**Task**: Understand the bootstrapping sequence.

**Action**: `SpringApplication` creates an `ApplicationContext`, loads beans, triggers `ApplicationRunner`/`CommandLineRunner`, and starts the embedded server if applicable.

**Result**: The application is ready to receive requests or execute scheduled tasks.

### 59. How do you enforce HTTPS in Spring Boot?

**Situation**: Communications must be secured with SSL/TLS.

**Task**: Redirect HTTP to HTTPS and configure certificates.

**Action**: Set up an SSL keystore and configure `server.ssl.*` properties. Optionally, create a WebSecurity configuration that redirects HTTP to HTTPS.

**Result**: Clients connect over secure channels, protecting data in transit.

### 60. Describe how actuator metrics can be exported to Prometheus.

**Situation**: You use Prometheus for monitoring your systems.

**Task**: Expose metrics in a format Prometheus can scrape.

**Action**: Add `micrometer-registry-prometheus` and access `/actuator/prometheus`. Configure Prometheus to scrape that endpoint.

**Result**: Spring Boot metrics appear in Prometheus dashboards.

### 61. What is the difference between `@MockBean` and `@Mock` in tests?

**Situation**: You want to replace real beans with mocks in your test context.

**Task**: Decide how to mock dependencies.

**Action**: `@Mock` creates a Mockito mock without Spring; `@MockBean` registers the mock with the ApplicationContext, replacing the bean of the same type.

**Result**: `@MockBean` is ideal for integration tests where Spring wiring is involved.

### 62. Explain double-checked locking in a Spring-managed singleton.

**Situation**: Some components lazily create an expensive object.

**Task**: Ensure thread-safe initialization without performance loss.

**Action**: Use a volatile field and check for null before and after entering a synchronized block. Spring-managed singletons usually avoid this, but it may appear in your own classes.

**Result**: The object is created once in a thread-safe manner.

### 63. How can you chain asynchronous tasks using `CompletableFuture`?

**Situation**: Some operations should run asynchronously and then process results when all complete.

**Task**: Compose asynchronous operations.

**Action**: Use `CompletableFuture.supplyAsync` and chain methods like `thenApply`, `thenCombine`, or `allOf`.

**Result**: Non-blocking asynchronous workflows improve performance for I/O heavy tasks.

### 64. What is the `spring.factories` file used for?

**Situation**: Auto-configuration classes must be discovered by Spring Boot.

**Task**: Register auto-configuration or other components using the `spring.factories` mechanism.

**Action**: Create `META-INF/spring.factories` with entries mapping configuration class names to enable them automatically when the jar is on the classpath.

**Result**: Custom auto-configuration modules plug seamlessly into Spring Boot.

### 65. How do you read configuration using the `Properties` class?

**Situation**: Legacy code relies on the `Properties` API.

**Task**: Load key-value pairs from a file.

**Action**: Use `Properties.load(InputStream)` to read from `application.properties` or another file. Optionally integrate with Spring's `PropertiesFactoryBean`.

**Result**: The application can access properties using the legacy `Properties` API when needed.

### 66. Describe the memory areas in the JVM relevant to Spring Boot applications.

**Situation**: Tuning JVM memory usage may be important for performance.

**Task**: Understand where objects and class metadata reside.

**Action**: The JVM uses a heap for object allocations, a stack for method frames, and metaspace for class metadata. Garbage collectors reclaim heap memory.

**Result**: Insight into these areas helps when tuning the JVM for large Spring Boot deployments.

### 67. How do you create a custom class loader for loading plugins in Spring Boot?

**Situation**: You want to load modules or plugins at runtime from external locations.

**Task**: Dynamically load classes.

**Action**: Extend `ClassLoader` or `URLClassLoader`, override `findClass`, and manage classpaths. Integration with Spring's `ParentLastURLClassLoader` may be necessary.

**Result**: The application can discover and load new functionality without restarting.

### 68. What is the difference between `@Qualifier` and `@Primary`?

**Situation**: Multiple beans of the same type exist.

**Task**: Choose which bean should be injected.

**Action**: Mark a bean as `@Primary` to use it by default, or use `@Qualifier("beanName")` at injection points to pick a specific bean.

**Result**: Dependencies resolve correctly even when multiple beans share the same type.

### 69. Explain generational garbage collection and how it affects Spring Boot.

**Situation**: GC pauses can impact application latency.

**Task**: Understand object lifecycles and tuning options.

**Action**: The JVM divides the heap into young and old generations. Spring Beans typically live long in the old generation. Tuning the young generation size or using G1/other collectors can reduce pause times.

**Result**: Proper GC tuning improves overall responsiveness of the application.

### 70. How do you implement custom HealthIndicators?

**Situation**: You need to monitor an external system such as a remote API.

**Task**: Add a custom check to the actuator health endpoint.

**Action**: Implement `HealthIndicator` and override `health()` to return `Health.up()` or `Health.down()` based on your logic.

**Result**: `/actuator/health` includes the additional status information.

```
[HealthEndpoint]
   |-- diskSpace
   |-- db
   |-- customIndicator
```

### 71. What is `ThreadLocal` and when is it useful in Spring applications?

**Situation**: Data specific to one thread (e.g., request context) should not be shared.

**Task**: Store and access data bound to the current thread.

**Action**: Use `ThreadLocal<T>` to hold data per thread. In web apps, frameworks like Spring MVC may use `ThreadLocal` to store request-scoped data.

**Result**: Each thread has its own copy, preventing data clashes in concurrent execution.

### 72. How can you expose a SOAP web service using Spring Boot?

**Situation**: Legacy systems require SOAP-based communication.

**Task**: Provide a SOAP endpoint.

**Action**: Include `spring-boot-starter-web-services`, define an endpoint with `@Endpoint` and `@PayloadRoot`, and use JAXB or other marshallers.

**Result**: The application serves SOAP requests alongside REST endpoints if needed.

### 73. Explain method security with `@PreAuthorize`.

**Situation**: Certain service methods should only be called by authorized users.

**Task**: Add pre-invocation authorization checks.

**Action**: Enable global method security with `@EnableMethodSecurity` (or `@EnableGlobalMethodSecurity`) and annotate methods with `@PreAuthorize("hasRole('ADMIN')")`.

**Result**: Access is denied before entering the method if the expression evaluates to false.

### 74. How do you refresh bean properties without restarting the application?

**Situation**: Config values may change at runtime (e.g., from a config server).

**Task**: Reload properties and update beans.

**Action**: Use Spring Cloud Context's `RefreshScope` and invoke `/actuator/refresh` or use `ContextRefresher` to reload properties.

**Result**: Beans annotated with `@RefreshScope` pick up new property values dynamically.

### 75. Describe a typical microservice architecture using Spring Boot.

**Situation**: Large applications are split into smaller services for scalability and autonomy.

**Task**: Outline how services communicate and are managed.

**Action**: Each microservice is built with Spring Boot, exposing REST or messaging endpoints. Discovery servers (Eureka), API gateways, and centralized configuration can be added.

**Result**: Teams deploy independently and scale services separately.

```
[Client] -> [Gateway] -> [Service A]
                        [Service B]
                        [Service C]
```

### 76. How do you implement custom security filters?

**Situation**: You need to add additional authentication steps.

**Task**: Intercept requests before they hit secured endpoints.

**Action**: Create a class extending `OncePerRequestFilter`, override `doFilterInternal`, and add it to the `SecurityFilterChain`.

**Result**: The filter executes for each request, enforcing custom security rules.

### 77. What is the difference between `WebMvcConfigurer` and `WebFluxConfigurer`?

**Situation**: Spring Boot supports both Servlet-based MVC and reactive WebFlux.

**Task**: Choose the correct configuration interface for customizing MVC/WebFlux settings.

**Action**: Implement `WebMvcConfigurer` for blocking MVC applications or `WebFluxConfigurer` for reactive ones. Methods allow customizing interceptors, message converters, etc.

**Result**: The correct configuration interface tailors your web stack as needed.

### 78. How do you handle transactions in Spring Boot?

**Situation**: Database operations must be atomic and rollback on failure.

**Task**: Manage transactions declaratively.

**Action**: Use `@Transactional` on service methods or classes. Configure a transaction manager (e.g., `JpaTransactionManager`).

**Result**: Spring manages commits and rollbacks automatically.

### 79. How can you serve static content in Spring Boot?

**Situation**: Your application must deliver static HTML, JavaScript, or CSS files.

**Task**: Map these resources from the classpath or file system.

**Action**: Place files under `src/main/resources/static` or `public`. Spring Boot automatically serves them under the same path.

**Result**: Browsers can request static files without additional configuration.

### 80. What is the purpose of `WebClient` in Spring WebFlux?

**Situation**: You need a non-blocking HTTP client for reactive applications.

**Task**: Perform asynchronous HTTP requests.

**Action**: Use `WebClient.builder()` to create an instance and call methods like `get()`, `post()` returning `Mono` or `Flux`.

**Result**: HTTP calls can be composed with other reactive streams without blocking threads.

### 81. Explain the difference between `Mono` and `Flux`.

**Situation**: Reactive programming introduces new abstractions for asynchronous values.

**Task**: Understand when to use each type.

**Action**: `Mono` represents zero or one element; `Flux` represents a stream of multiple elements.

**Result**: Reactive APIs return appropriate types depending on how many items they may emit.

### 82. How do you configure a DataSource for connection pooling?

**Situation**: High-traffic applications require efficient database connections.

**Task**: Use a pool to reuse connections.

**Action**: Spring Boot defaults to HikariCP. Configure pool settings via properties like `spring.datasource.hikari.maximum-pool-size`.

**Result**: Database connections are reused efficiently, reducing overhead.

### 83. What is the difference between `@Bean` and `@Component`?

**Situation**: Beans can be defined in two ways.

**Task**: Understand when to use each.

**Action**: `@Component` marks a class for component scanning, while `@Bean` declares a bean from within a `@Configuration` class and allows customizing its creation.

**Result**: Flexibility to define beans via scanning or explicit factory methods.

### 84. How do you create a custom annotation for a composed stereotype?

**Situation**: You repeatedly combine annotations like `@Service` and `@Transactional`.

**Task**: Bundle them into a single reusable annotation.

**Action**: Declare a new annotation and annotate it with `@Service`, `@Transactional`, and any other meta-annotations.

**Result**: Code is cleaner and more expressive with custom composed annotations.

### 85. What is `@ConditionalOnProperty` used for?

**Situation**: You want to enable or disable a bean based on a configuration property.

**Task**: Conditionally create beans.

**Action**: Annotate a configuration class or method with `@ConditionalOnProperty(prefix="feature", name="enabled", havingValue="true")`.

**Result**: Beans are only created when the condition matches, allowing feature toggles.

### 86. How do you configure a global CORS filter?

**Situation**: Your REST API needs to be accessed from web clients hosted on different domains.

**Task**: Permit cross-origin requests.

**Action**: Define a `CorsConfiguration` bean and register a `CorsFilter` or override `addCorsMappings` in a `WebMvcConfigurer`.

**Result**: Browsers are allowed to call your API from permitted origins.

### 87. What are the steps to package a Spring Boot application as a Docker container?

**Situation**: You want to deploy the application with all its dependencies in a portable way.

**Task**: Create a Docker image.

**Action**: Write a `Dockerfile` that uses an OpenJDK base image, copies the jar, and defines the `ENTRYPOINT` to run it. Build with `docker build` and run with `docker run`.

**Result**: The application can be deployed consistently across environments.

### 88. How does Spring Boot support testing with embedded databases?

**Situation**: Integration tests should not rely on an external database server.

**Task**: Use an in-memory database for tests.

**Action**: Add `spring-boot-starter-test` which includes H2 or use `@DataJpaTest` to automatically configure an embedded database.

**Result**: Tests run quickly and independently of external resources.

### 89. What is dynamic method dispatch in Spring Boot beans?

**Situation**: A bean may implement an interface and be referenced by its interface type.

**Task**: Understand how method calls are resolved at runtime.

**Action**: Java selects the implementation based on the actual object type (polymorphism). Spring AOP proxies also rely on this mechanism to intercept method calls.

**Result**: Overridden methods execute according to the runtime type, enabling aspects and proxies to function.

### 90. Explain the difference between `System.out` and a logging framework.

**Situation**: Printing to the console is straightforward but inflexible.

**Task**: Provide configurable logging levels and output destinations.

**Action**: Use a logging framework (Logback, Log4j2) configured by Spring Boot. Replace `System.out.println` with a `Logger` to log messages at various levels.

**Result**: Logs can be filtered, rotated, and sent to monitoring systems.

### 91. What is `spring-boot-starter-validation` and which annotations does it support?

**Situation**: User input needs to be validated declaratively.

**Task**: Provide validation annotations.

**Action**: The starter pulls in Hibernate Validator, enabling annotations like `@NotNull`, `@Size`, `@Email`, and custom validators via `@Constraint`.

**Result**: Annotated DTOs are validated automatically when used with `@Valid`.

### 92. How do you create custom Health Indicators for Actuator?

**Situation**: Monitoring requires checks beyond default indicators.

**Task**: Add new checks to the health endpoint.

**Action**: Implement `HealthIndicator` and register it as a bean. Build a `Health` object with details such as latency or connectivity.

**Result**: `/actuator/health` exposes extra health information.

### 93. How do you prevent SQL injection in Spring Data repositories?

**Situation**: Concatenating user input into queries is dangerous.

**Task**: Ensure queries are parameterized.

**Action**: Use method parameters or `@Param` bindings in repository methods. Spring Data automatically escapes values and uses prepared statements.

**Result**: Queries are executed safely with user input.

### 94. Explain property binding from YAML lists.

**Situation**: Configuration includes lists of values (e.g., server hosts).

**Task**: Map them to a collection.

**Action**: YAML entries under a property map to `List<String>` or other types using `@ConfigurationProperties`.

**Result**: Beans can access the list as a simple `List` or array.

### 95. How can you reload property files without restarting the application?

**Situation**: Configuration may change while the application is running.

**Task**: Reload properties dynamically.

**Action**: Use Spring Cloud Config or a custom watcher that calls `ContextRefresher` to refresh the environment.

**Result**: Updated property values take effect at runtime for beans in `@RefreshScope`.

### 96. What is a composite primary key and how do you map it in Spring Data JPA?

**Situation**: A table's primary key consists of multiple columns.

**Task**: Map the composite key to an entity.

**Action**: Create an `@Embeddable` key class and include it in the entity with `@EmbeddedId`. Each field in the key class maps to a primary key column.

**Result**: JPA handles entities with composite keys correctly.

### 97. How do you integrate Micrometer metrics with Spring Boot?

**Situation**: You need vendor-neutral metrics before exporting to different monitoring systems.

**Task**: Use a metrics facade that works with various backends.

**Action**: Spring Boot integrates Micrometer. Add the relevant registry (e.g., Prometheus, Datadog), and metrics appear automatically for common components.

**Result**: Metrics are collected consistently and can be exported to different monitoring tools.

### 98. What is the difference between `RestTemplate` and `WebClient`?

**Situation**: Your application calls external HTTP services.

**Task**: Choose between blocking and non-blocking clients.

**Action**: `RestTemplate` is synchronous and simple; `WebClient` is reactive and non-blocking, supporting streaming and backpressure.

**Result**: Use `WebClient` in reactive applications, `RestTemplate` in traditional ones (though WebClient is recommended for new code).

### 99. Describe the use of `@BeforeEach` and `@BeforeAll` in tests.

**Situation**: Test methods need setup code.

**Task**: Run initialization before tests.

**Action**: Annotate a method with `@BeforeEach` to run before every test case and `@BeforeAll` (static) to run once before all tests.

**Result**: Test fixtures are properly initialized.

### 100. How can you create a REST client using `RestTemplateBuilder`?

**Situation**: You want to customize timeouts, message converters, or authentication for HTTP calls.

**Task**: Provide a configured `RestTemplate` bean.

**Action**: Inject `RestTemplateBuilder`, call builder methods such as `setConnectTimeout`, then build the template. Use it to perform REST calls.

**Result**: A reusable, customized HTTP client is available.

```
[RestTemplateBuilder] -> [RestTemplate] -> [Remote API]
```
