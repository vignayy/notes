package jaava.j8features;

@FunctionalInterface
interface Lamby {
    void hiLambda(int i);
}

interface AddLamby {
    int addLambda(int i, int j);
}

public class LambdaIntro {
    public static void main(String[] args) {

        Lamby lamby = i -> System.out.println("Iam simple Lambda Exp" + i);   // -> Single parameter & Single Statement -> Simple Lambda Expression
        lamby.hiLambda(1);

        AddLamby addLamby = (i, j) -> i + j;          // Lamby with return -> {return i+ j;}; (Simplified more)
        int sum = addLamby.addLambda(4, 5);
        System.out.println("AddLamby: " + sum);

    }
}

