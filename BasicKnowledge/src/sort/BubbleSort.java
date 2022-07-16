package sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-06-03 14:35
 * @Description
 */

public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = new int[]{3, 9, -1, 10, 20};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random()*80000);
        }
//        System.out.println(Arrays.toString(arr));
        long start = System.currentTimeMillis();
        bubbleSort(arr);
        long end = System.currentTimeMillis();
//        System.out.println(Arrays.toString(arr));
        System.out.println(end - start);
    }

    public static void bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag; //判断一趟中是否有交换，无则表示排序完成
        //比较趟数为n-1
        for (int i = 0; i < arr.length - 1; i++) {
            flag = true;
            //每趟比较次数为n-1-趟次
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    flag = false;
                }
            }
            if (flag)
                break;
        }
    }
}
