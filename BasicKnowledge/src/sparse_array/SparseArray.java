package sparse_array;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author vdsklnl
 * @create 2022-05-28 13:54
 * @Description
 */

public class SparseArray {

    public static void main(String[] args) {
        //创建原始二维数组 11*11
        //0 表示没有棋子，1 表示黑子，2 表示篮子
        int[] chessArr1[] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        //输出原始二维数组
        for (int[] row:chessArr1) {
            for (int data:row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        System.out.println();
        //将二维数组转化为稀疏数组
        int sum = 0;
        //遍历二维数组找到非0数据
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1.length; j++) {
                if (chessArr1[i][j] != 0)
                    sum++;
            }
        }

        //创建相应的稀疏数组
        int sparseArr1[][] = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr1[0][0] = chessArr1.length;
        sparseArr1[0][1] = chessArr1.length;
        sparseArr1[0][2] = sum;

        //遍历二维数组，将非0值存放到sparseArr1中
        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1.length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr1[count][0] = i;
                    sparseArr1[count][1] = j;
                    sparseArr1[count][2] = chessArr1[i][j];
                }
            }
        }

        //将稀疏数组存储到文件中
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("BasicKnowledge\\src\\sparse_array\\sparse.txt"));
            for (int i = 0; i < sparseArr1.length; i++) {
                for (int j = 0; j < 3; j++) {
                    bw.write(String.valueOf(sparseArr1[i][j]) + '\t');
                }
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //读取文件生成稀疏数组
        BufferedReader br = null;
        int[][] sparseArr2 = new int[0][];
        try {
            br = new BufferedReader(new FileReader("BasicKnowledge\\src\\sparse_array\\sparse.txt"));
            String line = null;
            long lines = Files.lines(Paths.get("BasicKnowledge\\src\\sparse_array\\sparse.txt")).count();
            sparseArr2 = new int[(int) lines][3];
            for (int i = 0; i < lines; i++) {
                line = br.readLine();
                String[] row = line.split("\t");
                sparseArr2[i][0] = Integer.parseInt(row[0]);
                sparseArr2[i][1] = Integer.parseInt(row[1]);
                sparseArr2[i][2] = Integer.parseInt(row[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //输出原本稀疏数组形式
        for (int i = 0; i < sparseArr1.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr1[i][0], sparseArr1[i][1], sparseArr1[i][2]);
        }
        System.out.println();
        //输出读取稀疏数组形式
        for (int i = 0; i < sparseArr2.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr2[i][0], sparseArr2[i][1], sparseArr2[i][2]);
        }
        System.out.println();

        //将稀疏数组重构为二维数组
        int[][] chessArr2 = new int[sparseArr2[0][0]][sparseArr2[0][1]];
        for (int i = 1; i < sparseArr2.length; i++) {
            chessArr2[sparseArr2[i][0]][sparseArr2[i][1]] = sparseArr2[i][2];
        }

        //输出重构二维数组
        for (int[] row:chessArr2) {
            for (int data:row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

}
