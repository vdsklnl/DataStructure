package sort;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-06-03 21:08
 * @Description
 */

public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        System.out.println(Arrays.toString(arr));
//        shell(arr);
//        System.out.println(Arrays.toString(arr));
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random()*8000000);
        }
        long start = System.currentTimeMillis();
        shell(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    //交换法
//    public static void shell(int[] arr) {
//        int temp = 0; //辅助交换
//        //记录组数group
//        for (int group = arr.length / 2; group > 0; group /= 2) {
//            for (int i = group; i < arr.length; i++) {
//                //每组比较个数，有group个元素不用比较，i起始为group
//                for (int j = i - group; j >= 0; j -= group) {
//                    //j从0开始，与相隔group元素比较，大则交换
//                    if (arr[j] > arr[j + group]) {
//                        temp = arr[j];
//                        arr[j] = arr[j + group];
//                        arr[j + group] = temp;
//                    }
//                }
//            }
//        }
//    }

    //改为移位法，找到位置直接插入，而不遍历交换
    public static void shell(int[] arr) {
        int temp = 0; //定义待插入的数
        int insert = 0; //定义插入位置
        //记录组数group
        for (int group = arr.length / 2; group > 0; group /= 2) {
            for (int i = group; i < arr.length; i++) {
                temp = arr[i];
                insert = i - group; //定位于前一位置
                //保证到0位置停止，递增排序
                while (insert >= 0&&temp < arr[insert]) {
                    arr[insert + group] = arr[insert];
                    insert-=group;
                }
                //循环结束，temp位置找到，为insert + 1
                if (insert + group != i)
                    arr[insert + group] = temp;
            }
        }
    }
}
