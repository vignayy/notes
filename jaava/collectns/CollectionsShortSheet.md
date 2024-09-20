---

# Java Collections Framework Cheat Sheet

## 1. **Core Interfaces**

### 1.1 **Collection**

Base interface for all collections (except `Map`).

- **Methods**: `add(E e)`, `remove(Object o)`, `size()`, `clear()`, `contains(Object o)`

### 1.2 **List**

An ordered collection that allows duplicate elements.

- **Implementations**: `ArrayList`, `LinkedList`, `Vector`

```java
List<String> list = new ArrayList<>();
list.

add("Element");
list.

get(0); // Access by index
```

### 1.3 **Set**

A collection that does not allow duplicate elements.

- **Implementations**: `HashSet`, `LinkedHashSet`, `TreeSet`, `EnumSet`

```java
Set<String> set = new HashSet<>();
set.

add("Element");
set.

contains("Element");
```

### 1.4 **Queue**

A collection used to hold elements prior to processing, usually in FIFO order.

- **Implementations**: `LinkedList`, `PriorityQueue`, `ArrayDeque`

```java
Queue<String> queue = new LinkedList<>();
queue.

offer("Element");
queue.

poll(); // Removes head
```

### 1.5 **Deque**

A double-ended queue that allows insertion and removal from both ends.

- **Implementations**: `ArrayDeque`, `LinkedList`

```java
Deque<String> deque = new ArrayDeque<>();
deque.

addFirst("Element");
deque.

addLast("Element");
deque.

removeFirst(); // Removes from the front
deque.

removeLast();  // Removes from the end
```

### 1.6 **Map**

A collection that maps keys to values, with no duplicate keys allowed.

- **Implementations**: `HashMap`, `LinkedHashMap`, `TreeMap`, `Hashtable`

```java
Map<String, Integer> map = new HashMap<>();
map.

put("Key",1);
map.

get("Key");
map.

remove("Key");
```

---

## 2. **Common Implementations**

### 2.1 **ArrayList**

Resizable array implementation of `List`.

- **Fast Random Access**: O(1) for `get()`
- **Slow for Insert/Delete**: O(n) for `add/remove`

```java
List<String> arrayList = new ArrayList<>();
arrayList.

add("Element");
```

### 2.2 **LinkedList**

Doubly-linked list implementation of `List` and `Deque`.

- **Efficient for Insert/Delete**: O(1) for `add/remove`
- **Slower Random Access**: O(n) for `get()`

```java
List<String> linkedList = new LinkedList<>();
linkedList.

add("Element");
```

### 2.3 **Vector**

Synchronized, resizable array implementation of `List`.

- **Thread-Safe** but slower due to synchronization overhead.

```java
List<String> vector = new Vector<>();
vector.

add("Element");
```

### 2.4 **HashSet**

Hash table-backed implementation of `Set`.

- **No Order Guarantee**
- **Fast Lookups**: O(1) for `add`, `remove`, `contains`

```java
Set<String> hashSet = new HashSet<>();
hashSet.

add("Element");
```

### 2.5 **LinkedHashSet**

Hash table and linked list implementation of `Set`.

- **Maintains Insertion Order**
- **Fast Lookups**: O(1) for `add`, `remove`, `contains`

```java
Set<String> linkedHashSet = new LinkedHashSet<>();
linkedHashSet.

add("Element");
```

### 2.6 **TreeSet**

Red-Black tree implementation of `Set`.

- **Sorted Order** (natural/comparator)
- **Slow Lookups**: O(log n) for `add`, `remove`, `contains`

```java
Set<String> treeSet = new TreeSet<>();
treeSet.

add("Element");
```

### 2.7 **EnumSet**

A specialized `Set` for use with enum types.

- **Extremely Fast**: O(1) for `add`, `remove`, `contains`
- **All elements must be of the same enum type**

```java
enum Days {MONDAY, TUESDAY, WEDNESDAY}

Set<Days> enumSet = EnumSet.of(Days.MONDAY, Days.WEDNESDAY);
```

### 2.8 **PriorityQueue**

A priority heap-based implementation of `Queue`.

- **Elements Ordered by Natural Ordering or Comparator**
- **Not a FIFO Queue**: Orders based on priority

```java
Queue<Integer> priorityQueue = new PriorityQueue<>();
priorityQueue.

offer(10);
priorityQueue.

offer(20);
priorityQueue.

poll(); // Retrieves and removes the head
```

### 2.9 **ArrayDeque**

Resizable array implementation of `Deque`.

- **Efficient for Stack/Queue Operations**
- **No Null Elements Allowed**

```java
Deque<String> arrayDeque = new ArrayDeque<>();
arrayDeque.

push("Element"); // Stack operation
arrayDeque.

poll(); // Queue operation
```

### 2.10 **HashMap**

Hash table-backed implementation of `Map`.

- **No Order Guarantee**
- **Fast Lookups**: O(1) for `put`, `get`, `remove`

```java
Map<String, Integer> hashMap = new HashMap<>();
hashMap.

put("Key",1);
```

### 2.11 **LinkedHashMap**

Hash table and linked list implementation of `Map`.

- **Maintains Insertion Order**
- **Fast Lookups**: O(1) for `put`, `get`, `remove`

```java
Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
linkedHashMap.

put("Key",1);
```

### 2.12 **TreeMap**

Red-Black tree implementation of `Map`.

- **Sorted Order** (natural/comparator)
- **Slow Lookups**: O(log n) for `put`, `get`, `remove`

```java
Map<String, Integer> treeMap = new TreeMap<>();
treeMap.

put("Key",1);
```

### 2.13 **Hashtable**

Legacy synchronized hash table implementation of `Map`.

- **Thread-Safe** but slower due to synchronization overhead
- **No Null Keys/Values Allowed**

```java
Map<String, Integer> hashtable = new Hashtable<>();
hashtable.

put("Key",1);
```

---

## 3. **Key Concepts**

### 3.1 **Iteration**

- **For-each loop**:
  ```java
  for (String s : list) {
      System.out.println(s);
  }
  ```
- **Iterator**:
  ```java
  Iterator<String> it = list.iterator();
  while (it.hasNext()) {
      System.out.println(it.next());
  }
  ```

### 3.2 **Synchronization**

- **Collections.synchronizedList(new ArrayList<>())**
  ```java
  List<String> syncList = Collections.synchronizedList(new ArrayList<>());
  ```

### 3.3 **Unmodifiable Collections**

- **Collections.unmodifiableList(new ArrayList<>())**
  ```java
  List<String> unmodList = Collections.unmodifiableList(new ArrayList<>());
  ```

### 3.4 **Sorting**

- **Collections.sort(list)**
  ```java
  Collections.sort(list);
  ```

### 3.5 **Custom Comparator**

- **Comparator for Custom Objects**:
  ```java
  Collections.sort(list, new Comparator<CustomObject>() {
      public int compare(CustomObject o1, CustomObject o2) {
          return o1.getField().compareTo(o2.getField());
      }
  });
  ```

### 3.6 **Thread-Safe Collections**

- **`ConcurrentHashMap`**:
  ```java
  Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
  ```

---

This cheat sheet summarizes the key interfaces, implementations, and concepts of the Java Collections Framework. It is a
quick reference to help you remember the essential aspects of collections in Java.