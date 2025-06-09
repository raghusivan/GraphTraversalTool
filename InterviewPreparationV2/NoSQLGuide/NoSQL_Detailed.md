# Detailed NoSQL Interview Questions and Answers

These answers provide more depth, including diagrams and code snippets where useful.

## 1. What is eventual consistency?
In distributed NoSQL systems, eventual consistency means that data updates propagate to all replicas over time. Clients may read stale data immediately after a write but will eventually see the latest value.

## 2. How can you shard a collection in MongoDB?
Enable sharding, choose a shard key, and add shards to the cluster. MongoDB routes queries using metadata stored in the config servers.

```
sh.enableSharding('users')
sh.shardCollection('users.profiles', {userId: 'hashed'})
```

## 3. Describe a typical DynamoDB table design for time-series data.
Use a partition key such as `deviceId` and a sort key like `timestamp`. Enable auto-scaling and set up Time to Live (TTL) to expire old items.
