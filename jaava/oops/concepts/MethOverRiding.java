package jaava.oops.concepts;

class Father {

    public void showPhone() {
        System.out.println("I Have Iphone 12");
    }

    public void printItFthr() {
        System.out.println("Father");
    }
}

class Son extends Father {
    @Override   // Annotation
    public void showPhone() {                // Method OverRiding
//            super.showPhone();
        System.out.println("I Have Iphone 14 Plus - Overrided show() Method in Father");
    }

    public void printItSon() {
        System.out.println("Son");
    }
}

class GrandSon extends Son {

    public void showPhone() {                // Method OverRiding
        System.out.println("I Have Iphone 15 Pro Max - Overrided show() Method in Son");
    }
}

public class MethOverRiding {
    public static void main(String[] args) {

        Son obj = new Son();
        obj.showPhone();          // Prints Son's Phone -> Method OverRiding

        obj = new GrandSon();    // MethOvrRiding -> Dynamic Method Dispatch : RunTimePolymorphism
        obj.showPhone();        // Prints GrandSon's Phone as the Object Type is Grandson

        Father obj2 = new Son();
//        obj2.printItSon();

    }
}
