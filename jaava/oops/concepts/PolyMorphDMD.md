Polymorphism in Java is a concept that allows objects of different classes to be treated as objects of a common
superclass. It’s a core principle of object-oriented programming (OOP) and can be categorized mainly into two types:

1. **Compile-time Polymorphism (Method Overloading):** This occurs when multiple methods have the same name but
   different parameters within the same class. The method that gets called is determined at compile-time based on the
   method signature (name and parameter list).

   ```java
   class Example {
       void display(int a) {
           System.out.println("Integer: " + a);
       }

       void display(String a) {
           System.out.println("String: " + a);
       }
   }

   public class Main {
       public static void main(String[] args) {
           Example obj = new Example();
           obj.display(10);    // Calls the method with int parameter
           obj.display("Hello"); // Calls the method with String parameter
       }
   }
   ```

2. **Runtime Polymorphism (Method Overriding):** This occurs when a subclass provides a specific implementation of a
   method that is already defined in its superclass. The method that gets executed is determined at runtime based on the
   object’s actual class.

   ```java
   class Animal {
       void sound() {
           System.out.println("Animal makes a sound");
       }
   }

   class Dog extends Animal {
       @Override
       void sound() {
           System.out.println("Dog barks");
       }
   }

   public class Main {
       public static void main(String[] args) {
           Animal myDog = new Dog();
           myDog.sound(); // Calls the overridden method in Dog class
       }
   }
   ```

In the runtime example, even though `myDog` is of type `Animal`, the `sound` method from the `Dog` class is called
because the actual object is a `Dog`. This is a classic example of polymorphism in action.

---

Dynamic Method Dispatch is a mechanism in Java that allows a program to determine at runtime which method implementation
to invoke, based on the actual object type. It's closely related to runtime polymorphism and is a fundamental feature of
Java's object-oriented programming.

Here’s a breakdown of how it works:

1. **Method Overriding:** In Dynamic Method Dispatch, a subclass overrides a method defined in its superclass. This
   means that the subclass provides a specific implementation of the method that is different from the one in the
   superclass.

2. **Reference Variable:** You can use a reference variable of the superclass type to hold an object of the subclass.
   Even though the reference variable is of the superclass type, the actual object it refers to is of the subclass type.

3. **Method Call Resolution:** When you call an overridden method using the superclass reference variable, Java uses
   Dynamic Method Dispatch to determine which version of the method to execute. The decision is based on the actual
   object type (the subclass), not the reference variable type (the superclass).

### Example

Here’s an example to illustrate Dynamic Method Dispatch:

```java
class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {
    @Override
    void sound() {
        System.out.println("Cat meows");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal myAnimal;
        
        myAnimal = new Dog();
        myAnimal.sound();  // Outputs: Dog barks
        
        myAnimal = new Cat();
        myAnimal.sound();  // Outputs: Cat meows
    }
}
```

### Explanation

1. **Reference Variable:** `myAnimal` is a reference variable of type `Animal`.
2. **Object Creation:** `myAnimal` is first assigned a `Dog` object, and later, a `Cat` object.
3. **Method Call:** When `myAnimal.sound()` is called, Java uses Dynamic Method Dispatch to call the `sound` method of
   the actual object type (`Dog` or `Cat`), not the reference variable type (`Animal`).

This mechanism ensures that the method implementation that matches the actual object type is invoked, which supports
runtime polymorphism and allows for flexible and dynamic method execution.

---