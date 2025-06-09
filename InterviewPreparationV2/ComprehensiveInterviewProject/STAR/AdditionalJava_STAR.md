# Advanced Java Interview Questions in STAR Format

The following questions use the **Situation, Task, Action, Result** method to describe advanced Java topics.

## Questions and Answers

### 1. How do you handle memory leaks in a long-running Java application?
**Situation:** The application gradually consumes more memory over time.
**Task:** Identify and prevent memory leaks.
**Action:** Use profiling tools like VisualVM to monitor heap usage, analyze object retention, and fix references that should be released.
**Result:** Memory usage stabilizes and the application runs without OutOfMemoryErrors.

### 2. Describe a scenario where you used Java's `CompletableFuture`.
**Situation:** A service needed to call multiple APIs asynchronously.
**Task:** Combine the responses efficiently.
**Action:** Leveraged `CompletableFuture.supplyAsync` for each API and used `thenCombine` to merge results.
**Result:** Achieved non-blocking parallel processing and reduced request latency.

### 3. Explain the role of the Java Memory Model in concurrency.
**Situation:** Thread interactions caused inconsistent data reads.
**Task:** Ensure proper synchronization.
**Action:** Studied the Java Memory Model and used `volatile` fields and synchronized blocks to establish happens-before relationships.
**Result:** Threads observed consistent values, preventing race conditions.
