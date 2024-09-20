package jaava.basics;

import java.io.IOException;
import java.util.Scanner;

public class UserInput {
    public static void main(String[] args) throws IOException {     //for System.in.read
        System.out.println("Enter a number: ");

//        int num = System.in.read(); //Gives ASCII value and also throws Exception

//        InputStreamReader in = new InputStreamReader(System.in);  //Before java1.5
//        BufferedReader bf = new BufferedReader(in);
//        int num =Integer.parseInt(bf.readLine());     //Gets Int
//        bf.close();                                   //Close Resource after Use

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        System.out.println(num);

    }
}
