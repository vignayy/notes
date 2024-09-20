package jaava.adv;

class FirstThread extends Thread {

    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("First Thread");
        }
    }

}

class SecondThread extends Thread {
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Second Thread");
        }
    }
}

public class ThreadIntro {
    public static void main(String[] args) {
        System.out.println("Multiple Threads Execution....");

        FirstThread t1 = new FirstThread();
        SecondThread t2 = new SecondThread();

        t1.start();
        t2.start();

    }
}
