
# SQL Interview Questions in STAR Format

This document presents 50 frequently asked SQL interview questions. Each answer follows the **Situation, Task, Action, Result** (STAR) approach. Simple diagrams illustrate key concepts.

## Questions and Answers

### 1. What is a primary key?
**Situation:** Every table needs a way to uniquely identify rows.
**Task:** Choose a column or group of columns to ensure row uniqueness.
**Action:** Declare a PRIMARY KEY constraint when creating the table.
**Result:** The database enforces uniqueness and creates an index for fast lookup.

### 2. Explain the difference between INNER JOIN and OUTER JOIN.
**Situation:** Retrieving data from related tables.
**Task:** Decide whether to include unmatched rows.
**Action:** Use INNER JOIN for only matching rows or OUTER JOIN to include non-matching rows from one or both tables.
**Result:** Appropriate rows are returned based on join type.

### 3. What is normalization?
**Situation:** A database has redundant or inconsistent data.
**Task:** Organize tables to reduce duplication.
**Action:** Apply normal forms (1NF, 2NF, 3NF) by splitting data into related tables.
**Result:** Improved integrity and smaller storage requirements.

### 4. Describe a star schema.
**Situation:** You need to design a data warehouse for fast querying.
**Task:** Model facts with surrounding dimension tables.
**Action:** Create a central fact table connected to dimension tables in a star layout.
**Result:** Queries run efficiently for reporting.
```
      +-----------+
      |Dimension1 |
      +-----------+
           |
+---------+---------+
|  Fact Table      |
+---------+---------+
           |
      +-----------+
      |Dimension2 |
      +-----------+
```

### 5. How do indexes improve performance?
**Situation:** Queries on large tables run slowly.
**Task:** Speed up searches on specific columns.
**Action:** Create indexes on frequently filtered or joined columns.
**Result:** The database can locate rows quickly without scanning entire tables.

### 6. What is a foreign key?
**Situation:** Tables need to reference each other.
**Task:** Enforce referential integrity between tables.
**Action:** Define a FOREIGN KEY constraint linking a child column to the parent's PRIMARY KEY.
**Result:** Inconsistent relationships are prevented.

### 7. Explain ACID properties of a transaction.
**Situation:** Multiple statements must execute reliably.
**Task:** Ensure atomicity, consistency, isolation, and durability.
**Action:** Use transaction control statements like BEGIN, COMMIT, and ROLLBACK.
**Result:** The database remains in a consistent state even after failures.

### 8. What is a stored procedure?
**Situation:** You have repetitive SQL logic.
**Task:** Encapsulate logic on the server side.
**Action:** Create a stored procedure with parameters and call it when needed.
**Result:** Code reuse and reduced network traffic.

### 9. Difference between DELETE and TRUNCATE.
**Situation:** Remove data from a table.
**Task:** Choose the appropriate command for data deletion.
**Action:** DELETE removes rows one by one and logs each operation; TRUNCATE deallocates data pages.
**Result:** TRUNCATE is faster but cannot be rolled back in some DBMS.

### 10. What is a view?
**Situation:** You need to simplify complex queries or secure data.
**Task:** Present a virtual table based on SELECT statements.
**Action:** Create a view referencing underlying tables.
**Result:** Users query the view without seeing underlying complexity.

### 11. Explain GROUP BY with HAVING.
**Situation:** Summarizing data by categories.
**Task:** Aggregate rows and filter on the aggregates.
**Action:** Use GROUP BY to group records and HAVING to filter aggregated results.
**Result:** Reports show only the required aggregated data.

### 12. What is a composite key?
**Situation:** No single column uniquely identifies rows.
**Task:** Combine columns to form a unique identifier.
**Action:** Declare multiple columns in the PRIMARY KEY.
**Result:** Uniqueness is guaranteed by the combination.

### 13. Explain UNION vs. UNION ALL.
**Situation:** Combine results from two queries.
**Task:** Decide if duplicates should be removed.
**Action:** Use UNION to remove duplicates or UNION ALL to keep them.
**Result:** Merged result set with or without duplicates as required.

### 14. How do you handle missing values in SQL?
**Situation:** Tables contain NULL values.
**Task:** Substitute or filter them in queries.
**Action:** Use IS NULL checks, COALESCE, or NULLIF functions.
**Result:** Queries handle missing data appropriately.

### 15. What is an execution plan?
**Situation:** A query performs poorly.
**Task:** Diagnose how the database executes it.
**Action:** Display the execution plan using EXPLAIN or similar command.
**Result:** Insight into joins, indexes, and steps to optimize.

