# Spring Boot Interview Questions in STAR Format

These STAR examples address core Spring Boot concepts.

## Questions and Answers

### 1. How did you secure a REST API?
**Situation:** The API exposed sensitive data to clients.
**Task:** Implement authentication and authorization.
**Action:** Integrated Spring Security with JWT tokens and added method-level security annotations.
**Result:** Only authenticated users could access protected endpoints.

### 2. Describe a challenge when configuring microservices.
**Situation:** Multiple services required centralized configuration.
**Task:** Externalize and manage settings.
**Action:** Adopted Spring Cloud Config server and stored properties in a Git repository.
**Result:** Services pulled configuration at startup, simplifying environment changes.

### 3. Explain how you improved application startup time.
**Situation:** The application started slowly due to many auto-configured beans.
**Task:** Reduce initialization overhead.
**Action:** Used lazy initialization and disabled unnecessary starters.
**Result:** Startup time decreased significantly.
