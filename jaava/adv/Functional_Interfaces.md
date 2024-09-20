Functional interfaces are a key feature in Java, especially after the introduction of lambda expressions and the Stream
API in Java 8. A functional interface is an interface that contains exactly one abstract method, making it ideal for
representing single abstract operations or actions. These interfaces are the foundation of functional programming in
Java.

### Key Characteristics of Functional Interfaces:

1. **Single Abstract Method (SAM)**: A functional interface must have exactly one abstract method. This characteristic
   allows functional interfaces to be used with lambda expressions, method references, and constructor references.

2. **`@FunctionalInterface` Annotation**: Although not mandatory, it's good practice to use the `@FunctionalInterface`
   annotation to explicitly declare an interface as a functional interface. This annotation ensures that the interface
   cannot have more than one abstract method. If a second abstract method is added, the compiler will throw an error.

3. **Default and Static Methods**: A functional interface can have multiple `default` and `static` methods in addition
   to the single abstract method. These methods do not affect the functional nature of the interface because they are
   not abstract.

4. **Inheritance**: A functional interface can inherit from another interface as long as it does not add any new
   abstract methods. It can also inherit `default` or `static` methods from other interfaces.

### Example of a Functional Interface:

Here is a simple example of a functional interface:

```java
@FunctionalInterface
interface Calculator {
    int add(int a, int b);
}
```

This `Calculator` interface is a functional interface because it contains exactly one abstract method `add`.

### Using Functional Interfaces with Lambda Expressions:

Lambda expressions provide a concise way to implement functional interfaces. Since a functional interface has only one
abstract method, the lambda expression provides the implementation for that method.

**Example:**

```java
public class Main {
    public static void main(String[] args) {
        // Using a lambda expression to implement the Calculator interface
        Calculator calculator = (a, b) -> a + b;

        int result = calculator.add(10, 20);
        System.out.println("Result: " + result); // Output: Result: 30
    }
}
```

In this example, the lambda expression `(a, b) -> a + b` provides the implementation for the `add` method in
the `Calculator` interface.

### Common Functional Interfaces in Java:

Java 8 introduced several built-in functional interfaces in the `java.util.function` package, which are used extensively
in the Java API:

1. **`Predicate<T>`**:
    - Represents a boolean-valued function that takes a single argument.
    - Method: `boolean test(T t)`

   **Example:**
   ```java
   Predicate<String> isEmpty = s -> s.isEmpty();
   System.out.println(isEmpty.test("")); // Output: true
   ```

2. **`Function<T, R>`**:
    - Represents a function that takes a single argument and returns a result.
    - Method: `R apply(T t)`

   **Example:**
   ```java
   Function<Integer, String> intToString = i -> "Number: " + i;
   System.out.println(intToString.apply(5)); // Output: Number: 5
   ```

3. **`Consumer<T>`**:
    - Represents an operation that takes a single argument and returns no result (void).
    - Method: `void accept(T t)`

   **Example:**
   ```java
   Consumer<String> print = s -> System.out.println(s);
   print.accept("Hello, world!"); // Output: Hello, world!
   ```

4. **`Supplier<T>`**:
    - Represents a function that supplies a result without taking any arguments.
    - Method: `T get()`

   **Example:**
   ```java
   Supplier<Double> randomValue = () -> Math.random();
   System.out.println(randomValue.get()); // Output: Some random double value
   ```

5. **`BinaryOperator<T>`**:
    - Represents an operation upon two operands of the same type, producing a result of the same type.
    - Method: `T apply(T t1, T t2)`

   **Example:**
   ```java
   BinaryOperator<Integer> multiply = (a, b) -> a * b;
   System.out.println(multiply.apply(3, 5)); // Output: 15
   ```

6. **`UnaryOperator<T>`**:
    - Represents an operation on a single operand that produces a result of the same type.
    - Method: `T apply(T t)`

   **Example:**
   ```java
   UnaryOperator<Integer> square = x -> x * x;
   System.out.println(square.apply(4)); // Output: 16
   ```

### Functional Interfaces and Method References:

Method references provide another way to implement functional interfaces. A method reference is a shorthand for a lambda
expression that executes just one method.

**Example:**

```java
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Consumer<String> print = System.out::println;
        print.accept("Hello, Method Reference!"); // Output: Hello, Method Reference!
    }
}
```

In this example, `System.out::println` is a method reference that replaces the lambda
expression `s -> System.out.println(s)`.

### Functional Interfaces in Streams API:

Functional interfaces are heavily used in the Streams API to process collections of objects in a functional programming
style.

**Example:**

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Dave");

        // Using a stream to filter and collect names that start with 'A'
        List<String> namesStartingWithA = names.stream()
                                               .filter(name -> name.startsWith("A"))
                                               .collect(Collectors.toList());

        System.out.println(namesStartingWithA); // Output: [Alice]
    }
}
```

In this example, `filter` takes a `Predicate<String>` as its argument, which is provided as a lambda
expression (`name -> name.startsWith("A")`).

### Custom Functional Interfaces:

You can create your own custom functional interfaces if the existing ones in the `java.util.function` package don't meet
your needs.

**Example:**

```java
@FunctionalInterface
interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}

public class Main {
    public static void main(String[] args) {
        TriFunction<Integer, Integer, Integer, Integer> sum = (a, b, c) -> a + b + c;

        int result = sum.apply(1, 2, 3);
        System.out.println("Sum: " + result); // Output: Sum: 6
    }
}
```

Here, `TriFunction` is a custom functional interface that takes three arguments and returns a result.

### Conclusion:

Functional interfaces are a powerful feature in Java that enable functional programming techniques, allowing you to
write cleaner, more concise, and more flexible code. With the introduction of lambda expressions and method references
in Java 8, functional interfaces have become a cornerstone of modern Java development, particularly in the context of
the Streams API, event handling, and asynchronous programming.

Whether you're using built-in functional interfaces from the `java.util.function` package or creating your own,
understanding functional interfaces is essential for leveraging the full power of Java's functional programming
capabilities.