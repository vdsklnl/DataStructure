package sort;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-06-03 16:15
 * @Description
 */

public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1, -1, 90, 123};
//        System.out.println(Arrays.toString(arr));
//        insertSort(arr);
//        System.out.println(Arrays.toString(arr));
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random()*80000);
        }
        long start = System.currentTimeMillis();
        insertSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void insertSort(int[] arr) {
        int temp = 0; //定义待插入的数
        int insert = 0; //定义插入位置
        for (int i = 1; i < arr.length; i++) {
            temp = arr[i];
            insert = i - 1; //定位于前一位置
            //保证到0位置停止，递增排序
            while (insert >= 0&&temp < arr[insert]) {
                arr[insert + 1] = arr[insert];
                insert--;
            }
            //循环结束，temp位置找到，为insert + 1
            if (insert + 1 != i)
                arr[insert + 1] = temp;
        }
    }
}
