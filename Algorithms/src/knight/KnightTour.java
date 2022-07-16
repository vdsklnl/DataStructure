package knight;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author vdsklnl
 * @create 2022-06-16 20:06
 * @Description 使用贪心算法优化，每次走下一步的下一步更少的一步，减少回溯
 *              需要对下一步的下一步集合个数进行非递减排序(可以序号相同)
 */

public class KnightTour {
    private static int X; //棋盘列数
    private static int Y; //棋盘行数
    //数组记录被访问过位置
    private static boolean[] visited;
    private static boolean finished; //标记是否完成全部位置访问

    //根据当前位置，计算能到达下个地点(最多8个)
    public static ArrayList<Point> next(Point curPoint) {
        //Point为java类，表示一个点
        ArrayList<Point> points = new ArrayList<>();
        Point p1 = new Point();
        //判断8个点位
        if ((p1.x = curPoint.x - 2) >= 0&&(p1.y = curPoint.y - 1) >= 0)
            points.add(new Point(p1));
        if ((p1.x = curPoint.x - 2) >= 0&&(p1.y = curPoint.y + 1) < Y)
            points.add(new Point(p1));
        if ((p1.x = curPoint.x - 1) >= 0&&(p1.y = curPoint.y - 2) >= 0)
            points.add(new Point(p1));
        if ((p1.x = curPoint.x - 1) >= 0&&(p1.y = curPoint.y + 2) < Y)
            points.add(new Point(p1));
        if ((p1.x = curPoint.x + 1) < X&&(p1.y = curPoint.y - 2) >= 0)
            points.add(new Point(p1));
        if ((p1.x = curPoint.x + 1) < X&&(p1.y = curPoint.y + 2) < Y)
            points.add(new Point(p1));
        if ((p1.x = curPoint.x + 2) < X&&(p1.y = curPoint.y - 1) >= 0)
            points.add(new Point(p1));
        if ((p1.x = curPoint.x + 2) < X&&(p1.y = curPoint.y + 1) < Y)
            points.add(new Point(p1));

        return points;
    }

    //对points集合进行非递减排序，减少回溯
    public static void sort(ArrayList<Point> points) {
        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int size1 = next(o1).size();
                int size2 = next(o2).size();
                if (size1 > size2)
                    return 1;
                else if (size1 == size2)
                    return 0;
                else
                    return -1;
            }
        });
    }

    //骑士周游算法
    public static void tour(int[][] chess, int row, int column, int step) {
        //将位置标记为步数，表示已走过
        chess[row][column] = step;
        visited[row * X + column] = true;
        //获取当前节点下一步集合
        ArrayList<Point> points = next(new Point(column, row));
        //对集合进行排序，减少回溯
        sort(points);
        //对每个点进行判断
        while (!points.isEmpty()) {
            Point point = points.remove(0);
            //未被访问，则继续走下一步
            if (!visited[point.y * X + point.x])
                tour(chess, point.y, point.x, step + 1);
        }
        //判断是否完成任务，是否走完或者处于回溯阶段
        //若走出上述循环，且符合上述条件，将对应点置为未访问，回溯，否则表示成功
        if (step < X * Y&&!finished) {
            chess[row][column] = 0;
            visited[row * X + column] = false;
        } else
            finished = true;
    }

    public static void main(String[] args) {
        //算法测试
        X = 8;
        Y = 8;
        int row = 1;
        int column = 1;
        int[][] chess = new int[Y][X];
        visited = new boolean[X * Y];
        long start = System.currentTimeMillis();
        tour(chess, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("共用时：" + (end - start));
        for (int[] line: chess) {
            System.out.println(Arrays.toString(line));
        }
    }
}
