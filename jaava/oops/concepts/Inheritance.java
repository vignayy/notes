package jaava.oops.concepts;

class Calci {
    static int res;

    public void add(int n1, int n2) {
        res = n1 + n2;
        displayResults(res);
    }

    public void sub(int n1, int n2) {
        res = n1 - n2;
        displayResults(res);
    }

    void displayResults(int res) {
        System.out.println(res);
    }

}

class AdvCalci extends Calci {

    public int multi(int n1, int n2) {
        return n1 * n2;
    }

    public int divi(int n1, int n2) {
        return n1 / n2;
    }

}

class VeryAdvCalci extends AdvCalci {

    public void pow(int n1, int n2) {
        res = (int) Math.pow(n1, n2);
        System.out.println(res);
    }

}

public class Inheritance {

    public static void main(String[] args) {

        VeryAdvCalci obj = new VeryAdvCalci();
        obj.add(5, 6);
        obj.sub(6, 3);
        int r3 = obj.multi(4, 2);
        int r4 = obj.divi(8, 2);
        obj.pow(5, 2);

        System.out.println(r3 + " " + r4);
//        System.out.println(Runtime.getRuntime().availableProcessors());
    }

}
