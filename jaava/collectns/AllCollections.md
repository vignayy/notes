In Java, a `List` is an interface in the Java Collections Framework that represents an ordered collection of elements.
Lists allow you to store duplicate elements, and they maintain the order in which elements are inserted. This means you
can access elements by their index (position in the list), and the elements are stored sequentially.

### Key Characteristics of a List

1. **Ordered Collection**:
    - Elements in a list are ordered, meaning they maintain the sequence in which they were added.

2. **Allows Duplicates**:
    - Unlike sets, a list can contain duplicate elements.

3. **Indexed Access**:
    - You can access elements by their index, which is the position of the element in the list. The index starts
      from `0`.

4. **Dynamic Size**:
    - Lists can grow or shrink dynamically as elements are added or removed.

### Common Implementations of the List Interface

1. **ArrayList**:
    - Backed by a dynamic array.
    - Provides fast random access to elements (`O(1)` time complexity for accessing elements by index).
    - Insertion and removal of elements (except at the end of the list) can be slow (`O(n)` time complexity) because
      elements need to be shifted.

   ```java
   List<String> arrayList = new ArrayList<>();
   ```

2. **LinkedList**:
    - Backed by a doubly linked list.
    - Provides fast insertion and deletion of elements (`O(1)` time complexity) at any position in the list.
    - Access time is slower (`O(n)` time complexity) because it requires traversal from the start of the list.

   ```java
   List<String> linkedList = new LinkedList<>();
   ```

3. **Vector**:
    - Synchronized version of `ArrayList`. It is thread-safe but generally slower than `ArrayList` due to
      synchronization overhead.
    - Less commonly used in modern Java development, where `ArrayList` is preferred for non-thread-safe scenarios
      and `CopyOnWriteArrayList` or other concurrent collections for thread-safe scenarios.

   ```java
   List<String> vector = new Vector<>();
   ```

### Common Methods in the List Interface

- **`add(E element)`**: Adds an element to the list.
- **`add(int index, E element)`**: Adds an element at a specific position in the list.
- **`get(int index)`**: Retrieves the element at the specified index.
- **`remove(int index)`**: Removes the element at the specified index.
- **`remove(Object o)`**: Removes the first occurrence of the specified element from the list.
- **`set(int index, E element)`**: Replaces the element at the specified index with a new element.
- **`size()`**: Returns the number of elements in the list.
- **`contains(Object o)`**: Checks if the list contains the specified element.
- **`indexOf(Object o)`**: Returns the index of the first occurrence of the specified element, or `-1` if the list does
  not contain the element.
- **`lastIndexOf(Object o)`**: Returns the index of the last occurrence of the specified element, or `-1` if the list
  does not contain the element.
- **`subList(int fromIndex, int toIndex)`**: Returns a view of the portion of this list between the
  specified `fromIndex`, inclusive, and `toIndex`, exclusive.

### Example Usage

Here’s a basic example demonstrating some of the `List` interface's capabilities using an `ArrayList`:

```java
import java.util.ArrayList;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {
        // Creating a list
        List<String> fruits = new ArrayList<>();
        
        // Adding elements to the list
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        
        // Accessing an element by index
        String firstFruit = fruits.get(0); // "Apple"
        System.out.println("First fruit: " + firstFruit);
        
        // Modifying an element
        fruits.set(1, "Blueberry"); // Replaces "Banana" with "Blueberry"
        
        // Removing an element
        fruits.remove("Cherry"); // Removes "Cherry" from the list
        
        // Iterating over the list
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
        
        // Checking the size of the list
        System.out.println("List size: " + fruits.size()); // Prints "List size: 2"
    }
}
```

### When to Use a List

- **Preserve Insertion Order**: Use a `List` when you need to maintain the order in which elements are added.
- **Allow Duplicates**: Use a `List` if your collection needs to contain duplicate elements.
- **Access by Index**: Use a `List` when you need to frequently access elements by their index.

Understanding the differences between `ArrayList`, `LinkedList`, and other implementations will help you choose the
right `List` type for your specific use case.

---

A `LinkedList` in Java is a doubly-linked list implementation of the `List` and `Deque` interfaces. It provides a
versatile data structure that can be used to implement both stacks, queues, and lists. Unlike an `ArrayList`, which uses
a dynamic array to store elements, a `LinkedList` stores its elements in nodes that are linked to each other.

### Key Characteristics of LinkedList

1. **Doubly-Linked List**:
    - Each element (or node) in a `LinkedList` contains a reference to both the previous and the next node in the
      sequence.
    - This structure allows for efficient insertion and removal of elements from both ends of the list (`O(1)` time
      complexity).

2. **No Capacity Limit**:
    - Unlike `ArrayList`, which may need to resize its underlying array, a `LinkedList` doesn’t have a predefined
      capacity limit and can grow dynamically as needed.

3. **Efficient Insertion/Deletion**:
    - Inserting or removing elements at the beginning or end of a `LinkedList` is more efficient than in an `ArrayList`
      because it only requires updating the links between nodes.

4. **Sequential Access**:
    - `LinkedList` is generally slower than `ArrayList` for random access (getting elements by index) because it
      requires traversing the list from the start or end to find the element, leading to `O(n)` time complexity for
      access.

5. **Implements Queue and Deque Interfaces**:
    - `LinkedList` can be used as a `Queue`, a `Deque` (Double Ended Queue), or even a `Stack`, providing methods to
      add, remove, and examine elements at both ends of the list.

### Internal Structure of LinkedList

- **Node**:
    - Each element in a `LinkedList` is stored in a `Node` object, which contains:
        - **data**: The value stored in the node.
        - **next**: A reference to the next node in the list.
        - **prev**: A reference to the previous node in the list.

- **Head and Tail**:
    - The `LinkedList` maintains references to the first (`head`) and last (`tail`) nodes of the list.
    - Operations like `addFirst()`, `addLast()`, `removeFirst()`, and `removeLast()` are performed directly on these
      nodes, making them efficient.

### Key Methods of LinkedList

Since `LinkedList` implements both `List` and `Deque`, it inherits many methods from these interfaces. Here are some of
the most commonly used methods:

#### From the `List` Interface:

- **`add(E element)`**: Appends the specified element to the end of the list.
- **`add(int index, E element)`**: Inserts the specified element at the specified position in the list.
- **`get(int index)`**: Returns the element at the specified position in the list.
- **`set(int index, E element)`**: Replaces the element at the specified position with the specified element.
- **`remove(int index)`**: Removes the element at the specified position in the list.
- **`remove(Object o)`**: Removes the first occurrence of the specified element from the list.
- **`size()`**: Returns the number of elements in the list.
- **`clear()`**: Removes all elements from the list.
- **`isEmpty()`**: Checks if the list is empty.

