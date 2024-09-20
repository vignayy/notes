### Overview of Threads in Java

In Java, threads are the fundamental units of a program that execute concurrently. A thread is a lightweight process
that allows multiple tasks to be performed in parallel within a single application. Java provides built-in support for
multithreading, enabling developers to write efficient, responsive, and scalable applications.

### Why Use Threads?

- **Concurrency**: Threads allow multiple parts of a program to run simultaneously, which is particularly useful in
  tasks like handling multiple user requests in a web server.
- **Responsiveness**: Threads can keep a program responsive while performing time-consuming tasks in the background,
  such as file I/O or network communication.
- **Utilization of Multiprocessor Systems**: On systems with multiple CPUs, threads can be executed in parallel, leading
  to better utilization of the system's resources.

### Life Cycle of a Thread

A thread in Java can be in one of the following states:

1. **New**: A thread is in this state when it is created but not yet started.
2. **Runnable**: After calling the `start()` method, the thread enters the runnable state and is ready to run.
3. **Blocked/Waiting**: A thread may enter this state if it is waiting for a resource to become available or for another
   thread to complete its task.
4. **Timed Waiting**: A thread can enter this state when it is waiting for a specific period.
5. **Terminated**: The thread has finished executing and is no longer active.

### Creating and Starting Threads

There are two main ways to create a thread in Java:

1. **By Extending the `Thread` Class**:
    - You can create a thread by extending the `Thread` class and overriding its `run()` method.

   **Example:**
   ```java
   class MyThread extends Thread {
       public void run() {
           System.out.println("Thread is running");
       }
   }

   public class Main {
       public static void main(String[] args) {
           MyThread t1 = new MyThread();
           t1.start(); // Starts the thread, which calls the run() method
       }
   }
   ```

2. **By Implementing the `Runnable` Interface**:
    - You can also create a thread by implementing the `Runnable` interface and passing an instance of it to a `Thread`
      object.

   **Example:**
   ```java
   class MyRunnable implements Runnable {
       public void run() {
           System.out.println("Thread is running");
       }
   }

   public class Main {
       public static void main(String[] args) {
           MyRunnable myRunnable = new MyRunnable();
           Thread t1 = new Thread(myRunnable);
           t1.start(); // Starts the thread, which calls the run() method
       }
   }
   ```

### Thread Methods

Java provides several methods to manage and control threads:

1. **`start()`**:
    - Begins the execution of the thread. The JVM calls the thread's `run()` method.

2. **`run()`**:
    - Contains the code that constitutes the new thread's task. This method is called by the `start()` method.

3. **`sleep(long millis)`**:
    - Puts the thread to sleep for a specified amount of time (in milliseconds).

   **Example:**
   ```java
   Thread.sleep(1000); // Sleep for 1 second
   ```

4. **`join()`**:
    - Waits for a thread to die. If one thread calls `join()` on another thread, it will wait until the other thread
      finishes.

   **Example:**
   ```java
   t1.join(); // Wait until t1 finishes
   ```

5. **`yield()`**:
    - Temporarily pauses the currently executing thread to allow other threads of the same priority to run.

   **Example:**
   ```java
   Thread.yield(); // Current thread yields control
   ```

6. **`setPriority(int priority)`**:
    - Sets the priority of the thread. Thread priorities range from `MIN_PRIORITY` (1) to `MAX_PRIORITY` (10),
      with `NORM_PRIORITY` (5) as the default.

   **Example:**
   ```java
   t1.setPriority(Thread.MAX_PRIORITY); // Set maximum priority
   ```

7. **`isAlive()`**:
    - Checks if a thread is still running.

   **Example:**
   ```java
   if (t1.isAlive()) {
       System.out.println("Thread is still running");
   }
   ```

8. **`interrupt()`**:
    - Interrupts a thread. If the thread is in a blocked state (e.g., sleeping or waiting), it throws
      an `InterruptedException`.

   **Example:**
   ```java
   t1.interrupt(); // Interrupt the thread
   ```

