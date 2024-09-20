package jaava.ds;

class Student {
    String name;
    int marks;
}

public class ArrOfObjects {
    public static void main(String[] args) {

        Student s1 = new Student();
        Student s2 = new Student();
        Student s3 = new Student();

        s1.name = "John";
        s1.marks = 86;

        s2.name = "Wick";
        s2.marks = 96;

        s3.name = "Snow";
        s3.marks = 99;
//        Explicit way of creating a Student Array and assigning Values
        Student[] stu = new Student[3];
        stu[0] = s1;
        stu[1] = s2;
        stu[2] = s3;

        for (int i = 0; i < stu.length; i++) {
            System.out.println(stu[i].name + " : " + stu[i].marks);
        }

        // Store the students in an array directly
        Student[] students = {s1, s2, s3};
        // Using Enhanced For loop to print the details of each student
        for (Student s : students) {
            System.out.println(s.name + "'s Marks : " + s.marks);
        }
    }
}
