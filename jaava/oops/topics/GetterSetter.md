Setters and getters in Java are methods that allow controlled access to private variables (fields) in a class. They
follow the principle of encapsulation, which is one of the key principles of object-oriented programming (OOP).
Encapsulation involves hiding the internal state of an object and requiring all interactions to be performed through
methods. This ensures that the internal representation of the object can be changed without affecting the code that uses
it.

### What Are Getters and Setters?

- **Getters (Accessor Methods):** Methods that retrieve the value of a private variable.
- **Setters (Mutator Methods):** Methods that set or update the value of a private variable.

### Why Use Setters and Getters?

- **Encapsulation:** By making fields private and using getters and setters, you can control how fields are accessed and
  modified.
- **Data Validation:** Setters can include validation logic to ensure that only valid data is assigned to fields.
- **Flexibility:** You can change the internal implementation without affecting the external code that uses the class.
- **Read-Only and Write-Only Fields:** You can create read-only fields by providing only a getter or write-only fields
  by providing only a setter.

### How to Implement Setters and Getters?

Here's how you can implement setters and getters in Java.

#### Example with Getters and Setters

```java
class Person {
    // Private fields
    private String name;
    private int age;

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for age
    public int getAge() {
        return age;
    }

    // Setter for age with validation
    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            System.out.println("Age must be positive.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Person person = new Person();

        // Using setter to set values
        person.setName("John");
        person.setAge(30);

        // Using getter to retrieve values
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());

        // Attempt to set an invalid age
        person.setAge(-5);  // Output: Age must be positive.
    }
}
```

### Detailed Explanation

1. **Private Fields:**
    - The fields `name` and `age` are declared as `private`. This restricts direct access to these fields from outside
      the `Person` class.

2. **Getter Methods:**
    - The `getName()` method returns the value of the `name` field.
    - The `getAge()` method returns the value of the `age` field.

3. **Setter Methods:**
    - The `setName(String name)` method assigns the provided value to the `name` field.
    - The `setAge(int age)` method assigns the provided value to the `age` field, but it also includes a validation
      check to ensure the age is positive. If the provided age is not positive, it prints an error message instead of
      updating the field.

### Key Points About Getters and Setters

- **Naming Conventions:**
    - Getters typically start with `get`, followed by the capitalized name of the field (e.g., `getName()`).
    - Setters typically start with `set`, followed by the capitalized name of the field (e.g., `setName(String name)`).
    - For boolean fields, the getter might start with `is` instead of `get` (e.g., `isMarried()`).

- **Access Control:**
    - By using private fields with public getters and setters, you can control how the fields are accessed and modified.
    - For instance, if you want a field to be read-only, you can provide only a getter and omit the setter.

- **Validation in Setters:**
    - Setters can include validation logic to ensure that fields maintain valid values. This prevents invalid or
      inconsistent states within the object.

- **Immutability:**
    - If you want an object to be immutable (unchangeable after creation), you can omit setters entirely and only
      provide getters.

### Example of a Read-Only Field

```java
class Person {
    private final String name;

    // Constructor to initialize the name
    public Person(String name) {
        this.name = name;
    }

    // Getter only, no setter
    public String getName() {
        return name;
    }
}

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Alice");

        // Can retrieve the name
        System.out.println("Name: " + person.getName());

        // Cannot change the name, as there is no setter
        // person.setName("Bob");  // This would cause a compile-time error
    }
}
```

### Example of a Write-Only Field

```java
class Person {
    private String secretCode;

    // Setter only, no getter
    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }
}

public class Main {
    public static void main(String[] args) {
        Person person = new Person();

        // Can set the secret code
        person.setSecretCode("ABC123");

        // Cannot retrieve the secret code, as there is no getter
        // System.out.println(person.getSecretCode());  // This would cause a compile-time error
    }
}
```

### When to Use Getters and Setters?

- **When Fields Are Private:** Getters and setters are essential for accessing private fields.
- **When Validation Is Needed:** Use setters when you need to ensure that the values assigned to fields meet certain
  criteria.
- **When You Want to Hide Implementation Details:** Getters and setters allow you to change the internal representation
  of a field without changing the class's external interface.

### Advantages of Using Getters and Setters

1. **Encapsulation:** Protects the internal state of an object and provides controlled access.
2. **Validation:** Setters allow you to validate input data before setting it.
3. **Flexibility:** Internal implementation can change without affecting external code.
4. **Read-Only/Write-Only Access:** You can control whether a field is read-only, write-only, or both.

### Summary

- **Getters and setters** are methods that provide access to the private fields of a class while maintaining control
  over how those fields are accessed or modified.
- They are a fundamental part of Java’s encapsulation mechanism, allowing for data hiding, validation, and flexibility
  in how fields are accessed and updated.
- Proper use of getters and setters leads to better-designed and more maintainable code, ensuring that objects remain in
  a consistent and valid state throughout their lifecycle.

---