### Thread Synchronization

When multiple threads access shared resources, it can lead to inconsistent or incorrect results. Thread synchronization
ensures that only one thread can access a resource at a time, preventing race conditions.

1. **Synchronized Methods**:
    - You can synchronize a method by using the `synchronized` keyword. This ensures that only one thread can execute
      the method at a time.

   **Example:**
   ```java
   class Counter {
       private int count = 0;

       public synchronized void increment() {
           count++;
       }

       public int getCount() {
           return count;
       }
   }
   ```

2. **Synchronized Blocks**:
    - You can synchronize a block of code within a method using the `synchronized` keyword with a specific object as the
      lock.

   **Example:**
   ```java
   class Counter {
       private int count = 0;
       private final Object lock = new Object();

       public void increment() {
           synchronized (lock) {
               count++;
           }
       }

       public int getCount() {
           return count;
       }
   }
   ```

3. **Volatile Keyword**:
    - The `volatile` keyword ensures that the value of a variable is always read from the main memory and not from the
      thread's local cache.

   **Example:**
   ```java
   class MyThread extends Thread {
       private volatile boolean running = true;

       public void run() {
           while (running) {
               // Thread logic
           }
       }

       public void stopRunning() {
           running = false;
       }
   }
   ```

### Inter-Thread Communication

Java provides mechanisms like `wait()`, `notify()`, and `notifyAll()` to allow threads to communicate with each other.

1. **`wait()`**:
    - Causes the current thread to wait until another thread invokes `notify()` or `notifyAll()` on the same object.

   **Example:**
   ```java
   synchronized(lock) {
       lock.wait(); // The current thread waits
   }
   ```

2. **`notify()`**:
    - Wakes up a single thread that is waiting on the object's monitor.

   **Example:**
   ```java
   synchronized(lock) {
       lock.notify(); // Wake up one waiting thread
   }
   ```

3. **`notifyAll()`**:
    - Wakes up all threads that are waiting on the object's monitor.

   **Example:**
   ```java
   synchronized(lock) {
       lock.notifyAll(); // Wake up all waiting threads
   }
   ```

### Thread Pools

Creating and managing a large number of threads can be costly in terms of system resources. Thread pools are a way to
manage multiple threads efficiently. A thread pool is a pool of worker threads that execute tasks. Instead of creating a
new thread for each task, the task is assigned to a thread in the pool, reducing the overhead.

Java provides the `ExecutorService` interface for managing thread pools.

**Example:**

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            Runnable worker = new MyRunnable("" + i);
            executor.execute(worker);
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        System.out.println("All threads finished execution");
    }
}

class MyRunnable implements Runnable {
    private final String command;

    public MyRunnable(String s) {
        this.command = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Command = " + command);
        processCommand();
        System.out.println(Thread.currentThread().getName() + " End.");
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

### Deadlock

Deadlock is a situation where two or more threads are blocked forever, waiting for each other to release resources.
Deadlock can occur when two threads have a circular dependency on two or more resources.

**Example:**

```java
public class DeadlockExample {
    public static void main(String[] args) {
        final Object resource1 = "resource1";
        final Object resource2 = "resource2";

        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: locked resource 1");

                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }

                synchronized (resource2) {
                    System.out.println("Thread 1: locked resource 2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2:

                        locked resource 2");

                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }

                synchronized (resource1) {
                    System.out.println("Thread 2: locked resource 1");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
```

In this example, `t1` locks `resource1` and waits for `resource2`, while `t2` locks `resource2` and waits
for `resource1`, resulting in a deadlock.

### Conclusion

Threads are an essential part of Java for enabling concurrency and parallelism in programs. Understanding how to create,
manage, and synchronize threads, as well as handling common issues like deadlock, is crucial for developing efficient
and responsive applications. Proper use of threads can significantly improve the performance and responsiveness of your
Java applications.