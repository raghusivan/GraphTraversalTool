# NoSQL Interview Questions and Answers (Detailed)

This guide covers 50 popular NoSQL interview questions with thorough explanations, diagrams, and code snippets where appropriate. Topics span document, key-value, column, and graph databases, along with memory caching strategies.

## Questions and Answers

### 1. What is NoSQL and when should you choose it over SQL?
NoSQL refers to non-relational databases that provide flexible schemas and horizontal scaling. Choose NoSQL when your data is unstructured, requires high write throughput, or must scale to massive volumes across distributed nodes.

### 2. Explain document stores such as MongoDB.
Document databases store JSON-like documents. Each document can have a different structure, allowing dynamic schemas. Queries use indexes to search document fields efficiently.

### 3. How do key-value stores achieve fast lookups?
Key-value stores map keys directly to values in memory or on disk. By avoiding complex joins or schema checks, they can retrieve data in constant time. Redis keeps frequently accessed values in memory for sub-millisecond latency.

### 4. Describe column-family databases like Cassandra.
Column-family stores organize data by rows and columns, but columns can vary by row. Data is partitioned across nodes and written sequentially to immutable SSTables for efficient reads and writes.

### 5. What is eventual consistency?
Many NoSQL databases use eventual consistency to achieve high availability. Writes propagate asynchronously between replicas, so reads may temporarily return stale data until replicas converge.

### 6. How does sharding work in NoSQL systems?
Sharding partitions data across multiple nodes using a shard key. Each shard stores a subset of data, enabling linear scalability. A routing layer directs queries to the appropriate shard.

### 7. Explain replication in MongoDB.
MongoDB uses replica sets with a primary node and secondary nodes. The primary accepts writes and replicates them to secondaries. Clients can read from secondaries to balance load or ensure local reads.

### 8. What is a graph database and when would you use one?
Graph databases store nodes and edges representing relationships. They excel at traversing complex relationships, such as social networks or recommendation systems. Popular graph query languages include Cypher and Gremlin.

### 9. How do you model one-to-many relationships in a document store?
Embed the child documents inside the parent document if the relationship is mostly read together. Otherwise, store references using unique IDs and perform multiple queries or use a $lookup operation.

### 10. Explain how secondary indexes work in Cassandra.
Secondary indexes allow queries on non-primary key columns. Cassandra maintains index tables that map indexed values to the corresponding partitions. Overusing secondary indexes can lead to performance degradation.

### 11. What is MapReduce and how is it used in NoSQL?
MapReduce is a programming model for processing large data sets in parallel. Some NoSQL databases like MongoDB provide MapReduce functionality for batch analytics by mapping documents to intermediate results and reducing them to a final output.

### 12. Describe the CAP theorem.
The CAP theorem states that a distributed system can guarantee only two of the following: Consistency, Availability, or Partition tolerance. Many NoSQL databases favor availability and partition tolerance, sacrificing strong consistency.

### 13. How does Redis handle persistence?
Redis can store data entirely in memory with periodic snapshots (RDB) or append-only files (AOF) for durability. The append-only log helps reconstruct the dataset after a restart.

### 14. Explain the use of TTL indexes in MongoDB.
TTL (time to live) indexes automatically remove documents after a specified period. They are useful for expiring sessions or cached data without manual cleanup.

### 15. What are materialized views in Cassandra?
Materialized views automatically maintain alternate query patterns for a base table. They can simplify reads but may impact write throughput.

### 16. How do graph databases store edges efficiently?
Edges may be stored as adjacency lists, where each node maintains pointers to connected nodes. Some systems use compressed sparse row formats to optimize memory usage.

### 17. Describe consistent hashing.
Consistent hashing distributes data across nodes by hashing keys onto a ring. Adding or removing nodes minimally impacts the placement of existing keys, reducing rebalancing overhead.

### 18. How do you handle transactions in NoSQL databases?
Some NoSQL systems like MongoDB support multi-document ACID transactions. Others rely on single-document atomicity or application-level compensation to handle failure scenarios.

### 19. What is a wide-row design in Cassandra?
Wide rows store large amounts of related data under a single partition key. Columns can be added indefinitely, making it efficient for time-series or event data where you append new columns.

### 20. Explain the difference between eager and lazy fetching in graph databases.
Eager fetching retrieves all connected nodes at once, which can be memory intensive. Lazy fetching loads related nodes on demand, reducing initial memory consumption for large traversals.

### 21. Describe how Redis Pub/Sub works.
Redis provides a publish/subscribe messaging pattern. Clients subscribe to channels, and publishers send messages. Messages are delivered in-memory to subscribed clients without persistent storage.

### 22. How do you back up a MongoDB database?
Use `mongodump` to create binary backups or enable continuous backup with MongoDB Atlas. Replication and snapshots at the storage level also help protect data.

### 23. What are tombstones in Cassandra?
Tombstones mark deleted columns or rows. During compaction, Cassandra permanently removes tombstoned data. Excessive tombstones can increase disk usage and slow queries.

### 24. Explain Gremlin traversal language.
Gremlin is a graph traversal language that walks through vertices and edges. Queries are expressed as a chain of steps like `g.V().has("name","Alice").out("knows")` to find all friends of Alice.

