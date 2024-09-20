package jaava.oops.keywords;

// static Vriable | static Method | static block
class Mobile {

    static String name;     //Static Variable
    static String build;
    String brand;
    double price = 100;     //Intializing default values

    // Static Block -> Initializing static Variables
    static {
        build = "Indian";
    }

    //Constructor Method -> Initializing default Values
    public Mobile() {
        brand = "Nothing";
    }

    public void show() {
        System.out.println(brand + " " + name + " : $ " + price);
    }

    public static void showStatic() {
//         Cannot use non - static variables
        System.out.println("Only Static Variables " + name);
    }

    public static void showAndUseStatic(Mobile obj) {
//        We can use Non-Static Variable by passing Object Reference
        System.out.println("Use Non Static " + obj.brand + " " + name + " : $ " + obj.price);
    }
}

public class StaticKeyWord {
    public static void main(String[] args) {

        Mobile obj1 = new Mobile();
        obj1.brand = "Apple";
        obj1.price = 999;
//        obj1.name = "Iphone 69";
        Mobile.name = "Smart Phone";
        obj1.show();

        Mobile obj2 = new Mobile();
        obj2.brand = "Samsung";
        obj2.price = 909;
//        obj2.name = "S69 Utlra++";
//        obj2.show();

        Mobile.showStatic();            // Static Method
        Mobile.showAndUseStatic(obj2);  // Static Method using Non-Static variables using object reference
        System.out.println(Mobile.build);
    }

}
