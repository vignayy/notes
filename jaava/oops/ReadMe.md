**EveryThing is an OBJECT**

Object-Oriented Programming (OOP) in Java is a programming paradigm centered around the concept of objects, which can
contain data (attributes or fields) and code (methods). OOP in Java allows developers to create modular, reusable, and
organized code by modeling real-world entities as objects. Here’s a breakdown of the key concepts in OOP:

Let's break down Object-Oriented Programming (OOP) in Java using simple words and relatable examples.

### 1. **Classes and Objects**

- **Class**: Think of a class as a blueprint or a recipe. Just like a recipe tells you how to make a cake, a class tells
  you how to create objects.
- **Object**: An object is like the actual cake you make using the recipe. If you have a class called `Car`, the object
  would be a specific car like a red Tesla.

**Example**:

- **Class**: Recipe for a cake.
- **Object**: The cake you bake following the recipe.
- In code:
  ```java
  class Car {
      String color;
      String model;

      void drive() {
          System.out.println("The car is driving.");
      }
  }

  public class Main {
      public static void main(String[] args) {
          Car myCar = new Car(); // The object, a specific car
          myCar.color = "Red";
          myCar.model = "Tesla Model S";
          myCar.drive(); // The car drives
      }
  }
  ```

### 2. **Encapsulation**

- **Encapsulation**: Imagine a capsule (like a pill) that holds medicine inside. Encapsulation in OOP means keeping the
  important data (like the medicine) safe inside a capsule (the object) and only allowing certain people to access it
  through proper channels.
- In code, you hide the data inside the object and control who can access or change it.

**Example**:

- You keep your money in a bank account (object) and access it through a teller or ATM (methods).

   ```java
   class BankAccount {
       private double balance;

       // Method to deposit money
       public void deposit(double amount) {
           if (amount > 0) {
               balance += amount;
           }
       }

       // Method to check balance
       public double getBalance() {
           return balance;
       }
   }
   ```

### 3. **Inheritance**

- **Inheritance**: Inheritance is like a child inheriting traits from their parents. For example, if your dad has brown
  eyes, you might inherit those brown eyes.
- In code, one class can inherit properties and methods from another class.

**Example**:

- A basic `Animal` class might have a method to eat. A `Dog` class can inherit this method because dogs eat too, and
  the `Dog` class can also add its own method to bark.

   ```java
   class Animal {
       void eat() {
           System.out.println("This animal eats food.");
       }
   }

   class Dog extends Animal {
       void bark() {
           System.out.println("The dog barks.");
       }
   }
   ```

### 4. **Polymorphism**

- **Polymorphism**: This word sounds complex, but it just means "many forms." It’s like how a smartphone can be used as
  a camera, a phone, or a music player. The same device has many forms.
- In code, a method might do different things depending on which object is using it.

**Example**:

- Imagine a remote control that can turn on a TV, a fan, or a light. The remote has one button, but depending on what
  it’s pointed at, it does something different.

   ```java
   class Animal {
       void sound() {
           System.out.println("Animal makes a sound");
       }
   }

   class Cat extends Animal {
       @Override
       void sound() {
           System.out.println("The cat meows");
       }
   }
   ```

### 5. **Abstraction**

- **Abstraction**: Abstraction is about simplifying complex things. Think of a car: you don’t need to know how the
  engine works to drive it; you just need to know how to use the steering wheel, pedals, and gear shift.
- In code, abstraction hides the complex details and shows only what you need to use.

**Example**:

- You don’t need to know how a computer works inside to use one. You just need to know how to open programs and type.

   ```java
   abstract class Shape {
       abstract void draw(); // Abstract method, no details provided here
   }

   class Circle extends Shape {
       void draw() {
           System.out.println("Drawing a circle.");
       }
   }
   ```

In short:

- **Classes** are blueprints, and **objects** are the actual things created using those blueprints.
- **Encapsulation** is like protecting your data inside an object.
- **Inheritance** lets new classes take on properties of existing classes.
- **Polymorphism** allows one method to do different things based on the object it’s acting on.
- **Abstraction** hides complex details and shows only what's necessary.

These concepts work together to make programming in Java more organized and efficient.

---