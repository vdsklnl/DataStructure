package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-06-06 20:21
 * @Description
 */

public class InsertSearch {
    //对有序数组
    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 4, 5, 6};
//        System.out.println(binarySearch(arr, 0, arr.length - 1, 4));
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++)
            arr[i] = i;

        int search = insertSearch(arr, 0, arr.length - 1, 78);
        System.out.println(search);
    }

    public static int insertSearch(int[] arr, int left, int right, int value) {
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        //不加后面可能越界
        //插值查找在数据量较大，分布比较均匀的情况效率更高，若不均匀，不一定比二分查找好
        if (left > right||arr[left] > value||arr[right] < value)
            return -1;
        if (arr[mid] < value)
            return insertSearch(arr, mid + 1, right, value);
        else if (arr[mid] > value)
            return insertSearch(arr, left, mid - 1, value);
        else
            return mid;
    }

}
