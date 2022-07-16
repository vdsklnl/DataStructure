package recursive;

/**
 * @author vdsklnl
 * @create 2022-06-02 20:54
 * @Description
 */

public class Queen8 {

    public int size = 8; //八皇后问题
    public int[] arr = new int[size]; //记录位置，由二维数组修改为一维数组
    public static int count = 0;
    public static int judgeCount = 0;

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("共有%d种解法\n", count);
        System.out.printf("共判断%d次\n", judgeCount);
    }

    //放置第n个皇后
    private void check(int n) {
        if (n == size) { //表示已经放置完成
            show();
            return;
        }

        for (int i = 0; i < size; i++) {
            //先将第n个皇后排在第一列
            arr[n] = i;
            if (judge(n)) { //不冲突，则放入第n+1个皇后
                check(n + 1);
            }
            //若均冲突，则将皇后n摆在下一列，继续遍历
        }
    }

    //检查第n个皇后是否冲突
    private boolean judge(int n) {
        judgeCount++; //每判断一次自增
        for (int i = 0; i < n; i++) {
            //按照行来遍历，仅需考虑是否处于同一列或者斜线(形成“田”字形)
            if (arr[i] == arr[n]||Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    //输出摆放位置
    private void show() {
        count++; //每输出一次，表示一种解法
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
