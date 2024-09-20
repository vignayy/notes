In Java, the `Map` interface is part of the Java Collections Framework and is used to store key-value pairs, where each
key is unique. The `Map` interface is not a subtype of the `Collection` interface, but it is still considered part of
the Collections Framework because it provides a way to work with groups of objects.

### Key Characteristics of `Map`:

1. **Key-Value Pairs**: A `Map` stores elements as key-value pairs, where each key is mapped to a single value. You can
   retrieve the value associated with a specific key.

2. **Unique Keys**: Each key in a `Map` must be unique. If you try to insert a duplicate key, the previous value
   associated with that key will be replaced by the new value.

3. **Null Keys and Values**: Depending on the specific implementation of the `Map`, it can allow `null` keys and values.
   For instance, `HashMap` permits one null key and multiple null values, while `TreeMap` does not allow null keys but
   permits null values.

4. **Implementations**: The `Map` interface has several common implementations, each with its own characteristics:
    - **`HashMap`**: It uses a hash table to store the map's entries. It provides constant-time performance for basic
      operations (`get` and `put`), assuming the hash function disperses the elements properly among the buckets.
    - **`LinkedHashMap`**: Extends `HashMap` but maintains a doubly-linked list running through all its entries. This
      defines the iteration order, which is the order in which keys were inserted.
    - **`TreeMap`**: Implements a `NavigableMap`, which means it stores keys in a sorted order, according to their
      natural order or by a specified comparator.
    - **`Hashtable`**: Similar to `HashMap`, but it is synchronized and does not allow null keys or values.

5. **Basic Operations**:
    - **`put(K key, V value)`**: Associates the specified value with the specified key in this map.
    - **`get(Object key)`**: Returns the value to which the specified key is mapped, or `null` if this map contains no
      mapping for the key.
    - **`remove(Object key)`**: Removes the mapping for a key from this map if it is present.
    - **`containsKey(Object key)`**: Returns `true` if this map contains a mapping for the specified key.
    - **`containsValue(Object value)`**: Returns `true` if this map maps one or more keys to the specified value.
    - **`keySet()`**: Returns a `Set` view of the keys contained in this map.
    - **`values()`**: Returns a `Collection` view of the values contained in this map.
    - **`entrySet()`**: Returns a `Set` view of the mappings contained in this map.

### Example Usage:

```java
import java.util.HashMap;
import java.util.Map;

public class MapExample {
    public static void main(String[] args) {
        // Create a HashMap instance
        Map<String, Integer> map = new HashMap<>();
        
        // Add key-value pairs to the map
        map.put("Apple", 10);
        map.put("Banana", 15);
        map.put("Orange", 20);
        
        // Access a value by its key
        System.out.println("Value for key 'Apple': " + map.get("Apple"));
        
        // Check if a key is present
        if (map.containsKey("Banana")) {
            System.out.println("Banana is in the map.");
        }
        
        // Remove a key-value pair
        map.remove("Orange");
        
        // Iterate over the keys
        for (String key : map.keySet()) {
            System.out.println("Key: " + key + ", Value: " + map.get(key));
        }
        
        // Iterate over the key-value pairs
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}
```

### Iterating Through a `Map`:

There are several ways to iterate through a `Map`:

- **Using `keySet()`**: Iterating over the keys and then fetching the values using `get(key)`.
- **Using `entrySet()`**: Iterating over the key-value pairs directly.
- **Using `values()`**: Iterating over the values directly if you are only interested in the values.

### When to Use a `Map`:

- **Lookups**: If you need to quickly retrieve a value based on a key.
- **Association**: When there is a natural association between two pieces of data, such as a name and a phone number.

### Summary:

The `Map` interface is a versatile and powerful tool in Java for managing key-value pairs. Its various implementations
provide different performance characteristics and behaviors, allowing developers to choose the one that best fits their
needs.

---
`HashMap` is one of the most commonly used implementations of the `Map` interface in Java. It is a part of the Java
Collections Framework and is used to store key-value pairs. The `HashMap` allows for efficient data retrieval,
insertion, and deletion operations, which makes it a go-to choice when working with large datasets where quick access to
data is needed.

