package jaava.oops.keywords;

public class TestQsn {
    static int n = 1;

    static {
        System.out.print(n++ + " ");
    }

    public void TestQsn() {
        n++;
    }

    public void printS() {
        System.out.print(n++ + " ");
    }

    public static void main(String[] args) {
        System.out.print("0 ");
        new TestQsn().printS();
    }
}