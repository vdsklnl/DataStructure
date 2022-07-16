package binarysorttree;

/**
 * @author vdsklnl
 * @create 2022-06-09 18:51
 * @Description
 */

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        binarySortTree.infix(); //1 2 3 5 7 9 10 12

        System.out.println("删除测试");
        binarySortTree.delete(2);
        binarySortTree.delete(5);
        binarySortTree.delete(9);
        binarySortTree.delete(12);
        binarySortTree.delete(7);
        binarySortTree.delete(3);
        binarySortTree.delete(10);
        binarySortTree.delete(1);
        binarySortTree.infix();
    }
}

//二叉排序树
class BinarySortTree {
    private Node root;

    //删除节点
    public void delete(int value) {
        if (root == null)
            return;
        else {
            Node target = root.targetSearch(value);
            if (target == null) //未找到目标节点
                return;
            //下面情况属于已经找到目标节点
            //删除点无父节点(根节点)
            if (root.getLeft() == null&&root.getRight() == null) {
                root = null;
                return;
            }
            //删除点存在父节点
            Node parent = root.parentSearch(value);
            //删除叶子节点
            if (target.getLeft() == null&&target.getRight() == null) {
                if (parent.getLeft() != null&&parent.getLeft().value == value)
                    parent.setLeft(null); //叶子节点位于父节点左边
                else if (parent.getRight() != null&&parent.getRight().value == value)
                    parent.setRight(null); //叶子节点位于父节点右边
            } else if (target.getLeft() != null&&target.getRight() != null) {
                //删除有两颗子树的节点，方式自定(左或右)
                //删除节点右子树最小/左子树最大，将值赋给删除节点，并删除最值节点
                int rightMin = delRightMin(target.getRight());
                target.value = rightMin;
            } else { //剩余情况是删除一颗子树的节点
                if (target.getLeft() != null) { //删除节点存在左子树
                    if (parent != null) {
                        if (parent.getLeft().value == value)
                            parent.setLeft(target.getLeft()); //target为parent左子节点
                        else if (parent.getRight().value == value)
                            parent.setRight(target.getLeft()); //target为parent右子节点
                    } else //这种情况下，删除根节点且只有左子树
                        root = target.getLeft();
                } else { //删除节点存在右子树
                    if (parent != null) {
                        if (parent.getLeft().value == value)
                            parent.setLeft(target.getRight()); //target为parent左子节点
                        else if (parent.getRight().value == value)
                            parent.setRight(target.getRight()); //target为parent右子节点
                    } else //这种情况下，删除根节点且只有右子树
                        root = target.getRight();
                }
            }
        }
    }

    //查找节点右子树最小值(找到右子树最左子节点)
    public int delRightMin(Node node) {
        Node target = node;
        while (target.getLeft() != null)
            target = target.getLeft();
        //删除节点
        delete(target.value);
        return target.value;
    }

    //查找删除节点
    public Node targetSearch(int value) {
        if (root == null)
            return null;
        else
            return root.targetSearch(value);
    }

    //查找删除父节点
    public Node parentSearch(int value) {
        if (root == null)
            return null;
        else
            return root.parentSearch(value);
    }

    //添加节点的方法
    public void add(Node node) {
        if (root == null)
            root = node;
        else
            root.add(node);
    }

    //中序遍历
    public void infix() {
        if (root != null)
            root.infix();
        else
            System.out.println("二叉排序树为空，无法遍历！");
    }
}

//节点
class Node {
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

    //查找待删除节点
    public Node targetSearch(int value) {
        if (this.value == value)
            return this;
        else if (this.value > value) { //左子树递归
            if (this.left == null)
                return null;
            else
                return this.left.targetSearch(value);
        } else { //右子树递归
            if (this.right == null)
                return null;
            else
                return this.right.targetSearch(value);
        }
    }

    //查找待删除节点父节点
    public Node parentSearch(int value) {
        if ((this.left != null&&this.left.value == value)||(this.right != null&&this.right.value == value))
            return this;
        else {
            if (value < this.value&&this.left != null)
                return this.left.parentSearch(value); //左子树递归
            else if (value >= this.value&&this.right != null)
                return this.right.parentSearch(value); //右子树递归
            else
                return null;
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //添加新节点
    public void add(Node node) {
        if (node == null)
            return;
        //将新节点值与当前节点比较
        if (node.value < this.value) { //判断左子节点
            if (this.left == null)
                this.left = node;
            else //左子树递归添加
                this.left.add(node);
        } else { //判断右子节点
            if (this.right == null)
                this.right = node;
            else //右子树递归添加
                this.right.add(node);
        }
    }

    //中序遍历
    public void infix() {
        if (this.left != null)
            this.left.infix();
        System.out.println(this);
        if (this.right != null)
            this.right.infix();
    }
}