### 25. How does a document database handle indexing arrays?
An index can be created on each array element so that queries match documents containing a value in the array. MongoDB automatically indexes array values when using a standard field index.

### 26. Describe the architecture of Couchbase.
Couchbase combines key-value and document database features. It uses a memory-first architecture where data is stored in RAM and asynchronously persisted to disk. A built-in cache layer accelerates reads.

### 27. What is eventual consistency in DynamoDB?
DynamoDB offers both eventually consistent and strongly consistent reads. Eventually consistent reads may return stale data for a short time but provide lower latency and higher throughput.

### 28. Explain column pruning in Cassandra.
Cassandra can read only the needed columns from disk rather than entire rows. Column pruning reduces disk I/O and memory usage for queries that access a subset of columns.

### 29. How do you model many-to-many relationships in a document database?
Use an array of references or a separate collection to store relationship mappings. Embedding may be viable if the nested documents are small and rarely change.

### 30. What is Neo4j and how does it store data on disk?
Neo4j is a popular graph database that stores nodes and relationships in a custom format on disk. It also maintains in-memory caches for frequently accessed nodes and edges to accelerate queries.

### 31. Describe the write-ahead log in MongoDB.
MongoDB's journaling uses a write-ahead log to record changes before applying them. In a crash, the journal is replayed to ensure durability of committed operations.

### 32. How does Cassandra achieve high write throughput?
Cassandra uses a log-structured storage engine. Writes are appended to a commit log and a memtable in memory. Memtables are periodically flushed to SSTables on disk, enabling sequential writes for speed.

### 33. Explain memory caching in Redis.
Redis stores data in RAM, providing extremely fast access times. It supports eviction policies like LRU (least recently used) to remove old keys when memory is full, ensuring efficient usage.

### 34. What is a BLOB store in NoSQL systems?
A BLOB (binary large object) store, such as Azure Blob Storage or Amazon S3, stores unstructured data like images or backups. It typically provides simple APIs for read/write and integrates with CDN services for delivery.

### 35. Describe how indexes are built in Elasticsearch.
Elasticsearch indexes documents by tokenizing text fields and building inverted indexes. Data is distributed across shards and replicas, with segments merged in the background for efficient searches.

### 36. How do you perform a bulk write in MongoDB?
Use the `bulkWrite` API to group inserts, updates, or deletes into a single network round trip. Bulk operations minimize latency and can be executed in parallel.

### 37. Explain the use of TTL caches in Redis.
A TTL cache stores keys with an expiration time. Redis automatically removes expired keys, allowing you to implement short-lived caches or session stores.

### 38. What is a graph traversal algorithm?
Algorithms like breadth-first search (BFS) or depth-first search (DFS) explore nodes in a graph. Graph databases often include built-in traversal features to efficiently navigate relationships.

### 39. Describe data modeling in DynamoDB.
DynamoDB uses partition keys and optional sort keys to organize items. Secondary indexes enable alternate query patterns. Denormalization and composite keys are common to achieve high performance.

### 40. How does Cassandra perform read repairs?
When a node responds with stale data, Cassandra can trigger a read repair to synchronize replicas in the background, ensuring eventual consistency.

### 41. What is MongoDB aggregation pipeline?
The aggregation pipeline processes data through multiple stages, such as filtering, grouping, and sorting. It allows complex analytics similar to SQL aggregates but operates on documents.

### 42. Explain ACID compliance in NewSQL databases.
NewSQL databases aim to combine ACID transactions with horizontal scalability. They use distributed consensus protocols and sharding to maintain strong consistency across nodes.

### 43. How do you monitor a Redis cluster?
Redis provides INFO commands and Redis Sentinel for monitoring. Metrics like memory usage, eviction count, and replication lag help track health and performance.

### 44. What is a graph query language?
Languages like Cypher, Gremlin, and SPARQL allow expressive queries over graph data. They provide pattern matching and path-finding capabilities.

### 45. Describe compaction in Cassandra.
Compaction merges SSTables to free space and improve read performance. It removes outdated or tombstoned data. Different compaction strategies exist, including size-tiered and leveled compaction.

### 46. How does MongoDB handle large file storage?
MongoDB uses GridFS to store and retrieve large files by splitting them into chunks. GridFS works well for files larger than 16 MB, which exceed the BSON document size limit.

### 47. Explain eventual consistency in Couchbase.
Couchbase replicates data asynchronously between nodes. Reads are eventually consistent by default, but you can request stronger consistency levels depending on your application needs.

### 48. What are vector clocks used for?
Vector clocks record causal relationships between updates in distributed systems. They help resolve conflicts by tracking the version history of each replica.

### 49. Describe memory-first architecture in Couchbase.
Couchbase keeps data in RAM for fast access and writes changes to disk asynchronously. This design provides high throughput while ensuring durability through replication and write-ahead logs.

### 50. How does a graph database support recommendation systems?
By modeling users and products as nodes with edges representing interactions, graph databases can efficiently traverse relationships to find similar users or items. Graph algorithms like PageRank or personalized recommendations use memory caches for quick scoring.

```
+--------+     Shard      +--------+
| Client | ----------->  | Node A |
+--------+               +--------+
      \                       |
       \----- Shard ------>  | Node B |
```
This diagram shows a simple sharded architecture where clients read and write to multiple nodes.
