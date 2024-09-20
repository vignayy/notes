package jaava.adv;

// class  -> extends -> class
// class -> implements -> interface
// interface -> extends -> interface

interface InterX {
    void show();
}

interface InterY {
    void run();
}

interface InterZ extends InterY {           // Interface Extends Interface

}

class ImpleeB implements InterX, InterZ {        // Can Implement 2 Interfaces
    public void show() {
        System.out.println("in show");
    }

    public void run() {
        System.out.println("running...");
    }
}

public class InterfaceMore {
    public static void main(String[] args) {

        InterX obj;
        obj = new ImpleeB();
        obj.show();

        InterZ obj1 = new ImpleeB();
        obj1.run();

    }
}

