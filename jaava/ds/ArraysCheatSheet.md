### **Java Arrays Cheat Sheet**

#### **1. Declaration and Initialization**

```java
// Declaration
dataType[] arrayName; // Example: int[] numbers;

// Initialization
arrayName = new dataType[size]; // Example: numbers = new int[5];

// Declaration + Initialization
dataType[] arrayName = new dataType[size]; // Example: int[] numbers = new int[5];
dataType[] arrayName = {value1, value2, ...}; // Example: int[] numbers = {1, 2, 3, 4, 5};
```

#### **2. Accessing Array Elements**

```java
// Accessing an element
arrayName[index]; // Example: int firstElement = numbers[0];

// Modifying an element
arrayName[index] = value; // Example: numbers[1] = 10;
```

#### **3. Properties**

- **`length`**: Returns the number of elements in the array.
  ```java
  int len = arrayName.length; // Example: int len = numbers.length;
  ```

#### **4. Common Methods**

Since arrays in Java are objects, but not classes with methods, they don't directly have methods like `ArrayList`.
However, you can use static utility methods from the `java.util.Arrays` class:

- **`Arrays.toString(array)`**: Returns a string representation of the array.
  ```java
  String arrayString = Arrays.toString(arrayName); // Example: String arrayString = Arrays.toString(numbers);
  ```

- **`Arrays.sort(array)`**: Sorts the array in ascending order.
  ```java
  Arrays.sort(arrayName); // Example: Arrays.sort(numbers);
  ```

- **`Arrays.fill(array, value)`**: Fills the array with the specified value.
  ```java
  Arrays.fill(arrayName, value); // Example: Arrays.fill(numbers, 0);
  ```

- **`Arrays.copyOf(array, newLength)`**: Copies the array into a new array of specified length.
  ```java
  int[] newArray = Arrays.copyOf(arrayName, newLength); // Example: int[] copy = Arrays.copyOf(numbers, 3);
  ```

- **`Arrays.equals(array1, array2)`**: Compares two arrays for equality.
  ```java
  boolean areEqual = Arrays.equals(array1, array2); // Example: boolean areEqual = Arrays.equals(numbers, otherNumbers);
  ```

- **`Arrays.binarySearch(array, key)`**: Searches for a specific value in a sorted array.
  ```java
  int index = Arrays.binarySearch(arrayName, key); // Example: int index = Arrays.binarySearch(numbers, 4);
  ```

- **`Arrays.stream(array)`**: Creates a stream from the array (useful for functional programming).
  ```java
  IntStream stream = Arrays.stream(arrayName); // Example: IntStream stream = Arrays.stream(numbers);
  ```

#### **5. Iterating Over an Array**

```java
// Using a traditional for loop
for (int i = 0; i < arrayName.length; i++) {
    System.out.println(arrayName[i]);
}

// Using an enhanced for loop (foreach)
for (dataType element : arrayName) {
    System.out.println(element);
}
```

#### **6. Multi-Dimensional Arrays**

```java
// Declaration and Initialization
dataType[][] arrayName = new dataType[rows][columns]; // Example: int[][] matrix = new int[3][3];
dataType[][] arrayName = {{value1, value2}, {value3, value4}}; // Example: int[][] matrix = {{1, 2}, {3, 4}};

// Accessing elements
arrayName[rowIndex][columnIndex]; // Example: int value = matrix[0][1];

// Iterating over a 2D array
for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) {
        System.out.println(matrix[i][j]);
    }
}
```

#### **7. Default Values**

- `int`, `long`, `short`, `byte`, `char` → `0`
- `boolean` → `false`
- `float`, `double` → `0.0`
- Object references (e.g., `String`) → `null`

### **8. Example Usage**

```java
import java.util.Arrays;

public class ArrayCheatSheet {
    public static void main(String[] args) {
        // Declare and initialize an array
        int[] numbers = {3, 1, 4, 1, 5, 9};

        // Access and modify elements
        numbers[0] = 7;
        int firstElement = numbers[0];

        // Print array using Arrays.toString
        System.out.println(Arrays.toString(numbers)); // Output: [7, 1, 4, 1, 5, 9]

        // Sort the array
        Arrays.sort(numbers);
        System.out.println(Arrays.toString(numbers)); // Output: [1, 1, 4, 5, 7, 9]

        // Search for an element
        int index = Arrays.binarySearch(numbers, 4);
        System.out.println("Index of 4: " + index); // Output: Index of 4: 2
    }
}
```

This cheat sheet covers the basics of using arrays in Java. It should help you quickly recall how to work with arrays,
their properties, and associated methods.