# Java Interview Questions in STAR Format

These questions cover foundational Java concepts using the **Situation, Task, Action, Result** framework.

## Questions and Answers

### 1. Can you explain the use of the `final` keyword in Java?
**Situation:** You want to prevent modification of a variable or method.
**Task:** Declare an immutable reference.
**Action:** Use `final` when defining fields, methods, or classes to restrict inheritance or modification.
**Result:** The value or behavior remains constant, ensuring reliability.

### 2. Describe a time you used generics to improve code quality.
**Situation:** Type casting caused runtime errors in a collection.
**Task:** Enforce type safety at compile time.
**Action:** Introduced generics like `List<String>` to avoid `ClassCastException`.
**Result:** Cleaner code with fewer runtime failures.

### 3. How did you troubleshoot a multithreading issue?
**Situation:** Threads were deadlocking under heavy load.
**Task:** Identify the lock contention.
**Action:** Used a thread dump and the `jstack` tool to pinpoint threads waiting on locks, then refactored synchronization logic.
**Result:** Eliminated deadlock and improved throughput.
