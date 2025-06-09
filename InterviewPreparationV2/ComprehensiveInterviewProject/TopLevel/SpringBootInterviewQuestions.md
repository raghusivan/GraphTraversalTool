# Spring Boot Interview Questions

The following list includes one hundred Spring Boot interview questions covering essential concepts, common troubleshooting topics, and advanced features. Each question is accompanied by a detailed answer and code snippet where relevant.

## Questions and Answers

1. **What is Spring Boot and how does it differ from Spring Framework?**

   Spring Boot is an opinionated framework built on top of the Spring ecosystem. It simplifies application setup by providing auto-configuration, embedded servers, and production-ready features. Unlike the core Spring Framework, you don't need extensive XML or Java-based configuration to start a project with Spring Boot.

2. **How do you create a Spring Boot application?**

   You can use Spring Initializr (https://start.spring.io/) or Spring Boot CLI to generate a project. The main class is annotated with `@SpringBootApplication` which combines `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.
   ```java
   @SpringBootApplication
   public class DemoApplication {
       public static void main(String[] args) {
           SpringApplication.run(DemoApplication.class, args);
       }
   }
   ```

3. **What is auto-configuration in Spring Boot?**

   Auto-configuration attempts to automatically configure your application based on the dependencies present on the classpath. For instance, if `spring-web` is detected, Spring Boot configures an embedded web server automatically. You can customize or disable auto-configuration using properties or the `@EnableAutoConfiguration` annotation.

4. **How does Spring Boot embed web servers?**

   Spring Boot includes server libraries like Tomcat, Jetty, or Undertow as dependencies. When the application starts, the embedded server is automatically configured and started using the included libraries. This eliminates the need for deploying the application on an external server.

5. **Explain the `application.properties` and `application.yml` files.**

   These files hold external configuration for the application. You can define server ports, database settings, logging levels, and custom properties. YAML format (`application.yml`) is hierarchical and often easier to read, while `application.properties` uses key-value pairs.

6. **What is the purpose of `@SpringBootApplication`?**

   `@SpringBootApplication` is a convenience annotation that bundles `@Configuration`, `@ComponentScan`, and `@EnableAutoConfiguration`. It marks the primary configuration class and triggers component scanning as well as auto-configuration when the application launches.

7. **How can you customize the port for an embedded server?**

   Set `server.port` in `application.properties` or `application.yml`.
   ```properties
   server.port=8081
   ```
   Alternatively, use command-line arguments: `--server.port=8081`.

8. **How do you connect a Spring Boot application to a database?**

   Include the relevant starter (`spring-boot-starter-data-jpa` for JPA/Hibernate) and define datasource properties in `application.properties`. Spring Boot will create the `DataSource` and configure JPA repositories.
   ```properties
   spring.datasource.url=jdbc:mysql://localhost/db
   spring.datasource.username=user
   spring.datasource.password=pass
   ```

9. **What is the `@RestController` annotation used for?**

   `@RestController` is a shorthand for `@Controller` and `@ResponseBody`. It designates a controller whose methods directly return response bodies, typically JSON or XML.
   ```java
   @RestController
   public class HelloController {
       @GetMapping("/hello")
       public String hello() {
           return "Hello";
       }
   }
   ```

10. **Explain `spring-boot-starter` dependencies.**

    Starters are a set of convenient dependency descriptors you include in your build. For example, `spring-boot-starter-web` brings in Spring MVC, a web server, and other libraries needed to develop a web application. They reduce the need to search for compatible versions of individual dependencies.

11. **What are the differences between `@Component`, `@Service`, and `@Repository`?**

    All three are stereotypes for Spring-managed beans. `@Component` is a generic stereotype; `@Service` is used for business logic classes; `@Repository` indicates a data-access component and provides exception translation for persistence exceptions.

12. **How do you define a custom banner in Spring Boot?**

    Place a `banner.txt` file in the `resources` directory. Spring Boot reads and prints it at startup. You can customize it with ASCII art or properties such as `${application.version}`.

13. **Explain the Spring Boot actuator.**

    The actuator adds production-ready features like health checks, metrics, and info endpoints. Add `spring-boot-starter-actuator` to enable them, then access endpoints like `/actuator/health` or `/actuator/metrics`.

14. **How do you secure a Spring Boot application?**

    Include `spring-boot-starter-security`. Configure security with a `WebSecurityConfigurerAdapter` or new Lambda DSL in Spring Security 5.4+. You can define user credentials and roles in `application.properties` or integrate with authentication providers.

15. **What is Spring Boot DevTools?**

    DevTools provides utilities for development such as automatic restarts, live reload, and property defaults. It improves developer productivity by reloading code when classes or resources change.

16. **How can you enable hot swapping of code changes?**

    Spring Boot DevTools automatically restarts the application when classpath files change. Build tools like Maven or Gradle must copy resources to the output directory so DevTools can detect changes.

17. **What is a profile in Spring Boot?**

    Profiles allow grouping configuration for different environments such as dev, test, or prod. Annotate beans with `@Profile("dev")` or specify profile-specific property files like `application-dev.properties`.

18. **How do you log in Spring Boot?**

    Spring Boot uses Logback by default. Configure log levels in `application.properties` with `logging.level` keys. Custom logback configuration can be provided in `src/main/resources/logback-spring.xml`.

19. **How can you handle exceptions globally in a Spring Boot application?**

    Use `@ControllerAdvice` with `@ExceptionHandler` methods to capture and respond to exceptions thrown by controller methods.
   ```java
   @ControllerAdvice
   public class GlobalExceptionHandler {
       @ExceptionHandler(ResourceNotFoundException.class)
       public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
       }
   }
   ```

20. **What is Spring Data JPA?**

    Spring Data JPA simplifies repository implementations for JPA-based databases. By extending `JpaRepository`, you get CRUD operations, pagination, and custom query methods without writing boilerplate code.

21. **Explain the purpose of `CommandLineRunner` and `ApplicationRunner`.**

    These interfaces allow you to run code after the application context has been loaded. Implement either and annotate with `@Component` to perform tasks such as data seeding at startup.

22. **How do you test Spring Boot applications?**

    Use `spring-boot-starter-test` which includes JUnit, Spring Test, AssertJ, and Mockito. Annotate your test class with `@SpringBootTest` to load the application context or use slice tests like `@WebMvcTest`.

23. **What is the difference between `@SpringBootTest` and `@WebMvcTest`?**

    `@SpringBootTest` starts the full application context, while `@WebMvcTest` focuses on Spring MVC components only, without loading the entire context. Use `@WebMvcTest` for controller layer tests.

24. **How do you configure externalized properties for different environments?**

    Use environment-specific property files (e.g., `application-prod.properties`) and activate them with the `spring.profiles.active` property or `--spring.profiles.active=prod` command-line switch.

25. **What is the use of `@Value` annotation?**

    `@Value` injects property values from configuration files or environment variables. It supports SpEL expressions.
   ```java
   @Value("${server.port}")
   private int port;
   ```

26. **Explain what `@ConfigurationProperties` does.**

    It binds hierarchical properties to a POJO, allowing type-safe configuration.
   ```java
   @Component
   @ConfigurationProperties(prefix = "app")
   public class AppConfig {
       private String name;
       // getters and setters
   }
   ```

27. **How do you perform validation on properties bound with `@ConfigurationProperties`?**

    Combine with `@Validated` and add validation annotations to fields.
   ```java
   @Component
   @ConfigurationProperties(prefix = "app")
   @Validated
   public class AppConfig {
       @NotBlank
       private String name;
   }
   ```

28. **What is the difference between `@EnableAutoConfiguration` and `@SpringBootApplication`?**

    `@EnableAutoConfiguration` triggers auto-configuration but lacks component scanning. `@SpringBootApplication` combines auto-configuration with configuration and component scanning, making it more convenient for most applications.

29. **Explain `@EntityScan` and `@EnableJpaRepositories`.**

    `@EntityScan` tells Spring Boot where to find JPA entities if they're not in the main package. `@EnableJpaRepositories` specifies packages containing repository interfaces.

30. **How do you implement pagination in Spring Data JPA?**

    Use `Pageable` in repository methods.
   ```java
   Page<User> findByActiveTrue(Pageable pageable);
   ```
   Then call the repository method with `PageRequest.of(page, size)`.

31. **What are Spring Boot starters and how do they simplify build configuration?**

    Starters bundle dependencies that are known to work together, reducing manual version management. For example, `spring-boot-starter-web` brings in Spring MVC and Tomcat with compatible versions.

32. **How does Spring Boot handle JSON serialization?**

    Spring Boot uses Jackson as the default JSON library. You can customize the `ObjectMapper` bean to change serialization settings.

33. **What is the difference between `@Bean` and `@Component`?**

    `@Bean` is used within `@Configuration` classes to declare a bean explicitly in Java code. `@Component` is placed directly on a class so that component scanning detects it automatically.

34. **Explain the role of `applicationContext` in Spring Boot.**

    The `ApplicationContext` is the central interface to the Spring container. It holds all bean definitions, manages their lifecycle, and handles dependency injection.

35. **How can you override default auto-configuration?**

    Provide your own bean of the same type or disable specific auto-configuration classes using `@SpringBootApplication(exclude = ...)` or `@EnableAutoConfiguration(exclude = ...)`.

36. **What is the purpose of `spring-boot-devtools`?**

    `spring-boot-devtools` improves developer experience by enabling automatic restarts, live reload, and property defaults for caching or template engines during development.

37. **How do you monitor and manage a Spring Boot application in production?**

    Use Actuator endpoints to expose health checks, metrics, and environment info. Integrate with monitoring systems like Prometheus or use Spring Boot Admin for a centralized dashboard.

38. **Explain the difference between `@ResponseBody` and `ResponseEntity`.**

    `@ResponseBody` writes the return object directly to the HTTP response body. `ResponseEntity` provides additional control over status codes and headers.

39. **How do you handle 404 errors with custom JSON responses?**

    Implement `ErrorController` or create an `@ControllerAdvice` that handles `NoHandlerFoundException`. Configure `spring.mvc.throw-exception-if-no-handler-found=true` and `spring.web.resources.add-mappings=false`.

40. **What is a starter parent in Maven and why is it useful?**

    `spring-boot-starter-parent` is a parent POM that manages dependency versions and plugin configurations. It ensures your project uses consistent versions that are tested with Spring Boot.

41. **How do you access command-line arguments in a Spring Boot application?**

    The `SpringApplication.run` method returns an `ApplicationContext`, from which you can fetch the `ApplicationArguments` bean.
   ```java
   @Component
   public class ArgsPrinter implements ApplicationRunner {
       @Override
       public void run(ApplicationArguments args) {
           System.out.println(args.getSourceArgs());
       }
   }
   ```

42. **What is the default embedded container in Spring Boot? Can you change it?**

    The default is Tomcat. You can switch to Jetty or Undertow by excluding Tomcat and adding the desired server starter.

43. **Explain how to configure HTTPS for Spring Boot.**

    Provide an SSL certificate (keystore) and set `server.ssl.key-store`, `server.ssl.key-store-password`, and related properties in `application.properties`. Spring Boot will start the embedded server using HTTPS.

44. **What are conditional annotations in Spring Boot?**

    Conditional annotations like `@ConditionalOnProperty` or `@ConditionalOnClass` allow beans to be registered only if certain conditions are met. They're heavily used in auto-configuration classes.

45. **How do you implement caching in Spring Boot?**

    Add `spring-boot-starter-cache` and annotate methods with `@Cacheable`, `@CachePut`, or `@CacheEvict`. Configure a cache manager such as caffeine or EhCache via properties.

46. **Explain Spring Boot's support for internationalization (i18n).**

    Place `messages_{locale}.properties` files in the classpath and configure a `LocaleResolver`. Use `MessageSource` or `@RequestHeader("Accept-Language")` to serve content in different languages.

47. **How do you schedule tasks in Spring Boot?**

    Use `@EnableScheduling` and annotate methods with `@Scheduled` to run them at fixed rates or cron expressions.
   ```java
   @Scheduled(cron = "0 0 * * * *")
   public void hourlyTask() {
       // task code
   }
   ```

48. **What is the difference between `@LoadBalanced` and `@FeignClient`?**

    `@LoadBalanced RestTemplate` uses Ribbon or Spring Cloud LoadBalancer to perform client-side load balancing. `@FeignClient` declares a REST client interface that can also integrate with load balancing and circuit breakers.

49. **How do you implement REST versioning in Spring Boot?**

    Approaches include URL versioning (`/v1/resource`), request parameter versioning, custom headers, or content negotiation via `produces` and `consumes` attributes.

50. **What is Spring Boot Actuator's `/shutdown` endpoint?**

    When enabled, the `/actuator/shutdown` endpoint gracefully closes the application context, shutting down the application. For security reasons, it is disabled by default.

51. **Explain the difference between `EntityManager` and Spring Data repositories.**

    `EntityManager` is part of JPA, providing low-level CRUD operations and queries. Spring Data repositories build on top of it, offering high-level methods and query creation from method names.

52. **How do you disable a specific auto-configuration class?**

    Use `@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})` or property `spring.autoconfigure.exclude` to prevent certain auto-configuration classes from running.

53. **How can you add custom startup logic before the application fully starts?**

    Implement `ApplicationRunner` or `CommandLineRunner` and register your bean in the context. These run after the context is prepared but before the application is ready to receive requests.

54. **What is the use of `spring.main.allow-bean-definition-overriding`?**

    When set to `true`, this property allows bean definitions with the same name to override existing ones. This can be useful in tests or when customizing auto-configured beans.

55. **How do you integrate Swagger/OpenAPI with Spring Boot?**

    Add the SpringDoc or Swagger dependencies (`springdoc-openapi-ui`). Annotate your controllers with standard Swagger/OpenAPI annotations. Access `/swagger-ui.html` or `/swagger-ui/index.html` for the UI.

56. **Explain the concept of reactive programming in Spring Boot.**

    Spring WebFlux enables building reactive, non-blocking applications using Reactor types (`Mono` and `Flux`). It is suited for applications that require scalability with fewer threads.

57. **How do you configure a reactive web application in Spring Boot?**

    Add `spring-boot-starter-webflux` dependency. Controllers return `Mono<T>` or `Flux<T>`, and you can use `RouterFunction` with functional endpoints if desired.

58. **What is a `Filter` and how do you register one in Spring Boot?**

    A `Filter` intercepts HTTP requests before they reach a servlet. Annotate with `@Component` or register with `FilterRegistrationBean` for custom order and URL patterns.

59. **Explain the use of `@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)`**

    This loads the full application context and starts an embedded server on a random port for integration testing, allowing real HTTP requests against the running application.

60. **How do you secure REST endpoints using JWT in Spring Boot?**

    Configure Spring Security with a filter that validates JWT tokens, typically using `OncePerRequestFilter`. Issue tokens after authentication and include them in the `Authorization` header for subsequent requests.

61. **What is `SpringApplicationBuilder` used for?**

    It provides a fluent API for building and customizing a `SpringApplication`. You can specify sources, profiles, and run listeners before starting the application.

62. **How do you handle WebSocket communication in Spring Boot?**

    Include `spring-boot-starter-websocket`. Configure a `@Controller` or `@MessageMapping` for STOMP messages, and use `WebSocketConfig` implementing `WebSocketMessageBrokerConfigurer`.

63. **Explain the difference between `JdbcTemplate` and `JpaRepository`.**

    `JdbcTemplate` is a lower-level abstraction for executing SQL queries directly. `JpaRepository` uses JPA and ORM mapping, which is more feature-rich but can introduce overhead for simple queries.

64. **How do you run a Spring Boot application as a service or daemon?**

    Use the `spring-boot-maven-plugin` or `spring-boot-gradle-plugin` to build an executable jar. Then configure your OS-specific service wrapper or use `java -jar app.jar` with proper init scripts.

65. **What are `@SpringBootTest` slices and why are they useful?**

    Slice annotations (like `@WebMvcTest`, `@DataJpaTest`, `@RestClientTest`) load only a portion of the application context for faster tests targeting specific layers.

66. **Describe the `@Conditional` annotation.**

    `@Conditional` allows conditional bean creation based on custom logic. Create a class implementing `Condition` and reference it in `@Conditional(MyCondition.class)`.

67. **How do you upload files in Spring Boot?**

    Use `MultipartFile` in your controller and set `spring.servlet.multipart.enabled=true` (enabled by default). Configure max upload size with `spring.servlet.multipart.max-file-size`.

68. **What is the difference between `@Entity` and `@Document` in Spring Boot?**

    `@Entity` marks a class as a JPA entity for relational databases. `@Document` is used in Spring Data MongoDB to map a class to a MongoDB collection.

69. **How do you implement transaction management?**

    Add `@Transactional` on methods that need transactions. Configure transaction managers automatically via `DataSourceTransactionManager` or JPA's `PlatformTransactionManager`.

70. **Explain the role of properties `spring.jpa.hibernate.ddl-auto`.**

    This property controls how Hibernate handles schema generation. Values include `none`, `update`, `create`, `create-drop`, and `validate`.

71. **How do you display custom error pages for specific HTTP statuses?**

    Add HTML error files named with the status code (e.g., `404.html`) in `src/main/resources/public/error`. You can also implement `ErrorViewResolver` for programmatic control.

72. **What is the difference between `Spring MVC` and `Spring WebFlux`?**

    Spring MVC is the traditional, servlet-based web framework using the blocking I/O model. Spring WebFlux is reactive and non-blocking, supporting both annotation-based and functional endpoints.

73. **How do you integrate Spring Boot with a NoSQL database such as MongoDB?**

    Add `spring-boot-starter-data-mongodb` and define connection properties. Use `MongoRepository` or `ReactiveMongoRepository` to interact with collections.

74. **What is `Spring Boot Admin`?**

    An open-source application that provides a user interface for managing and monitoring Spring Boot applications via actuator endpoints. You register your applications as clients with the admin server.

75. **Explain how to serve static resources in Spring Boot.**

    Place files in `src/main/resources/static` or `public`. Spring Boot maps these locations to the root context, so `/img/logo.png` will be served at `http://localhost:8080/img/logo.png`.

76. **How do you use Lombok with Spring Boot?**

    Add Lombok as a dependency, then annotate your classes with Lombok annotations like `@Getter`, `@Setter`, and `@Builder`. Ensure your IDE supports Lombok via its plugin.

77. **How can you implement rate limiting?**

    Use libraries like Bucket4j or implement your own filter that tracks request counts per client. For distributed systems, an external rate limiter such as Redis or a gateway solution may be necessary.

78. **What is Spring Cloud Config and how do you use it?**

    Spring Cloud Config provides server and client-side support for centralized configuration across environments. The config server exposes property sources from Git, files, or other backends. Spring Boot apps fetch these configs via the client starter.

79. **Explain circuit breaker patterns in Spring Boot.**

    Using Resilience4j or Spring Cloud Circuit Breaker, you can wrap remote calls to return fallbacks or open circuits during failures. Annotations like `@CircuitBreaker` or `@Retry` implement this behavior.

80. **How do you run batch jobs in Spring Boot?**

    Include `spring-boot-starter-batch` and configure `Job` and `Step` beans. Launch jobs using `JobLauncher` or command-line parameters.

81. **What is `@RepositoryRestResource`?**

    From Spring Data REST, it exposes repository methods as RESTful endpoints automatically. It's convenient for creating hypermedia-driven APIs quickly.

82. **How can you customize an embedded servlet container such as Tomcat?**

    Implement `WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>` and override server properties like port, session timeout, or connectors.

83. **What is the purpose of `@JsonIgnoreProperties(ignoreUnknown = true)`?**

    This Jackson annotation tells the deserializer to ignore properties not mapped to fields, preventing errors when JSON contains extra fields.

84. **How do you configure asynchronous method execution?**

    Enable with `@EnableAsync` and annotate methods with `@Async`. Provide an executor bean to customize thread pool size.

85. **What are `spring-boot-starter-parent` advantages?**

    It provides default plugin configurations, dependency versions, and a consistent build setup. It simplifies POM files and ensures compatibility among dependencies.

86. **Explain the use of `WebClient` in reactive applications.**

    `WebClient` is the non-blocking, reactive alternative to `RestTemplate`. It returns `Mono` or `Flux` and can integrate with reactive streams for asynchronous HTTP calls.

87. **How do you send email from a Spring Boot application?**

    Include `spring-boot-starter-mail` and configure mail server properties. Inject `JavaMailSender` and create a `MimeMessage` or `SimpleMailMessage` to send.

88. **What is Spring Boot's support for Kotlin?**

    Spring Boot fully supports Kotlin as a first-class language. Use the Kotlin starters or templates from Spring Initializr, and configure `kotlin-spring` plugin for additional features like open classes.

89. **How do you configure Cross-Origin Resource Sharing (CORS)?**

    In Spring MVC, add `@CrossOrigin` on controllers or configure a `CorsConfigurationSource` bean. In WebFlux, use the `CorsWebFilter` bean.

90. **What is the `SpringBootServletInitializer` class used for?**

    When deploying to a traditional servlet container (WAR packaging), you extend `SpringBootServletInitializer` and override `configure` to register the application when the container starts.

91. **Explain property binding from YAML lists.**

    YAML lists can be mapped to `List` or arrays using `@ConfigurationProperties`.
   ```yaml
   app:
     servers:
       - host1
       - host2
   ```
   ```java
   @ConfigurationProperties(prefix = "app")
   public class ServerConfig {
       private List<String> servers;
   }
   ```

92. **What is a `RestTemplate` and is it deprecated?**

    `RestTemplate` is a synchronous client for HTTP requests. It's still supported but the Spring team recommends `WebClient` for new development, especially in reactive contexts.

93. **How do you create custom Health Indicators for Actuator?**

    Implement `HealthIndicator` and register it as a bean. Override `health()` to return a `Health` object describing the component status.

94. **Explain the difference between `@BeforeEach` and `@BeforeAll` in tests.**

    In JUnit 5, `@BeforeEach` runs before each test method, while `@BeforeAll` runs once before all tests in the class. `@BeforeAll` must be static unless the test class is annotated with `@TestInstance(Lifecycle.PER_CLASS)`.

95. **How can you reload property files without restarting the application?**

    Use Spring Cloud Config or implement a configuration watcher that refreshes beans via `ContextRefresher` from the actuator.

96. **What is a composite primary key and how do you map it in Spring Data JPA?**

    A composite key consists of multiple fields. Create an `@Embeddable` key class and use `@EmbeddedId` in the entity.

97. **How do you prevent SQL injection in Spring Data repositories?**

    Use parameter binding in JPQL or repository method query parameters. Avoid string concatenation in queries.

98. **Explain the `spring-boot-starter-validation` dependency.**

    It brings in Hibernate Validator and related dependencies, enabling JSR-380/JSR-303 bean validation annotations such as `@NotNull`, `@Size`, and `@Email`.

99. **What is Micrometer and how does it relate to Spring Boot?**

    Micrometer is a metrics collection facade. Spring Boot integrates it via the actuator to expose metrics to systems like Prometheus, Datadog, or New Relic.

100. **How can you create a REST client using `RestTemplateBuilder`?**

    Inject `RestTemplateBuilder` to configure timeouts, message converters, or basic authentication, then build the `RestTemplate`.
   ```java
   @Service
   public class ApiService {
       private final RestTemplate restTemplate;
       public ApiService(RestTemplateBuilder builder) {
           this.restTemplate = builder.setConnectTimeout(Duration.ofSeconds(5))
                                     .build();
       }
   }
   ```

