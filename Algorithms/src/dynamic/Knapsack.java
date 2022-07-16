package dynamic;

/**
 * @author vdsklnl
 * @create 2022-06-13 21:01
 * @Description 0-1背包装货问题，动态规划
 */

public class Knapsack {
    public static void main(String[] args) {
        int[] w = {1, 4, 3}; //物品重量
        int[] v = {1500, 3000, 2000}; //物品价值
        int m = 4; //背包容量
        int n = v.length; //物品个数

        //创建二维数组，val[i][j]表示前i个物品放入容量为j的背包最大价值
        int[][] val = new int[n + 1][m + 1];
        //记录相应存放情况
        int[][] path = new int[n + 1][m + 1];

        //第一行第一列默认为0，表示第0件物品和0容量背包
        for (int i = 0; i < val.length; i++)
            val[i][0] = 0;
        for (int i = 0; i < val[0].length; i++)
            val[0][i] = 0;

        //动态规划求解
        for (int i = 1; i < val.length; i++) {
            for (int j = 1; j < val[0].length; j++) {
                //不处理一行一列
                if (w[i - 1] > j) //下标减一
                    val[i][j] = val[i - 1][j];
                else {
                    //不能简单计算，要具体情况分析
//                    val[i][j] = Math.max(val[i - 1][j], v[i - 1] + val[i - 1][j - w[i - 1]]);
                    if (val[i - 1][j] < v[i - 1] + val[i - 1][j - w[i - 1]]) {
                        val[i][j] = v[i - 1] + val[i - 1][j - w[i - 1]];
                        //这种情况下表示i放入j中
                        path[i][j] = 1;
                    } else
                        val[i][j] = val[i - 1][j];

                }
            }
        }

        //输出val
        for (int i = 0; i < val.length; i++) {
            for (int j = 0; j < val[0].length; j++) {
                System.out.print(val[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("物品存放情况");
        //输出存放情况
        //正向输出会输出所有存放情况，需逆序
        int i = val.length - 1; //行最值
        int j = val[0].length - 1; //列最值
        while (i > 0&&j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个物品放入背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }

    }
}
