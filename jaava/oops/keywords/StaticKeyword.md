The `static` keyword in Java is used for memory management and can be applied to variables, methods, blocks, and nested
classes. Here's a breakdown of its uses:

### 1. **Static Variables**

- Also known as class variables.
- Shared among all instances of a class. When a variable is declared as static, only a single copy of it exists,
  regardless of the number of objects created from the class.
- The static variable is initialized only once, at the start of the execution, and remains in memory until the program
  ends.

**Example:**

   ```java
   class MyClass {
       static int count = 0;
       
       MyClass() {
           count++;
           System.out.println(count);
       }
   }

   public class Main {
       public static void main(String[] args) {
           MyClass obj1 = new MyClass(); // Output: 1
           MyClass obj2 = new MyClass(); // Output: 2
       }
   }
   ```

### 2. **Static Methods**

- Can be called without creating an object of the class. They belong to the class rather than any specific instance.
- Inside static methods, you can only access static data (variables) and call other static methods. You cannot directly
  access instance variables/methods.

**Example:**

   ```java
   class MyClass {
       static void staticMethod() {
           System.out.println("Static method called.");
       }
   }

   public class Main {
       public static void main(String[] args) {
           MyClass.staticMethod(); // Output: Static method called.
       }
   }
   ```

### 3. **Static Block**

- Used for static initializations of a class. This block runs only once when the class is loaded into memory, before any
  static variables or methods are accessed.

**Example:**

   ```java
   class MyClass {
       static {
           System.out.println("Static block executed.");
       }
   }

   public class Main {
       public static void main(String[] args) {
           MyClass obj = new MyClass(); // Output: Static block executed.
       }
   }
   ```

### 4. **Static Nested Classes**

- A static class defined within another class. It can access the static members of the outer class but cannot access
  non-static members directly.

**Example:**

   ```java
   class OuterClass {
       static int outerValue = 10;
       
       static class StaticNestedClass {
           void display() {
               System.out.println("Outer value: " + outerValue);
           }
       }
   }

   public class Main {
       public static void main(String[] args) {
           OuterClass.StaticNestedClass nested = new OuterClass.StaticNestedClass();
           nested.display(); // Output: Outer value: 10
       }
   }
   ```

### Key Points:

- **Static variables** save memory as they are shared among all instances of a class.
- **Static methods** can be called without creating an object, but they can't access instance variables/methods
  directly.
- **Static blocks** are useful for initializing static data or executing code once when the class is first loaded.
- **Static nested classes** can be used to logically group classes that are only used within the context of the outer
  class.

The `static` keyword is helpful for creating utility or helper methods that don't need to rely on object state or when
you want to maintain a single state across all instances of a class.