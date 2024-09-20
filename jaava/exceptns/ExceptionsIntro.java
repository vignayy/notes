package jaava.exceptns;

public class ExceptionsIntro {
    public static void main(String[] args) {

        int i = 0;
        int j = 0;

        try {
            j = 9 / i;
        } catch (Exception e) {
            System.out.println("Something Went Wrong..! " + e);
        }
        System.out.println(j);
        System.out.println("Handled Exception");

    }
}
