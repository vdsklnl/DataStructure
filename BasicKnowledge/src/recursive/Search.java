package recursive;

/**
 * @author vdsklnl
 * @create 2022-05-10 17:06
 * @Description
 */

public class Search {

    public static void main(String[] args) {

        System.out.println(binarySearch(new int[]{1, 2, 3, 11, 13, 20},-2));

    }

    // return -1表示未找到
    public static int binarySearch(int[] a, int x) {

        int low = 0;
        int high = a.length - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (a[mid] < x) {
                low = mid + 1;
            } else if (a[mid] > x) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

}
