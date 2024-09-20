package jaava.oops;

class A {
    public int addThem(int a, int b) {
        return a + b;
    }
}

class B extends A {
    public int addThem(int a, int b) {       // Method OverRiding - for addThem
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
