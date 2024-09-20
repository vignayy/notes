package jaava.j8features;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Student {

    String name;
    int age;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Students{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class ConstructorReference {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("John", "Jon", "Snow", "Alex", "Joffery");

        List<Student> students = new ArrayList<Student>();

//        for(String name: names){
//            students.add(new Student(name));
//        }

        students = names.stream()
//              .map(str -> new Student(str))
                .map(Student::new)
                .toList();

        System.out.println(students);
    }
}
