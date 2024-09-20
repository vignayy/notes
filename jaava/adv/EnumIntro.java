package jaava.adv;

enum Status {
    Running, Failed, Pending, Success
}

public class EnumIntro {
    public static void main(String[] args) {

        int i = 5;
//    	Status s= Status.Running;
//    	Status s= Status.Failed;
//    	Status s= Status.NoIdea;
//    	Status s= Status.Success;

//    	System.out.println(s);
//    	System.out.println(s.ordinal());

        Status[] ss = Status.values();
        System.out.println(ss);

        for (Status s : ss) {
            System.out.println(s);
            System.out.println(s + " : " + s.ordinal());
        }


        Status s = Status.Pending;
        switch (s) {
            case Running:
                System.out.println("All Good");
                break;

            case Failed:
                System.out.println("Try Again");
                break;

            case Pending:
                System.out.println("Please Wait");
                break;

            default:
                System.out.println("Done");
                break;
        }

        if (s == Status.Running)
            System.out.println("All Good");
        else if (s == Status.Failed)
            System.out.println("Try Again");
        else if (s == Status.Pending)
            System.out.println("Please Wait");
        else
            System.out.println("Done");


    }
}