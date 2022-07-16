package recursive;

/**
 * @author vdsklnl
 * @create 2022-05-10 17:34
 * @Description
 */

public class Others {

    // 欧几里得算法求最大公约数
    public static long maxComDiv(long m, long n) {
        long rem;
        while (n != 0) {
            rem = m % n;
            m = n;
            n = rem;
        }
        return m;
    }

    // 快速幂运算
    public static long pow(long x, int n) {
        if (n == 0)
            return 1;
        if (n == 1)
            return x;
        if (n % 2 == 0)
            return pow(x*x,n / 2);
        else
            return pow(x*x, n / 2) * x;
    }

    public static void main(String[] args) {
        System.out.println(maxComDiv(500,200));
        System.out.println(pow(500,3));
        System.out.println(500 * 500* 500);
    }

}