#### From the `Deque` Interface:

- **`addFirst(E e)`**: Inserts the specified element at the beginning of the list.
- **`addLast(E e)`**: Inserts the specified element at the end of the list.
- **`removeFirst()`**: Removes and returns the first element from the list.
- **`removeLast()`**: Removes and returns the last element from the list.
- **`getFirst()`**: Returns the first element in the list without removing it.
- **`getLast()`**: Returns the last element in the list without removing it.
- **`offerFirst(E e)`**: Inserts the specified element at the beginning of the list (as an alternative to `addFirst`).
- **`offerLast(E e)`**: Inserts the specified element at the end of the list (as an alternative to `addLast`).
- **`pollFirst()`**: Removes and returns the first element of the list, or returns `null` if the list is empty.
- **`pollLast()`**: Removes and returns the last element of the list, or returns `null` if the list is empty.

### Example Usage

Here's a basic example demonstrating how to use a `LinkedList`:

```java
import java.util.LinkedList;

public class LinkedListExample {
    public static void main(String[] args) {
        // Create a LinkedList of Strings
        LinkedList<String> fruits = new LinkedList<>();
        
        // Add elements to the LinkedList
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        
        // Add elements at the beginning and end
        fruits.addFirst("Mango");
        fruits.addLast("Pineapple");
        
        // Access the first and last elements
        String firstFruit = fruits.getFirst(); // "Mango"
        String lastFruit = fruits.getLast(); // "Pineapple"
        System.out.println("First fruit: " + firstFruit);
        System.out.println("Last fruit: " + lastFruit);
        
        // Remove elements
        fruits.removeFirst(); // Removes "Mango"
        fruits.removeLast(); // Removes "Pineapple"
        
        // Access an element by index
        String fruit = fruits.get(1); // "Banana"
        System.out.println("Second fruit: " + fruit);
        
        // Iterate over the LinkedList
        for (String f : fruits) {
            System.out.println(f);
        }
        
        // Clear the LinkedList
        fruits.clear();
        
        // Check if the LinkedList is empty
        boolean isEmpty = fruits.isEmpty(); // true
        System.out.println("Is the list empty? " + isEmpty);
    }
}
```

### ArrayList vs. LinkedList

When deciding between `ArrayList` and `LinkedList`, consider the following:

- **Access Time**:
    - `ArrayList` provides faster random access (`O(1)`), whereas `LinkedList` has slower access (`O(n)`).

- **Insertion/Deletion**:
    - `LinkedList` is more efficient for inserting or deleting elements at the beginning or middle of the list (`O(1)`
      for these operations). `ArrayList` may require shifting elements, resulting in `O(n)` complexity.

- **Memory Overhead**:
    - `LinkedList` requires more memory than `ArrayList` because each element is stored in a node, which also stores
      references to the next and previous nodes.

- **Iteration**:
    - Iterating over an `ArrayList` is generally faster due to better cache locality, as elements are stored in
      contiguous memory locations. `LinkedList` requires more traversal operations as elements are dispersed in memory.

### Use Cases for LinkedList

- **When you frequently need to add or remove elements at the beginning or middle of the list**: For instance, in queue
  or deque implementations, where elements are often added and removed from both ends.

- **When the list size is unpredictable**: `LinkedList` dynamically handles growth and shrinkage without the need to
  worry about capacity.

- **When you want to implement stack, queue, or deque functionality**: `LinkedList` naturally supports these operations
  efficiently.

### Limitations

- **Slower Random Access**: Accessing elements by index is slower compared to `ArrayList`, making `LinkedList` less
  suitable for scenarios where frequent random access is required.

- **Memory Overhead**: Each element in a `LinkedList` requires additional memory for storing the pointers to the
  previous and next nodes.

### Conclusion

`LinkedList` is a powerful and flexible data structure in Java, particularly useful when frequent insertions and
deletions are needed, or when implementing queue-like data structures. However, due to its slower access times and
higher memory overhead, it is important to choose `LinkedList` over `ArrayList` only when the use case justifies its
advantages.

---

A `Vector` in Java is a part of the Java Collections Framework and is very similar to an `ArrayList`, with a few key
differences, most notably that `Vector` is synchronized, making it thread-safe. `Vector` was introduced in Java 1.0 and
was one of the original collection classes, but it has largely been replaced by `ArrayList` in non-threaded environments
due to performance considerations.

### Key Characteristics of Vector

1. **Synchronization**:
    - `Vector` is synchronized, meaning that all methods that modify the `Vector` are thread-safe. This makes `Vector`
      suitable for use in multi-threaded environments where multiple threads might access and modify the same
      collection.
    - However, synchronization introduces a performance overhead, making `Vector` slower than `ArrayList` in
      single-threaded environments.

2. **Resizable Array**:
    - Like `ArrayList`, `Vector` is implemented as a dynamically resizable array. The size of the `Vector` can grow as
      needed to accommodate more elements.
    - By default, `Vector` doubles its size when it needs to grow. However, you can specify an increment to control how
      much additional space is allocated each time the `Vector` resizes.

3. **Random Access**:
    - `Vector` allows fast random access to its elements, similar to an `ArrayList`, because it uses an array as its
      underlying data structure. Accessing an element by its index is an `O(1)` operation.

4. **Legacy Class**:
    - `Vector` is considered a legacy class, meaning it was part of the original Java 1.0 release and has since been
      largely replaced by newer, more efficient classes like `ArrayList` and `CopyOnWriteArrayList`.
    - It has been retrofitted to implement the `List` interface, making it compatible with modern Java APIs.

5. **Enumeration**:
    - `Vector` introduced the `Enumeration` interface, which was the standard way to iterate over elements in
      collections before the introduction of the `Iterator` interface in Java 2. While `Enumeration` is still
      supported, `Iterator` is preferred in modern Java development.

### Internal Structure of Vector

- **Capacity**:
    - `Vector` has a capacity, which is the size of the underlying array that holds the elements. This capacity
      increases as elements are added, but it can also be explicitly set or managed by the programmer.

- **Growth Factor**:
    - When `Vector` needs to grow, it increases its size by a growth factor, which is by default 100% (it doubles in
      size). This behavior can be modified by specifying an increment value.

### Key Methods of Vector

`Vector` implements all methods of the `List` interface, and it also includes several legacy methods. Here are some
commonly used methods:

