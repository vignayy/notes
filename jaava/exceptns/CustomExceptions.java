package jaava.exceptns;

class MyOwnException extends Exception {
    public MyOwnException(String message) {
        super(message);
    }
}

public class CustomExceptions {
    public static void main(String[] args) {

        int i = 20;
        int j = 0;

        try {
            j = 18 / i;
            if (j == 0) throw new MyOwnException("I don't print zeros");
        } catch (MyOwnException e) {
            j = 18;
            System.out.println("Hey That's Worng " + e);
        } catch (Exception e) {
            System.out.println("Something went wrong." + e);
        }
        System.out.println(j);
        System.out.println("HASTA LA VISTA ");
    }
}


