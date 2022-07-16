package prim;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-06-15 13:13
 * @Description
 */

public class PrimAlgorithm {
    public static void main(String[] args) {
        int vertex = 7;
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] weight = {
                {1000, 5, 7, 1000, 1000, 1000, 2},
                {5, 1000, 1000, 9, 1000, 1000, 3},
                {7, 1000, 1000, 1000, 8, 1000, 1000},
                {1000, 9, 1000, 1000, 1000, 4, 1000},
                {1000, 1000, 8, 1000, 1000, 5, 4},
                {1000, 1000, 1000, 4, 5, 1000, 6},
                {2, 3, 1000, 1000, 4, 6, 1000}
        };

        MGraph mGraph = new MGraph(vertex);
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, vertex, data, weight);

        minTree.show(mGraph);
        minTree.prim(mGraph, 0);
    }
}

class MinTree{
    //根据数据，创建图
    public void createGraph(MGraph graph, int vertex, char[] data, int[][] weight) {
        for (int i = 0; i < vertex; i++) {
            graph.getData()[i] = data[i];
            for (int j = 0; j < vertex; j++) {
                graph.getWeight()[i][j] = weight[i][j];
            }
        }
    }

    //显示邻接矩阵
    public void show(MGraph graph) {
        for (int[] link:graph.getWeight()) {
            System.out.println(Arrays.toString(link));
        }
    }

    //prim，生成最小生产树
    public void prim(MGraph graph, int v) {
        //标记顶点是否被访问过
        int[] visited = new int[graph.getVertex()];
        visited[v] = 1;
        int h1 = -1; //记录已访问节点下标
        int h2 = -1; //记录未访问节点下标
        int minWeight = 1000;
        int road = 0;
        for (int k = 1; k < visited.length; k++) {
            //n顶点生成n-1边
            for (int i = 0; i < graph.getVertex(); i++) {
                //i表示已被访问过的节点
                for (int j = 0; j < graph.getVertex(); j++) {
                    //j表示未被访问过的节点
                    if (visited[i] == 1&&visited[j] == 0&&graph.getWeight()[i][j] < minWeight) {
                        h1 = i;
                        h2 = j;
                        minWeight = graph.getWeight()[i][j];
                    }
                }
            }
            //循环结束，找到最小边
            System.out.println("边<" + graph.getData()[h1] + "," + graph.getData()[h2] + "> 权值：" + minWeight);
            road += minWeight;
            //标记h2，重置最小权值
            visited[h2] = 1;
            minWeight = 1000;
        }
        System.out.println("总路程为：" + road);
    }
}

class MGraph {
    private int vertex; //顶点数
    private char[] data; //存放顶点数据
    private int[][] weight; //邻接矩阵

    public MGraph(int vertex) {
        this.vertex = vertex;
        data = new char[vertex];
        weight = new int[vertex][vertex];
    }

    public int getVertex() {
        return vertex;
    }

    public char[] getData() {
        return data;
    }


    public int[][] getWeight() {
        return weight;
    }
}