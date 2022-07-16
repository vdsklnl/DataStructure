package recursive;

/**
 * @author vdsklnl
 * @create 2022-05-10 16:26
 * @Description
 */

public class SubSequence {

    public static int maxSum(int[] a, int left, int right) {

        // 基准情形
        if (left == right) {
            if (a[left] > 0) {
                return a[left];
            } else {
                return 0;
            }
        }

        // 递归情形
        int mid = (left + right) / 2;
        int maxLeftSum = maxSum(a, left, mid);
        int maxRightSum = maxSum(a, mid + 1,right);

        int maxLeftBorder = 0;
        int leftBorder = 0;
        for (int i = mid; i >= left ; i--) {
            leftBorder += a[i];
            if (leftBorder > maxLeftBorder)
                maxLeftBorder = leftBorder;
        }

        int maxRightBorder = 0;
        int rightBorder = 0;
        for (int i = mid + 1; i <= right ; i++) {
            rightBorder += a[i];
            if (rightBorder > maxRightBorder)
                maxRightBorder = rightBorder;
        }

        return max3(maxLeftSum, maxRightSum,maxLeftBorder + maxRightBorder);

    }

    private static int max3(int left, int right, int all) {
        int max = 0;
        if (left >= right)
            max = left;
        else
            max = right;
        if (max < all)
            max = all;
        return max;
    }

    // 分治策略(devide and conquer)
    public static int maxSubSum(int[] a) {
        return maxSum(a,0,a.length - 1);
    }

    public static void main(String[] args) {

        System.out.println(maxSubSum(new int[]{-2, 11, -4, 13, -5, -2}));
        System.out.println(maxOnLine(new int[]{-2, 11, -4, 13, -5, -2}));

    }

    // 联机算法
    public static int maxOnLine(int[] a) {

        int maxSum = 0;
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            if (sum > maxSum)
                maxSum = sum;
            else if (sum < 0)
                sum = 0;
        }

        return maxSum;
    }

}
