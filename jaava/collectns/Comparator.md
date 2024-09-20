### Overview of Comparator in Java

In Java, the `Comparator` interface is used to define custom orderings for objects that don't have a natural order or
when you want to order objects differently from their natural ordering. It is part of the `java.util` package and is
typically used in sorting operations where custom comparison logic is required.

### Why Use a Comparator?

- **Custom Sorting**: You can use a `Comparator` to sort objects in a way that differs from their natural order, or to
  define orderings for objects that don't implement the `Comparable` interface.
- **Multiple Sorting Sequences**: If you need to sort objects by different attributes, you can create multiple
  comparators, each implementing different sorting logic.

### The Comparator Interface

The `Comparator` interface contains the following key methods:

1. **`int compare(T o1, T o2)`**:
    - **Description**: Compares its two arguments for order. Returns a negative integer, zero, or a positive integer if
      the first argument is less than, equal to, or greater than the second.
    - **Return Values**:
        - A negative integer: `o1` is less than `o2`.
        - Zero: `o1` is equal to `o2`.
        - A positive integer: `o1` is greater than `o2`.

2. **`boolean equals(Object obj)`**:
    - **Description**: Indicates whether some other object is "equal to" this comparator. This method is typically not
      overridden.

### Creating a Comparator

There are two main ways to implement a `Comparator`:

1. **Anonymous Class**:
    - You can create a `Comparator` using an anonymous class.

   **Example:**
   ```java
   import java.util.*;

   class Main {
       public static void main(String[] args) {
           List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

           // Sort names in reverse order using Comparator
           Collections.sort(names, new Comparator<String>() {
               @Override
               public int compare(String s1, String s2) {
                   return s2.compareTo(s1); // Reverse order
               }
           });

           System.out.println(names); // Output: [Charlie, Bob, Alice]
       }
   }
   ```

2. **Lambda Expression**:
    - Since Java 8, you can use lambda expressions to simplify the creation of comparators.

   **Example:**
   ```java
   import java.util.*;

   class Main {
       public static void main(String[] args) {
           List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

           // Sort names in reverse order using Comparator with Lambda
           Collections.sort(names, (s1, s2) -> s2.compareTo(s1));

           System.out.println(names); // Output: [Charlie, Bob, Alice]
       }
   }
   ```

### Example: Sorting with Comparator

Let's consider an example where we have a `Person` class, and we want to sort a list of `Person` objects by different
criteria (e.g., by name, age).

**Person Class:**

```java
class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}
```

**Comparators for Sorting:**

```java
import java.util.*;

class Main {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 35));

        // Sort by name
        Comparator<Person> nameComparator = new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getName().compareTo(p2.getName());
            }
        };
        Collections.sort(people, nameComparator);
        System.out.println("Sorted by name: " + people);

        // Sort by age
        Comparator<Person> ageComparator = new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return Integer.compare(p1.getAge(), p2.getAge());
            }
        };
        Collections.sort(people, ageComparator);
        System.out.println("Sorted by age: " + people);
    }
}
```

**Output:**

```
Sorted by name: [Person{name='Alice', age=30}, Person{name='Bob', age=25}, Person{name='Charlie', age=35}]
Sorted by age: [Person{name='Bob', age=25}, Person{name='Alice', age=30}, Person{name='Charlie', age=35}]
```

### Comparator in Functional Programming

Java 8 introduced several default and static methods to the `Comparator` interface, making it more powerful and
flexible.

#### Key Methods:

1. **`thenComparing()`**:
    - Used to chain multiple comparators. For example, you can first sort by one attribute, and if they are equal, sort
      by another attribute.

   **Example:**
   ```java
   Comparator<Person> comparator = Comparator.comparing(Person::getName)
                                             .thenComparing(Person::getAge);
   Collections.sort(people, comparator);
   ```

2. **`reversed()`**:
    - Returns a comparator that imposes the reverse of the natural ordering.

   **Example:**
   ```java
   Comparator<Person> ageComparatorReversed = Comparator.comparing(Person::getAge).reversed();
   Collections.sort(people, ageComparatorReversed);
   ```

3. **`nullsFirst()` and `nullsLast()`**:
    - These methods handle `null` values. `nullsFirst()` returns a comparator that considers `null` to be less than
      non-null values, and `nullsLast()` considers `null` to be greater.

   **Example:**
   ```java
   Comparator<String> nullsFirstComparator = Comparator.nullsFirst(Comparator.naturalOrder());
   List<String> names = Arrays.asList("Alice", null, "Charlie", "Bob");
   Collections.sort(names, nullsFirstComparator);
   ```

### Example: Using Multiple Comparators

Let's say we have a more complex sorting requirement where we want to sort `Person` objects first by age, then by name.

```java
import java.util.*;

class Main {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 25));
        people.add(new Person("David", 35));

        // Sort by age, then by name
        Comparator<Person> comparator = Comparator.comparing(Person::getAge)
                .thenComparing(Person::getName);

        Collections.sort(people, comparator);
        System.out.println("Sorted by age and name: " + people);
    }
}
```

**Output:**

```
Sorted by age and name: [Person{name='Bob', age=25}, Person{name='Charlie', age=25}, Person{name='Alice', age=30}, Person{name='David', age=35}]
```

### Conclusion

The `Comparator` interface in Java is a powerful tool for custom sorting, providing the flexibility to define multiple
and complex sorting criteria. With the addition of functional programming features in Java 8, `Comparator` has become
even more versatile, allowing for more readable and maintainable code when dealing with sorting tasks. Understanding how
to implement and use `Comparator` effectively is essential for any Java developer working with collections.