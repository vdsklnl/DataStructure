package kmp;

/**
 * @author vdsklnl
 * @create 2022-06-14 10:36
 * @Description
 */

public class ViolenceMatch {
    public static void main(String[] args) {
        String str = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String sub = "尚硅谷你尚硅你1";
        System.out.println(violenceMatch(str, sub));
    }

    public static int violenceMatch(String str, String sub) {
        char[] strArr = str.toCharArray();
        char[] subArr = sub.toCharArray();

        int strlen = strArr.length;
        int sublen = subArr.length;

        int i = 0; //指向strArr
        int j = 0; //指向subArr

        while (i < strlen&&j < sublen) {
            if (strArr[i] == subArr[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1); //定位strArr下一字符
                j = 0;
            }
        }

        if (j == sublen)
            return i - j;
        else
            return -1;
    }
}
