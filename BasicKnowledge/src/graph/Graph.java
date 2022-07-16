package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author vdsklnl
 * @create 2022-06-13 14:43
 * @Description
 */

public class Graph {

    private ArrayList<String> vertexList; //存储顶点集合
    private int[][] edges; //存储图对应邻接矩阵
    private int numOfEdges; //表示边的个数
    private boolean[] isVisited; //表示节点是否被访问过

    public Graph(int n) {
        numOfEdges = 0;
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        isVisited = new boolean[n];
    }

    //获取顶点数
    public int getVertexNums() {
        return vertexList.size();
    }

    //获取边数
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //获取顶点信息
    public String getVertex(int index) {
        return vertexList.get(index);
    }

    //插入顶点
    public void insert(String vertex) {
        vertexList.add(vertex);
    }

    //获取边权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //显示邻接矩阵
    public void showGraph() {
        for (int[] link:edges)
            System.out.println(Arrays.toString(link));
    }

    //插入边
    public void insertEdge(int v1, int v2, int weight) {
        //v1，v2为对应顶点，weight为权值
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    //返回节点第一个邻接节点下标
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0)
                return i;
        }
        return -1;
    }

    //返回节点下一个邻接节点下标
    public int getNextNeighbor(int index, int v) {
        for (int i = v + 1; i < vertexList.size(); i++) {
            if (edges[index][i] > 0)
                return i;
        }
        return -1;
    }

    //深度优先遍历计算
    public void dfs(boolean[] isVisited, int index) {
        //访问节点并输出，标记该节点已访问
        System.out.print(getVertex(index) + "->");
        //设置节点为已访问
        isVisited[index] = true;
        //查找index第一个邻接节点
        int w = getFirstNeighbor(index);
        while (w != -1) { //存在
            if (!isVisited[w]) //未被访问过
                dfs(isVisited, w);
            //已被访问过，查找下一邻接节点
            w = getNextNeighbor(index, w);
        }
    }

    //对dfs进行重载，遍历所有节点并进行DFS
    public void dfs() {
        //遍历所有节点并进行DFS(回溯)
        for (int i = 0; i < getVertexNums(); i++) {
            if (!isVisited[i])
                dfs(isVisited, i);
        }
    }

    //对节点进行广度优先遍历(BFS)
    public void bfs(boolean[] isVisited, int index) {
        int u; //队列头节点下标
        int w; //邻接节点下标
        //队列，记录访问顺序
        LinkedList<Integer> queue = new LinkedList<>();
        //访问节点，并输出
        System.out.print(getVertex(index) + "=>");
        //标记为已访问
        isVisited[index] = true;
        //将节点加入队列
        queue.addLast(index);
        while (!queue.isEmpty()) {
            //取出队列头节点下标
            u = queue.removeFirst();
            //获取邻接节点
            w = getFirstNeighbor(u);
            while (w != -1) { //找到
                //判断是否访问过
                if (!isVisited[w]) {
                    //访问节点，并输出
                    System.out.print(getVertex(w) + "=>");
                    //标记为已访问
                    isVisited[w] = true;
                    //将节点加入队列
                    queue.addLast(w);
                }
                //已访问过则判断下一节点
                w = getNextNeighbor(u, w);
            }
        }
    }

    //遍历所有节点(BFS)
    public void bfs() {
        for (int i = 0; i < getVertexNums(); i++) {
            if (!isVisited[i])
                bfs(isVisited, i);
        }
    }

    public static void main(String[] args) {
//        int n = 5;
//        String[] vertex = {"A", "B", "C", "D", "E"};
//        Graph graph = new Graph(5);
//        //创建顶点
//        for (String v: vertex) {
//            graph.insert(v);
//        }
//        //创建边：A-B-C B-C B-D
//        graph.insertEdge(0, 1, 1);
//        graph.insertEdge(0, 2, 1);
//        graph.insertEdge(1, 2, 1);
//        graph.insertEdge(1, 3, 1);
//        graph.insertEdge(1, 4, 1);

        int n = 8;
        Graph graph = new Graph(n);
        String[] vertex = {"1", "2", "3", "4", "5", "6", "7", "8"};
        //创建顶点
        for (String v: vertex) {
            graph.insert(v);
        }

        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(5, 6, 1);

        graph.showGraph();

        System.out.println("深度优先");
        graph.dfs();
//        System.out.println("广度优先");
//        graph.bfs();
    }

}
