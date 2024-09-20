package jaava.oops.keywords;

abstract class Car {                     // Abstract Class

    abstract public void drive();       // Abstract Methods

    abstract public void fly();

    void playMusic() {
        System.out.println("Music Playing...");
    }
}

abstract class Swift extends Car {

    public void drive() {
        System.out.println("Driving..");
    }
}

class SwiftProMax extends Swift {            // Concrete Class

    public void fly() {
        System.out.println("Flying...");
    }
}

public class AbstractKeyword {
    public static void main(String[] args) {

        Car myCar = new SwiftProMax();          //We Cannot Create an Object of Abstract Class - but also we can use Anonymous Inner Class
        myCar.playMusic();
        myCar.drive();
        myCar.fly();

    }

}
