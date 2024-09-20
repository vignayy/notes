package jaava.j8features;

interface Printer {
    void print();

    static void fun() {
        System.out.println("Have Fun");
    }

    default void scan() {
        System.out.println("Scanner not supported..");
    }
}

class AdvancedPrinter implements Printer {
    public void print() {
        System.out.println("Hello UST");
    }

    public void scan() {
        System.out.println("Enhanced Scanner..");
    }
}

class BasicPrinter implements Printer {
    public void print() {
        System.out.println("Hello UST");
    }
}

public class DefaultAndStatic {
    public static void main(String[] args) {
        Printer printer = new AdvancedPrinter();
        printer.print();
        printer.scan();
        Printer.fun();
    }


}