- **`add(E element)`**: Adds the specified element to the end of the `Vector`.
- **`add(int index, E element)`**: Inserts the specified element at the specified position in the `Vector`.
- **`get(int index)`**: Returns the element at the specified position in the `Vector`.
- **`set(int index, E element)`**: Replaces the element at the specified position with the specified element.
- **`remove(int index)`**: Removes the element at the specified position in the `Vector`.
- **`remove(Object o)`**: Removes the first occurrence of the specified element from the `Vector`.
- **`size()`**: Returns the number of elements in the `Vector`.
- **`capacity()`**: Returns the current capacity of the `Vector`.
- **`clear()`**: Removes all elements from the `Vector`.
- **`isEmpty()`**: Checks if the `Vector` is empty.
- **`contains(Object o)`**: Checks if the `Vector` contains the specified element.
- **`indexOf(Object o)`**: Returns the index of the first occurrence of the specified element, or `-1` if it’s not
  found.
- **`firstElement()`**: Returns the first element of the `Vector`.
- **`lastElement()`**: Returns the last element of the `Vector`.
- **`removeElementAt(int index)`**: Removes the element at the specified index.
- **`elements()`**: Returns an `Enumeration` of the elements in the `Vector`.

### Example Usage

Here’s an example demonstrating how to use a `Vector`:

```java
import java.util.Vector;

public class VectorExample {
    public static void main(String[] args) {
        // Create a Vector of Strings
        Vector<String> fruits = new Vector<>();
        
        // Add elements to the Vector
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        
        // Add an element at a specific index
        fruits.add(1, "Blueberry");
        
        // Access an element by index
        String fruit = fruits.get(2); // "Banana"
        System.out.println("Second fruit: " + fruit);
        
        // Modify an element
        fruits.set(2, "Blackberry"); // Replaces "Banana" with "Blackberry"
        
        // Remove an element
        fruits.remove("Apple"); // Removes "Apple" from the Vector
        
        // Iterate over the Vector using a for-each loop
        for (String f : fruits) {
            System.out.println(f);
        }
        
        // Check the size and capacity of the Vector
        System.out.println("Size: " + fruits.size()); // Size: 3
        System.out.println("Capacity: " + fruits.capacity()); // Default capacity is 10

        // Use Enumeration to iterate (legacy approach)
        System.out.println("Using Enumeration:");
        Enumeration<String> e = fruits.elements();
        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }
    }
}
```

### Vector vs. ArrayList

When deciding between `Vector` and `ArrayList`, consider the following:

- **Synchronization**:
    - `Vector` is synchronized, making it thread-safe. This is an advantage if multiple threads are accessing
      the `Vector` simultaneously. However, this synchronization comes with a performance cost.
    - `ArrayList` is not synchronized. In a multi-threaded environment, you can manually synchronize the `ArrayList` or
      use `Collections.synchronizedList()` or `CopyOnWriteArrayList`.

- **Performance**:
    - In single-threaded environments, `ArrayList` generally performs better due to the lack of synchronization
      overhead.
    - `Vector` has a legacy synchronized method, which makes it slower in single-threaded scenarios.

- **Capacity Management**:
    - `Vector` doubles its size when it needs to grow, which can lead to wasteful memory usage if not managed properly.
    - `ArrayList` increases its size by 50% when it grows, which is a more efficient approach for memory management in
      most cases.

- **Use Case**:
    - `Vector` is preferred if you need a thread-safe list without external synchronization.
    - `ArrayList` is preferred for non-threaded applications or when manual synchronization is acceptable.

### Legacy Status

While `Vector` is still available in Java and can be useful in certain scenarios, it is considered a legacy class. In
modern Java development, `ArrayList` is usually the preferred choice for non-threaded environments, and other concurrent
collections like `CopyOnWriteArrayList` are preferred for thread-safe operations.

### Conclusion

`Vector` is a thread-safe, dynamically resizable array that provides a lot of the same functionality as `ArrayList`, but
with built-in synchronization. It is useful in multi-threaded environments where thread safety is required, but for
single-threaded applications or when manual synchronization is sufficient, `ArrayList` is typically a better option due
to its better performance and more modern design.

---

In Java, a `Set` is a collection that contains no duplicate elements. It models the mathematical set abstraction and is
part of the Java Collections Framework. The `Set` interface extends the `Collection` interface and provides various
implementations, each with different performance characteristics and behaviors.

### Key Characteristics of Set

1. **No Duplicates**:
    - A `Set` does not allow duplicate elements. If you attempt to add a duplicate element to a `Set`, the operation
      will be ignored, and the `Set` will remain unchanged.

2. **Unordered Collection**:
    - A `Set` is typically an unordered collection, meaning the elements are not stored in any particular order.
      However, specific implementations of `Set` can maintain a certain order, such as insertion order or natural order.

3. **Efficient Lookup**:
    - `Set` implementations are optimized for efficient membership checks (i.e., checking if an element is in
      the `Set`). Depending on the implementation, these checks can be very fast.

### Set Implementations in Java

There are several implementations of the `Set` interface, each with its unique characteristics:

1. **HashSet**:
    - **Unordered**: `HashSet` does not guarantee any specific order of elements.
    - **Fast Operations**: Operations like `add`, `remove`, and `contains` are generally `O(1)` because they are based
      on a hash table.
    - **Allows `null`**: `HashSet` permits one `null` element.

   Example:
   ```java
   Set<String> hashSet = new HashSet<>();
   hashSet.add("Apple");
   hashSet.add("Banana");
   hashSet.add("Apple"); // Duplicate element, will not be added
   ```

2. **LinkedHashSet**:
    - **Ordered**: Maintains insertion order, meaning the elements are stored in the order in which they were added.
    - **Performance**: Slightly slower than `HashSet` due to maintaining the order, but still provides `O(1)` time
      complexity for basic operations.
    - **Allows `null`**: Like `HashSet`, it allows one `null` element.

   Example:
   ```java
   Set<String> linkedHashSet = new LinkedHashSet<>();
   linkedHashSet.add("Apple");
   linkedHashSet.add("Banana");
   linkedHashSet.add("Cherry");
   ```

3. **TreeSet**:
    - **Sorted**: `TreeSet` maintains its elements in natural order (ascending order for numbers, alphabetical order for
      strings) or according to a custom comparator provided at set creation.
    - **NavigableSet**: `TreeSet` implements the `NavigableSet` interface, which provides additional methods for
      navigating the set (e.g., `lower`, `floor`, `ceiling`, `higher`).
    - **Performance**: Basic operations like `add`, `remove`, and `contains` have a time complexity of `O(log n)`
      because `TreeSet` is implemented using a Red-Black tree.
    - **Does not allow `null`**: Inserting `null` into a `TreeSet` will result in a `NullPointerException`.

   Example:
   ```java
   Set<String> treeSet = new TreeSet<>();
   treeSet.add("Banana");
   treeSet.add("Apple");
   treeSet.add("Cherry");
   ```

