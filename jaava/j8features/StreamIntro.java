package jaava.j8features;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class StreamIntro {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(9, 45, 27, 63, 81, 90, 72);
//        nums.forEach(n-> System.out.println(n));
        // Filter even numbers -> Double them -> add them

/*
        Stream<Integer> s1 = nums.stream();
        Stream<Integer> s2 = s1.filter(n -> n%2 == 0);
        Stream<Integer> s3 = s2.map(n -> n*2);
        int result = s3.reduce(0,(c,e) -> c+e);
        System.out.println(result);
*/
        // Short-cut Stream
        int result = nums.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .reduce(0, (c, e) -> c + e);
        // One-Line
//        int res = nums.stream().filter(n->n%2==0).map(n->n*2).reduce(0, (c,e) -> c+e);
        System.out.println(result);

//        s1.forEach(n -> System.out.println(n));       // Cannot use stream more than once
        System.out.println(nums);
    }
}
