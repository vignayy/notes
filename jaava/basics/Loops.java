package jaava.basics;

public class Loops {
    public static void main(String[] args) {
//        While Loop
        System.out.println("While Loop");
        int w = 0;
        while (w <= 4) {
            System.out.println("Hi " + w);
            int j = 1;
            while (j <= 3) {
                System.out.println("While " + j);
                j++;
            }
            w++;
        }
        System.out.println("Bye " + w);

//        DoWhile Loop
        System.out.println("DoWhile Loop");
        int dw = 3;
        do {
            System.out.println("DoWhile " + dw);
            dw++;
        } while (dw <= 5);

//         For Loop
        for (int i = 0; i < 5; i++) {
            System.out.println("For " + i);
        }
//        Enhanced For Loop -> For Iteration only
        int[] arr = {1, 2, 3, 4};
        for (int i : arr) {
            System.out.println("EnhancedFor " + i);
        }
    }

}
