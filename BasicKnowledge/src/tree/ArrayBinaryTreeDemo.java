package tree;

/**
 * @author vdsklnl
 * @create 2022-06-07 20:48
 * @Description
 */

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree tree = new ArrayBinaryTree(arr);
        //数组起始位置为0
//        tree.preOrder(); //1 2 4 5 3 6 7
//        tree.infixOrder(); //4 2 5 1 6 3 7
        tree.postOrder(); //4 5 2 6 7 3 1
    }
}

class ArrayBinaryTree {
    private int[] arr; //存储数据节点的数组

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        this.preOrder(0);
    }

    //二叉树前序遍历数组
    public void preOrder(int index) {
        if (arr == null||arr.length == 0)
            System.out.println("数组为空，不能遍历！");
        System.out.println(arr[index]); //输出本节点
        //左遍历
        if ((2 * index + 1) < arr.length)
            preOrder(2 * index + 1);
        //右遍历
        if ((2 * index + 2) < arr.length)
            preOrder(2 * index + 2);
    }

    public void infixOrder() {
        this.infixOrder(0);
    }

    //二叉树中序遍历数组
    public void infixOrder(int index) {
        if (arr == null||arr.length == 0)
            System.out.println("数组为空，不能遍历！");
        //左遍历
        if ((2 * index + 1) < arr.length)
            infixOrder(2 * index + 1);

        System.out.println(arr[index]); //输出本节点

        //右遍历
        if ((2 * index + 2) < arr.length)
            infixOrder(2 * index + 2);
    }

    public void postOrder() {
        this.postOrder(0);
    }

    //二叉树后序遍历数组
    public void postOrder(int index) {
        if (arr == null||arr.length == 0)
            System.out.println("数组为空，不能遍历！");
        //左遍历
        if ((2 * index + 1) < arr.length)
            postOrder(2 * index + 1);

        //右遍历
        if ((2 * index + 2) < arr.length)
            postOrder(2 * index + 2);

        System.out.println(arr[index]); //输出本节点
    }
}