### Overview of Exceptions in Java

In Java, an exception is an event that disrupts the normal flow of a program's execution. Exceptions are used to handle
errors or other exceptional conditions that arise during the execution of a program, such as trying to divide by zero,
accessing an invalid array index, or working with a file that does not exist. Java provides a robust framework for
handling exceptions, ensuring that your program can gracefully recover from unexpected situations.

### Exception Hierarchy

All exceptions in Java are derived from the `Throwable` class, which is the root of the exception hierarchy.
The `Throwable` class has two main subclasses:

1. **Exception**: Represents conditions that a reasonable application might want to catch. Most of the exceptions that a
   programmer needs to handle derive from this class.
2. **Error**: Represents serious problems that a reasonable application should not try to catch. Errors are usually
   caused by the environment in which the application is running (e.g., memory overflow, JVM crash). Errors are
   unchecked exceptions, and it is generally not advisable to handle them in the application.

### Types of Exceptions

1. **Checked Exceptions**:
    - These are exceptions that are checked at compile time. The compiler ensures that the program handles these
      exceptions in some way, either by using a `try-catch` block or by declaring the exception using the `throws`
      keyword.
    - Examples: `IOException`, `SQLException`, `ClassNotFoundException`.

2. **Unchecked Exceptions**:
    - Also known as runtime exceptions, these exceptions are not checked at compile time. They occur during the
      execution of the program and are usually a result of programming errors, such as logic errors or improper use of
      an API.
    - Examples: `NullPointerException`, `ArrayIndexOutOfBoundsException`, `ArithmeticException`.

3. **Errors**:
    - Errors are serious problems that an application typically cannot handle. They are usually outside the control of
      the application.
    - Examples: `OutOfMemoryError`, `StackOverflowError`, `VirtualMachineError`.

### Common Java Exceptions

1. **`NullPointerException`**:
    - Thrown when an application attempts to use `null` in a case where an object is required, such as calling a method
      on a `null` object.

2. **`ArrayIndexOutOfBoundsException`**:
    - Thrown when an attempt is made to access an array element with an invalid index (either negative or greater than
      the array size).

3. **`ArithmeticException`**:
    - Thrown when an exceptional arithmetic condition occurs, such as division by zero.

4. **`ClassNotFoundException`**:
    - Thrown when an application tries to load a class through its name and the class cannot be found.

5. **`IOException`**:
    - Thrown when an I/O operation fails or is interrupted.

6. **`SQLException`**:
    - Thrown when an error occurs while accessing a database.

### Exception Handling Mechanisms

Java provides several mechanisms to handle exceptions:

1. **Try-Catch Block**:
    - The `try` block contains the code that might throw an exception. The `catch` block is used to handle the
      exception. If an exception occurs in the `try` block, the control is transferred to the `catch` block.

   **Syntax:**
   ```java
   try {
       // Code that may throw an exception
   } catch (ExceptionType e) {
       // Code to handle the exception
   }
   ```

   **Example:**
   ```java
   try {
       int result = 10 / 0; // This will throw ArithmeticException
   } catch (ArithmeticException e) {
       System.out.println("Cannot divide by zero.");
   }
   ```

2. **Multiple Catch Blocks**:
    - You can have multiple `catch` blocks to handle different types of exceptions that might be thrown by the `try`
      block.

   **Example:**
   ```java
   try {
       int[] arr = new int[5];
       arr[10] = 7; // This will throw ArrayIndexOutOfBoundsException
   } catch (ArithmeticException e) {
       System.out.println("Arithmetic Exception occurred.");
   } catch (ArrayIndexOutOfBoundsException e) {
       System.out.println("Array Index Out Of Bounds Exception occurred.");
   }
   ```

3. **Finally Block**:
    - The `finally` block contains code that is always executed after the `try-catch` block, regardless of whether an
      exception was thrown or not. It is often used to release resources like closing a file or a database connection.

   **Syntax:**
   ```java
   try {
       // Code that may throw an exception
   } catch (ExceptionType e) {
       // Code to handle the exception
   } finally {
       // Code that will always be executed
   }
   ```

   **Example:**
   ```java
   try {
       int[] arr = new int[5];
       arr[10] = 7; // This will throw ArrayIndexOutOfBoundsException
   } catch (ArrayIndexOutOfBoundsException e) {
       System.out.println("Array Index Out Of Bounds Exception occurred.");
   } finally {
       System.out.println("This block is always executed.");
   }
   ```

4. **Throws Keyword**:
    - If a method does not handle a checked exception, it must declare it using the `throws` keyword. This keyword
      indicates that the method might throw an exception and that the caller of the method must handle it.

   **Syntax:**
   ```java
   void method() throws ExceptionType {
       // Method code
   }
   ```

   **Example:**
   ```java
   void readFile() throws IOException {
       FileReader file = new FileReader("file.txt");
       BufferedReader fileInput = new BufferedReader(file);
   }
   ```

5. **Throw Keyword**:
    - The `throw` keyword is used to explicitly throw an exception. It is often used to create a custom exception or to
      rethrow an exception caught in a `catch` block.

   **Syntax:**
   ```java
   throw new ExceptionType("Exception message");
   ```

   **Example:**
   ```java
   void checkAge(int age) {
       if (age < 18) {
           throw new IllegalArgumentException("Age must be at least 18.");
       }
   }
   ```

### Custom Exceptions

Java allows you to create your own exceptions by extending the `Exception` class or any of its subclasses. This is
useful when you want to handle application-specific conditions.

**Example:**

```java
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class Main {
    static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be at least 18.");
        }
    }

    public static void main(String[] args) {
        try {
            validateAge(16);
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage()); // Output: Age must be at least 18.
        }
    }
}
```

### Exception Propagation

When an exception is thrown in a method, it propagates up the call stack until it is caught or reaches the main method.
If it is not caught by any method, the program terminates.

**Example:**

```java
public class Main {
    static void methodA() throws Exception {
        methodB();
    }

    static void methodB() throws Exception {
        methodC();
    }

    static void methodC() throws Exception {
        throw new Exception("Exception in methodC");
    }

    public static void main(String[] args) {
        try {
            methodA();
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Output: Exception in methodC
        }
    }
}
```

### Best Practices for Exception Handling

1. **Use Specific Exceptions**: Always catch specific exceptions rather than a generic `Exception`. This makes the code
   easier to understand and debug.

2. **Use Finally for Cleanup**: Use the `finally` block to clean up resources like file streams, database connections,
   etc.

3. **Avoid Empty Catch Blocks**: Do not leave catch blocks empty. At the very least, log the exception so that the
   problem can be diagnosed later.

4. **Use Custom Exceptions**: Create custom exceptions for specific error conditions related to your application.

5. **Document Exceptions**: Use Javadoc comments to document the exceptions that a method might throw.

### Conclusion

Exception handling in Java is a powerful mechanism that allows developers to manage runtime errors effectively and
ensure that the program can recover from unexpected situations. By understanding the exception hierarchy, types of
exceptions, and how to handle them, you can write more robust and error-resistant applications.