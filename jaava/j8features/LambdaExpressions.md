Lambda expressions were introduced in Java 8 as part of the language's move toward functional programming. They provide
a clear and concise way to represent a single method interface (functional interface) using an expression rather than an
entire class implementation. Lambda expressions enable you to write more readable and compact code, especially when
dealing with collections and streams.

### Key Features of Lambda Expressions

1. **Conciseness**: Lambda expressions reduce the boilerplate code required for implementing interfaces with a single
   method.
2. **Functional Programming**: Lambdas enable a functional programming style in Java, allowing functions to be treated
   as first-class citizens.
3. **Type Inference**: The Java compiler can often infer the types of parameters, making the syntax more concise.
4. **Contextual Use**: Lambdas can be passed as arguments, returned from methods, or assigned to variables, giving you
   great flexibility in how you use them.

### Syntax of Lambda Expressions

A lambda expression consists of three parts:

1. **Parameter List**: A comma-separated list of parameters enclosed in parentheses.
2. **Arrow Token (`->`)**: Separates the parameters from the body of the expression.
3. **Body**: Contains the logic or code to be executed, which can be a single expression or a block of statements.

**Basic Syntax:**

```java
(parameters) -> expression

// Or with a block of statements:
(parameters) -> { statements }
```

### Examples of Lambda Expressions

1. **Simple Lambda Expression:**

   **Example:**
   ```java
   // A lambda that takes two integers and returns their sum
   (int a, int b) -> a + b
   ```

   This lambda expression takes two parameters `a` and `b` and returns their sum.

2. **Lambda with Type Inference:**

   Java can infer the types of the parameters based on the context, so you can omit the types.

   **Example:**
   ```java
   // Type inference
   (a, b) -> a + b
   ```

3. **Lambda with a Block of Statements:**

   If the body of the lambda expression contains more than one statement, you need to enclose it in curly braces `{}`.

   **Example:**
   ```java
   (int a, int b) -> {
       int sum = a + b;
       System.out.println("Sum: " + sum);
       return sum;
   }
   ```

4. **Lambda with No Parameters:**

   **Example:**
   ```java
   // A lambda with no parameters
   () -> System.out.println("Hello, World!")
   ```

5. **Lambda with One Parameter:**

   If there is only one parameter, you can omit the parentheses.

   **Example:**
   ```java
   // A lambda with a single parameter
   s -> s.toUpperCase()
   ```

### Lambda Expressions and Functional Interfaces

Lambda expressions work only with functional interfaces, which are interfaces with exactly one abstract method. The
lambda expression provides the implementation for that method.

**Example:**

```java
@FunctionalInterface
interface MyFunction {
    int apply(int a, int b);
}

public class Main {
    public static void main(String[] args) {
        MyFunction sum = (a, b) -> a + b;
        System.out.println("Sum: " + sum.apply(5, 3)); // Output: Sum: 8
    }
}
```

In this example, the `MyFunction` interface has one abstract method `apply`, which is implemented by the lambda
expression `(a, b) -> a + b`.

### Common Use Cases of Lambda Expressions

1. **Using Lambda with Collections**:

   Lambda expressions are often used to perform operations on collections, such as sorting, filtering, and mapping.

   **Example: Sorting a List:**
   ```java
   import java.util.Arrays;
   import java.util.List;

   public class Main {
       public static void main(String[] args) {
           List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Dave");

           // Sort names in alphabetical order using a lambda
           names.sort((a, b) -> a.compareTo(b));

           System.out.println(names); // Output: [Alice, Bob, Charlie, Dave]
       }
   }
   ```

2. **Using Lambda with Streams API**:

   The Streams API, introduced in Java 8, heavily relies on lambda expressions for processing sequences of elements.

   **Example: Filtering and Mapping a List:**
   ```java
   import java.util.Arrays;
   import java.util.List;
   import java.util.stream.Collectors;

   public class Main {
       public static void main(String[] args) {
           List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Dave");

           // Filter names that start with "A" and convert them to uppercase
           List<String> filteredNames = names.stream()
                                             .filter(name -> name.startsWith("A"))
                                             .map(name -> name.toUpperCase())
                                             .collect(Collectors.toList());

           System.out.println(filteredNames); // Output: [ALICE]
       }
   }
   ```

3. **Event Handling with Lambdas**:

   In GUI applications (like those using Swing or JavaFX), lambdas can simplify event handling.

   **Example:**
   ```java
   import javax.swing.JButton;
   import javax.swing.JFrame;

   public class Main {
       public static void main(String[] args) {
           JFrame frame = new JFrame("Lambda Example");
           JButton button = new JButton("Click Me");

           // Using lambda expression for event handling
           button.addActionListener(e -> System.out.println("Button clicked!"));

           frame.add(button);
           frame.setSize(200, 200);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setVisible(true);
       }
   }
   ```

4. **Runnable and Callable with Lambdas**:

   Lambda expressions can also simplify the implementation of `Runnable` and `Callable` interfaces.

   **Example:**
   ```java
   public class Main {
       public static void main(String[] args) {
           // Runnable using lambda
           Runnable task = () -> System.out.println("Task executed");

           // Start the thread
           new Thread(task).start();
       }
   }
   ```

### Method References in Lambda Expressions

Method references are a shorthand notation of a lambda expression that executes a single method. They can be used to
refer to static methods, instance methods, or constructors.

**Example:**

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Dave");

        // Method reference to static method
        names.forEach(System.out::println);
    }
}
```

In this example, `System.out::println` is a method reference to the `println` method, which is used as an alternative to
the lambda expression `name -> System.out.println(name)`.

### Capturing Lambdas (Effectively Final Variables)

Lambda expressions can capture variables from the enclosing scope, but only if those variables are effectively final (
i.e., they are not modified after being assigned).

**Example:**

```java
public class Main {
    public static void main(String[] args) {
        String greeting = "Hello";

        // Lambda capturing a variable from the enclosing scope
        Runnable r = () -> System.out.println(greeting);

        // Start the thread
        new Thread(r).start();
    }
}
```

Here, the variable `greeting` is captured by the lambda expression and used in its body. The variable must remain
unchanged (effectively final) after it's assigned, otherwise the code won't compile.

### Exception Handling in Lambda Expressions

If a lambda expression throws a checked exception, it must be handled either within the lambda or by the method that
calls the lambda.

**Example:**

```java
import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args) throws Exception {
        // Lambda that throws an exception
        Callable<Integer> task = () -> {
            if (true) throw new Exception("Exception in lambda");
            return 42;
        };

        try {
            task.call();
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Output: Exception in lambda
        }
    }
}
```

### Conclusion

Lambda expressions in Java are a powerful feature that bring functional programming concepts into the language. They
make your code more concise and readable, especially when working with collections, streams, and event-driven
programming. Understanding how to use lambda expressions effectively is key to writing modern, efficient Java code.

---