4. **EnumSet**:
    - **Specialized Set for Enums**: `EnumSet` is a specialized set for use with enum types. All elements in
      an `EnumSet` must come from a single enum type.
    - **Efficient and Compact**: `EnumSet` is highly efficient and compact, often implemented as a bit vector.
    - **Order**: Maintains the natural order of the enum constants.

   Example:
   ```java
   enum Fruit { APPLE, BANANA, CHERRY }
   Set<Fruit> enumSet = EnumSet.of(Fruit.APPLE, Fruit.BANANA);
   ```

### Key Methods of Set

The `Set` interface inherits methods from the `Collection` interface, but with the guarantee that no duplicates will be
present. Some of the commonly used methods include:

- **`add(E element)`**: Adds the specified element to the set if it is not already present.
- **`remove(Object o)`**: Removes the specified element from the set if it is present.
- **`contains(Object o)`**: Returns `true` if the set contains the specified element.
- **`size()`**: Returns the number of elements in the set.
- **`isEmpty()`**: Returns `true` if the set contains no elements.
- **`clear()`**: Removes all elements from the set.
- **`iterator()`**: Returns an iterator over the elements in the set.

### Example Usage

Here's an example demonstrating how to use a `HashSet`, one of the most common `Set` implementations:

```java
import java.util.HashSet;
import java.util.Set;

public class SetExample {
    public static void main(String[] args) {
        // Create a HashSet
        Set<String> fruitSet = new HashSet<>();
        
        // Add elements to the set
        fruitSet.add("Apple");
        fruitSet.add("Banana");
        fruitSet.add("Cherry");
        
        // Attempt to add a duplicate element
        fruitSet.add("Apple"); // This will not be added
        
        // Check if the set contains an element
        boolean hasBanana = fruitSet.contains("Banana"); // true
        System.out.println("Contains Banana: " + hasBanana);
        
        // Remove an element from the set
        fruitSet.remove("Banana");
        
        // Iterate over the set
        for (String fruit : fruitSet) {
            System.out.println(fruit);
        }
        
        // Check the size of the set
        System.out.println("Set size: " + fruitSet.size()); // 2
        
        // Clear the set
        fruitSet.clear();
        
        // Check if the set is empty
        boolean isEmpty = fruitSet.isEmpty(); // true
        System.out.println("Is the set empty? " + isEmpty);
    }
}
```

### Set vs. List

When deciding between `Set` and `List`, consider the following:

- **No Duplicates**:
    - Use a `Set` if you need to ensure that no duplicate elements are stored.
    - Use a `List` if you need to allow duplicate elements.

- **Order**:
    - `Set` generally does not maintain any order (except `LinkedHashSet` and `TreeSet`).
    - `List` maintains the order of elements, either by insertion order or by custom sorting.

- **Performance**:
    - `Set` implementations like `HashSet` provide fast membership checks and are generally more efficient for this
      purpose than `List`.
    - `List` is better suited for scenarios where you need to access elements by their position (index-based access).

### Conclusion

The `Set` interface in Java is a powerful tool when you need a collection that automatically prevents duplicates and
offers efficient membership checks. Depending on your specific requirements—such as the need for order, performance
characteristics, or thread safety—you can choose from various `Set` implementations
like `HashSet`, `LinkedHashSet`, `TreeSet`, and `EnumSet`. Understanding the properties of each implementation will help
you make the best choice for your particular use case.

---

`HashSet` is one of the most commonly used implementations of the `Set` interface in Java. It is backed by a hash
table (specifically, a `HashMap`), which allows for fast operations like adding, removing, and checking for the presence
of elements. `HashSet` is part of the Java Collections Framework and was introduced in Java 2 (JDK 1.2).

### Key Characteristics of HashSet

1. **No Duplicates**:
    - `HashSet` does not allow duplicate elements. If you attempt to add a duplicate element, the `HashSet` will ignore
      the operation, and the set will remain unchanged.

2. **Unordered**:
    - `HashSet` does not maintain any order for its elements. The elements are stored based on their hash values, so the
      order of elements when iterating through the set is not guaranteed and can change over time.

3. **Null Elements**:
    - `HashSet` allows one `null` element. If you try to add more than one `null`, the subsequent attempts will be
      ignored.

4. **Efficient Performance**:
    - `HashSet` provides constant time (`O(1)`) performance for basic operations like `add`, `remove`, and `contains`,
      assuming the hash function disperses elements properly among the buckets.

5. **Backed by HashMap**:
    - Internally, `HashSet` is backed by a `HashMap`. Each element in the `HashSet` is stored as a key in the `HashMap`,
      with a constant dummy value (like `Boolean.TRUE`) associated with it.

6. **Thread Safety**:
    - `HashSet` is not synchronized. If you need a thread-safe set, you should
      use `Collections.synchronizedSet(new HashSet<>())` or consider using `ConcurrentHashMap` with a `Set` view.

### How HashSet Works Internally

1. **Hashing**:
    - When an element is added to a `HashSet`, its hash code is computed using the `hashCode()` method of the object.
      This hash code determines the bucket where the element will be placed in the underlying hash table.
    - If two elements have the same hash code (a hash collision), they are stored in the same bucket using a linked
      list (or a balanced tree structure in Java 8+ for large buckets).

2. **Equality Check**:
    - If a hash collision occurs (i.e., two different elements produce the same hash code), `HashSet` uses
      the `equals()` method to check whether the elements are actually equal. Only if the elements are not equal will
      both be stored in the `HashSet`.

3. **Capacity and Load Factor**:
    - The initial capacity of a `HashSet` determines how many elements it can hold before needing to resize. The load
      factor determines when the `HashSet` should increase its capacity (by default, the load factor is 0.75, meaning
      the `HashSet` will resize when it is 75% full).
    - When the number of elements exceeds the capacity multiplied by the load factor, the `HashSet` increases its size (
      rehashing occurs, and the elements are redistributed across the new buckets).

### Common Methods of HashSet

`HashSet` inherits methods from the `Set` interface, and it also provides its own specific methods. Some of the commonly
used methods include:

- **`add(E element)`**: Adds the specified element to the `HashSet` if it is not already present. Returns `true` if the
  element was added, and `false` if it was already in the set.

- **`remove(Object o)`**: Removes the specified element from the `HashSet` if it is present. Returns `true` if the
  element was removed, and `false` if it was not found.

- **`contains(Object o)`**: Returns `true` if the `HashSet` contains the specified element, and `false` otherwise.

- **`size()`**: Returns the number of elements in the `HashSet`.

