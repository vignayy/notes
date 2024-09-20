### This Keyword

The `this` keyword in Java is a reference to the current object—the object whose method or constructor is being invoked.
It is commonly used in several situations to clarify or manipulate object-related actions.

### 1. **Referencing Instance Variables**

- When instance variables (class fields) and parameters have the same names, `this` is used to distinguish between them.
- It allows you to refer to the instance variable of the current object explicitly.

**Example:**

   ```java
   class MyClass {
       int x;

       MyClass(int x) {
           this.x = x;  // 'this.x' refers to the instance variable, 'x' refers to the constructor parameter.
       }

       void display() {
           System.out.println("x: " + this.x);  // Using 'this' is optional here.
       }
   }

   public class Main {
       public static void main(String[] args) {
           MyClass obj = new MyClass(10);
           obj.display();  // Output: x: 10
       }
   }
   ```

### 2. **Invoking Current Class Methods**

- You can use `this` to invoke another method of the current class from within a method.
- Though not necessary in most cases, it can make the code more readable and emphasize that a method of the same object
  is being called.

**Example:**

   ```java
   class MyClass {
       void method1() {
           System.out.println("Method 1");
           this.method2();  // 'this' is optional here.
       }

       void method2() {
           System.out.println("Method 2");
       }
   }

   public class Main {
       public static void main(String[] args) {
           MyClass obj = new MyClass();
           obj.method1();  
           // Output:
           // Method 1
           // Method 2
       }
   }
   ```

### 3. **Invoking Current Class Constructor**

- `this()` can be used to call one constructor from another constructor within the same class. This is useful for
  constructor chaining.
- It helps avoid code duplication and can simplify constructor code by reusing other constructors.

**Example:**

   ```java
   class MyClass {
       int x, y;

       MyClass() {
           this(10, 20);  // Calls the parameterized constructor.
           System.out.println("Default constructor");
       }

       MyClass(int x, int y) {
           this.x = x;
           this.y = y;
           System.out.println("Parameterized constructor");
       }
   }

   public class Main {
       public static void main(String[] args) {
           MyClass obj = new MyClass();
           // Output:
           // Parameterized constructor
           // Default constructor
       }
   }
   ```

### 4. **Passing the Current Object as a Parameter**

- `this` can be passed as an argument in a method or constructor call to pass the current object as a parameter.
- This is useful in event handling and callback mechanisms.

**Example:**

   ```java
   class MyClass {
       void display() {
           System.out.println("Display method called.");
       }

       void execute() {
           anotherMethod(this);  // Passing the current object to another method.
       }

       void anotherMethod(MyClass obj) {
           obj.display();
       }
   }

   public class Main {
       public static void main(String[] args) {
           MyClass obj = new MyClass();
           obj.execute();  // Output: Display method called.
       }
   }
   ```

### 5. **Returning the Current Object**

- `this` can be used to return the current object from a method. This is often seen in method chaining.

**Example:**

   ```java
   class MyClass {
       int x;

       MyClass setX(int x) {
           this.x = x;
           return this;
       }

       void display() {
           System.out.println("x: " + x);
       }
   }

   public class Main {
       public static void main(String[] args) {
           MyClass obj = new MyClass();
           obj.setX(10).display();  // Output: x: 10
       }
   }
   ```

### Key Points:

- The `this` keyword is implicitly available in every non-static method and constructor of a class.
- It clarifies which instance variables, methods, or constructors are being referenced when there might be ambiguity.
- It is especially useful in constructor chaining and method chaining to maintain code clarity and reduce redundancy.

---

### Final Keyword

In Java, the `final` keyword is used to :

- define `constants` (for Variables),
- *Prevent* `method overriding` (for Methods)
- *Prevent* `inheritance` (for Classes)

Here's a breakdown of its uses:

1. **Final Variables**: When a variable is declared as `final`, its value cannot be changed once it has been
   initialized. This is useful for defining constants.

   ```java
   final int MAX_VALUE = 100;
   ```

   Here, `MAX_VALUE` is a constant whose value cannot be altered.

2. **Final Methods**: When a method is declared as `final`, it cannot be overridden by subclasses. This ensures that the
   method's behavior remains unchanged in the inheritance hierarchy.

   ```java
   class Parent {
       final void show() {
           System.out.println("Parent class show method");
       }
   }

   class Child extends Parent {
       // This will cause a compile-time error
       // void show() {
       //     System.out.println("Child class show method");
       // }
   }
   ```

3. **Final Classes**: When a class is declared as `final`, it cannot be subclassed. This prevents other classes from
   inheriting from it.

   ```java
   final class Immutable {
       // class contents
   }

   // This will cause a compile-time error
   // class ExtendedImmutable extends Immutable {
   // }
   ```

Using `final` helps in enforcing certain behaviors and can make your code more predictable and easier to understand.

---