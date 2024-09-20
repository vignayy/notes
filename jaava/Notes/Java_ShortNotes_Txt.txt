###### Day 1

(1.Intro - 30.Arrays)

- Intro:
    - Introduction to Java and its basics.
- JDK setup:
    - Installing JDK and setting up the environment.
- Hello World:
    - First program to print "Hello World".
- How Java Works: JDK:JRE:JVM:
    - The components of Java (JDK, JRE, JVM) and their roles.
- Variables:
    - Containers to store data in memory.
- DataTypes, Literal:
    - Types of data variables can store (int, float, etc.), literals are constant values.
- Type Conversion:
    - Changing a variable from one type to another.
- Arithmetic Operators:
    - Operators for mathematical operations (+, -, *, /, %).
- Relational Operators:
    - Operators to compare values (>, <, >=, <=, ==, !=).
- Logical Operators:
    - Operators for logical operations (&&, ||, !).
- If-Else, IfElseIf, Nested IfElse:
    - Conditional statements for decision-making.
- Ternary Operator:
    - Shorthand for an if-else condition (int max = (a > b) ? a : b;).
- Switch Statement:
    - A control statement to select one option from multiple values.
- For, While, DoWhile Loops:
    - Loops for repeating a block of code based on a condition.
- Class and Object:
    - Class is a blueprint; Object is an instance of a class.
- Methods:
    - Block of code that performs a specific task, invoked by name.
- Method OverLoading:
    - Defining multiple methods with the same name but different parameters.
- Java Memory Allocation: Stack & Heap:
    - Stack stores local variables, Heap stores objects.
- Arrays:
    - Collection of elements of the same type, stored in contiguous memory.

---

###### Day 2

(31.MultiDimensional Arrays - 47.Naming Convention)

- MultiDimensional Arrays:
    - Arrays of arrays, like matrices (2D or more dimensions).
- Jagged Array & 3D Array:
    - Array of arrays with different lengths (Jagged), 3D is an array of 2D arrays.
- Array Of Objects:
    - Array that holds objects instead of primitives.
- Enhanced For Loop:
    - Simplified for loop to iterate over arrays or collections.
- Strings:
    - Sequence of characters, immutable in nature.
- Mutable vs Immutable Strings:
    - Mutable can change, immutable cannot (e.g., String vs StringBuilder).
- StringBuffer & StringBuilder:
    - Classes for mutable strings, StringBuilder is faster, StringBuffer is thread-safe.
- Static KeyWord:
    - Keyword used to define class-level variables and methods - makes them members of Class.
- Encapsulation:
    - Wrapping data and code as a single unit, restricting access.
- Getters & Setters:
    - Methods to access and modify private variables.
- This KeyWord:
    - Refers to the current instance of the class.
- Constructor:
    - Special method used to initialize objects.
- Default & Parameterized Constructor:
    - Default constructor has no parameters; parameterized takes arguments.
- Naming Convention:
    - Standard way of naming variables, methods, classes, etc.
    - variables/methods/objects - camelCasing (starts with lowercase) --> firstName, nextLine()
    - class/interface/record names - PascalCase --> Scanner
    - package - lowercase --> import java.util.*
    - constants/enums - UPPERCASE --> MAXVALUE

---

###### Day 3

(48.Anonymous Object - 62.Wrapper Class)
_**Completed Core Java**_

- Anonymous Object:
    - An object created without reference, used instantly. new CLass(), new A().show();
- Inheritance:
    - Mechanism where a class acquires properties from another class.
- Types of Inheritance:
    - Single, multilevel, hierarchical inheritance.
- This and Super method:
    - 'This' refers to the current object; 'Super' refers to the parent class.
- Method OverRiding:
    - Redefining a parent class method in the child class.
- Packages:
    - Grouping of related classes and interfaces.
- Access Modifiers:
    - Define access levels (public, private(class), default(package), protected(subclass of other package)).
- Polymorphism:
    - Ability of a method to behave differently based on the object.
- Dynamic Method Dispatch:
    - Deciding which method to call at runtime.
- Final Keyword:
    - Used to restrict modification (classes - Inheritance, methods - !overriding, variables - constants).
- ObjectClass equals() toString() hashCode():
    - Methods of Object class for equality, string representation, and hash codes.
- UpCasting & DownCasting:
    - Converting one object type to another (parent to child or child to parent).
- Wrapper Class:
    - Classes that encapsulate primitive types (Integer, Float, etc.).

+ Quiz Project **Inc.**

---

###### Day 4

(68.Abstract Keyword - 91.Try With Resources)

- Abstract Keyword:
    - Used to define abstract classes and methods.
- Inner Class:
    - A class within another class.
- Anonymous Inner Class:
    - A class without a name, used to override methods or implement interfaces. - To Override Once and use
- Abstract Anony Inner CLass:
    - Anonymous inner class extending an abstract class. - To implement once and use
- Interface:
    - A reference type containing abstract methods, can be implemented by classes.
- Enum:
    - A special class representing a group of constants.
- Annotations:
    - Metadata for classes, methods, variables, etc.
- Functional Interfaces:
    - Interfaces with a single abstract method (SAM).
- Lambda Expression:
    - A concise way to represent anonymous methods (functions).
- Exceptions:
    - Events that disrupt the normal flow of a program.
- Exception Handling: Try-Catch:
    - Mechanism to handle exceptions and prevent program crashes.
- Exception Hierarchy:
    - The structure of exceptions (Checked, Unchecked).
- Throw Keyword:
    - Used to explicitly throw an exception.
- Custom Exception:
    - User-defined exception class extending Exception or RuntimeException.
- Throws Keyword -> Ducking Exception:
    - Used in method signatures to declare exceptions that might be thrown.
- UserInput: BufferReader & Scanner:
    - Classes used to read user input from different sources.
- Try With Resources - Finally Keyword:
    - Ensures automatic resource management, finally block executes always.

---

###### Day 5

(92.Threads - 102.Comparator-Comparable)

- Threads Intro:
    - Lightweight sub-processes, allow multitasking.
- Thread Priority, Sleep:
    - Priority determines thread execution order, sleep pauses thread temporarily.
- Runnable Implementation:
    - Implementing Runnable interface to create threads.
- Race Condition (join):
    - A condition where threads race for resources, join ensures thread completion.
- Thread LifeCycle:
    - States of a thread: New, Runnable, Blocked, Waiting, Timed Waiting, Terminated.
- Collection API:
    - Framework for storing and manipulating groups of objects.
- ArrayList intro:
    - Resizable array implementation of List.
- Set Intro:
    - A collection that does not allow duplicate elements.
- Map Intro:
    - A collection that maps keys to values.
- Comparator:
    - Interface to define custom comparison for sorting.
- Comparable - compareTo():
    - Interface to define natural ordering of objects.

---

###### Day 6

(103.Stream API - 110.Constructor Reference)
_Completed Adv. Java_

- Stream API:
    - A framework to process sequences of data in a functional style.
- forEach -> Consumer:
    - Performs an action for each element of the stream.
- map() filter() reduce() sorted():
    - Functional operations to transform, filter, reduce, and sort streams.
- Parallel Stream:
    - A stream that can process elements in parallel using multiple threads.
- Optional Class - orElse:
    - Used to handle null values, provides default value if null.
- Method Reference:
    - Shorthand for calling methods via a class or object.
- Constructor Reference:
    - Method reference to call a constructor.

---