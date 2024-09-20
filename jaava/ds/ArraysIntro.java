package jaava.ds;

public class ArraysIntro {
    public static void main(String[] args) {

//        int[] arr = new int[5];
//        int arr[] = {10,20,50,70,80}; -> C Style
//        int []arr = {10,20,50,70,80}; -> Java style (Version 2)
        int[] arr = {10, 20, 50, 70, 80};
/*
        for(int i=0; i< arr.length; i++){
            System.out.println(arr[i]);
        }
*/
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

//        Multi Dimensional Arrays
        System.out.println("----- Multi Dimensional Array (2D) -----");

        int[][] nums = new int[3][4];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                nums[i][j] = (int) (Math.random() * 100);
            }
        }

        for (int[] n : nums) {
            for (int m : n) {
                System.out.print(m + " ");
            }
            System.out.println();
        }

//        Jagged Array
        System.out.println("----- Jagged Array -----");

        int[][] jaggedArr = new int[3][];
        jaggedArr[0] = new int[4];
        jaggedArr[1] = new int[6];
        jaggedArr[2] = new int[3];

        for (int i = 0; i < jaggedArr.length; i++) {
            for (int j = 0; j < jaggedArr[i].length; j++) {
                jaggedArr[i][j] = (int) (Math.random() * 100);
            }
        }

        for (int[] n : jaggedArr) {
            for (int m : n) {
                System.out.print(m + " ");
            }
            System.out.println();
        }

//        3-D Array
        System.out.println("----- 3-D Array -----");

        int[][][] Three3DArr = new int[3][4][5];

        for (int[][] i : Three3DArr) {
            for (int[] j : i) {
                for (int k : j) {
                    System.out.print(k + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
