package jaava.ds;

public class StringsIntro {
    public static void main(String[] args) {

        String name = "Jon ";
        System.out.println("You Know Nothing " + name);
        System.out.println(name.concat("Snow"));
        name.concat("Snow");
        System.out.println(name);

        System.out.println("-".repeat(100));

        String str1 = "Hello World!";
        String str2 = "  hello world!  ";

//        Remove any leading and trailing whitespace
        System.out.println(str2.strip());
//         Comparing two strings
        System.out.println(str1.equals(str2));
//         Comparing two strings ignoring case
        System.out.println(str1.equalsIgnoreCase(str2));

//        String Buffer
        StringBuffer sbf = new StringBuffer();
        System.out.println("Empty sbf capacity() - " + sbf.capacity() + " sbf length() - " + sbf.length());
        sbf.insert(0, "Filled");
        System.out.println(sbf + " - sbf capacity() - " + sbf.capacity() + " sbf length() - " + sbf.length());

//        String Builder is almost same as String Buffer

        StringBuilder sb1 = new StringBuilder(str1);
        sb1.reverse();
        System.out.println(sb1);

    }

}
