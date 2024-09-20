package jaava.adv;

interface Inter {
    String name = "Iphone";    // Static and FINAL by Default
    int num = 15;            // Variables in Interface are

    //    public abstract void jump();
    void jump();            // Methods are abstract public by Default
}

class Implee implements Inter {

    public void jump() {
        System.out.println("I can Jump");
    }
}

public class InterfaceIntro {
    public static void main(String[] args) {

        Inter obj = new Implee();
        obj.jump();
        System.out.println(Inter.name + Inter.num);
    }
}