### Key Characteristics of `HashMap`:

1. **Structure**:
    - `HashMap` is internally implemented using an array of buckets, where each bucket is essentially a linked list (in
      JDK 1.7 and earlier) or a combination of a linked list and a binary tree (in JDK 1.8 and later).
    - Each key-value pair in the `HashMap` is stored in an instance of the `Node` class, which contains the key, value,
      hash, and a pointer to the next `Node`.

2. **Hashing**:
    - `HashMap` uses a hash function to compute an index for each key, which determines the bucket where the key-value
      pair will be stored.
    - The hash function typically involves calling the `hashCode()` method on the key object and then processing the
      result to fit within the bounds of the array.
    - If two keys produce the same hash code, a collision occurs. In the case of a collision, `HashMap` stores the new
      entry in the same bucket using chaining (a linked list) or, since Java 8, a balanced tree structure (when the
      number of collisions exceeds a certain threshold).

3. **Performance**:
    - **Time Complexity**: In the best case, `HashMap` operations like `get`, `put`, and `remove` have an average time
      complexity of O(1) due to the direct access provided by the hash function. However, in the worst case, when many
      collisions occur and are handled by chaining or tree structures, the time complexity can degrade to O(n).
    - **Capacity and Load Factor**: `HashMap` has an initial capacity and a load factor. The initial capacity is the
      size of the hash table, and the load factor (default is 0.75) determines when the hash table should be resized (
      rehashing) to maintain efficient performance. Rehashing occurs when the number of entries exceeds the product of
      the load factor and the current capacity.

4. **Null Keys and Values**:
    - `HashMap` allows one null key and multiple null values. The null key is always placed in the first bucket (index
        0) because its hash is calculated as 0.

5. **Order of Entries**:
    - `HashMap` does not guarantee any specific order of the elements. The order of keys and values may change if the
      map is modified, such as when new elements are added, or rehashing occurs.

6. **Concurrency**:
    - `HashMap` is not synchronized, which means it is not thread-safe. If multiple threads access a `HashMap`
      concurrently and at least one of them modifies it structurally, it must be synchronized externally.
      The `ConcurrentHashMap` class is a better choice for thread-safe operations.

### Basic Operations:

- **`put(K key, V value)`**: Associates the specified value with the specified key. If the map previously contained a
  mapping for the key, the old value is replaced.

  ```java
  Map<String, Integer> map = new HashMap<>();
  map.put("Apple", 10);
  ```

- **`get(Object key)`**: Returns the value to which the specified key is mapped, or `null` if the map contains no
  mapping for the key.

  ```java
  Integer value = map.get("Apple");
  ```

- **`remove(Object key)`**: Removes the mapping for the specified key from the map if it is present.

  ```java
  map.remove("Apple");
  ```

- **`containsKey(Object key)`**: Returns `true` if the map contains a mapping for the specified key.

  ```java
  boolean hasApple = map.containsKey("Apple");
  ```

- **`containsValue(Object value)`**: Returns `true` if the map maps one or more keys to the specified value.

  ```java
  boolean hasValue10 = map.containsValue(10);
  ```

- **`keySet()`**: Returns a `Set` view of the keys contained in the map.

  ```java
  Set<String> keys = map.keySet();
  ```

- **`values()`**: Returns a `Collection` view of the values contained in the map.

  ```java
  Collection<Integer> values = map.values();
  ```

- **`entrySet()`**: Returns a `Set` view of the mappings contained in the map.

  ```java
  Set<Map.Entry<String, Integer>> entries = map.entrySet();
  ```

### Example Usage:

Here’s an example demonstrating the basic usage of a `HashMap`:

```java
import java.util.HashMap;
import java.util.Map;

public class HashMapExample {
    public static void main(String[] args) {
        // Create a HashMap instance
        Map<String, Integer> map = new HashMap<>();
        
        // Adding key-value pairs
        map.put("Apple", 10);
        map.put("Banana", 20);
        map.put("Orange", 30);
        
        // Accessing a value by key
        System.out.println("Value for Apple: " + map.get("Apple"));
        
        // Checking if a key is present
        if (map.containsKey("Banana")) {
            System.out.println("Banana is present with value: " + map.get("Banana"));
        }
        
        // Removing a key-value pair
        map.remove("Orange");
        
        // Iterating over the key-value pairs
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}
```

