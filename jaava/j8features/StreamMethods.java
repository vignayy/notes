package jaava.j8features;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamMethods {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(9, 45, 27, 63, 81, 90, 72);
        System.out.println(nums);

        // Detail Implementation of filter map reduce

        //filter(predicate) -> Predicate obj Implementation -> FuncInter -> lambda to shorten
        Predicate<Integer> pred = new Predicate<Integer>() {
            public boolean test(Integer n) {
                return n % 2 == 0;
            }
        };

        // map(function) -> Function obj implementation -> FunInter -> lambda to shorten
        Function<Integer, Integer> fun = new Function<Integer, Integer>() {
            public Integer apply(Integer n) {
                return n * 2;
            }
        };

        // .reduce(0, (c, e) -> c + e); -> Start with 0(int sum = 0), carry c + element e(next element);
        BinaryOperator<Integer> adder = new BinaryOperator<Integer>() {
            public Integer apply(Integer c, Integer e) {
                return c + e;
            }
        };

        int result = nums.stream()
                .filter(pred)
                .map(fun)
                .reduce(0, adder);

        System.out.println(result);

        Stream<Integer> sortedNums = nums.stream().filter(pred).sorted();
        sortedNums.forEach(n -> System.out.println(n));
    }
}
