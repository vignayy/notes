package jaava.j8features;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MethodReference {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("John", "Jon", "Snow", "Alex", "Joffery");

        List<String> capNames = names.stream()
//                              .map(str -> str.toUpperCase())
                .map(String::toUpperCase)
                .toList();

//        capNames.forEach(i -> System.out.println(i));
        capNames.forEach(System.out::println);

    }
}
