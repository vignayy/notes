package jaava.oops.concepts;

class OverloadingCalci {
//    Method Overloading - same methods with different Parameters
//    Parameters must and should differ - not possible if we change only the return type

    public int add(int n1, int n2) {
        return n1 + n2;
    }

    public int add(int n1, int n2, int n3) {
        System.out.println(n1 + n2 + n3);
        return 0;
    }

    public double add(double n1, double n2) {
        System.out.println(n1 + n2);
        return 0;
    }
}

public class MethOverLoading {
    public static void main(String[] args) {
        OverloadingCalci calci = new OverloadingCalci();
        calci.add(3, 6);
        calci.add(3, 6, 9);
        calci.add(1.5, 7.5);
    }
}
