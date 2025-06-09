# CI/CD Interview Questions

These questions explore continuous integration and deployment tooling and practices.

## Questions and Answers

### 1. What is the goal of a CI/CD pipeline?
**Answer:** The goal is to automate building, testing, and deploying applications so changes can be delivered quickly and reliably. This reduces manual errors and allows faster feedback.

### 2. How would you implement automated testing in a CI pipeline?
**Answer:** Tests should run on each commit using tools like JUnit for Java or Jasmine for Angular. The pipeline executes these tests in an isolated environment, failing the build if any test fails.

### 3. Explain the purpose of artifact repositories.
**Answer:** Artifact repositories store build outputs such as JAR files or Docker images. They provide versioning and an immutable history of artifacts that can be deployed to different environments.

### 4. Describe a strategy for rolling back failed deployments.
**Answer:** Use blue-green or canary deployment patterns where the previous stable version remains available. If new code causes issues, traffic can be routed back to the stable version while investigating.

### 5. What tools have you used for CI/CD?
**Answer:** Jenkins, GitHub Actions, GitLab CI, and cloud-native tools like AWS CodePipeline. Tool choice depends on project requirements and existing infrastructure.
