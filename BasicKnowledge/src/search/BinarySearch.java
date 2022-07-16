package search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-06-06 18:30
 * @Description
 */

public class BinarySearch {
    //对有序数组
    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 4, 5, 6};
//        System.out.println(binarySearch(arr, 0, arr.length - 1, 4));
        int[] arr = {1, 2, 3, 4, 4, 4, 4, 4, 5, 6, 7, 8, 9};
        List<Integer> list = binarySearchList(arr, 0, arr.length - 1, 4);
        System.out.println(list);
    }

    public static int binarySearch(int[] arr, int left, int right, int value) {
        int mid = (left + right) / 2;
        if (left > right||arr[left] > value||arr[right] < value)
            return -1;
        if (arr[mid] < value)
            return binarySearch(arr, mid + 1, right, value);
        else if (arr[mid] > value)
            return binarySearch(arr, left, mid - 1, value);
        else
            return mid;
    }

    public static List<Integer> binarySearchList(int[] arr, int left, int right, int value) {
        int mid = (left + right) / 2;
        ArrayList<Integer> list = new ArrayList<>();
        if (left > right||arr[left] > value||arr[right] < value)
            return new ArrayList<>();
        if (arr[mid] < value)
            return binarySearchList(arr, mid + 1, right, value);
        else if (arr[mid] > value)
            return binarySearchList(arr, left, mid - 1, value);
        else {
            int l = mid - 1;
            while (true) {
                if (l < 0||arr[l] != value)
                    break;
                list.add(l);
                l--;
            }
            list.add(mid);
            int r = mid + 1;
            while (true) {
                if (r > right||arr[r] != value)
                    break;
                list.add(r);
                r++;
            }
            return list;
        }
    }
}
