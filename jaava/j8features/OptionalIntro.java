package jaava.j8features;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalIntro {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("John", "Jon", "Snow", "Alex", "Joffery");

        Optional<String> name = names.stream()
                .filter(str -> str.contains("x"))
                .findFirst();
//                            .orElse("Not Other");

//        System.out.println(name);     // use .orElse in Stream to avoid NullPointerException or use Optional class

        System.out.println(name.orElse("Not Found"));

    }
}
