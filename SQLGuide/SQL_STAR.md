# SQL Interview Questions in STAR Format

The following questions use the **Situation, Task, Action, Result** approach to highlight SQL best practices.

## Questions and Answers

### 1. Describe a time you optimized a slow query.
**Situation:** A query joining multiple tables ran in seconds instead of milliseconds.
**Task:** Improve its performance.
**Action:** Analyzed the execution plan, created indexes on the join columns, and rewrote the query using subqueries.
**Result:** Execution time dropped dramatically, supporting real-time analytics.

### 2. How did you enforce data integrity between tables?
**Situation:** Foreign key constraints were missing in a legacy schema.
**Task:** Ensure referential integrity.
**Action:** Added primary and foreign key constraints and configured cascading deletes where appropriate.
**Result:** Prevented orphan records and simplified application logic.

### 3. Explain your approach to handling schema migrations.
**Situation:** Continuous deployment required evolving database schemas without downtime.
**Task:** Coordinate migrations with application releases.
**Action:** Used tools like Flyway/Liquibase and wrote idempotent scripts with version control.
**Result:** Automated migrations kept environments in sync and reduced manual errors.
