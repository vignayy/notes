In Java, an inner class is a class that is defined within another class. Inner classes are a powerful feature because
they allow you to logically group classes that are only used in one place, which increases encapsulation. They also have
access to the members (including private members) of the outer class.

### Types of Inner Classes

1. **Member Inner Class**
2. **Static Nested Class**
3. **Local Inner Class**
4. **Anonymous Inner Class**

#### 1. **Member Inner Class**

- A member inner class is defined inside another class and is not static.
- It can access the outer class's members, including private ones.
- To instantiate a member inner class, you need an instance of the outer class.

**Example:**

```java
class OuterClass {
    private final String message = "Hello from Outer Class";

    class InnerClass {
        void display() {
            System.out.println(message); // Accessing outer class's private member
        }
    }
}

public class Main {
    public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        OuterClass.InnerClass inner = outer.new InnerClass();
        inner.display();
    }
}
```

#### 2. **Static Nested Class**

- A static nested class is similar to a member inner class but with the `static` modifier.
- Unlike member inner classes, a static nested class does not have access to the instance members of the outer class.
- It can only access static members of the outer class.

**Example:**

```java
class OuterClass {
    static String staticMessage = "Hello from Outer Class";

    static class StaticNestedClass {
        void display() {
            System.out.println(staticMessage); // Accessing outer class's static member
        }
    }
}

public class Main {
    public static void main(String[] args) {
        OuterClass.StaticNestedClass nested = new OuterClass.StaticNestedClass();
        nested.display();
    }
}
```

#### 3. **Local Inner Class**

- A local inner class is defined within a block of code, such as a method or a constructor.
- It can only be accessed within the block where it is defined.
- Like other inner classes, it has access to the members of the outer class, but it can also access local variables of
  the method if they are declared final or effectively final.

**Example:**

```java
class OuterClass {
    void outerMethod() {
        String localVar = "Local variable";

        class LocalInnerClass {
            void display() {
                System.out.println(localVar); // Accessing local variable
            }
        }

        LocalInnerClass localInner = new LocalInnerClass();
        localInner.display();
    }
}

public class Main {
    public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        outer.outerMethod();
    }
}
```

#### 4. **Anonymous Inner Class**

- An anonymous inner class is a local inner class without a name and is used to instantiate objects with certain
  modifications, usually to override methods of a class or interface.
- It is often used in event handling or in scenarios where a class needs to be used once.

**Example:**

```java
abstract class Animal {
    abstract void makeSound();
}

public class Main {
    public static void main(String[] args) {
        Animal dog = new Animal() { // Anonymous inner class
            void makeSound() {
                System.out.println("Woof Woof!");
            }
        };
        dog.makeSound();
    }
}
```

### Key Points:

- **Encapsulation**: Inner classes can hide implementation details, making your code more modular.
- **Access**: Inner classes can access the outer class's members, which can be handy in certain scenarios.
- **Organization**: Grouping related classes together improves code organization.

Inner classes can be a powerful tool in Java when used appropriately, providing more flexibility and control over the
structure and behavior of your code.