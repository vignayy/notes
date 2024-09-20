Concise cheat sheet on the types of exceptions in Java:

### **Java Exceptions Cheat Sheet**

---

### **1. Exception Hierarchy**

- **`Throwable`**
    - Root class for all errors and exceptions.
    - Subclasses: **`Error`**, **`Exception`**

- **`Error`** (`java.lang.Error`)
    - Unrecoverable conditions; usually not caught.
    - Examples: **`OutOfMemoryError`**, **`StackOverflowError`**

- **`Exception`** (`java.lang.Exception`)
    - Recoverable conditions; should be handled by the program.
    - Subclasses: **`RuntimeException`**, **`IOException`**, etc.

---

### **2. Types of Exceptions**

#### **Checked Exceptions**

- Must be caught or declared in the method signature using `throws`.
- Direct subclasses of `Exception` but not `RuntimeException`.
- **Common Examples:**
    - **`IOException`**: Problems with I/O operations (e.g., file not found).
    - **`SQLException`**: Database access errors.
    - **`ClassNotFoundException`**: Class loading issues.

#### **Unchecked Exceptions**

- Do not require explicit handling; occur at runtime.
- Subclasses of `RuntimeException`.
- **Common Examples:**
    - **`NullPointerException`**: Attempt to use an object reference that is `null`.
    - **`ArrayIndexOutOfBoundsException`**: Array index is out of bounds.
    - **`ArithmeticException`**: Arithmetic operation (e.g., division by zero).
    - **`IllegalArgumentException`**: Passed an illegal or inappropriate argument.
    - **`NumberFormatException`**: Conversion from string to number failed.

---

### **3. Common Errors**

- **`OutOfMemoryError`**
    - Java heap space exhausted.
- **`StackOverflowError`**
    - Too deep recursion, exceeding stack size.

---

### **4. Custom Exceptions**

- Extend `Exception` for checked exceptions.
- Extend `RuntimeException` for unchecked exceptions.

---

### **5. Exception Handling Keywords**

- **`try`**: Block of code to monitor for exceptions.
- **`catch`**: Handles exceptions thrown by the try block.
- **`finally`**: Executes code regardless of exception handling.
- **`throw`**: Explicitly throws an exception.
- **`throws`**: Declares exceptions that a method may throw.

---