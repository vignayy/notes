package jaava.collectns;


import java.util.HashMap;
import java.util.Map;

public class MapIntro {
    public static void main(String[] args) {

//        Map<String, Integer> students = new HashTable<>();  //Synchronised
//        HashMap<String, Integer> students = new HashMap<>();
        Map<String, Integer> students = new HashMap<>();

        students.put("Jon", 70);
        students.put("Snow", 70);
        students.put("Robert", 92);
        students.put("Brandon", 99);
        students.put("Jon", 98);

        System.out.println(students.get("Robert"));
        System.out.println(students.keySet());
        System.out.println(students.values());

        for (String key : students.keySet()) {
            System.out.println(key + " : " + students.get(key));
        }

        System.out.println(students);

    }
}