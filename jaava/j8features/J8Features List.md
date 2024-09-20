### Java 8 Key Features Cheat Sheet

1. **Lambda Expressions**
    - **Description**: Anonymous functions for concise code.
    - **Syntax**: `(params) -> expression`  
      **Example**:
      ```java
      Runnable r = () -> System.out.println("Hello!");
      ```

2. **Functional Interfaces**
    - **Description**: Interfaces with a single abstract method.
    - **Syntax**: `@FunctionalInterface`  
      **Example**:
      ```java
      @FunctionalInterface
      interface MyFunc { void apply(); }
      ```

3. **Streams API**
    - **Description**: Process sequences of elements with functional-style operations.
    - **Syntax**: `stream().operation()`  
      **Example**:
      ```java
      List<String> names = Arrays.asList("John", "Jane");
      names.stream().filter(name -> name.startsWith("J")).forEach(System.out::println);
      ```

4. **Default and Static Methods in Interfaces**
    - **Description**: Add concrete methods in interfaces.
    - **Syntax**:
      ```java
      interface MyInterface {
          default void defaultMethod() { }
          static void staticMethod() { }
      }
      ```  
      **Example**:
      ```java
      MyInterface.staticMethod();
      ```

5. **Method References**
    - **Description**: Shortcuts to method calls.
    - **Syntax**: `ClassName::methodName`  
      **Example**:
      ```java
      List<String> names = Arrays.asList("John", "Jane");
      names.forEach(System.out::println);
      ```

6. **Optional Class**
    - **Description**: Container for optional values, avoiding nulls.
    - **Syntax**: `Optional.of(value)`  
      **Example**:
      ```java
      Optional<String> opt = Optional.of("Hello");
      opt.ifPresent(System.out::println);
      ```

7. **New Date and Time API (java.time)**
    - **Description**: Modern classes for date and time handling.
    - **Syntax**: `LocalDate.now()`  
      **Example**:
      ```java
      LocalDate today = LocalDate.now();
      ```

8. **Nashorn JavaScript Engine**
    - **Description**: Run JavaScript code from Java.
    - **Syntax**: `new ScriptEngineManager().getEngineByName("nashorn")`  
      **Example**:
      ```java
      ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
      engine.eval("print('Hello')");
      ```

9. **Collectors in Stream API**
    - **Description**: Collect stream elements into collections.
    - **Syntax**: `Collectors.toList()`  
      **Example**:
      ```java
      List<String> result = names.stream().collect(Collectors.toList());
      ```

10. **Parallel Streams**
    - **Description**: Process streams in parallel for improved performance.
    - **Syntax**: `parallelStream()`  
      **Example**:
      ```java
      names.parallelStream().forEach(System.out::println);
      ```

---