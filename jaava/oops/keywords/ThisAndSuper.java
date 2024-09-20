package jaava.oops.keywords;

class A {
    public A() {
//        super();  -> by default super(); is present
        System.out.println("in A");
    }

    public A(int n) {
        super();
        System.out.println("in A int");
    }
}

final class B extends A {            //final class B so B cannot be inherited further
    public B() {
        super(3);
        System.out.println("in B");
    }

    public B(int n) {
        this();
        System.out.println("in B int");

    }
}

public class ThisAndSuper {
    public static void main(String[] args) {

        B obj = new B(3);

    }
}