- **`isEmpty()`**: Returns `true` if the `HashSet` contains no elements, and `false` otherwise.

- **`clear()`**: Removes all elements from the `HashSet`.

- **`iterator()`**: Returns an iterator over the elements in the `HashSet`. The order of elements is not guaranteed.

- **`clone()`**: Returns a shallow copy of the `HashSet`.

### Example Usage of HashSet

Here’s an example demonstrating how to use a `HashSet` in Java:

```java
import java.util.HashSet;
import java.util.Set;

public class HashSetExample {
    public static void main(String[] args) {
        // Create a HashSet of Strings
        Set<String> fruitSet = new HashSet<>();
        
        // Add elements to the HashSet
        fruitSet.add("Apple");
        fruitSet.add("Banana");
        fruitSet.add("Cherry");
        
        // Add a duplicate element (will not be added)
        fruitSet.add("Apple");
        
        // Check if the HashSet contains an element
        boolean hasBanana = fruitSet.contains("Banana");
        System.out.println("Contains Banana: " + hasBanana);
        
        // Remove an element from the HashSet
        fruitSet.remove("Banana");
        
        // Iterate over the HashSet
        for (String fruit : fruitSet) {
            System.out.println(fruit);
        }
        
        // Check the size of the HashSet
        System.out.println("Size of HashSet: " + fruitSet.size());
        
        // Clear the HashSet
        fruitSet.clear();
        
        // Check if the HashSet is empty
        boolean isEmpty = fruitSet.isEmpty();
        System.out.println("Is the HashSet empty? " + isEmpty);
    }
}
```

### Performance Considerations

- **Time Complexity**:
    - The primary advantage of `HashSet` is its time complexity for operations like `add`, `remove`, and `contains`,
      which are generally `O(1)`.

- **Hash Collisions**:
    - The efficiency of `HashSet` heavily relies on the quality of the `hashCode()` implementation of the objects it
      stores. Poorly implemented `hashCode()` methods can lead to frequent collisions, degrading performance to `O(n)`
      in the worst case.

- **Memory Usage**:
    - Because `HashSet` is backed by a `HashMap`, it may use more memory than other `Set` implementations like `TreeSet`
      or `LinkedHashSet`.

### When to Use HashSet

- **When you need a collection of unique elements and do not care about the order**: `HashSet` is ideal when you need to
  ensure that there are no duplicates in your collection and order does not matter.

- **When you need fast access, insertion, and deletion**: If you require quick lookups, insertions, and
  deletions, `HashSet` is typically faster than `TreeSet` and `LinkedHashSet` because it does not maintain order or
  sorting.

### Limitations

- **No Order Guarantee**: `HashSet` does not maintain any order of elements. If you need to preserve the order of
  elements, consider using `LinkedHashSet` (which maintains insertion order) or `TreeSet` (which maintains natural
  ordering or custom order).

- **Not Thread-Safe**: `HashSet` is not synchronized, so it is not thread-safe without external synchronization. For a
  thread-safe alternative, consider using `ConcurrentHashMap` with a `Set` view or synchronize the `HashSet` manually.

### Conclusion

`HashSet` is a powerful and efficient collection class in Java, ideal for use cases where you need a collection of
unique elements and do not require any specific ordering. It provides fast performance for basic operations due to its
hash table backing, but it does not maintain any element order and is not thread-safe. When choosing a `Set`
implementation, understanding the characteristics and behavior of `HashSet` will help you determine if it’s the right
choice for your specific use case.

---

Certainly! Let's go through each of these `Set` implementations in detail.

## 1. LinkedHashSet

### Overview

`LinkedHashSet` is a subclass of `HashSet` that maintains a doubly-linked list running through all of its entries. This
linked list defines the iteration order, which is the order in which elements were inserted into the set (insertion
order).

### Key Characteristics

- **Insertion Order**: `LinkedHashSet` maintains the order in which elements are inserted. When you iterate over
  a `LinkedHashSet`, the elements are returned in the order they were added.

- **No Duplicates**: Like all `Set` implementations, `LinkedHashSet` does not allow duplicate elements. If you try to
  add a duplicate, it will be ignored.

- **Performance**: `LinkedHashSet` has slightly lower performance than `HashSet` due to the overhead of maintaining the
  linked list. However, it still offers `O(1)` time complexity for basic operations like `add`, `remove`,
  and `contains`.

- **Allows Null Elements**: `LinkedHashSet` allows `null` elements, similar to `HashSet`.

### Use Cases

- When you need to maintain the order of elements as they are added.
- When you need fast access to elements (but can tolerate a small performance overhead compared to `HashSet`).

### Example

```java
import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetExample {
    public static void main(String[] args) {
        Set<String> linkedHashSet = new LinkedHashSet<>();
        
        linkedHashSet.add("Apple");
        linkedHashSet.add("Banana");
        linkedHashSet.add("Cherry");
        linkedHashSet.add("Apple"); // Duplicate, will not be added
        
        // Iterating over the elements (insertion order is preserved)
        for (String fruit : linkedHashSet) {
            System.out.println(fruit);
        }
    }
}
```

## 2. TreeSet

### Overview

`TreeSet` is a class that implements the `NavigableSet` interface, which extends `SortedSet`. It uses a Red-Black tree (
a self-balancing binary search tree) to store its elements, which means it keeps them in sorted order.

### Key Characteristics

- **Sorted Order**: `TreeSet` maintains its elements in their natural order (e.g., numerical or alphabetical).
  Alternatively, you can provide a custom comparator to define the sorting order.

- **No Duplicates**: `TreeSet` does not allow duplicate elements. If you try to add a duplicate, it will be ignored.

- **Performance**: Basic operations (`add`, `remove`, `contains`) have a time complexity of `O(log n)` due to the
  underlying tree structure.

- **NavigableSet Features**: `TreeSet` provides methods to navigate through the set, such
  as `lower()`, `floor()`, `ceiling()`, and `higher()`.

- **Does Not Allow Null Elements**: Unlike `HashSet` and `LinkedHashSet`, `TreeSet` does not allow `null` elements.
  Attempting to add `null` will result in a `NullPointerException`.

### Use Cases

- When you need a sorted set of elements.
- When you need to perform operations that involve the natural or custom order of elements.

### Example

```java
import java.util.Set;
import java.util.TreeSet;

public class TreeSetExample {
    public static void main(String[] args) {
        Set<String> treeSet = new TreeSet<>();
        
        treeSet.add("Banana");
        treeSet.add("Apple");
        treeSet.add("Cherry");
        // treeSet.add(null); // Throws NullPointerException
        
        // Iterating over the elements (sorted order is maintained)
        for (String fruit : treeSet) {
            System.out.println(fruit);
        }
    }
}
```

