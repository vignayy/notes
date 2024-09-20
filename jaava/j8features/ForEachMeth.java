package jaava.j8features;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ForEachMeth {
    public static void main(String[] args) {

        List<Integer> nums = Arrays.asList(9, 45, 27, 63, 81, 90, 72);

        // For Each Inner Implementation
        Consumer<Integer> con = new Consumer<Integer>() {
            public void accept(Integer n) {
                System.out.println(n);
            }
        };
        nums.forEach(con);

        System.out.println("For Each by Lambda");
        // For each by Lambda Exp
        nums.forEach(n -> System.out.println(n));
        System.out.println(nums);
    }
}
