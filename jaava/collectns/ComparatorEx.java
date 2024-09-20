package jaava.collectns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//class Student implements Comparable<Student> {    // Also can override compareTo
class Student {
    int age;
    String name;

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String toString() {
        return "Students{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

/*    public int compareTo(Student o) {
        if(i.age > j.age) return 1;
        else return -1;
     }
*/
}

public class ComparatorEx {
    public static void main(String[] args) {

        List<Integer> nums = new ArrayList<>(List.of(63, 81, 36, 18, 27));
        Collections.sort(nums);
        System.out.println(nums);

        Comparator<Integer> myRule = new Comparator<Integer>() {
            public int compare(Integer i, Integer j) {
                if (i % 10 > j % 10) return 1;
                else return -1;
            }
        };
        Collections.sort(nums, myRule);
        System.out.println(nums);

        List<Student> studs = new ArrayList<Student>();
        studs.add(new Student(19, "Jon"));
        studs.add(new Student(17, "Snow"));
        studs.add(new Student(22, "White"));
        studs.add(new Student(17, "Wolf"));
        studs.add(new Student(15, "Lord"));

//        Collections.sort(studs);  //if we implement Comparable and override compareTo in Student class
        for (Student s : studs) {
            System.out.println(s);
        }
/*                      // Anony InnerCLass
        Comparator<Student> byAge = new Comparator<Student>() {
            public int compare(Student i,  Student j) {
                return (i.age > j.age) ? 1 : -1;
            }
        };
*/                      // Lambda Exp
//        Comparator<Student> byAge = (i, j) -> i.age > j.age?1:-1;
//        Collections.sort(studs, byAge);
        // Direct Lambda
        Collections.sort(studs, (i, j) -> (i.age > j.age) ? 1 : -1);

        System.out.println(studs);


    }
}
