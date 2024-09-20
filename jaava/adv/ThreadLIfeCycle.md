The life cycle of a thread in Java refers to the various states that a thread goes through during its lifetime. A thread
can be in one of several states, from its creation to its termination. Understanding these states is crucial for
effectively managing thread behavior in a multithreaded environment.

### Thread Life Cycle States

1. **New**:
    - **State**: The thread is created but not yet started.
    - **Explanation**: When a thread is instantiated (for example, `Thread t = new Thread();`), it is in the "new"
      state. At this point, the thread is not yet active. It remains in this state until the `start()` method is
      invoked.
    - **Key Point**: No system resources have been allocated to the thread in this state.

2. **Runnable**:
    - **State**: The thread is ready to run and is waiting for CPU time.
    - **Explanation**: Once the `start()` method is called, the thread transitions to the "runnable" state. In this
      state, the thread is considered alive and can be scheduled for execution by the thread scheduler. However, it may
      not be running immediately, as it could be waiting for CPU resources.
    - **Key Point**: The thread can be running or waiting to be run.

3. **Blocked**:
    - **State**: The thread is blocked and waiting for a monitor lock.
    - **Explanation**: A thread enters the "blocked" state when it is trying to access a synchronized block or method
      but another thread holds the monitor lock. The thread will remain in this state until it acquires the required
      lock.
    - **Key Point**: The thread cannot proceed until the lock is available.

4. **Waiting**:
    - **State**: The thread is waiting indefinitely for another thread to perform a particular action.
    - **Explanation**: A thread enters the "waiting" state when it calls `Object.wait()` or `Thread.join()` without a
      timeout, or it is waiting for another thread to notify it using `Object.notify()` or `Object.notifyAll()`. The
      thread will remain in this state until it is notified or interrupted.
    - **Key Point**: The thread is not consuming CPU time while waiting.

5. **Timed Waiting**:
    - **State**: The thread is waiting for another thread to perform a particular action for a specified amount of time.
    - **Explanation**: A thread enters the "timed waiting" state when it calls methods
      like `Thread.sleep(long millis)`, `Object.wait(long timeout)`, `Thread.join(long millis)`,
      or `LockSupport.parkNanos()`. The thread will return to the runnable state after the specified time has elapsed or
      it has been notified.
    - **Key Point**: The thread will automatically wake up after the time has passed.

6. **Terminated (Dead)**:
    - **State**: The thread has finished executing.
    - **Explanation**: A thread enters the "terminated" state when its `run()` method or `call()` method (for
      a `Callable` object) completes, or when the thread is explicitly terminated by throwing an unhandled exception.
      Once a thread is terminated, it cannot be restarted.
    - **Key Point**: The thread's lifecycle has ended, and it will not move to any other state.

### Transition Between States

- **New → Runnable**: When the `start()` method is invoked on a thread, it moves from the "new" state to the "runnable"
  state.
- **Runnable → Running**: The thread scheduler picks a thread from the runnable pool to run. The thread moves from "
  runnable" to "running" when it gets CPU time.
- **Running → Blocked**: If a running thread tries to enter a synchronized block or method and the lock is not
  available, it moves to the "blocked" state.
- **Blocked → Runnable**: Once the thread acquires the lock it was waiting for, it moves back to the "runnable" state.
- **Running → Waiting**: If a running thread calls `wait()`, `join()`, or another method that causes it to wait, it
  moves to the "waiting" state.
- **Waiting → Runnable**: When the thread is notified or the waiting condition is met, it moves back to the "runnable"
  state.
- **Running → Timed Waiting**: When a running thread calls `sleep()` or a timed version of `wait()` or `join()`, it
  moves to the "timed waiting" state.
- **Timed Waiting → Runnable**: After the specified time has elapsed, or if it is notified, the thread moves back to
  the "runnable" state.
- **Running → Terminated**: When the thread finishes executing its `run()` method or an uncaught exception occurs, it
  moves to the "terminated" state.

### Diagram of Thread Life Cycle

A simplified diagram of the thread life cycle is as follows:

```
New -> Runnable -> Running
         ^        |
         |        v
   Terminated <- Blocked
        ^         |
        |         v
        Waiting <-> Timed Waiting
```

### Example Code Demonstrating Thread Life Cycle

```java
class MyThread extends Thread {
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running.");
        try {
            Thread.sleep(1000); // Moves to Timed Waiting
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " has finished executing.");
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new MyThread();

        System.out.println("Thread state after creation: " + t1.getState()); // NEW
        t1.start();
        System.out.println("Thread state after calling start(): " + t1.getState()); // RUNNABLE

        Thread.sleep(500); // Wait for a moment
        System.out.println("Thread state while sleeping: " + t1.getState()); // TIMED_WAITING

        t1.join();
        System.out.println("Thread state after completion: " + t1.getState()); // TERMINATED
    }
}
```

### Conclusion

The thread life cycle in Java is a sequence of states that a thread passes through during its execution. Understanding
these states helps in writing efficient and correct multithreaded programs. Proper handling of thread states and
transitions ensures that your program can manage concurrency issues like deadlocks, race conditions, and resource
contention effectively.