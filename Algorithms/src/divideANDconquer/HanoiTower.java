package divideANDconquer;

/**
 * @author vdsklnl
 * @create 2022-06-13 19:52
 * @Description
 */

public class HanoiTower {
    public static void main(String[] args) {
        honoi(5, 'A', 'B', 'C');
    }

    //分治算法求解
    public static void honoi(int num, char a, char b, char c) {
        if (num == 1)
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
        else {
            //将汉诺塔始终视为只有上下两层结构，进行递归处理
            //先将上层移动到b，途中经过c
            honoi(num - 1, a, c, b);
            //将最后一个盘从a移动到c
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            //再将所有盘从b移动到c，图中经过a
            honoi(num - 1, b, a, c);
        }
    }
}
