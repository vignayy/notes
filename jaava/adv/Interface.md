An interface in Java is a reference type, similar to a class, that can contain only constants, method signatures,
default methods, static methods, and nested types. Interfaces cannot contain instance fields or constructors, and all
methods within an interface are abstract by default (until Java 8 introduced default and static methods).

### Key Characteristics of an Interface:

1. **Abstract Methods**: By default, all methods declared in an interface are abstract, meaning they do not have a body.
   A class implementing an interface must provide implementations for all its methods unless the class is abstract.

2. **Constants**: All fields in an interface are implicitly public, static, and final, which means they are constants.

3. **No Constructors**: Interfaces cannot have constructors because they cannot be instantiated directly.

4. **Multiple Inheritance**: A class can implement multiple interfaces, which allows Java to support a form of multiple
   inheritance, providing more flexibility.

5. **Default Methods (Java 8 and above)**: Interfaces can have default methods with a body, which means implementing
   classes can choose to override them or use the default implementation.

6. **Static Methods (Java 8 and above)**: Interfaces can also have static methods with a body, which are not inherited
   by implementing classes and can be called on the interface itself.

7. **Functional Interfaces (Java 8 and above)**: An interface with exactly one abstract method is known as a functional
   interface. These are often used with lambda expressions and method references.

### Declaring an Interface

You define an interface using the `interface` keyword.

```java
interface Animal {
    void makeSound(); // Abstract method
    int legs = 4; // Constant field
}
```

### Implementing an Interface

A class implements an interface using the `implements` keyword and must provide concrete implementations of all the
interface’s methods.

```java
class Dog implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof! Woof!");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.makeSound();
        System.out.println("Legs: " + Dog.legs);
    }
}
```

### Multiple Interface Implementation

A class can implement more than one interface, making it a versatile tool for defining capabilities that can be shared
across different classes.

```java
interface Animal {
    void makeSound();
}

interface Pet {
    void play();
}

class Dog implements Animal, Pet {
    @Override
    public void makeSound() {
        System.out.println("Woof! Woof!");
    }

    @Override
    public void play() {
        System.out.println("Playing fetch!");
    }
}
```

### Default Methods in Interfaces

Introduced in Java 8, default methods allow you to add new methods to interfaces without breaking the classes that
already implement those interfaces. Default methods have a body and can be overridden by implementing classes.

```java
interface Animal {
    void makeSound();

    default void sleep() { // Default method
        System.out.println("Sleeping...");
    }
}

class Dog implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof! Woof!");
    }

    // sleep() method can be overridden but it is optional
}
```

### Static Methods in Interfaces

Static methods in interfaces are similar to static methods in classes. They are not inherited by the implementing class
and can be called directly on the interface.

```java
interface Animal {
    static void info() {
        System.out.println("Animals are living beings.");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal.info(); // Calling static method of interface
    }
}
```

### Functional Interfaces and Lambda Expressions

A functional interface has exactly one abstract method and can represent a single functionality. These interfaces are
the foundation of lambda expressions in Java.

```java
@FunctionalInterface
interface Greeting {
    void sayHello(String name);
}

public class Main {
    public static void main(String[] args) {
        Greeting greet = (name) -> System.out.println("Hello, " + name);
        greet.sayHello("Alice");
    }
}
```

### Marker Interfaces

A marker interface is an interface with no methods or fields. It is used to mark or tag classes that implement it,
usually for some specific purpose defined by frameworks or the Java runtime itself.

**Example:**

- `Serializable`
- `Cloneable`
- `Remote`

```java
class ExampleClass implements Serializable {
    // This class is now marked as serializable
}
```

### Interface vs. Abstract Class

- **Multiple Inheritance**: Interfaces allow a class to implement multiple interfaces, while a class can inherit from
  only one abstract class.
- **Flexibility**: Interfaces are generally used to define a contract that multiple classes can implement in different
  ways. Abstract classes are used when there is a need for shared code (default behavior) among subclasses.
- **Fields**: Abstract classes can have instance variables, while interfaces can only have public, static, final
  constants.

### Conclusion

Interfaces are a core component of Java’s design, allowing for a flexible and powerful way to define contracts and
capabilities that classes can implement. They are key to Java’s support for multiple inheritance and are used
extensively in frameworks, libraries, and APIs. With features like default methods and static methods introduced in Java
8, interfaces have become even more versatile, allowing developers to evolve APIs without breaking existing
implementations.

---