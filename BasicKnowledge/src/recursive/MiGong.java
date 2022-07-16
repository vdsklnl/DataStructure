package recursive;

/**
 * @author vdsklnl
 * @create 2022-06-02 14:20
 * @Description
 */

public class MiGong {

    public static void main(String[] args) {
        //创建地图
        int[][] map = new int[8][7];
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = 1;
            map[map.length-1][i] = 1;
        }
        for (int i = 0; i < map.length; i++) {
            map[i][0] = 1;
            map[i][map[0].length-1] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
//        map[1][2] = 1;
//        map[2][2] = 1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        findWay(map, 1, 1);
        System.out.println("==============");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    //使用递归寻路
    /*
     * map表示地图，map[i][j]为起始位置，到map[6][5]表示找到通路
     * 1表示墙，0表示未走过，2表示已走过，3表示走不通
     * 约定寻路顺序：下->右->上->左
     */
    public static boolean findWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) //找到路径
            return true;
        else if (map[i][j] == 0) {
            //假定该点可以走通
            map[i][j] = 2;
            //下->右->上->左
            if (findWay(map, i + 1, j))
                return true;
            else if (findWay(map, i, j + 1))
                return true;
            else if (findWay(map, i - 1, j))
                return true;
            else if (findWay(map, i, j - 1))
                return true;
            else {
                //此时此点走不通
                map[i][j] = 3;
                return false;
            }
        } else { //map[i][j] = 1 2 3
            return false;
        }
    }

}
