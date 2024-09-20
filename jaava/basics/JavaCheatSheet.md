Sure! Let's dive into the details of each of the elements mentioned in the cheatsheet above.

### 1. Arrays

**Declaration and Initialization:**

- **Declaration**: This defines an array variable without actually creating an array.
  ```java
  int[] arr;
  ```
- **Initialization**: This creates the array and assigns it to the variable.
  ```java
  arr = new int[5];
  ```
- **Combined Declaration and Initialization**: This creates an array and initializes it with specified values.
  ```java
  int[] arr = {1, 2, 3, 4, 5};
  ```
- **Multi-dimensional array**: This creates a two-dimensional array.
  ```java
  int[][] multiArr = {
      {1, 2, 3},
      {4, 5, 6}
  };
  ```

**Common Operations:**

- **Accessing elements**: Retrieve an element from the array using its index.
  ```java
  int firstElement = arr[0];
  ```
- **Iterating through an array**: Loop through each element of the array.
  ```java
  for (int i = 0; i < arr.length; i++) {
      System.out.println(arr[i]);
  }
  // Enhanced for loop
  for (int num : arr) {
      System.out.println(num);
  }
  ```
- **Sorting an array**: Sort the array in ascending order.
  ```java
  Arrays.sort(arr);
  ```
- **Finding length of array**: Get the number of elements in the array.
  ```java
  int length = arr.length;
  ```

### 2. Lists

**Declaration and Initialization:**

- **Declaration and Initialization**: Creates a `List` object using the `ArrayList` implementation.
  ```java
  import java.util.ArrayList;
  import java.util.List;

  List<Integer> list = new ArrayList<>();
  ```

**Common Operations:**

- **Adding elements**: Add elements to the list.
  ```java
  list.add(1);
  list.add(2);
  list.add(3);
  ```
- **Accessing elements**: Retrieve an element from the list using its index.
  ```java
  int firstElement = list.get(0);
  ```
- **Iterating through a list**: Loop through each element of the list.
  ```java
  for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i));
  }
  // Enhanced for loop
  for (int num : list) {
      System.out.println(num);
  }
  ```
- **Removing elements**: Remove an element from the list by its index.
  ```java
  list.remove(0); // Removes the first element
  ```
- **Finding size of list**: Get the number of elements in the list.
  ```java
  int size = list.size();
  ```
- **Sorting a list**: Sort the list in ascending order.
  ```java
  Collections.sort(list);
  ```

### 3. Strings

**Declaration and Initialization:**

- **Declaration and Initialization**: Creates a `String` object.
  ```java
  String str = "Hello, World!";
  ```

**Common Operations:**

- **Length of a string**: Get the number of characters in the string.
  ```java
  int length = str.length();
  ```
- **Accessing characters**: Retrieve a character from the string using its index.
  ```java
  char firstChar = str.charAt(0);
  ```
- **Substring**: Extract a portion of the string.
  ```java
  String substr = str.substring(0, 5); // "Hello"
  ```
- **Concatenation**: Combine two strings.
  ```java
  String greeting = str + " How are you?";
  ```
- **String comparison**: Check if two strings are equal.
  ```java
  boolean isEqual = str.equals("Hello, World!");
  ```
- **Ignore case comparison**: Check if two strings are equal, ignoring case.
  ```java
  boolean isEqualIgnoreCase = str.equalsIgnoreCase("hello, world!");
  ```
- **Converting to uppercase/lowercase**: Change the case of the string.
  ```java
  String upper = str.toUpperCase();
  String lower = str.toLowerCase();
  ```
- **Splitting a string**: Split the string into an array based on a delimiter.
  ```java
  String[] words = str.split(", ");
  ```
- **Replacing characters**: Replace part of the string with another string.
  ```java
  String replaced = str.replace("World", "Java");
  ```

### 4. Basic Examples of DSA Problems

**Array - Finding Maximum Element:**

- This function iterates through the array to find and return the maximum element.
  ```java
  public int findMax(int[] arr) {
      int max = arr[0];
      for (int i = 1; i < arr.length; i++) {
          if (arr[i] > max) {
              max = arr[i];
          }
      }
      return max;
  }
  ```

**List - Reversing a List:**

- This function creates a new list and adds elements from the original list in reverse order.
  ```java
  public List<Integer> reverseList(List<Integer> list) {
      List<Integer> reversed = new ArrayList<>();
      for (int i = list.size() - 1; i >= 0; i--) {
          reversed.add(list.get(i));
      }
      return reversed;
  }
  ```

**String - Checking for Palindrome:**

- This function checks if a given string reads the same forward and backward.
  ```java
  public boolean isPalindrome(String str) {
      int left = 0, right = str.length() - 1;
      while (left < right) {
          if (str.charAt(left) != str.charAt(right)) {
              return false;
          }
          left++;
          right--;
      }
      return true;
  }
  ```

### Additional Useful Java 8 Features

**Lambda Expressions:**

- **Lambda expressions**: Provide a clear and concise way to implement single-method interfaces (functional interfaces)
  using an expression.
  ```java
  List<String> list = Arrays.asList("a", "b", "c");
  list.forEach(item -> System.out.println(item));
  ```

**Streams:**

- **Streams**: Provide a powerful way to process sequences of elements (e.g., collections) in a functional style.
  ```java
  List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
  List<Integer> evenNumbers = numbers.stream()
                                      .filter(n -> n % 2 == 0)
                                      .sorted()
                                      .collect(Collectors.toList());
  ```

---
Java 8 syntax and some fundamental DSA problem-solving techniques.

---