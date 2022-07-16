package tree;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-06-08 15:48
 * @Description
 */

public class HeapSort {
    public static void main(String[] args) {
//        int[] arr = {4, 6, 8, 5, 9, -1, 77, -123};
//        heapSort(arr);
//        System.out.println(Arrays.toString(arr));
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random()*8000000);
        }
        long start = System.currentTimeMillis();
        heapSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    //升序->构建大顶堆 降序->构建小顶堆
    public static void heapSort(int[] arr) {
        int temp = 0; //辅助交换

        //第一次调整，要将整个数组进行整理，从最大非叶子节点开始
        //将小值往下整理，大值往上整理
        //整理完成后，后面每次只需从起始节点0开始
        for (int i = arr.length / 2 - 1; i >= 0; i--)
            adjustHeap(arr, i, arr.length);
//        adjustHeap(arr, 0, arr.length);

        //共调整arr.length - 1次
        for (int i = arr.length - 1; i > 0; i--) {
            temp = arr[i]; //最末尾值
            arr[i] = arr[0]; //最大值
            arr[0] = temp;
            //交换完成，循环调整
            adjustHeap(arr, 0, i);
        }

    }

    //调整数组(二叉树)成大顶堆
    /*
     * i为非叶子节点对应数组编号，len表示数组长度
     */
    public static void adjustHeap(int[] arr, int i, int len) {
        int temp = arr[i];
        for (int j = 2 * i + 1; j < len; j = 2 * j + 1) {
            //比较左右子节点大小，加前半句提高效率
            if ((j + 1) < len&&arr[j] < arr[j + 1])
                j++;
            //此时arr[j]是较大值，直接比较非叶子节点
            if (arr[j] > temp) {
                arr[i] = arr[j];
                i = j;
            } else
                break;
        }
        //调整完后，i指向被交换节点，将temp赋值给i
        arr[i] = temp;
    }
}