### Key Points to Remember:

- `HashMap` provides fast performance with O(1) time complexity for basic operations like add, remove, and search in the
  average case.
- It allows null keys and multiple null values.
- It is not synchronized, so it is not thread-safe. For multi-threaded environments, use `ConcurrentHashMap` or
  synchronize the `HashMap` externally.
- The order of the elements in a `HashMap` is not guaranteed and can change when the map is modified.

`HashMap` is a powerful and flexible data structure that is widely used in Java programming for its performance and ease
of use when managing key-value pairs.

---

`LinkedHashMap` is a subclass of `HashMap` that maintains a doubly-linked list running through all of its entries. This
linked list defines the iteration order, which is either the order in which keys were inserted (insertion-order) or the
order in which they were last accessed (access-order).

### Key Characteristics of `LinkedHashMap`:

1. **Ordering**:
    - **Insertion-Order**: By default, `LinkedHashMap` maintains the order in which keys were inserted into the map.
      When iterating over the map, the entries will be returned in the order in which they were added.
    - **Access-Order**: If the `LinkedHashMap` is created with the `accessOrder` flag set to `true`, the iteration order
      will be the order in which entries were last accessed, either via `get` or `put` operations.

2. **Structure**:
    - `LinkedHashMap` is implemented as a hash table and linked list, with the linked list preserving the order of
      entries. It uses a combination of an array (similar to `HashMap`) and a doubly-linked list.
    - Each entry in the `LinkedHashMap` is an instance of a subclass of `HashMap.Entry`, which has additional pointers
      to the next and previous entries in the linked list.

3. **Performance**:
    - **Time Complexity**: Like `HashMap`, the `LinkedHashMap` provides O(1) time complexity for basic
      operations (`get`, `put`, `remove`). The additional linked list structure does not significantly affect
      performance.
    - **Memory Overhead**: The linked list requires additional memory to store the ordering information (pointers to the
      next and previous entries).

4. **Null Keys and Values**:
    - `LinkedHashMap`, like `HashMap`, allows one null key and multiple null values.

5. **Concurrency**:
    - `LinkedHashMap` is not synchronized, meaning it is not thread-safe. If multiple threads access the map
      concurrently and at least one of them modifies it, external synchronization is required.

6. **Removal Order**:
    - The linked list allows for predictable removal order, which can be beneficial in certain applications like caching
      where you might want to remove the oldest entries.

### Basic Operations in `LinkedHashMap`:

- **Insertion**: When you insert a key-value pair, the key is placed at the end of the linked list, preserving the
  insertion order.

  ```java
  Map<String, Integer> map = new LinkedHashMap<>();
  map.put("Apple", 10);
  ```

- **Access**: When you access a key (via `get`), the entry is placed at the end of the linked list if access-order is
  enabled.

  ```java
  Integer value = map.get("Apple");
  ```

- **Iteration**: When you iterate over the map, the entries are returned in the order defined by the linked list (
  insertion or access order).

  ```java
  for (Map.Entry<String, Integer> entry : map.entrySet()) {
      System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
  }
  ```

- **Remove Eldest Entry**: `LinkedHashMap` provides a method `removeEldestEntry(Map.Entry<K,V> eldest)` which can be
  overridden to implement logic for removing the oldest entry. This is often used in caching scenarios to implement a
  Least Recently Used (LRU) cache.

  ```java
  protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
      return size() > 100; // Keep the map's size below 100
  }
  ```

### Example Usage of `LinkedHashMap`:

