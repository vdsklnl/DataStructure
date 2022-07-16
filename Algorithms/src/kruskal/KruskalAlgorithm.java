package kruskal;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-06-15 15:42
 * @Description
 */

public class KruskalAlgorithm {
    private int edgeNum;
    private char[] vertex;
    private int[][] weight;
    //定义INF，表示两个顶点不连通
    private static final int INF = Integer.MAX_VALUE;

    //初始化
    public KruskalAlgorithm(char[] vertex, int[][] weight) {
        int len = vertex.length;
        edgeNum = 0;
        this.vertex = new char[len];
        this.weight = new int[len][len];
        //复制，不影响原数据
        for (int i = 0; i < len; i++) {
            this.vertex[i] = vertex[i];
            for (int j = 0; j < len; j++) {
                this.weight[i][j] = weight[i][j];
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (weight[i][j] < INF)
                    edgeNum++;
            }
        }
    }

    //显示邻接矩阵
    public void show() {
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%12d", weight[i][j]);
            }
            System.out.println();
        }
    }

    //冒泡排序
    public void sort(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].getWeight() > edges[j + 1].getWeight()) {
                    EData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    //返回顶点对应下标
    public int getPosition(char ch) {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == ch)
                return i;
        }
        return -1;
    }

    //获取边构成数组
    public EData[] getEdges() {
        int index = 0;
        EData[] eData = new EData[edgeNum];
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (weight[i][j] != INF)
                    eData[index++] = new EData(vertex[i], vertex[j], weight[i][j]);
            }
        }
        return eData;
    }

    //获取顶点的终点(避免生成回环)
    public int getEnd(int[] ends, int v) {
        //ends数组是动态形成的，不断更新
        //未加入时，ends[v] = 0，认为顶点为其本身
        while (ends[v] != 0)
            v = ends[v];
        //此时v对应原顶点终点下标
        return v;
    }

    public void kruskal() {
        int index = 0; //结果数组索引
        int[] ends = new int[edgeNum]; //存放已有最小生成树各顶点终点
        EData[] res = new EData[edgeNum]; //存放已生成最小生成树

        //获取边数组并排序(从小到大)
        EData[] edges = getEdges();
        sort(edges);

        int p1 = -1; //记录边的顶点
        int p2 = -1; //记录边的顶点
        int e1 = -1; //记录顶点终点
        int e2 = -1; //记录顶点终点
        //遍历edges数组，判断是否形成回路，未形成则加入边到结果集
        for (int i = 0; i < edgeNum; i++) {
            p1 = getPosition(edges[i].getStart());
            p2 = getPosition(edges[i].getEnd());

            e1 = getEnd(ends, p1);
            e2 = getEnd(ends, p2);

            //判断是否构成回路
            if (e1 != e2) {
                ends[e1] = e2;
                res[index++] = edges[i]; //加入边至结果集
            }
        }

        //输出结果
        for (int i = 0; i < index; i++)
            System.out.println(res[i]);
    }

    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] weight = {
                {  0,  12, INF, INF, INF,  16,  14},
                { 12,   0,  10, INF, INF,   7, INF},
                {INF,  10,   0,   3,   5,   6, INF},
                {INF, INF,   3,   0,   4, INF, INF},
                {INF, INF,   5,   4,   0,   2,   8},
                { 16,   7,   6, INF,   2,   0,   9},
                { 14, INF, INF, INF,   8,   9,   0}
        };
        //Prim算法案例
//        int[][] weight = {
//                {  0,   5,   7, INF, INF, INF,   2},
//                {  5,   0, INF,   9, INF, INF,   3},
//                {  7, INF,   0, INF,   8, INF, INF},
//                {INF,   9, INF,   0, INF,   4, INF},
//                {INF, INF,   8, INF,   0,   5,   4},
//                {INF, INF, INF,   4,   5,   0,   6},
//                {  2,   3, INF, INF,   4,   6,   0}
//        };
        KruskalAlgorithm krus = new KruskalAlgorithm(data, weight);
        krus.kruskal();
    }
}

//创建类EData，对象实例表示一条边
class EData {
    private char start; //顶点
    private char end; //顶点
    private int weight; //权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public char getStart() {
        return start;
    }

    public char getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "<" + start +
                "," + end +
                "> = " + weight +
                '}';
    }
}
