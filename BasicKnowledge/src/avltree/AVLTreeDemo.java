package avltree;

/**
 * @author vdsklnl
 * @create 2022-06-11 14:33
 * @Description
 */

public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8}; //左旋转测试
//        int[] arr = {10, 12, 8, 9, 7, 6}; //右旋转测试
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
//        avlTree.infix();

        System.out.println(avlTree.getRoot().height());
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println(avlTree.getRoot().rightHeight());
        System.out.println(avlTree.getRoot());
    }
}

//AVL树(基于二叉排序树，基本方法相同)
class AVLTree {

    private Node root;

    public Node getRoot() {
        return root;
    }

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

    //返回当前节点为根节点左子树高度
    public int leftHeight() {
        if (left == null)
            return 0;
        else
            return left.height();
    }

    //返回当前节点为根节点右子树高度
    public int rightHeight() {
        if (right == null)
            return 0;
        else
            return right.height();
    }

    //返回以当前节点为根节点高度
    public int height() {
        //max(a, b)，a，b分别为左右子树高度，递归查找每一个子树
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转方法
    private void leftRotate() {
        //以当前节点值创建新节点
        Node node = new Node(value);
        //将新节点左子树连接到此节点左子树
        node.setLeft(left);
        //将新节点右子树设置成此节点右子树左子树
        node.setRight(right.left);
        //将当前节点值替换成右子节点值
        value = right.value;
        //将当前节点右子树设置成右子节点右子树
        right = right.right;
        //将当前节点左子节点设置为新节点
        this.setLeft(node);
    }

    //右旋转方法
    private void rightRotate() {
        //以当前节点值创建新节点
        Node node = new Node(value);
        //将新节点右子树连接到此节点右子树
        node.setRight(right);
        //将新节点右子树设置成此节点左子树右子树
        node.setLeft(left.right);
        //将当前节点值替换成左子节点值
        value = left.value;
        //将当前节点左子树设置成左子节点左子树
        left = left.left;
        //将当前节点右子节点设置为新节点
        this.setRight(node);
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

        //添加节点后，若 h右 - h左 > 1，需要左旋转
        if (rightHeight() - leftHeight() > 1) {
            if (right != null&&right.leftHeight() > right.rightHeight())
                right.rightRotate();
            leftRotate();
            return; //十分重要，不需要后面判断且避免异常情况
        }
        //添加节点后，若 h左 - h右 > 1，需要右旋转
        if (leftHeight() - rightHeight() > 1) {
            if (left != null&&left.rightHeight() > left.leftHeight())
                left.leftRotate();
            rightRotate();
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