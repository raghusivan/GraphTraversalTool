# Detailed Spring Boot Interview Questions and Answers

This guide elaborates on Spring Boot topics with code snippets.

## 1. How does Spring Boot autoconfiguration work?
Autoconfiguration loads default beans based on classpath dependencies and configuration properties. Customize with `@SpringBootApplication` and conditional annotations.

```java
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

## 2. What is Actuator and how do you secure it?
Actuator exposes operational endpoints like `/health` and `/metrics`. Secure it by configuring roles in `application.properties` and using Spring Security.

## 3. When would you use Spring Boot's embedded server versus a traditional WAR deployment?
Embedded servers simplify packaging by running with `java -jar`, while WAR deployments are useful when deploying to a shared container.
