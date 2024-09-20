**OOPs Concepts**

### 1. **Classes and Objects**

- **Class**: A blueprint for creating objects. It defines the properties (fields) and behaviors (methods) that the
  objects created from the class will have.
- **Object**: An instance of a class. Objects hold specific values for the properties defined in the class.

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
           Car myCar = new Car(); // Creating an object of the Car class
           myCar.color = "Red";
           myCar.model = "Tesla Model S";
           myCar.drive(); // Calling the drive method
       }
   }
   ```

### 2. **Encapsulation**

- Encapsulation is the concept of wrapping the data (fields) and code (methods) together as a single unit. In Java, this
  is typically achieved by using access modifiers (like `private`, `protected`, `public`) to restrict access to the
  fields and providing public methods (getters and setters) to access or modify them.

   ```java
   class Person {
       private String name;
       private int age;

       // Getter for name
       public String getName() {
           return name;
       }

       // Setter for name
       public void setName(String newName) {
           this.name = newName;
       }
   }
   ```

### 3. **Inheritance**

- Inheritance allows a new class (subclass or child class) to inherit fields and methods from an existing class (
  superclass or parent class). This promotes code reusability.

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

   public class Main {
       public static void main(String[] args) {
           Dog myDog = new Dog();
           myDog.eat(); // Inherited method
           myDog.bark(); // Method from Dog class
       }
   }
   ```

### 4. **Polymorphism**

- Polymorphism allows methods to do different things based on the object that it is acting upon. It can be achieved
  through method overloading (same method name with different parameters) and method overriding (subclass provides a
  specific implementation of a method that is already defined in its superclass).

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

   public class Main {
       public static void main(String[] args) {
           Animal myAnimal = new Animal();
           Animal myCat = new Cat();
           myAnimal.sound();
           myCat.sound(); // Calls the overridden method in Cat
       }
   }
   ```

### 5. **Abstraction**

- Abstraction is the concept of hiding the complex implementation details and showing only the essential features of an
  object. This is achieved in Java using abstract classes and interfaces.

   ```java
   abstract class Shape {
       abstract void draw(); // Abstract method
   }

   class Circle extends Shape {
       void draw() {
           System.out.println("Drawing a circle.");
       }
   }

   public class Main {
       public static void main(String[] args) {
           Shape myShape = new Circle();
           myShape.draw();
       }
   }
   ```

In summary, OOP in Java helps organize code into a more modular and manageable structure by using classes and objects,
which model real-world entities, and by leveraging the principles of encapsulation, inheritance, polymorphism, and
abstraction.

---

