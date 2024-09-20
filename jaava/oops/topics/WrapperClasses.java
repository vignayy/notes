package jaava.oops.topics;

//Wrapper Classes
//int -> Integer
//char -> Character
//double -> Double
public class WrapperClasses {
    public static void main(String[] args) {

        int num = 7;
//   	Integer num1=new Integer(8);        // Old Style
//    	Integer num1=8;

//    	Integer num1=new Integer(num);     //boxing
        Integer num1 = num;                  // Auto - boxing

//      int num2=num1.intValue();          // unboxing
        int num2 = num1;                     // Auto - unboxing

//    	System.out.println(num1);
        System.out.println(num2);

        String str = "12";
        int num3 = Integer.parseInt(str);

        System.out.println(num3 + 2);
    }
}
