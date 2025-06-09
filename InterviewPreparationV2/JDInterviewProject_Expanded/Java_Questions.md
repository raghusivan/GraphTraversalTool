# Frequently Asked Java Interview Questions

The following collection contains commonly asked Java interview questions with concise explanations and code snippets. These questions focus on fundamental concepts that appear often in interviews.

## Questions and Answers

1. **What is the difference between JDK and JRE?**

   The JDK (Java Development Kit) includes tools for developing and debugging Java applications in addition to the JRE (Java Runtime Environment). The JRE contains the JVM and standard libraries needed to run Java programs but lacks development tools.

2. **Explain the use of the `public`, `private`, and `protected` access modifiers.**

   - `public`: accessible from any other class.
   - `protected`: accessible within the same package and subclasses.
   - `private`: accessible only within the declared class.

3. **What are wrapper classes in Java?**

   Wrapper classes provide object representations for primitive types. Examples include `Integer` for `int` and `Double` for `double`. They are useful for collections and APIs that require objects instead of primitives.

4. **How do you create an array in Java?**

   Arrays hold a fixed number of elements of the same type. Example:
   ```java
   int[] numbers = new int[5];
   numbers[0] = 1;
   ```

5. **What is a `package` in Java?**

   Packages organize related classes. Declaring `package com.example;` at the top of a file places the class in that package and determines its directory structure.

6. **Describe the concept of method overloading.**

   Method overloading allows multiple methods with the same name but different parameter lists in the same class. The compiler determines which method to call based on argument types.

7. **What is method overriding?**

   A subclass can provide its own implementation of a method defined in its superclass. The method signature must be identical. The `@Override` annotation is recommended to catch errors.

8. **Explain the `static` keyword.**

   `static` members belong to the class rather than an instance. Static methods can be called without creating an object. Static blocks initialize static variables when the class loads.

9. **What is the difference between `String`, `StringBuilder`, and `StringBuffer`?**

   - `String` objects are immutable.
   - `StringBuilder` is mutable and not synchronized, suitable for single-threaded contexts.
   - `StringBuffer` is synchronized and thread-safe, but often slower than `StringBuilder`.

10. **How do you handle exceptions in Java?**

   Use `try-catch` blocks to handle checked and unchecked exceptions. Optionally include a `finally` block for cleanup.
   ```java
   try {
       riskyCall();
   } catch (IOException e) {
       e.printStackTrace();
   } finally {
       resource.close();
   }
   ```

11. **What is the purpose of the `throws` keyword?**

   Methods declare checked exceptions using `throws`, informing callers that they must handle or further declare the exception.

12. **How does the `finally` block work?**

   The `finally` block executes after `try` and `catch` regardless of whether an exception occurred, making it useful for releasing resources.

13. **Explain the difference between `==` and `.equals()` for objects.**

   `==` compares references, while `.equals()` checks logical equality. Classes like `String` override `.equals()` to compare contents.

14. **What is a constructor? Can you overload one?**

   A constructor initializes an object when it is created. Constructors can be overloaded by defining multiple constructors with different parameters.

15. **What are the primitive data types in Java?**

   The eight primitives are `byte`, `short`, `int`, `long`, `float`, `double`, `char`, and `boolean`.

16. **Describe the Java `switch` statement.**

   `switch` selects among multiple branches based on a variable's value. Example:
   ```java
   switch(day) {
       case MONDAY -> System.out.println("Start");
       case FRIDAY -> System.out.println("End");
       default -> System.out.println("Midweek");
   }
   ```

17. **What is the `break` statement used for?**

   `break` exits the nearest loop or `switch` statement immediately.

18. **How do you create a thread by implementing `Runnable`?**

   ```java
   class Task implements Runnable {
       public void run() {
           System.out.println("Running");
       }
   }
   Thread t = new Thread(new Task());
   t.start();
   ```

19. **Explain the difference between `ArrayList` and `LinkedList`.**

   `ArrayList` provides fast random access but slower insertions and deletions in the middle. `LinkedList` allows constant-time insertions and deletions at known positions but slower random access.

20. **What does the `final` keyword do?**

   - Applied to variables, it makes them immutable after initialization.
   - Applied to methods, it prevents overriding.
   - Applied to classes, it prevents inheritance.

21. **What is a `Map`? Give an example.**

   A `Map` stores key-value pairs and does not allow duplicate keys. Example using `HashMap`:
   ```java
   Map<String, Integer> ages = new HashMap<>();
   ages.put("Alice", 30);
   int age = ages.get("Alice");
   ```

