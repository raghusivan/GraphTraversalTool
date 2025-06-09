# Spring Boot REST API Questions in STAR Format

The STAR framework illustrates how to handle REST API scenarios.

## Questions and Answers

### 1. How did you implement pagination in a Spring Boot API?
**Situation:** Large result sets were overwhelming the client.
**Task:** Provide data in manageable chunks.
**Action:** Used `Pageable` in Spring Data and exposed `page` and `size` query parameters.
**Result:** Clients retrieved data efficiently, improving responsiveness.

### 2. Describe a time you optimized JSON serialization.
**Situation:** API responses were too large and slow to generate.
**Task:** Reduce payload size.
**Action:** Employed `@JsonView` and Jackson mixins to send only required fields.
**Result:** Smaller responses and faster network transfers.

### 3. Explain how you tested error handling.
**Situation:** The API needed reliable error responses.
**Task:** Verify that exceptions map to meaningful HTTP codes.
**Action:** Wrote integration tests with `MockMvc` to assert response bodies and status codes.
**Result:** Consistent error handling improved API reliability.