## 3. EnumSet

### Overview

`EnumSet` is a specialized `Set` implementation for use with enum types. It is highly efficient, providing a compact and
fast implementation for sets of enum constants.

### Key Characteristics

- **Works Only with Enums**: `EnumSet` can only hold elements of a single enum type. It cannot be used with other types.

- **Ordered**: The elements are maintained in the natural order of the enum constants (i.e., the order in which they are
  declared in the enum type).

- **Efficient and Compact**: `EnumSet` is implemented as a bit vector, making it extremely memory-efficient and fast for
  operations like `add`, `remove`, and `contains`.

- **Not Thread-Safe**: Like other `Set` implementations, `EnumSet` is not synchronized.

### Creation Methods

- **`EnumSet.allOf(Class<E> elementType)`**: Creates an `EnumSet` containing all enum constants of the specified enum
  type.
- **`EnumSet.noneOf(Class<E> elementType)`**: Creates an empty `EnumSet`.
- **`EnumSet.of(E first, E... rest)`**: Creates an `EnumSet` containing the specified elements.
- **`EnumSet.range(E from, E to)`**: Creates an `EnumSet` containing all elements within the specified range.

### Use Cases

- When you need a set of enum constants.
- When performance and memory efficiency are important.

### Example

```java
import java.util.EnumSet;
import java.util.Set;

enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

public class EnumSetExample {
    public static void main(String[] args) {
        // Create an EnumSet with some days
        Set<Day> weekend = EnumSet.of(Day.SATURDAY, Day.SUNDAY);
        
        // Add an element to the EnumSet
        weekend.add(Day.FRIDAY);
        
        // Iterate over the EnumSet
        for (Day day : weekend) {
            System.out.println(day);
        }
    }
}
```

## Summary of Differences

1. **`LinkedHashSet`**:
    - Maintains insertion order.
    - Slightly slower than `HashSet` due to the linked list overhead.
    - Allows one `null` element.

2. **`TreeSet`**:
    - Maintains elements in sorted order.
    - Slower than `HashSet` due to the sorting process (`O(log n)`).
    - Does not allow `null` elements.

3. **`EnumSet`**:
    - Designed specifically for enum types.
    - Highly efficient and compact.
    - Maintains elements in the natural order of the enum.

Each of these `Set` implementations has its strengths, and the choice of which to use depends on the specific
requirements of your application—whether you need order, sorting, or memory efficiency.

---


The `Stack` class in Java is a part of the `java.util` package and is one of the classic data structures that follows
the **Last-In-First-Out (LIFO)** principle. This means that the last element added to the stack is the first one to be
removed.

### Key Characteristics of Stack

1. **LIFO Order**:
    - `Stack` operates on the LIFO principle, where the last element pushed onto the stack is the first one to be popped
      off.

2. **Extends Vector**:
    - The `Stack` class extends the `Vector` class, meaning that it inherits the properties and methods of a `Vector`,
      such as dynamic array resizing and synchronization. However, it also means that `Stack` is synchronized, which can
      be a performance drawback in single-threaded applications.

3. **Legacy Class**:
    - `Stack` is considered a legacy class in Java. While it is still widely used, it is generally recommended to
      use `Deque` (especially `ArrayDeque`) when you need stack-like functionality because `Deque` is more modern and
      provides better performance.

### Stack Methods

The `Stack` class provides several methods specific to stack operations:

- **`push(E item)`**: Adds an item to the top of the stack.
- **`pop()`**: Removes and returns the item at the top of the stack. Throws `EmptyStackException` if the stack is empty.
- **`peek()`**: Returns the item at the top of the stack without removing it. Throws `EmptyStackException` if the stack
  is empty.
- **`isEmpty()`**: Checks if the stack is empty. Returns `true` if the stack is empty, and `false` otherwise.
- **`search(Object o)`**: Returns the 1-based position of the item on the stack, where the top of the stack is 1.
  Returns `-1` if the item is not found.

### Internal Structure

- **Array-based Implementation**:
    - Since `Stack` extends `Vector`, it uses an array-based structure for storage, which dynamically resizes as
      elements are added. This allows random access to elements, but the LIFO behavior is achieved by restricting
      operations to the end of the list.

- **Synchronized**:
    - Being a subclass of `Vector`, `Stack` inherits the synchronized nature of `Vector`. This means that all the
      methods of `Stack` are thread-safe, which can lead to a performance overhead in single-threaded scenarios.

### Example Usage of Stack

Here’s an example that demonstrates how to use a `Stack` in Java:

```java
import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        // Push elements onto the stack
        stack.push(10);
        stack.push(20);
        stack.push(30);
        
        // Peek the top element
        System.out.println("Top element is: " + stack.peek());
        
        // Pop an element off the stack
        System.out.println("Popped element is: " + stack.pop());
        
        // Search for an element (returns the position from the top)
        int position = stack.search(10);
        System.out.println("Position of 10 from top: " + position);
        
        // Check if the stack is empty
        System.out.println("Is stack empty? " + stack.isEmpty());
        
        // Iterate over the stack
        System.out.println("Stack elements:");
        for (int element : stack) {
            System.out.println(element);
        }
    }
}
```

### Performance Considerations

- **Efficiency**:
    - `Stack` operations such as `push`, `pop`, and `peek` are generally `O(1)` due to the array-based structure.
      However, since `Stack` is synchronized, the overhead of locking can make it slower in single-threaded scenarios
      compared to alternatives like `ArrayDeque`.

- **Thread Safety**:
    - `Stack` is synchronized, making it safe for use in multi-threaded environments without additional synchronization.
      However, in most modern applications, using a non-synchronized stack-like structure and managing synchronization
      externally (if needed) is preferred for better performance.

### Limitations of Stack

1. **Legacy Design**:
    - Since `Stack` extends `Vector`, it inherits the drawbacks of `Vector`, including synchronized methods and being
      part of the legacy collection framework.

2. **Better Alternatives Available**:
    - The Java Collections Framework recommends using `Deque` (especially `ArrayDeque`) when stack-like behavior is
      needed. `Deque` is more versatile, efficient, and modern, with better performance characteristics in
      single-threaded environments.

### Modern Alternative: Deque (ArrayDeque)

