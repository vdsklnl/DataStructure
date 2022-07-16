package search;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-06-06 20:38
 * @Description
 */

public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println(fibonacci(arr, 16));
//        System.out.println(Arrays.toString(fib()));
    }

    public static int maxSize = 20;

    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    public static int fibonacci(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0; //存放斐波那契数下标
        int mid = 0;
        int[] fib = fib();
        //找到与数组元素匹配的斐波那契数
        while (fib[k] < high + 1)
            k++;
        //需要扩充数列到能使用黄金分割数(斐波那契)
        int[] newArr = Arrays.copyOf(arr, fib[k]);
        //将补充的数全由最大数覆盖
        for (int i = high + 1; i < newArr.length; i++)
            newArr[i] = arr[high];
        while (low <= high) {
            mid = low + fib[k - 1] - 1;
            //在左边寻找，左边为fib[k - 1]
            if (newArr[mid] > value) {
                high = mid - 1;
                k -= 1;
            } else if (newArr[mid] < value) {
                low = mid + 1;
                k -= 2;
            } else {
                //返回mid和high中较小值
                if (mid <= high)
                    return mid;
                else
                    return high;
            }
        }
        return -1;
    }
}
