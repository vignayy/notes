package jaava.basics;

public class SwitchCase {
    public static void main(String[] args) {
//          Old Switch Statement
        int n = 1;
        switch (n) {
            case (1):
                System.out.println("Its One");
                break;
            case (3): {
                System.out.println("Its Three");
                break;
            }
            case (9):
                System.out.println("Its Nine");
                break;
            default:
                System.out.println("Enter a Valid number");
        }

//          Another Switch Statement - Can Use Strings (After Java 5)
        String day = "Monday";
        switch (day) {
            case "Saturday", "Sunday":
                System.out.println("8 AM");
                break;
            case "Monday": {
                System.out.println("6 AM");
                break;
            }
            default:
                System.out.println("7 AM");
        }
//          New Version Switch Statement -> No need Break
        String alaram = "";
        switch (day) {
            case "Saturday", "Sunday" -> System.out.println("8 AM");
            case "Monday" -> System.out.println("3 AM");
            default -> System.out.println("7 AM");
        }
        switch (day) {
            case "Sunday" -> alaram = "8 AM";
            case "Monday" -> alaram = "6 AM";
            default -> alaram = "7 AM";
        }
        System.out.println(alaram);

//          New Version Advanced -> Can Return as an Expression

        String time = "";
        time = switch (day) {
            case "Sunday" -> "8 AM";
            case "Monday" -> "9 AM";
            default -> "7 AM";
        };
        System.out.println(time);

//          If we dont want to use -> use : with yield

        String wakeup = switch (day) {
            case "Sunday":
                yield "8 AM";
            case "Monday":
                yield "5 AM";
            default:
                yield "7 AM";
        };
        System.out.println(wakeup);

    }

}
