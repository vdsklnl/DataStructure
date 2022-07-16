package dijkstra;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-06-16 12:25
 * @Description
 */

public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int N = 65535;
        int[][] matrix = {
                {N, 5, 7, N, N, N, 2},
                {5, N, N, 9, N, N, 3},
                {7, N, N, N, 8, N, N},
                {N, 9, N, N, N, 4, N},
                {N, N, 8, N, N, 5, 4},
                {N, N, N, 4, 5, N, 6},
                {2, 3, N, N, 4, 6, N}
        };

        Graph graph = new Graph(vertex, matrix);
        graph.dijkstra(2);
        graph.showDijkstra();
    }
}

class Graph {
    private char[] vertex; //顶点数组
    private int[][] matrix; //邻接矩阵
    private VisitedVertex vv;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    //显示邻接矩阵
    public void show() {
        for (int[] link:matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    //迪杰斯特拉算法
    public void dijkstra(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index); //更新节点对应距离和前驱节点
        for (int i = 0; i < vertex.length; i++) {
            index = vv.updateVertex();
            update(index);
        }
    }

    //显示最终结果
    public void showDijkstra() {
        vv.show();
    }

    //更新index下标节点到其它节点的距离和其它节点的前驱节点
    private void update(int index) {
        int len = 0;
        //访问index对应邻接矩阵
        for (int i = 0; i < matrix[index].length; i++) {
            //getDis(index)，初始点到index距离
            //matrix[index][i]，index到节点距离
            len = vv.getDis(index) + matrix[index][i];
            //当i未被访问过，且通过index访问时，距离更小，需要更新顶点到节点距离
            if (!vv.isVisited(i)&&len < vv.getDis(i)) {
                vv.updatePre(index, i);
                vv.updateDis(len, i);
            }
        }
    }
}

//已访问顶点集合
class VisitedVertex {
    //记录顶点是否已访问
    private int[] already;
    //记录每个顶点前驱节点下标
    private int[] previous;
    //记录出发顶点到所有顶点最短距离，动态更新
    private int[] distance;

    public VisitedVertex(int len, int index) {
        //len表示集合大小，index为起始顶点下标
        this.already = new int[len];
        this.previous = new int[len];
        this.distance = new int[len];
        //初始化dis，除自己外全置成最大
        Arrays.fill(distance, 65535);
        distance[index] = 0;
        //设置出发顶点被访问过
        this.already[index] = 1;
    }

    //返回节点是否被访问过
    public boolean isVisited(int index) {
        return already[index] == 1;
    }

    //更新distance数组
    public void updateDis(int len, int index) {
        distance[index] = len;
    }

    //更新前驱节点数组，将index节点前驱节点更新为pre
    public void updatePre(int pre, int index) { previous[index] = pre; }

    //返回出发顶点到index节点的距离
    public int getDis(int index) {
        return distance[index];
    }

    //继续选择并返回新的访问节点(不是初始顶点)
    public int updateVertex() {
        int index = 0;
        int min = 65535;
        for (int i = 0; i < already.length; i++) {
            if (already[i] == 0&&distance[i] < min) {
                min = distance[i];
                index = i;
            }
        }
        //将index标记为已访问
        already[index] = 1;
        return index;
    }

    //显示结果
    public void show() {
        for (int i:already) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i:previous) {
            System.out.print(i + " ");
        }
        System.out.println();
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        for (int i = 0; i < vertex.length; i++) {
            if (distance[i] != 65535)
                System.out.print(vertex[i] + "(" + distance[i] + ")");
            else
                System.out.print(vertex[i] + "(N)");
        }
    }
}