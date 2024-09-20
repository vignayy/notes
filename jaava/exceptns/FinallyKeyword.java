package jaava.exceptns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FinallyKeyword {
    public static void main(String[] args) throws NumberFormatException, IOException {

        int i = 0;
        int j = 0;
        try {
            j = 18 / i;

        } catch (Exception e) {
            System.out.println("Someting went wrong.");
            System.out.println("Bye");
        } finally {
            System.out.println("Bye");
        }

        int num = 0;
        //BufferedReader br=null;
        //Try with Resources
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
//    		InputStreamReader in =new InputStreamReader(System.in);
//    		BufferedReader br=new BufferedReader(in);
            num = Integer.parseInt(br.readLine());
            System.out.println(num);
        }
    }

}
