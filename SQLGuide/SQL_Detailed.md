# Detailed SQL Interview Questions and Answers

Here are common SQL questions with expanded explanations and diagrams.

## 1. What is a window function?
A window function performs a calculation across a set of table rows that are somehow related. Example: calculating a running total.

```sql
SELECT order_id, amount,
       SUM(amount) OVER (ORDER BY order_date) AS running_total
FROM orders;
```

## 2. How do indexes impact performance?
Indexes speed up search queries but slow down writes. Use `EXPLAIN` to evaluate the index's effect and avoid unnecessary indexes.

## 3. What is ACID compliance?
ACID stands for Atomicity, Consistency, Isolation, Durability. These properties ensure reliable transactions even in the presence of errors or crashes.
