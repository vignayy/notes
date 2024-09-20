package jaava.oops.keywords;

//final - variable, method, class

//final class Calc                  //prevents Inheritance
class Calc {
    //    public final void show()  //prevents Overriding
    public void show() {
        System.out.println("Final Class prevents its Inheritance");
    }

    public void add(int a, int b) {
        System.out.println(a + b);
    }
}

class AdvCalc extends Calc {
    public void show() {
        System.out.println("Final Medtod prevents its Overriding");
    }
}


public class FinalKeyword {
    public static void main(String[] args) {

        final int num = 8;    //num is a constant now
//        num=9;
//        System.out.println(num);
        new Calc().show();
        AdvCalc obj = new AdvCalc();
        obj.show();
        obj.add(4, 5);
    }


}