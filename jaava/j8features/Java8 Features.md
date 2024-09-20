Java 8 introduced several key features that revolutionized the way Java programming is approached. Here are the major
Java 8 features explained in detail:

### 1. **Lambda Expressions**

- **What is it?**
  A Lambda Expression is essentially an anonymous function that allows for cleaner and more concise code, especially for
  functional programming.

- **Syntax:**
  ```java
  (parameters) -> expression
  (parameters) -> { statements }
  ```

- **Example:**
  ```java
  // Before Java 8 (using an anonymous class)
  Runnable r1 = new Runnable() {
      @Override
      public void run() {
          System.out.println("Hello World!");
      }
  };
  
  // Using Lambda Expression
  Runnable r2 = () -> System.out.println("Hello World!");
  ```

- **Benefits:**
    - Eliminates the need for boilerplate code when using functional interfaces.
    - Makes the code more readable and compact.
    - Supports the functional programming paradigm.

### 2. **Functional Interfaces**

- **What is it?**
  A functional interface is an interface that contains exactly one abstract method. Java 8 introduced
  the `@FunctionalInterface` annotation to ensure that an interface qualifies as functional.

- **Common Functional Interfaces in Java 8:**
    - `Predicate<T>`: Takes a value and returns a boolean.
    - `Function<T, R>`: Takes a value and returns a result.
    - `Supplier<T>`: Takes no value and returns a result.
    - `Consumer<T>`: Takes a value and returns nothing.

- **Example:**
  ```java
  @FunctionalInterface
  public interface MyFunctionalInterface {
      void doWork();
  }
  
  MyFunctionalInterface work = () -> System.out.println("Doing work...");
  work.doWork();
  ```

### 3. **Streams API**

- **What is it?**
  Streams API provides a high-level abstraction for operations on a sequence of elements, such as collections. It
  supports functional-style operations on collections of objects and helps in processing large amounts of data
  efficiently.

- **Main operations on streams:**
    - **Filter**: Filters elements based on a condition.
    - **Map**: Transforms elements.
    - **Reduce**: Aggregates elements.

- **Example:**
  ```java
  List<String> names = Arrays.asList("John", "Jane", "Mark", "Chris");
  
  // Filter names that start with "J" and convert to uppercase
  List<String> result = names.stream()
                             .filter(name -> name.startsWith("J"))
                             .map(String::toUpperCase)
                             .collect(Collectors.toList());
  ```

- **Benefits:**
    - Allows easy parallelization with `parallelStream()`.
    - Simplifies the process of working with collections and reduces boilerplate code.

### 4. **Default and Static Methods in Interfaces**

- **What is it?**
  Java 8 allows you to add non-abstract methods in interfaces using the `default` and `static` keywords. This allows
  developers to add new functionality to interfaces without breaking existing implementations.

- **Example:**
  ```java
  public interface MyInterface {
      default void defaultMethod() {
          System.out.println("Default method in interface");
      }
      
      static void staticMethod() {
          System.out.println("Static method in interface");
      }
  }
  
  class MyClass implements MyInterface {
  }
  
  public class Main {
      public static void main(String[] args) {
          MyClass obj = new MyClass();
          obj.defaultMethod(); // Calls default method
          
          MyInterface.staticMethod(); // Calls static method
      }
  }
  ```

### 5. **Method References**

- **What is it?**
  Method references provide a shorthand for invoking methods. They can be used to replace Lambda expressions where the
  Lambda body is simply calling an existing method.

- **Syntax:**
  ```java
  ClassName::methodName
  ```

- **Types of method references:**
    - **Static method reference:** `ClassName::staticMethod`
    - **Instance method reference:** `object::instanceMethod`
    - **Constructor reference:** `ClassName::new`

- **Example:**
  ```java
  List<String> names = Arrays.asList("John", "Jane", "Mark", "Chris");
  
  // Using Lambda
  names.forEach(name -> System.out.println(name));
  
  // Using Method Reference
  names.forEach(System.out::println);
  ```

### 6. **Optional Class**

- **What is it?**
  `Optional` is a container object which may or may not contain a non-null value. It helps in
  avoiding `NullPointerException` by providing a way to represent the absence of a value.

- **Common Methods:**
    - `isPresent()`: Checks if a value is present.
    - `ifPresent()`: Executes a code block if a value is present.
    - `orElse()`: Returns a default value if no value is present.

- **Example:**
  ```java
  Optional<String> optional = Optional.ofNullable(null);
  
  // Check if value is present
  if(optional.isPresent()) {
      System.out.println(optional.get());
  } else {
      System.out.println("Value is absent");
  }
  
  // Or use default value
  String result = optional.orElse("Default Value");
  System.out.println(result);
  ```

### 7. **New Date and Time API (java.time package)**

- **What is it?**
  Java 8 introduced a new `java.time` package that provides a comprehensive set of classes for date and time
  manipulation. The new API is more flexible and thread-safe compared to the old `java.util.Date`
  and `java.util.Calendar`.

- **Key Classes:**
    - `LocalDate`: Represents a date without time.
    - `LocalTime`: Represents a time without a date.
    - `LocalDateTime`: Combines date and time.
    - `ZonedDateTime`: Represents a date-time with a time-zone.
    - `Period` and `Duration`: Represents amounts of time.

- **Example:**
  ```java
  LocalDate today = LocalDate.now();
  LocalDate birthday = LocalDate.of(1990, Month.APRIL, 15);
  Period period = Period.between(birthday, today);
  System.out.println("Age: " + period.getYears());
  ```

### 8. **Nashorn JavaScript Engine**

- **What is it?**
  Java 8 introduced the Nashorn JavaScript Engine, which allows you to run JavaScript code directly from within Java
  applications. This feature enables better interoperability between Java and JavaScript.

- **Example:**
  ```java
  ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
  engine.eval("print('Hello from JavaScript')");
  ```

### 9. **Collectors in Stream API**

- **What is it?**
  Collectors provide a way to accumulate the elements of a stream into a collection, a string, or another result type.

- **Common Collectors:**
    - `toList()`: Collects elements into a `List`.
    - `joining()`: Concatenates stream elements into a single string.
    - `groupingBy()`: Groups elements by a certain property.

- **Example:**
  ```java
  List<String> names = Arrays.asList("John", "Jane", "Mark", "Chris");
  
  // Joining names with a delimiter
  String result = names.stream()
                       .collect(Collectors.joining(", "));
  System.out.println(result); // Outputs: John, Jane, Mark, Chris
  ```

### 10. **Parallel Streams**

- **What is it?**
  Parallel streams enable parallel processing of stream operations. It divides the data into multiple chunks, processes
  them in parallel, and combines the results.

- **Example:**
  ```java
  List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
  
  // Processing in parallel
  numbers.parallelStream()
         .forEach(System.out::println);
  ```

---

These are some of the key features introduced in Java 8. Each of these features brought significant improvements in
terms of performance, readability, and maintainability of the code.