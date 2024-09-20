package jaava.basics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class SecondLarge {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
//      int arr[] = new int[5];
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(scan.nextInt());
        }
//        Collections.sort(arr);
//        System.out.println(arr.get(n-2));
        int max = Integer.MIN_VALUE, result = Integer.MIN_VALUE;
        for (int i : arr) {
            if (i > max) {
                result = max;
                max = i;
            }
            if (i < max && i > result) {
                result = i;
            }
        }
        System.out.println(result);
    }
}