package sort;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-06-04 20:51
 * @Description
 */

public class MergeSort {
    public static void main(String[] args) {
//        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
//        int[] temp = new int[arr.length];
//        System.out.println(Arrays.toString(arr));
//        mergeSort(arr, 0, arr.length - 1, temp);
//        System.out.println(Arrays.toString(arr));
        int[] arr = new int[8000000];
        int[] temp = new int[arr.length];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random()*8000000);
        }
        long start = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length - 1, temp);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    //分+合
    /*
     * 对8个数数组，合并顺序为：
     * 0，1 —> 2，3 —> 0，3 —> 4，5 —> 6，7 —> 4，7 —> 0，7
     */
    public static void mergeSort(int[] arr, int left, int right ,int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //左右分解
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            //分解完成后，进行合并
            //先将左边合并完成，再合并右边的，最后一齐合并成最终数组
            merge(arr, left, mid, right, temp);
        }
    }

    //合并的方法
    public static void merge(int[] arr, int left, int mid, int right ,int[] temp) {
        int l = left; //左边序列初始索引
        int m = mid + 1; //右边序列初始索引
        int t = 0; //temp数组初始索引

        //先把左右两边序列依次放入temp，直至一边放完
        while (l <= mid&&m <= right) {
            if (arr[l] < arr[m]) {
                temp[t] = arr[l];
                l++;
                t++;
            } else {
                temp[t] = arr[m];
                m++;
                t++;
            }
        }

        //将剩余数组加进temp
        while (l <= mid) {
            //左边序列剩余
            temp[t] = arr[l];
            l++;
            t++;
        }
        while (m <= right) {
            //右边序列剩余
            temp[t] = arr[m];
            m++;
            t++;
        }

        //拷贝temp数据到arr，每次只拷贝对应部分
        t = 0;
        int templeft = left;
        while (templeft <= right) {
            arr[templeft] = temp[t];
            t++;
            templeft++;
        }
    }
}
