package jaava.basics;

public class Basics {
    public static void main(String[] args) {

        System.out.println("Data Types \n");
        int num1 = 9;
//        int num2 = 14;
//        int result = num1 + num2;
        byte by = 127;
        short sh = 549;
        long lng = 99999L;

        float fl = 3.14f;
        double dl = 99.99;

        char c = 'A';

        boolean bool = true;

        float ft = 3.784f;
        System.out.printf("%.2f", ft);

//        Operators -> Arithematic, Relational, Logical
        int a = 3;
        int b = 6;
//        a+b, a-b, a*b, a/b, a%b, a++, ++a;
//        <, >, <=, >=, ==
//        &&, ||, ! (&, | - are also valid but not short circuit)
        boolean res = a < b & !(a < 0);
        System.out.println(res);
//        if-else
//        Ternary Operator
        int n = 10;
//        if (n>0) n=9;
//        else n++;
//        n = n<0 ? 9 : n--;
        n = n < 0 ? 9 : --n;
        System.out.println(n);
//        System.out.printf("%d %f",n);
//        SwitchCase statement Old & New versions
//        Loops

    }
}