```java
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapExample {
    public static void main(String[] args) {
        // Create a LinkedHashMap instance with insertion-order
        Map<String, Integer> map = new LinkedHashMap<>();
        
        // Adding key-value pairs
        map.put("Apple", 10);
        map.put("Banana", 20);
        map.put("Orange", 30);
        
        // Accessing a value by key
        System.out.println("Value for Apple: " + map.get("Apple"));
        
        // Iterating over the key-value pairs
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        
        // Creating a LinkedHashMap with access-order
        Map<String, Integer> accessOrderMap = new LinkedHashMap<>(16, 0.75f, true);
        accessOrderMap.put("Apple", 10);
        accessOrderMap.put("Banana", 20);
        accessOrderMap.put("Orange", 30);
        
        // Accessing elements to change their order
        accessOrderMap.get("Apple");
        accessOrderMap.get("Orange");
        
        // Iterating over the map to see the access order
        System.out.println("Access-order iteration:");
        for (Map.Entry<String, Integer> entry : accessOrderMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}
```

### Key Points to Remember:

- **Predictable Iteration Order**: `LinkedHashMap` is useful when you need to maintain a predictable iteration order,
  either insertion-order or access-order.
- **Use Cases**: Common use cases include implementing LRU caches, maintaining a history of operations, and any scenario
  where order matters.
- **Performance**: While `LinkedHashMap` has a bit more overhead than `HashMap` due to the linked list, it still
  provides fast O(1) access times for basic operations.
- **Thread Safety**: Like `HashMap`, `LinkedHashMap` is not synchronized and should be externally synchronized if used
  in a multi-threaded environment.

`LinkedHashMap` is a versatile and powerful tool when you need both the benefits of a `HashMap` and the ability to
maintain the order of entries. Its flexibility in ordering and predictable iteration make it ideal for specific use
cases like LRU caching or any scenario where the order of elements is important.

---

`TreeMap` is a concrete implementation of the `NavigableMap` interface in Java, which in turn extends the `SortedMap`
interface. It is part of the Java Collections Framework and is used to store key-value pairs in a sorted order,
according to the natural ordering of its keys or by a specified comparator.

### Key Characteristics of `TreeMap`:

1. **Sorted Order**:
    - `TreeMap` stores its entries in a sorted order based on the natural ordering of the keys (if they
      implement `Comparable`) or by a custom `Comparator` provided at map creation time.
    - The keys are always sorted in ascending order, making it useful when you need to maintain a sorted list of
      key-value pairs.

2. **Balanced Tree Structure**:
    - Internally, `TreeMap` is implemented as a Red-Black tree, which is a self-balancing binary search tree. This
      ensures that all basic operations (`get`, `put`, `remove`, etc.) have a time complexity of O(log n), where n is
      the number of entries in the map.
    - The self-balancing nature of the Red-Black tree ensures that the tree's height is kept as low as possible,
      preventing performance degradation in the worst case.

3. **Key Characteristics**:
    - **Null Keys**: `TreeMap` does not allow `null` keys because the comparison of `null` with any other object is
      undefined and would throw a `NullPointerException`. However, it allows multiple `null` values.
    - **Natural Ordering or Comparator**: If the keys implement `Comparable`, they are sorted according to their natural
      order. Alternatively, you can provide a custom `Comparator` to define the sort order.

4. **NavigableMap Features**:
    - As a `NavigableMap`, `TreeMap` provides several methods to navigate the map, such as retrieving the greatest key
      less than or equal to a given key, the least key greater than or equal to a given key, and so on. These methods
      include `lowerKey`, `floorKey`, `ceilingKey`, and `higherKey`.
    - It also supports views of the map in various ranges (`subMap`, `headMap`, `tailMap`), allowing you to work with
      specific portions of the map.

5. **Concurrency**:
    - `TreeMap` is not synchronized, meaning it is not thread-safe. If multiple threads access the `TreeMap`
      concurrently, and at least one of them modifies the map, it must be synchronized externally. For a thread-safe
      sorted map, you can use `ConcurrentSkipListMap`, which provides similar functionality.

### Basic Operations in `TreeMap`:

- **Insertion**:
    - Inserting a key-value pair in a `TreeMap` involves placing the key in the correct location in the Red-Black tree
      to maintain the sorted order.

  ```java
  Map<String, Integer> map = new TreeMap<>();
  map.put("Apple", 10);
  map.put("Banana", 20);
  ```

- **Retrieval**:
    - Retrieving a value by key involves navigating the tree from the root to the appropriate node.

  ```java
  Integer value = map.get("Apple");
  ```

