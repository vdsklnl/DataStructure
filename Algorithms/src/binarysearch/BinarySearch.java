package binarysearch;

/**
 * @author vdsklnl
 * @create 2022-06-13 17:23
 * @Description 此为非递归版本
 */

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 60, 97, 100};
        System.out.println(binarySearch(arr, 1000));
    }

    //二分查找非递归版
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target)
                return mid;
            else if (arr[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return -1;
    }
}