### 16. Describe a clustered index.
**Situation:** Data retrieval by primary key must be efficient.
**Task:** Order table rows to match the primary key.
**Action:** Use a clustered index which determines row order on disk.
**Result:** Faster range queries and improved cache locality.

### 17. Explain database transactions isolation levels.
**Situation:** Concurrent transactions may conflict.
**Task:** Choose the right level of isolation.
**Action:** Set isolation level (READ COMMITTED, REPEATABLE READ, SERIALIZABLE).
**Result:** Balance consistency with performance.

### 18. What is denormalization and when would you use it?
**Situation:** Normalized schema causes many joins for reporting.
**Task:** Improve read performance.
**Action:** Intentionally duplicate data in fewer tables.
**Result:** Fewer joins and faster reads at the cost of extra storage.

### 19. Explain a self join.
**Situation:** A table must be joined with itself to compare rows.
**Task:** Retrieve related data from the same table.
**Action:** Use table aliases to perform a self join.
**Result:** The query returns relationships within the same table.

### 20. What are window functions?
**Situation:** Need running totals or rankings.
**Task:** Perform calculations across sets of rows related to the current row.
**Action:** Use OVER() clause with functions like ROW_NUMBER, RANK, or SUM.
**Result:** Detailed analytics without subqueries.

### 21. How does the SQL engine use memory for joins?
**Situation:** Large join operations cause high memory usage.
**Task:** Optimize joins to fit memory budgets.
**Action:** Use indexed joins or break queries into smaller parts.
**Result:** Reduced memory pressure and improved performance.

### 22. What is an index scan vs. index seek?
**Situation:** Evaluating query performance.
**Task:** Understand how indexes are used.
**Action:** Review execution plan for SEEK (precise match) or SCAN (traverses index).
**Result:** Insights to adjust indexing strategies.

### 23. Describe the difference between char and varchar.
**Situation:** Designing string columns.
**Task:** Choose a fixed or variable length type.
**Action:** Use CHAR for fixed size and VARCHAR for variable data.
**Result:** Proper storage allocation and memory usage.

### 24. How do you back up a database?
**Situation:** Protect against data loss.
**Task:** Create a copy of the database state.
**Action:** Use BACKUP commands or dump utilities provided by the DBMS.
**Result:** The backup can be restored if necessary.

### 25. What are temp tables?
**Situation:** Complex queries need intermediate results.
**Task:** Store these results temporarily.
**Action:** Create temporary tables or table variables.
**Result:** Simplifies logic and can improve performance.

### 26. Explain indexing strategies for large tables.
**Situation:** Table grows to millions of rows.
**Task:** Maintain fast queries and updates.
**Action:** Index columns used for searching and consider partitioning.
**Result:** Balanced performance for reads and writes.

### 27. What is a materialized view?
**Situation:** Expensive queries are run frequently.
**Task:** Cache query results on disk.
**Action:** Create a materialized view that refreshes periodically.
**Result:** Faster retrieval at the cost of storage.

### 28. Explain optimistic vs. pessimistic locking.
**Situation:** Concurrent updates may lead to conflicts.
**Task:** Decide how to manage locking.
**Action:** Use optimistic locking with version checks or pessimistic locking with explicit locks.
**Result:** Proper balance between concurrency and consistency.

### 29. What are triggers?
**Situation:** Need automatic actions on data changes.
**Task:** Execute logic after inserts, updates, or deletes.
**Action:** Define triggers responding to table events.
**Result:** Business rules enforced consistently.

### 30. Describe a star join optimization.
**Situation:** Star schema queries require joining fact and dimension tables.
**Task:** Tune joins for large analytic workloads.
**Action:** Use bitmap indexes and star transformations if supported.
**Result:** Faster data warehouse queries.

### 31. How do you handle schema migrations?
**Situation:** Database structure evolves during development.
**Task:** Apply schema changes safely.
**Action:** Use migration tools like Liquibase or Flyway with version control.
**Result:** Repeatable updates across environments.

### 32. What is a common table expression (CTE)?
**Situation:** Writing a recursive or complex query.
**Task:** Break query into named subqueries.
**Action:** Use WITH clause to define CTEs.
**Result:** More readable SQL and reusable subqueries.

### 33. Explain indexing for text search.
**Situation:** You must search large text columns.
**Task:** Provide full text search capability.
**Action:** Create full-text indexes with stemming and ranking.
**Result:** Efficient retrieval of textual data.

