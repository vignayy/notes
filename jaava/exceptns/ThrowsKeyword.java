package jaava.exceptns;

class DukingException {
    public void showClass() throws ClassNotFoundException {
        Class.forName("Duck");
    }
}

public class ThrowsKeyword {
    public static void main(String[] args) {

        DukingException dukky = new DukingException();
        try {
            dukky.showClass();
        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
            System.out.println(e);
        }

    }
}
