# Detailed Java Interview Questions and Answers

This guide offers clear explanations with code snippets for common Java topics.

## 1. What is the difference between `ArrayList` and `LinkedList`?
`ArrayList` stores elements in a dynamically resizing array, providing O(1) access but O(n) insertion in the middle. `LinkedList` is made of nodes linked together, enabling O(1) insertion but slower random access.

## 2. How does the `try-with-resources` statement work?
The construct automatically closes resources that implement `AutoCloseable` at the end of the block.

```java
try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
    return br.readLine();
}
```

## 3. What are functional interfaces?
A functional interface defines exactly one abstract method and can be implemented with a lambda expression. Example: `Runnable` and `Callable`.
