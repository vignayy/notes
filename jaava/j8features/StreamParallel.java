package jaava.j8features;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StreamParallel {
    public static void main(String[] args) {
        int size = 10000;
        List<Integer> nums = new ArrayList<>(size);
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            nums.add(rand.nextInt(100));
        }
//        System.out.println(nums);
//        int sum = nums.stream().map(n -> n*2).reduce(0, (c,e) -> c+e);
//        System.out.println(sum);

        //Normal Stream
        long strartSeq = System.currentTimeMillis();
//        int sum1 = nums.stream().map(n -> n * 2).mapToInt(i -> i).sum();
        int sum1 = nums.stream()
                .map(n -> {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                    }
                    return n * 2;
                })
                .mapToInt(i -> i).sum();
        long endSeq = System.currentTimeMillis();
        long seqTime = endSeq - strartSeq;

        //Parallel Stream
        long strartParallel = System.currentTimeMillis();
//        int sum2 = nums.parallelStream().map(n -> n * 2).mapToInt(i -> i).sum();
        int sum2 = nums.parallelStream()
                .map(n -> {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                    }
                    return n * 2;
                })
                .mapToInt(i -> i).sum();
        long endParallel = System.currentTimeMillis();
        long parallelTime = endParallel - strartParallel;

        System.out.println("SeqSum: " + sum1 + " ParallelSum: " + sum2);
        System.out.println("SeqTime: " + seqTime + "  " + "ParallelTime: " + parallelTime);
    }
}