### 34. What is partitioning?
**Situation:** Large tables hinder performance.
**Task:** Spread data across partitions based on a key.
**Action:** Define range, list, or hash partitioning in table definition.
**Result:** Queries can target specific partitions, improving speed.

### 35. How do transactions interact with caching?
**Situation:** Cached query results may become stale after updates.
**Task:** Maintain cache consistency.
**Action:** Invalidate or refresh caches upon transaction commit.
**Result:** Clients see up-to-date information while benefiting from caching.

### 36. Explain cross join.
**Situation:** You need all combinations of two tables.
**Task:** Produce a Cartesian product.
**Action:** Use CROSS JOIN without a join condition.
**Result:** Each row from the first table pairs with every row of the second table.

### 37. What is the difference between HAVING and WHERE?
**Situation:** Filtering aggregated vs. non-aggregated data.
**Task:** Use the correct clause for conditions.
**Action:** Apply WHERE before grouping and HAVING after grouping.
**Result:** Queries return correct filtered results.

### 38. Describe a correlated subquery.
**Situation:** A subquery depends on each row of the outer query.
**Task:** Retrieve data referencing outer query columns.
**Action:** Write a subquery that references columns from the outer statement.
**Result:** The subquery runs for each row of the outer query.

### 39. What is the role of the query optimizer?
**Situation:** You want the fastest execution plan.
**Task:** Choose the best plan among many possibilities.
**Action:** The optimizer evaluates statistics and cost to produce a plan.
**Result:** SQL statements run efficiently without manual tuning.

### 40. How do you enforce data integrity?
**Situation:** Prevent invalid data from entering tables.
**Task:** Apply constraints and checks.
**Action:** Define NOT NULL, UNIQUE, CHECK, and FOREIGN KEY constraints.
**Result:** Data adheres to business rules.

### 41. Explain the difference between LTRIM and RTRIM.
**Situation:** Strings may have unwanted spaces.
**Task:** Remove leading or trailing whitespace.
**Action:** Use LTRIM to trim left side and RTRIM to trim right side.
**Result:** Clean string values.

### 42. What is a surrogate key?
**Situation:** Natural keys are long or volatile.
**Task:** Provide a simple numeric identifier.
**Action:** Create an auto-increment column as the surrogate key.
**Result:** Tables have stable and efficient primary keys.

### 43. How do you implement pagination in SQL?
**Situation:** Applications display results page by page.
**Task:** Limit result sets.
**Action:** Use LIMIT/OFFSET or window functions with row numbers.
**Result:** Only the requested subset of rows is returned.

### 44. Describe in-memory databases.
**Situation:** Ultra-fast data access is required.
**Task:** Keep data entirely in RAM.
**Action:** Use an in-memory DBMS or memory-optimized tables.
**Result:** Extremely low-latency queries but data must be persisted separately.

### 45. What is cascading referential integrity?
**Situation:** Deleting a parent row leaves orphaned child rows.
**Task:** Automatically manage related records.
**Action:** Define ON DELETE CASCADE or ON UPDATE CASCADE in foreign keys.
**Result:** Consistency maintained without extra queries.

### 46. Explain the purpose of the EXCEPT operator.
**Situation:** Compare two result sets.
**Task:** Return rows from the first set not present in the second.
**Action:** Use EXCEPT or MINUS depending on the DBMS.
**Result:** Differences between sets are identified.

### 47. How do you detect deadlocks?
**Situation:** Transactions are stuck waiting for each other.
**Task:** Identify and resolve circular locking.
**Action:** Monitor logs or use DBMS commands to detect deadlocks.
**Result:** Deadlocked transactions are rolled back to free resources.

### 48. What is an upsert?
**Situation:** Insert a row or update it if it already exists.
**Task:** Combine insert and update logic.
**Action:** Use INSERT ... ON CONFLICT or MERGE statement.
**Result:** Single statement handles both cases efficiently.

### 49. How do you calculate running totals?
**Situation:** Need cumulative sums over a result set.
**Task:** Add each row's value to prior ones.
**Action:** Use SUM(column) OVER (ORDER BY ...) window function.
**Result:** Query returns running totals for each row.

### 50. Describe indexing best practices for memory usage.
**Situation:** Too many indexes consume memory.
**Task:** Balance read speed with storage requirements.
**Action:** Create indexes only on columns used in queries and periodically review them.
**Result:** Queries are optimized without excessive memory consumption.

