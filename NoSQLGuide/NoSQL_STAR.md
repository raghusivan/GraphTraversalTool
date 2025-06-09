# NoSQL Interview Questions in STAR Format

This guide includes frequently asked NoSQL questions using the **Situation, Task, Action, Result** structure.

## Questions and Answers

### 1. How did you design a schema for a document database?
**Situation:** The application required flexible storage for user profiles.
**Task:** Model data in a document-oriented way.
**Action:** Used nested objects in MongoDB and created indexes on frequently queried fields.
**Result:** Achieved faster lookups with minimal data duplication.

### 2. Explain a time when you tuned a Cassandra cluster.
**Situation:** Write operations were slow under heavy traffic.
**Task:** Optimize the cluster for performance.
**Action:** Adjusted replication factor and compaction strategy, and enabled write-back cache.
**Result:** Write throughput improved significantly.

### 3. Describe your caching strategy with Redis.
**Situation:** API responses were computationally expensive.
**Task:** Reduce latency for frequent requests.
**Action:** Implemented a Redis cache with expiration policies based on data freshness.
**Result:** Response time dropped to under 50 ms.
