package jaava.oops.concepts;

class Calculator {
    int r = 0;

    public int add(int n1, int n2) {
        r = n1 + n2;
        return r;
    }
}

public class ClassNObj {
    public static void main(String[] args) {
//        Stack Memory
        System.out.println("This is main()");
        int num1 = 3, num2 = 6;
        Calculator calcy = new Calculator(); //Reference Variable - Object Creation
//        Object is Created in Heap Memory
        int res = calcy.add(num1, num2);
        System.out.println(res);
//        Anonymous Object - Using Object only Once
        int anony = new Calculator().add(9, 9);
        System.out.println("Anonymous Object - " + anony);

    }
}
