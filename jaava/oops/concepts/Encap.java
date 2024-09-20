package jaava.oops.concepts;

class Human {

    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int a) {
        age = a;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

}

public class Encap {
    public static void main(String[] args) {

        Human obj = new Human();

        obj.setAge(18);
        obj.setName("Jon");
        System.out.println(obj.getName() + " : " + obj.getAge());

    }

}
