# Database Interview Questions

This section covers both SQL and NoSQL administration and usage topics.

## Questions and Answers

### 1. Compare SQL and NoSQL databases.
**Answer:** SQL databases store data in tables with predefined schemas and support ACID transactions. NoSQL databases use flexible schemas and scale horizontally, trading strict consistency for performance and availability in many cases.

### 2. What indexing strategies improve query performance in SQL?
**Answer:** Indexes on frequently filtered columns, composite indexes for multi-column searches, and covering indexes can speed up queries. It's important to balance index count with write performance and storage overhead.

### 3. How do you handle backups in a NoSQL database?
**Answer:** NoSQL backups often involve snapshotting data directories or using built-in replication to another cluster. Some cloud providers offer managed backup services. Regular backups and verification ensure data recovery is possible.

### 4. Explain normalization and when you might denormalize data.
**Answer:** Normalization reduces redundancy by splitting data into related tables. Denormalization duplicates data for read performance, useful in reporting or analytic scenarios where join performance is critical.

### 5. Describe a scenario where you would choose a document database over a relational database.
**Answer:** When the data structure is flexible or semi-structured and schema changes are frequent, a document database like MongoDB can simplify development. It suits content management systems and catalogs where each record may have varying fields.
