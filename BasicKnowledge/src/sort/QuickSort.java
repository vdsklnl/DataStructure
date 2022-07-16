package sort;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-06-04 20:06
 * @Description
 */

public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-9, 78 ,0, 23, -567, 70, 100, -450, 54};
//        System.out.println(Arrays.toString(arr));
//        quickSort(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random()*8000000);
        }
        long start = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void quickSort(int[] arr, int left, int right) {
        //左右游标，初始值为left，right
        int l = left;
        int r = right;
        int temp = 0; //辅助交换
        int pivot = arr[(left + right) / 2]; //中间值，以pivot为标准
        //将比pivot小的值放左边，大的值放右边
        while (l < r) {
            //寻找左边比pivot大的值
            while (arr[l] < pivot)
                l++;
            //寻找右边比pivot小的值
            while (arr[r] > pivot)
                r--;
            //当l = r时，表示pivot两边已经排好
            if (l == r)
                break;
            //进行交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //arr[l]=pivot，表示交换完成，l已在中轴位置，下次比较可以直接比较r-1位置
            if (arr[l] == pivot)
                r--;
            //arr[r]=pivot，表示交换完成，r已在中轴位置，下次比较可以直接比较l+1位置
            if (arr[r] == pivot)
                l++;
        }
        //while结束，表示已经完成初轮排序，l = r在pivot位置，以此为根据，对pivot两边进行排序
        //首先将l，r值进行更新，否则栈溢出，分别为pivot前一和后一位置
        l--;
        r++;
        //左边
        if (left < l)
            quickSort(arr, left, l);
        //右边
        if (right > r)
            quickSort(arr, r, right);
    }
}
