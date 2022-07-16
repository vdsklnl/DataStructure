package kmp;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-06-14 11:11
 * @Description
 */

public class KMPAlgorithm {
    public static void main(String[] args) {
        String str = "BBC ABCDAB ABCDABCDABDE";
        String sub = "ABCDABD";
        int[] next = kmpNext(sub);
        System.out.println(Arrays.toString(next));
        System.out.println(KMP(str, sub, next));
    }

    //KMP算法
    public static int KMP(String str, String sub, int[] next) {
        for (int i = 0, j = 0; i < str.length(); i++) {
            //KMP算法核心，使用next更新
            while (j > 0&&str.charAt(i) != sub.charAt(j))
                j = next[j - 1];

            if (str.charAt(i) == sub.charAt(j))
                j++;
            if (j == sub.length())
                return i - j + 1; //i的增加要慢一步，所以加一
        }
        return -1;
    }

    //求出字串部分匹配值表
    public static int[] kmpNext(String sub) {
        int[] next = new int[sub.length()];
        next[0] = 0; //字符串长度为一，匹配0
        for (int i = 1, j = 0; i < next.length; i++) {
            //sub.charAt(i) != sub.charAt(j)，则需要一直寻找
            //直至满足sub.charAt(i) == sub.charAt(j)或者j = 0
            while (j > 0&&sub.charAt(i) != sub.charAt(j))
                j = next[j - 1];

            if (sub.charAt(i) == sub.charAt(j))
                //满足时，表示部分匹配值加一
                j++;

            next[i] = j;
        }
        return next;
    }
}