While `Stack` is still commonly used, `ArrayDeque` from the `Deque` interface is often recommended for stack operations
in modern Java programming due to its flexibility and performance.

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeExample {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();

        // Push elements onto the stack
        stack.push(10);
        stack.push(20);
        stack.push(30);
        
        // Peek the top element
        System.out.println("Top element is: " + stack.peek());
        
        // Pop an element off the stack
        System.out.println("Popped element is: " + stack.pop());
        
        // Check if the stack is empty
        System.out.println("Is stack empty? " + stack.isEmpty());
        
        // Iterate over the stack
        System.out.println("Stack elements:");
        for (int element : stack) {
            System.out.println(element);
        }
    }
}
```

### Conclusion

The `Stack` class is a simple and effective way to implement stack functionality in Java, but it is considered a legacy
class. For most modern Java applications, `Deque` (particularly `ArrayDeque`) is the preferred option due to its
flexibility and better performance. However, `Stack` remains a useful class in certain scenarios, especially when thread
safety is a concern and you prefer built-in synchronization.

---

The `Queue` interface in Java is part of the `java.util` package and represents a collection designed for holding
elements prior to processing. `Queue` generally follows the **First-In-First-Out (FIFO)** principle, where elements are
inserted at the end of the queue and removed from the beginning. However, Java provides several implementations of
the `Queue` interface, some of which allow different ordering mechanisms, such as priority-based ordering.

### Key Characteristics of Queue

1. **FIFO Order**:
    - In a standard `Queue`, the first element added is the first one to be removed, adhering to the FIFO principle.

2. **Interface**:
    - `Queue` is an interface, so you cannot instantiate it directly. Instead, you instantiate one of its implementing
      classes, such as `LinkedList`, `PriorityQueue`, or `ArrayDeque`.

3. **Methods**:
    - The `Queue` interface defines several methods for adding, removing, and inspecting elements. These methods often
      come in two forms: one that throws an exception on failure and another that returns a special value (
      usually `null` or `false`).

### Common Methods of Queue

- **`add(E e)`**: Inserts the specified element into the queue. If the operation fails (e.g., due to capacity
  restrictions), it throws an `IllegalStateException`.

- **`offer(E e)`**: Inserts the specified element into the queue. Unlike `add`, it returns `false` if the queue is full
  or the operation fails, rather than throwing an exception.

- **`remove()`**: Removes and returns the element at the head of the queue. Throws a `NoSuchElementException` if the
  queue is empty.

- **`poll()`**: Removes and returns the element at the head of the queue, or returns `null` if the queue is empty.

- **`element()`**: Retrieves, but does not remove, the head of the queue. Throws a `NoSuchElementException` if the queue
  is empty.

- **`peek()`**: Retrieves, but does not remove, the head of the queue, or returns `null` if the queue is empty.

### Types of Queue Implementations

Java provides several implementations of the `Queue` interface, each with different characteristics and use cases.

#### 1. LinkedList (as a Queue)

`LinkedList` is a doubly-linked list that can act as both a `List` and a `Queue`. When used as a `Queue`, it provides a
simple FIFO queue.

- **Key Characteristics**:
    - No capacity restrictions.
    - Allows `null` elements.
    - Implements both `Queue` and `Deque` interfaces, allowing it to be used as a `Deque` (double-ended queue) or
      a `Stack` as well.

- **Use Cases**:
    - When you need a general-purpose, unbounded queue with basic FIFO behavior.

- **Example**:
  ```java
  import java.util.LinkedList;
  import java.util.Queue;

  public class LinkedListQueueExample {
      public static void main(String[] args) {
          Queue<String> queue = new LinkedList<>();
          
          queue.offer("First");
          queue.offer("Second");
          queue.offer("Third");
          
          System.out.println("Head of the queue: " + queue.peek());
          
          System.out.println("Removed from queue: " + queue.poll());
          
          System.out.println("Queue after poll: " + queue);
      }
  }
  ```

#### 2. PriorityQueue

`PriorityQueue` is a special type of queue where elements are ordered based on their natural ordering (for example,
numbers in ascending order) or by a custom comparator provided at the time of creation.

- **Key Characteristics**:
    - Elements are ordered according to their natural order or a custom comparator.
    - Does not allow `null` elements.
    - Unbounded but has a growing internal array that will resize as needed.

- **Use Cases**:
    - When you need to process elements based on priority rather than insertion order (e.g., tasks with priorities).

- **Example**:
  ```java
  import java.util.PriorityQueue;
  import java.util.Queue;

  public class PriorityQueueExample {
      public static void main(String[] args) {
          Queue<Integer> priorityQueue = new PriorityQueue<>();
          
          priorityQueue.offer(30);
          priorityQueue.offer(20);
          priorityQueue.offer(10);
          
          System.out.println("Head of the queue: " + priorityQueue.peek());
          
          System.out.println("Removed from queue: " + priorityQueue.poll());
          
          System.out.println("Queue after poll: " + priorityQueue);
      }
  }
  ```

#### 3. ArrayDeque

`ArrayDeque` is a resizable array implementation of the `Deque` interface. It can be used as both a stack (LIFO) and a
queue (FIFO).

- **Key Characteristics**:
    - More efficient than `LinkedList` for both stack and queue operations.
    - Does not allow `null` elements.
    - No capacity restrictions and grows dynamically as needed.

- **Use Cases**:
    - When you need a highly efficient, flexible data structure that can act as both a stack and a queue.

- **Example**:
  ```java
  import java.util.ArrayDeque;
  import java.util.Queue;

  public class ArrayDequeQueueExample {
      public static void main(String[] args) {
          Queue<String> queue = new ArrayDeque<>();
          
          queue.offer("First");
          queue.offer("Second");
          queue.offer("Third");
          
          System.out.println("Head of the queue: " + queue.peek());
          
          System.out.println("Removed from queue: " + queue.poll());
          
          System.out.println("Queue after poll: " + queue);
      }
  }
  ```

#### 4. BlockingQueue

`BlockingQueue` is an interface that extends `Queue` and is part of the `java.util.concurrent` package. It provides
thread-safe operations and is often used in producer-consumer scenarios where one thread produces elements and another
consumes them.

- **Key Characteristics**:
    - Supports operations that wait for the queue to become non-empty when retrieving elements, and wait for space to
      become available when adding elements.
    - Various implementations, including `ArrayBlockingQueue`, `LinkedBlockingQueue`, `PriorityBlockingQueue`,
      and `SynchronousQueue`.
    - Does not allow `null` elements.

- **Use Cases**:
    - When you need thread-safe operations in a multi-threaded environment, especially in producer-consumer scenarios.

- **Example**:
  ```java
  import java.util.concurrent.BlockingQueue;
  import java.util.concurrent.LinkedBlockingQueue;

  public class BlockingQueueExample {
      public static void main(String[] args) throws InterruptedException {
          BlockingQueue<String> queue = new LinkedBlockingQueue<>(2);
          
          queue.put("First");
          queue.put("Second");
          // This will block until space becomes available
          // queue.put("Third");
          
          System.out.println("Head of the queue: " + queue.take());
          
          System.out.println("Queue after take: " + queue);
      }
  }
  ```

### Summary of Queue Implementations

1. **`LinkedList`**:
    - General-purpose queue.
    - Unbounded, allows `null`, and maintains insertion order.

2. **`PriorityQueue`**:
    - Orders elements by natural order or custom comparator.
    - Unbounded but does not allow `null` elements.

3. **`ArrayDeque`**:
    - Efficient for both stack and queue operations.
    - Unbounded, does not allow `null` elements, and can be used as a stack or queue.

4. **`BlockingQueue`**:
    - Thread-safe, blocking operations.
    - Various implementations for different use cases, all disallowing `null` elements.

### Conclusion

The `Queue` interface in Java is versatile and supports various implementations to suit different needs, from simple
FIFO operations with `LinkedList` and `ArrayDeque` to more complex priority-based processing with `PriorityQueue` and
multi-threaded environments with `BlockingQueue`. Choosing the right `Queue` implementation depends on the specific
requirements of your application, such as whether you need ordering, blocking, or thread safety.

---

In Java, the `Map` interface is part of the `java.util` package and represents a collection of key-value pairs. It is an
essential part of the Java Collections Framework and provides a way to store and manage associations between keys and
values, where each key is unique, and each key maps to exactly one value.

### Key Characteristics of Map

1. **Key-Value Pairs**:
    - A `Map` stores data in key-value pairs, where each key is unique and is associated with a specific value.

2. **No Duplicate Keys**:
    - A `Map` does not allow duplicate keys. If you try to insert a key that already exists in the map, the new value
      will replace the existing value associated with that key.

3. **Null Keys and Values**:
    - Some implementations of `Map` allow `null` keys and values, while others do not.

4. **No Specific Order**:
    - The order in which mappings are stored and retrieved can vary depending on the specific implementation of `Map`.
      For example, `HashMap` does not guarantee any order, while `LinkedHashMap` maintains insertion order,
      and `TreeMap` maintains keys in a sorted order.

### Commonly Used Map Implementations

1. **HashMap**
2. **LinkedHashMap**
3. **TreeMap**
4. **Hashtable**

Each of these implementations has different characteristics and use cases.

### 1. HashMap

`HashMap` is one of the most commonly used implementations of the `Map` interface. It uses a hash table to store the
key-value pairs.

#### Key Characteristics

- **Performance**: `HashMap` provides constant-time performance (`O(1)`) for basic operations like `put`, `get`,
  and `remove`, assuming the hash function distributes elements properly across buckets.

- **No Order Guarantee**: `HashMap` does not maintain any order for its elements, neither the order of insertion nor any
  sorted order.

- **Allows Null**: `HashMap` allows one `null` key and multiple `null` values.

- **Thread-Safety**: `HashMap` is not synchronized. If multiple threads access a `HashMap` concurrently and at least one
  of them modifies the map structurally, it must be externally synchronized.

#### Example

```java
import java.util.HashMap;
import java.util.Map;

