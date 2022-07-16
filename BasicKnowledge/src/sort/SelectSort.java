package sort;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-06-03 15:37
 * @Description
 */

public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1, -1, 90, 123};
//        System.out.println(Arrays.toString(arr));
//        select(arr);
//        System.out.println(Arrays.toString(arr));
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random()*80000);
        }
        long start = System.currentTimeMillis();
        select(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    //每轮选择最小的放在相应位置
    public static void select(int[] arr) {
        int temp = 0; //记录最小值
        int index = 0; //记录最小值位置
        for (int i = 0; i < arr.length - 1; i++) {
            //假定每轮初始位置最小
            temp = arr[i];
            index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (temp > arr[j]) {
                    temp = arr[j];
                    index = j;
                }
            }
            //若更新，则交换位置
            if (index != i) {
                arr[index] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
