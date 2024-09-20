package jaava.oops.topics;


class UpperClass {

    public void printUpper() {
        System.out.println("Upper");
    }
}

class LowerClass extends UpperClass {

    public void printLower() {
        System.out.println("Lower");
    }
}

public class TypeCastingObjects {
    public static void main(String[] args) {

        double d = 4.5;
        int i = (int) d;         // TypeCasting -> Double to Int -> Loss of Data
        System.out.println(i);  // Prints 4

        UpperClass obj = new LowerClass();      //UpCasting -> no Need to Mention : Implicit
        obj.printUpper();

        LowerClass obj1 = (LowerClass) obj;     //DownCastinh : Upper obj to Lower obj1
        obj1.printLower();

    }
}
