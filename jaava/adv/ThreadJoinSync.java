package jaava.adv;

// Mutation in Thread in not good
// Two Threads working on same variable (Changing variable)
// 2 Threads -> join() - full thread execution &then main runs -> synchronized for maintaining Queue: Called by one method at a time
//Saved form Race Condition

class Counter {
    int count;

    public synchronized void increaseby1() {
        count++;
    }
}

public class ThreadJoinSync {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Race Condition saved by \n Join Threads \n Sync Threads \n print Count: ");
        int rounds = 10000;
        Counter cnt = new Counter();

        Runnable rnbl1 = () -> {
            for (int i = 0; i < rounds; i++) {
                cnt.increaseby1();
            }
        };

        Thread th1 = new Thread(rnbl1);

        // Direct Lambda Expression
        Thread th2 = new Thread(() -> {
            for (int i = 0; i < rounds; i++) {
                cnt.increaseby1();
            }
        });

        th1.start();
        th2.start();

        th1.join();
        th2.join();

        System.out.println(cnt.count);

    }
}
