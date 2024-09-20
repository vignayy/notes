package jaava.adv;

class FirstSleepyThread extends Thread {
    int count = ThreadPrioritySleep.count;

    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println("Wakeup Early");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

}

class SecondSleepyThread extends Thread {
    int count = ThreadPrioritySleep.count;

    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println("Sleep Again");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

public class ThreadPrioritySleep {
    static int count = 100;

    public static void main(String[] args) {
        System.out.println("Multiple Threads Execution....");

        FirstSleepyThread th1 = new FirstSleepyThread();
        SecondSleepyThread th2 = new SecondSleepyThread();

        th1.setPriority(8);                     // SUGGESTING Priority
        th1.setPriority(Thread.MAX_PRIORITY);
        System.out.println(th1.getPriority());  // Getting the Priority

        th1.start();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        th2.start();


    }
}
