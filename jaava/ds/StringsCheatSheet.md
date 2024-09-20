### **Java Strings Cheat Sheet**

#### **1. Declaration and Initialization**

```java
// Declaration
String str;

// Initialization
str = "Hello, World!"; // Example of direct initialization

// Declaration + Initialization
String str = "Hello, World!"; // Direct assignment
String str = "Hello, World!"; // Using the String constructor
```

#### **2. Basic String Operations**

```java
// Getting the length of a string
int length = str.length(); // Example: int len = str.length();

// Accessing characters in a string
char ch = str.charAt(index); // Example: char ch = str.charAt(0); // Gets the first character

// Concatenating strings
String fullName = firstName + " " + lastName; // Using the '+' operator
String fullName = firstName.concat(lastName); // Using the concat() method

// Comparing strings
boolean isEqual = str1.equals(str2); // Case-sensitive comparison
boolean isEqualIgnoreCase = str1.equalsIgnoreCase(str2); // Case-insensitive comparison

// Substring extraction
String subStr = str.substring(beginIndex); // Example: String subStr = str.substring(6);
String subStr = str.substring(beginIndex, endIndex); // Example: String subStr = str.substring(0, 5);
```

#### **3. Common String Methods**

```java
// Converting to lowercase/uppercase
String lowerStr = str.toLowerCase(); // Example: String lowerStr = str.toLowerCase();
String upperStr = str.toUpperCase(); // Example: String upperStr = str.toUpperCase();

// Trimming whitespace
String trimmedStr = str.trim(); // Example: String trimmedStr = str.trim();

// Replacing characters or substrings
String replacedStr = str.replace(oldChar, newChar); // Example: String replacedStr = str.replace('o', 'a');
String replacedStr = str.replace(oldString, newString); // Example: String replacedStr = str.replace("World", "Java");

// Splitting a string
String[] words = str.split(delimiter); // Example: String[] words = str.split(" ");

// Checking if a string starts/ends with a substring
boolean startsWith = str.startsWith(prefix); // Example: boolean startsWith = str.startsWith("Hello");
boolean endsWith = str.endsWith(suffix); // Example: boolean endsWith = str.endsWith("World!");

// Checking if a string contains a substring
boolean contains = str.contains(sequence); // Example: boolean contains = str.contains("World");

// Finding the index of a character or substring
int index = str.indexOf('a'); // First occurrence of character
int index = str.indexOf("substring"); // First occurrence of substring
int lastIndex = str.lastIndexOf('a'); // Last occurrence of character or substring

// Converting other data types to strings
String strNum = String.valueOf(number); // Example: String strNum = String.valueOf(123);
String strChar = Character.toString('c'); // Example: String strChar = Character.toString('c');
```

#### **4. String Immutability**

- **Immutable Nature:** Strings in Java are immutable, meaning once a `String` object is created, its value cannot be
  changed. Any modification creates a new `String` object.
  ```java
  String str1 = "Hello";
  str1 = "World"; // This creates a new string, "World", and str1 now references this new string.
  ```

#### **5. StringBuilder and StringBuffer**

For mutable strings, where frequent modifications are required, you can use `StringBuilder` or `StringBuffer`.

```java
// Using StringBuilder
StringBuilder sb = new StringBuilder("Hello");
sb.append(" World!"); // Appends to the existing string
String result = sb.toString(); // Converts StringBuilder to String

// Using StringBuffer (Thread-safe version)
StringBuffer sb = new StringBuffer("Hello");
sb.append(" World!"); // Appends to the existing string
String result = sb.toString(); // Converts StringBuffer to String
```

#### **6. String Formatting**

```java
// Formatting strings
String formattedStr = String.format("Hello, %s!", name); // Example: String formattedStr = String.format("Hello, %s!", "Alice");

// Formatting numbers, dates, etc.
String formattedNumber = String.format("%d", 123); // Example: String formattedNumber = String.format("%d", 123);
String formattedFloat = String.format("%.2f", 12.34567); // Example: String formattedFloat = String.format("%.2f", 12.34567);
```

#### **7. Example Usage**

```java
public class StringCheatSheet {
    public static void main(String[] args) {
        // Declaration and Initialization
        String greeting = "Hello, World!";

        // Length of the string
        int length = greeting.length();

        // Accessing characters
        char firstChar = greeting.charAt(0);

        // Concatenation
        String fullName = "John" + " " + "Doe";

        // Substring
        String hello = greeting.substring(0, 5);

        // Replace
        String replacedGreeting = greeting.replace("World", "Java");

        // Split
        String[] words = greeting.split(" ");

        // StringBuilder example
        String result = "Hello" + ", Java!";

        // Print all results
        System.out.println("Length: " + length);
        System.out.println("First Character: " + firstChar);
        System.out.println("Full Name: " + fullName);
        System.out.println("Substring: " + hello);
        System.out.println("Replaced Greeting: " + replacedGreeting);
        System.out.println("Split Words: " + Arrays.toString(words));
        System.out.println("StringBuilder Result: " + result);
    }
}
```

#### **8. Performance Consideration**

- **String vs. StringBuilder:** Use `StringBuilder` for string concatenations in loops or when performance is critical,
  as `String` concatenation with `+` creates multiple `String` objects due to its immutable nature.

This cheat sheet should help you quickly recall and use common `String` methods and operations in Java.