22. **How does `HashSet` differ from `TreeSet`?**

   `HashSet` stores elements in an unordered way and allows fast lookups. `TreeSet` stores elements in sorted order using their natural ordering or a comparator.

23. **What are generics in Java?**

   Generics provide type safety for collections and other classes by allowing you to specify parameter types. Example:
   ```java
   List<String> names = new ArrayList<>();
   names.add("Bob");
   ```

24. **What is a `ClassLoader`?**

   A `ClassLoader` loads classes into the JVM. The system class loader loads classes from the classpath, while custom class loaders can load classes from other sources.

25. **Explain the difference between checked and unchecked exceptions.**

   Checked exceptions must be declared or caught at compile time. Unchecked exceptions (runtime exceptions) do not require explicit handling.

26. **How do you read text from a file?**

   ```java
   try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
       String line = br.readLine();
   }
   ```

27. **What is the purpose of `serialization`?**

   Serialization converts an object into a byte stream for persistence or transmission. Implement `Serializable` to enable it.

28. **Describe autoboxing and unboxing.**

   Autoboxing automatically converts primitives to their wrapper types, while unboxing converts wrappers back to primitives.

29. **What is the Java Collections Framework?**

   A set of interfaces and classes (like `List`, `Set`, `Map`) for storing and manipulating groups of objects.

30. **How do you implement a singleton?**

   ```java
   public class Singleton {
       private static final Singleton INSTANCE = new Singleton();
       private Singleton() {}
       public static Singleton getInstance() { return INSTANCE; }
   }
   ```

31. **What is polymorphism?**

   Polymorphism allows objects of different types to be treated as instances of the same superclass, enabling dynamic method dispatch.

32. **Explain `inheritance`.**

   Inheritance allows a class to acquire fields and methods from another class using the `extends` keyword.

33. **What is encapsulation?**

   Encapsulation hides internal state through private fields and exposes behavior via public methods, improving maintainability.

34. **Why is `main` declared as `public static void main(String[] args)`?**

   - `public`: accessible by the JVM.
   - `static`: no object required to invoke it.
   - `void`: it does not return a value.
   - `String[] args`: accepts command-line arguments.

35. **What is the difference between `throw` and `throws`?**

   `throw` is used to explicitly throw an exception. `throws` declares that a method might throw an exception.

36. **How do you create a `List` from an array?**

   ```java
   String[] arr = {"a", "b"};
   List<String> list = Arrays.asList(arr);
   ```

37. **Explain the `try-with-resources` statement.**

   Declares resources in parentheses and automatically closes them at the end of the statement.

38. **What is a lambda expression?**

   A concise way to implement functional interfaces using `() -> {}` syntax.
   ```java
   Runnable r = () -> System.out.println("Hello");
   ```

39. **What is the default value of a member variable?**

   Uninitialized instance variables get default values like `0`, `false`, or `null` depending on their type.

40. **Describe the `equals` and `hashCode` contract.**

   If two objects are equal according to `equals`, they must have the same `hashCode` value. This is essential when using objects as keys in hash-based collections.

41. **How do you create an immutable class?**

   Mark the class `final`, make fields `private final`, and do not provide setters. Initialize all fields in the constructor.

42. **What is a `Scanner` used for?**

   `java.util.Scanner` reads input from various sources such as `System.in` or files.
   ```java
   Scanner scanner = new Scanner(System.in);
   String name = scanner.nextLine();
   ```

43. **Explain the role of the JVM.**

   The Java Virtual Machine executes compiled Java bytecode, providing portability across platforms through its abstraction layer.

44. **What are annotations?**

   Annotations provide metadata about code and can influence compilation or runtime behavior. Example: `@Override`.

45. **How can you handle multiple exceptions in one catch block?**

   ```java
   try {
       process();
   } catch (IOException | SQLException e) {
       e.printStackTrace();
   }
   ```

46. **What is the `transient` keyword?**

   Fields marked `transient` are skipped during serialization.

47. **Explain the difference between `System.out` and `System.err`.**

   `System.out` is for regular output; `System.err` is for error messages. Both are `PrintStream` objects.

48. **What is the purpose of the `volatile` keyword?**

   It ensures that reads and writes to a variable are directly from and to main memory, providing visibility across threads.

49. **Describe garbage collection in Java.**

   The JVM automatically frees memory by removing objects that are no longer reachable, reducing the chance of memory leaks.

50. **How do you read command-line arguments?**

   They are passed to `main` as the `String[] args` parameter. Access them by index:
   ```java
   public static void main(String[] args) {
       if (args.length > 0) {
           System.out.println("First arg: " + args[0]);
       }
   }
   ```
