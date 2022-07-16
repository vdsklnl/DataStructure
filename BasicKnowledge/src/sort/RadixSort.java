package sort;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-06-04 22:12
 * @Description
 */

public class RadixSort {
    public static void main(String[] args) {
//        int[] arr = {53, 3, 542, 748, 14, 214};
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random()*8000000);
        }
//        System.out.println(Arrays.toString(arr));
//        radix(arr);
//        System.out.println(Arrays.toString(arr));
        long start = System.currentTimeMillis();
        radix(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void radix(int[] arr) {

        //定义十个桶，用来对每位数进行比较，用二维数组进行模拟
        //空间换时间，二维数组行数为10，列数为arr.length
        int[][] bucket = new int[10][arr.length];
        //记录每个桶存放数据个数，使用一维数组
        int[] bucketEleCounts = new int[10];
        //先找到数组中最大的数，并定义循环次数

        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //获取最大数位数
        int digit = (max + "").length();
        int digitElement = 0;
        int index = 0;
        for (int i = 0, n = 1; i < digit; i++, n *= 10) {
            //每一轮筛选分桶
            for (int j = 0; j < arr.length; j++) {
                digitElement = arr[j] / n % 10;
                //digitElement记录桶号，bucketEleCounts[digitElement]则为对应个数
                bucket[digitElement][bucketEleCounts[digitElement]] = arr[j];
                //相应桶号个数增加
                bucketEleCounts[digitElement]++;
            }
            //重新放入arr顺序
            index = 0;
            for (int j = 0; j < bucketEleCounts.length; j++) {
                //桶中有数据才放入
                if (bucketEleCounts[j] != 0) {
                    for (int k = 0; k < bucketEleCounts[j]; k++)
                        arr[index++] = bucket[j][k];
                }
                //结束后，将对应个数置为0
                bucketEleCounts[j] = 0;
            }
        }

    }
}
