package jaava.adv;

// #2nd Way of Implementing Thread through Runnable for Multiple Inheritance
class FirstRunningThread implements Runnable {
    int count = ThreadRunnable.count;

    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println("Thread 1------------- " + i);
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

public class ThreadRunnable {
    static int count = 1000;

    public static void main(String[] args) {
        System.out.println("Multiple Threads Execution by Implementing Runnable Interface");

        Runnable rnbl1 = new FirstRunningThread();

        //Using Lambda Expressions, as Runnable is a Functional Interface
        Runnable rnblLambda = () -> {
            for (int i = 0; i < count; i++) System.out.println("Lambda Thread... " + i);
        };

        Thread th1 = new Thread(rnbl1);             //start() is Not There in Runnable
        Thread thLambda = new Thread(rnblLambda);

        th1.start();
        System.out.println("First************Excecution");
        thLambda.start();

    }
}
