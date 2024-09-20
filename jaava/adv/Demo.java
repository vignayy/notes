package jaava.adv;

class A {
    public int addThem(int a, int b) {
        return a + b;
    }
}

class B extends A {
    @Override   // Annotation
    public int addThem(int a, int b) {
        return a + b + 1;
    }
}

public class Demo {
    public static void main(String[] args) {

        B obj = new B();
        int k = obj.addThem(4, 4);
        System.out.println(k);

    }
}
