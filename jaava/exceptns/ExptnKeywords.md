### **Detailed Explanation of Exception Handling Keywords in Java**

Java provides robust exception handling mechanisms through five key keywords: `try`, `catch`, `finally`, `throw`,
and `throws`. These keywords help manage runtime errors, ensuring that the program can continue running or fail
gracefully.

---

### **1. `try` Block**

The `try` block is used to wrap the code that might throw an exception. If an exception occurs within the `try` block,
it is caught by the corresponding `catch` block (if one exists).

**Syntax:**

```java
try {
    // Code that might throw an exception
}
```

**Example:**

```java
try {
    int result = 10 / 0; // This will throw an ArithmeticException
}
```

- **Key Points:**
    - The `try` block must be followed by either a `catch` block or a `finally` block.
    - You can have multiple statements inside a `try` block.
    - If no exception occurs, the code inside the `try` block is executed normally, and the `catch` block is skipped.

---

### **2. `catch` Block**

The `catch` block is used to handle exceptions thrown by the `try` block. You can catch specific types of exceptions and
handle them appropriately.

**Syntax:**

```java
catch (ExceptionType e) {
    // Code to handle the exception
}
```

**Example:**

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero.");
}
```

- **Key Points:**
    - You can have multiple `catch` blocks to handle different types of exceptions.
    - The `catch` block parameter (`e` in the example) is an object of the exception class that was thrown. It contains
      information about the exception.
    - If an exception occurs that does not match any of the `catch` blocks, it propagates up the call stack.

---

### **3. `finally` Block**

The `finally` block contains code that is always executed, regardless of whether an exception was thrown or caught. It
is typically used to release resources like closing files, streams, or connections.

**Syntax:**

```java
try {
    // Code that might throw an exception
} catch (ExceptionType e) {
    // Code to handle the exception
} finally {
    // Code that will always execute
}
```

**Example:**

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero.");
} finally {
    System.out.println("This will always execute.");
}
```

- **Key Points:**
    - The `finally` block is optional but recommended.
    - It will execute even if there is a `return` statement inside the `try` or `catch` block.
    - The `finally` block does not execute if the JVM exits (e.g., due to a `System.exit()` call) or if a fatal error
      occurs.

---

### **4. `throw` Keyword**

The `throw` keyword is used to explicitly throw an exception, either predefined or custom. It is commonly used to
enforce certain conditions or handle errors explicitly within the code.

**Syntax:**

```java
throw new ExceptionType("Error Message");
```

**Example:**

```java
if (age < 18) {
    throw new IllegalArgumentException("Age must be 18 or older.");
}
```

- **Key Points:**
    - You can throw any exception that extends the `Throwable` class, typically an `Exception` or `Error`.
    - When an exception is thrown using `throw`, the normal flow of the program is interrupted, and control is
      transferred to the nearest `catch` block or up the call stack if no matching `catch` block is found.

---

### **5. `throws` Keyword**

The `throws` keyword is used in a method signature to declare that a method may throw one or more exceptions. It is a
way of notifying the caller of the method that they should handle these exceptions.

**Syntax:**

```java
ReturnType methodName() throws ExceptionType1, ExceptionType2 {
    // Method code
}
```

**Example:**

```java
public void readFile(String fileName) throws IOException {
    FileReader file = new FileReader(fileName);
}
```

- **Key Points:**
    - If a method throws a checked exception, it must either handle it with a `try-catch` block or declare it
      using `throws`.
    - Multiple exceptions can be declared, separated by commas.
    - The `throws` clause only applies to checked exceptions. Unchecked exceptions (subclasses of `RuntimeException`) do
      not need to be declared.

---

### **Putting It All Together:**

Here’s a simple example that demonstrates the use of all these keywords:

```java
public class ExceptionHandlingExample {
    public static void main(String[] args) {
        try {
            int result = divide(10, 0);
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Exception caught: " + e.getMessage());
        } finally {
            System.out.println("Execution completed.");
        }
    }

    public static int divide(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return a / b;
    }
}
```

- **Explanation:**
    - The `divide` method declares that it may throw an `ArithmeticException` using the `throws` keyword.
    - Inside the `divide` method, if `b` is `0`, an `ArithmeticException` is thrown using the `throw` keyword.
    - The `main` method contains a `try` block that calls the `divide` method. If an exception occurs, it is caught by
      the `catch` block.
    - The `finally` block ensures that a completion message is always printed, regardless of whether an exception was
      thrown.

This example showcases the full process of handling exceptions, from detecting them to managing them gracefully in a
Java program.

---