- **Iteration**:
    - When you iterate over the map, the entries are returned in the sorted order of the keys.

  ```java
  for (Map.Entry<String, Integer> entry : map.entrySet()) {
      System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
  }
  ```

- **NavigableMap Methods**:
    - `TreeMap` provides methods to retrieve specific keys based on their position relative to other keys:

  ```java
  String lowerKey = map.lowerKey("Banana");  // Returns "Apple"
  String floorKey = map.floorKey("Banana");  // Returns "Banana"
  String ceilingKey = map.ceilingKey("Banana");  // Returns "Banana"
  String higherKey = map.higherKey("Banana");  // Returns null
  ```

- **Submaps**:
    - You can create a view of the map containing only a specific range of keys using `subMap`, `headMap`, or `tailMap`.

  ```java
  SortedMap<String, Integer> subMap = map.subMap("Apple", "Banana");
  ```

### Example Usage of `TreeMap`:

```java
import java.util.Map;
import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {
        // Create a TreeMap instance
        Map<String, Integer> map = new TreeMap<>();
        
        // Adding key-value pairs
        map.put("Banana", 20);
        map.put("Apple", 10);
        map.put("Orange", 30);
        
        // Accessing a value by key
        System.out.println("Value for Apple: " + map.get("Apple"));
        
        // Iterating over the key-value pairs
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        
        // Navigating using NavigableMap methods
        System.out.println("Lower key than 'Banana': " + ((TreeMap<String, Integer>)map).lowerKey("Banana"));
        System.out.println("Higher key than 'Banana': " + ((TreeMap<String, Integer>)map).higherKey("Banana"));
        
        // Creating a submap
        Map<String, Integer> subMap = ((TreeMap<String, Integer>)map).subMap("Apple", "Orange");
        System.out.println("SubMap: " + subMap);
    }
}
```

### Key Points to Remember:

- **Sorted Order**: `TreeMap` maintains the keys in a sorted order, either by their natural order or by a custom
  comparator. This makes it ideal for scenarios where you need to keep entries sorted.
- **O(log n) Time Complexity**: All basic operations in `TreeMap` (`put`, `get`, `remove`) are O(log n) due to the
  underlying Red-Black tree structure.
- **No Null Keys**: Unlike `HashMap` or `LinkedHashMap`, `TreeMap` does not allow `null` keys.
- **NavigableMap Methods**: `TreeMap` provides additional methods for navigating through the map based on the ordering
  of keys, making it more powerful than a simple sorted map.
- **Use Cases**: `TreeMap` is especially useful in applications where you need a sorted map, such as in range queries,
  ordered statistics, and similar scenarios.

`TreeMap` is a powerful and flexible tool for maintaining a map with sorted keys. Its performance characteristics and
sorting capabilities make it ideal for many applications where order matters.

---

`Hashtable` is a data structure in Java that implements the `Map` interface, used to store key-value pairs. It is one of
the original implementations of the `Map` interface in Java and has been available since Java 1.0. While `Hashtable` is
similar to `HashMap`, it has some distinct characteristics and use cases, primarily related to its synchronization and
thread safety.

### Key Characteristics of `Hashtable`:

1. **Thread Safety**:
    - `Hashtable` is synchronized, meaning that it is thread-safe for use by multiple threads concurrently. All methods
      in `Hashtable` are synchronized, which ensures that only one thread can modify the hashtable at a time.
    - This makes `Hashtable` suitable for applications where multiple threads might be accessing or modifying the map
      simultaneously, without requiring external synchronization.

2. **No Null Keys or Values**:
    - Unlike `HashMap`, `Hashtable` does not allow `null` keys or values. Attempting to insert a `null` key or value
      will result in a `NullPointerException`.
    - This is because the `Hashtable` relies on the `hashCode()` method of keys, and calling `hashCode()` on `null`
      would cause an error.

3. **Legacy Class**:
    - `Hashtable` is considered a legacy class, meaning it has been part of Java since the early versions. In modern
      Java applications, `HashMap` or `ConcurrentHashMap` are often preferred due to better performance and flexibility.

