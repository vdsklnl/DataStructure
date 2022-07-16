package floyd;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-06-16 16:56
 * @Description
 */

public class FloydAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int N = 65535;
        int[][] matrix = {
                {0, 5, 7, N, N, N, 2},
                {5, 0, N, 9, N, N, 3},
                {7, N, 0, N, 8, N, N},
                {N, 9, N, 0, N, 4, N},
                {N, N, 8, N, 0, 5, 4},
                {N, N, N, 4, 5, 0, 6},
                {2, 3, N, N, 4, 6, 0}
        };
        Graph graph = new Graph(vertex, matrix);
        graph.floyd();
        graph.show();
    }
}

class Graph {
    private char[] vertex; //记录顶点
    private int[][] distance; //保存各节点之间的距离(最后结果)，动态更新
    private int[][] previous; //保存到达目标顶点的前驱节点，动态更新

    public Graph(char[] vertex, int[][] distance) {
        this.vertex = vertex;
        this.distance = distance;
        this.previous = new int[vertex.length][vertex.length];
        //将previous矩阵初始化为本节点
        for (int i = 0; i < vertex.length; i++) {
            Arrays.fill(previous[i], i);
        }
    }

    //显示结果
    public void show() {
        for (int i = 0; i < vertex.length; i++) {
            System.out.println(vertex[i] + " 到各节点的前驱节点：");
            for (int j = 0; j < vertex.length; j++) {
                System.out.print(vertex[previous[i][j]] + " ");
            }
            System.out.println();
            for (int j = 0; j < vertex.length; j++) {
                System.out.println(vertex[i] + " 到 " + vertex[j] + " 的最短距离为：" + distance[i][j]);
            }
            System.out.println();
        }
    }

    //Floyd算法
    public void floyd() {
        int len; //保存距离
        //从中间顶点开始，i表示下标
        for (int i = 0; i < vertex.length; i++) {
            //起始节点j
            for (int j = 0; j < vertex.length; j++) {
                //终点k
                for (int k = 0; k < vertex.length; k++) {
                    //起始j -> 中间i -> 终点k = len
                    len = distance[j][i] + distance[i][k];
                    if (len < distance[j][k]) {
                        //直达不如中转，更新距离距离和前驱节点
                        distance[j][k] = len;
                        previous[j][k] = previous[i][k];
                    }
                }
            }
        }
    }
}
