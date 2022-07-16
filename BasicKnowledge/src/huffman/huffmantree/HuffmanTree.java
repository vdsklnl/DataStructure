package huffman.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-06-08 21:20
 * @Description
 */

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuff(arr);
        preOrder(root);
    }

    //创建赫夫曼树，返回根节点
    public static Node createHuff(int[] arr) {
        //先遍历数组放到集合中，生成Node方便操作
        List<Node> list = new ArrayList<>();
        for (int value:arr) {
            list.add(new Node(value));
        }

        //辅助节点三个
        Node left = null;
        Node right = null;
        Node parent = null;
        //循环直至集合中只剩一个节点
        while (list.size() > 1) {
            //排序，Collections工具类
            Collections.sort(list);
            //取出左右子节点
            left = list.get(0);
            right = list.get(1);
            //构建新赫夫曼树
            parent = new Node(left.value + right.value);
            parent.setLeft(left);
            parent.setRight(right);
            //移除左右子节点并将新节点放入集合
            list.remove(left);
            list.remove(right);
            list.add(parent);
        }
        return list.get(0);
    }

    //前序遍历
    public static void preOrder(Node node) {
        if (node != null)
            node.preOrder();
        else
            System.out.println("此二叉树为空树！");
    }
}

//创建节点并实现比较功能
class Node implements Comparable<Node> {

    public int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //升序排列，降序则加负号
        return this.value - o.value;
    }

    //前序遍历查看效果
    public void preOrder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }
}