public class HashMapExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Cherry", 3);
        
        System.out.println("Apple: " + map.get("Apple"));
        
        map.put("Apple", 10); // Replace value for key "Apple"
        
        System.out.println("Updated Apple: " + map.get("Apple"));
        
        System.out.println("Map size: " + map.size());
        
        // Iterating over keys and values
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
```

### 2. LinkedHashMap

`LinkedHashMap` is a subclass of `HashMap` that maintains a doubly-linked list running through all its entries. This
linked list defines the iteration order, which is the order in which keys were inserted into the map.

#### Key Characteristics

- **Insertion Order**: `LinkedHashMap` maintains the order of insertion. When you iterate over the map, the entries are
  returned in the order they were added.

- **Access Order**: Alternatively, `LinkedHashMap` can be configured to order its entries based on access order (the
  order in which keys are accessed).

- **Performance**: Similar to `HashMap`, with slightly more overhead due to maintaining the linked list.

- **Allows Null**: Similar to `HashMap`, `LinkedHashMap` allows one `null` key and multiple `null` values.

#### Example

```java
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>();
        
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Cherry", 3);
        
        System.out.println("Insertion order:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
```

### 3. TreeMap

`TreeMap` is a `Map` implementation that uses a Red-Black tree structure to store its entries. It orders its keys
according to their natural ordering or by a custom comparator provided at map creation time.

#### Key Characteristics

- **Sorted Order**: `TreeMap` maintains its entries in sorted order of keys, either natural order or as specified by a
  comparator.

- **No Null Keys**: `TreeMap` does not allow `null` keys, though it allows multiple `null` values.

- **Performance**: The basic operations (`put`, `get`, `remove`) are `O(log n)` due to the tree structure.

#### Example

```java
import java.util.Map;
import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new TreeMap<>();
        
        map.put("Banana", 2);
        map.put("Apple", 1);
        map.put("Cherry", 3);
        
        System.out.println("Sorted order:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
```

### 4. Hashtable

`Hashtable` is a legacy class that was part of the original version of Java. It is synchronized and thread-safe but is
generally slower compared to `HashMap` due to its synchronization overhead.

#### Key Characteristics

- **Thread-Safe**: `Hashtable` is synchronized, meaning it can be safely accessed by multiple threads concurrently
  without the need for additional synchronization.

- **No Null**: `Hashtable` does not allow `null` keys or values.

- **Performance**: Generally slower than `HashMap` due to synchronization overhead.

#### Example

```java
import java.util.Hashtable;
import java.util.Map;

public class HashtableExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new Hashtable<>();
        
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Cherry", 3);
        
        System.out.println("Apple: " + map.get("Apple"));
    }
}
```

### Summary of Differences

1. **`HashMap`**:
    - No ordering guarantee.
    - Allows one `null` key and multiple `null` values.
    - Not synchronized (thread-unsafe).

2. **`LinkedHashMap`**:
    - Maintains insertion order (or access order if configured).
    - Allows one `null` key and multiple `null` values.
    - Not synchronized.

3. **`TreeMap`**:
    - Maintains keys in sorted order.
    - Does not allow `null` keys (allows `null` values).
    - Not synchronized.

4. **`Hashtable`**:
    - Legacy class, synchronized and thread-safe.
    - Does not allow `null` keys or values.

### Conclusion

The `Map` interface in Java provides a powerful way to store and manage key-value pairs. Each
implementation (`HashMap`, `LinkedHashMap`, `TreeMap`, `Hashtable`) offers different trade-offs in terms of ordering,
performance, and synchronization. Choosing the right `Map` implementation depends on your specific requirements, such as
whether you need ordering, thread safety, or null handling.

---
