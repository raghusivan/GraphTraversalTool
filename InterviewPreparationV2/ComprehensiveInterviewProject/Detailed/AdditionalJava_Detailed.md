# Advanced Java Interview Questions with Detailed Answers

These questions cover high-level Java concepts with thorough explanations and code samples.

## 1. What is a soft reference and when would you use it?
Soft references allow objects to be collected only when memory is low. They are implemented with `java.lang.ref.SoftReference`. Soft references are useful for implementing caches because they give the garbage collector discretion to reclaim memory under pressure.

```java
SoftReference<MyObject> ref = new SoftReference<>(new MyObject());
MyObject cached = ref.get();
```

## 2. How do you customize serialization?
Implement the `Externalizable` interface to control the serialization mechanism. Override `writeExternal` and `readExternal` to specify the fields to write and read, giving full control over the serialized form.

```java
public class Config implements Externalizable {
    private String value;
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(value);
    }
    public void readExternal(ObjectInput in) throws IOException {
        value = in.readUTF();
    }
}
```

## 3. Explain the difference between `HashMap` and `ConcurrentHashMap`.
`HashMap` is not thread-safe, whereas `ConcurrentHashMap` allows concurrent access by locking segments internally. Use `ConcurrentHashMap` when multiple threads need to read/write without explicit synchronization.
