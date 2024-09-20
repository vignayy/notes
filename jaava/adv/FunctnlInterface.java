package jaava.adv;

@FunctionalInterface
interface FunInter {
    void sAm();
//    void extraMeth();
}

public class FunctnlInterface {
    public static void main(String[] args) {
        FunInter funObj = new FunInter() {  // Using Anony InnerClass to implement Functional interface Directly
            @Override
            public void sAm() {
                System.out.println("Single Abstract Method (SAM): A functional interface has only one abstract method");
            }
        };
        funObj.sAm();
    }

}
