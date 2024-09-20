package jaava.oops.topics;

class Man {

    private int age;
    private String name;

    public Man() {             //Default Constructor - blank

    }

    public Man(int age, String name) {      //Parameterized Constructor
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class ThisGetSetConstruct {
    public static void main(String[] args) {

        Man obj = new Man();

        obj.setAge(18);
        obj.setName("Jon");
        System.out.println(obj.getName() + " : " + obj.getAge());

        Man obj1 = new Man(27, "John");
        System.out.println(obj1.getName() + " : " + obj1.getAge());


    }

}
