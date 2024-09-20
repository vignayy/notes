package jaava.oops.topics;

class Computer {
    public void playMusic() {
        System.out.println("Playing Music");
    }

    public String getPenName() {
        return "Cello";
    }

    public String buyPen(int num) {
        if (num < 5) return "Insufficient Amount";
        else return "Bought";
    }
}

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

public class Methods {
    public static void main(String[] args) {

        Computer myComp = new Computer();
        myComp.playMusic();
        String penName = myComp.getPenName();
        String status = myComp.buyPen(20);
        System.out.println(penName + status);

        // Method Overloading - same methods with different Parameters and functionality
        OverloadingCalci calci = new OverloadingCalci();
        calci.add(3, 6);
        calci.add(3, 6, 9);
        calci.add(1.5, 7.5);

    }
}
