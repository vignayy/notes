In Java, the `abstract` keyword is used to define abstract classes and abstract methods. These concepts are central to
Java's approach to object-oriented programming, specifically in the context of inheritance and polymorphism.

### 1. **Abstract Class**

- An abstract class is a class that cannot be instantiated directly. Instead, it serves as a blueprint for other
  classes.
- You can declare an abstract class by using the `abstract` keyword before the class keyword.
- An abstract class can have abstract methods (methods without a body) and concrete methods (methods with a body).
- A subclass of an abstract class must implement all abstract methods of the superclass, or the subclass must also be
  declared abstract.

**Example:**

```java
abstract class Animal {
    abstract void makeSound(); // Abstract method

    void sleep() { // Concrete method
        System.out.println("Sleeping...");
    }
}
```

### 2. **Abstract Method**

- An abstract method is a method that does not have a body; it is declared without implementation.
- Abstract methods must be implemented by subclasses.
- You declare an abstract method within an abstract class using the `abstract` keyword.

**Example:**

```java
class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Woof! Woof!");
    }
}
```

### Key Points:

- Abstract classes are useful when you want to provide a common base class with some shared code but also require that
  subclasses provide specific implementations for certain methods.
- You cannot create objects of an abstract class directly. Instead, you use it as a base class from which other classes
  derive.
- Abstract methods do not have a body, and any class that extends an abstract class must either implement all its
  abstract methods or be declared abstract itself.

This concept is particularly useful when designing frameworks or libraries where you want to enforce a certain structure
while still allowing flexibility in implementation.