4. **Implementation**:
    - Internally, `Hashtable` uses an array of buckets to store key-value pairs, similar to `HashMap`. Each bucket can
      store multiple entries in a linked list if collisions occur (i.e., if multiple keys have the same hash code).
    - `Hashtable` uses a hashing function to determine the index in the array where the key-value pair should be stored.

5. **Performance**:
    - **Time Complexity**: In general, `Hashtable` provides O(1) time complexity for basic operations like `get`, `put`,
      and `remove`, assuming good distribution of keys across buckets. However, because of its synchronization, it can
      be slower than `HashMap` in single-threaded scenarios.
    - **Synchronization Overhead**: The synchronization that makes `Hashtable` thread-safe also introduces overhead,
      which can impact performance in scenarios where thread safety is not needed.

6. **Enumeration**:
    - `Hashtable` provides a method called `elements()` that returns an `Enumeration` of the values in the
      hashtable. `Enumeration` is a legacy interface that predates the `Iterator` interface and is less commonly used
      today.

### Basic Operations in `Hashtable`:

- **Insertion**:
    - To insert a key-value pair into a `Hashtable`, you use the `put()` method. This method is synchronized to ensure
      thread safety.

  ```java
  Hashtable<String, Integer> table = new Hashtable<>();
  table.put("Apple", 10);
  table.put("Banana", 20);
  ```

- **Retrieval**:
    - To retrieve a value associated with a key, use the `get()` method. It will return `null` if the key is not present
      in the hashtable.

  ```java
  Integer value = table.get("Apple");
  ```

- **Removal**:
    - The `remove()` method removes the key-value pair associated with the specified key.

  ```java
  table.remove("Apple");
  ```

- **Iteration**:
    - You can iterate over the keys, values, or entries in a `Hashtable` using an `Enumeration` or by using
      the `keySet()`, `values()`, or `entrySet()` methods, similar to `HashMap`.

  ```java
  for (String key : table.keySet()) {
      System.out.println("Key: " + key + ", Value: " + table.get(key));
  }
  ```

### Example Usage of `Hashtable`:

```java
import java.util.Hashtable;
import java.util.Enumeration;

public class HashtableExample {
    public static void main(String[] args) {
        // Create a Hashtable instance
        Hashtable<String, Integer> table = new Hashtable<>();
        
        // Adding key-value pairs
        table.put("Apple", 10);
        table.put("Banana", 20);
        table.put("Orange", 30);
        
        // Accessing a value by key
        System.out.println("Value for Apple: " + table.get("Apple"));
        
        // Removing a key-value pair
        table.remove("Banana");
        
        // Iterating over the key-value pairs using Enumeration
        Enumeration<String> keys = table.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            System.out.println("Key: " + key + ", Value: " + table.get(key));
        }
        
        // Iterating using entrySet
        for (var entry : table.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}
```

### Comparison with `HashMap`:

- **Thread Safety**: `Hashtable` is synchronized, making it thread-safe, while `HashMap` is not synchronized by default.
  For thread-safe operations in modern applications, `ConcurrentHashMap` is often preferred.
- **Null Keys/Values**: `Hashtable` does not allow null keys or values, whereas `HashMap` allows one null key and
  multiple null values.
- **Legacy Status**: `Hashtable` is a legacy class and is generally not recommended for new code. `HashMap` (
  or `ConcurrentHashMap` for thread-safe operations) is preferred for new applications.

### Use Cases for `Hashtable`:

- **Multi-threaded Environments**: `Hashtable` can be useful in legacy applications or scenarios where you need a simple
  thread-safe map without using external synchronization.
- **Strict Null Policy**: In applications where you want to enforce a strict no-null policy for keys and
  values, `Hashtable` provides this out of the box.

### Summary:

- **`Hashtable`** is a synchronized, thread-safe map implementation that stores key-value pairs in a hash table.
- It does not allow null keys or values, differentiating it from `HashMap`.
- While useful in multi-threaded environments, it has largely been replaced by more modern alternatives
  like `ConcurrentHashMap`.
- Its synchronized methods introduce overhead, making it slower than `HashMap` in single-threaded environments.
- For new code, `HashMap` or `ConcurrentHashMap` is generally recommended, but `Hashtable` may still be found in legacy
  systems or specific use cases requiring its characteristics.

---