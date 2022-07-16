package tree.threadedBtree;

/**
 * @author vdsklnl
 * @create 2022-06-07 21:29
 * @Description
 */

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "A");
        HeroNode node1 = new HeroNode(3, "C");
        HeroNode node2 = new HeroNode(6, "F");
        HeroNode node3 = new HeroNode(8, "H");
        HeroNode node4 = new HeroNode(10, "J");
        HeroNode node5 = new HeroNode(14, "N");

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);

        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);

//        threadedBinaryTree.threadedPreNodes();

//        System.out.println(node5.getLeft());
//        System.out.println(node5.getRight());

//        System.out.println("前序遍历线索化二叉树"); //1 3 8 10 6 14
//        threadedBinaryTree.threadedPreList();

//        threadedBinaryTree.threadedInfixNodes();
//
//        System.out.println(node4.getLeft()); //3
//        System.out.println(node4.getRight()); //1
//
//        System.out.println("中序遍历线索化二叉树"); //8 3 10 1 14 6
//        threadedBinaryTree.threadedInfixList();

        threadedBinaryTree.postOrder();

        threadedBinaryTree.threadedPostNodes();
//        System.out.println(node5.getLeft());
//        System.out.println(node5.getRight());
        //需要增加父节点指向指针
//        System.out.println("后序遍历线索化二叉树"); //8 10 3 14 6 1
//        threadedBinaryTree.threadedPostList();

    }
}

class ThreadedBinaryTree {
    private HeroNode root; //根节点

    //为完成线索化，需要辅助节点始终指向当前节点前驱节点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //遍历前序线索化二叉树
    public void threadedPreList() {
        //定义辅助节点存储遍历节点
        HeroNode node = root;
        while (node != null) {
            //当此节点左节点指向子树时，直接打印并移动
            while (node.getLeftType() == 0) {
                System.out.println(node);
                node = node.getLeft();
            }
            //
            //验证该点右指针是否指向后继节点
            while (node.getRightType() == 1) {
                //输出节点并右移
                System.out.println(node);
                node = node.getRight();
            }
            //将最后一个节点输出
            System.out.println(node);
            node = node.getRight();
        }
    }

    public void threadedPreNodes () {
        this.threadedPreNodes(root);
    }

    //前序线索化二叉树
    public void threadedPreNodes (HeroNode node) {
        //不能线索化
        if (node == null)
            return;
        //线索化本节点
        //前驱节点
        if (node.getLeft() == null) {
            //将左指针指向前驱节点
            node.setLeft(pre);
            //更新类型
            node.setLeftType(1);
        }
        //pre节点的右指针指向node，node为后继节点
        if (pre != null&&pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //移动指针
        pre = node;
        //线索化左子树
        if (node.getLeftType() == 0)
            threadedPreNodes(node.getLeft());
        //线索化右子树
        if (node.getRightType() == 0)
            threadedPreNodes(node.getRight());
    }

    //遍历中序化线索二叉树
    public void threadedInfixList() {
        //定义辅助节点存储遍历节点
        HeroNode node = root;
        while (node != null) {
            //每一次，先找到左指针指向前驱节点的点，表示这次循环的开始
            while (node.getLeftType() == 0)
                node = node.getLeft();
            //打印该点
            System.out.println(node);
            //验证该点右指针是否指向后继节点
            while (node.getRightType() == 1) {
                node = node.getRight();
                //输出右节点
                System.out.println(node);
            }
            //循环结束时，node右指针指向右子树，且已被输出，直接换成右节点并开始新循环
            node = node.getRight();
        }
    }

    public void threadedInfixNodes() {
        this.threadedInfixNodes(root);
    }

    //中序线索化方法(node为待线索化节点)
    public void threadedInfixNodes (HeroNode node) {
        //不能线索化
        if (node == null)
            return;
        //线索化左子树
        threadedInfixNodes(node.getLeft());
        //线索化本节点
        //前驱节点
        if (node.getLeft() == null) {
            //将左指针指向前驱节点
            node.setLeft(pre);
            //更新类型
            node.setLeftType(1);
        }
        //pre节点的右指针指向node，node为后继节点
        if (pre != null&&pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //移动指针
        pre = node;

        //线索化右子树
        threadedInfixNodes(node.getRight());
    }

    //需要增加父节点指针
//    //遍历后序线索化二叉树
//    public void threadedPostList() {
//        this.threadedPostList(root);
//    }
//    public void threadedPostList(HeroNode node) {
//        //对node节点进行判断
//        //先判断root左边
//        if (node.getLeftType() == 0) {
//            node = node.getLeft();
//            threadedPostList(node);
//        }
//        //先判断本节点右节点是否为后继节点
//        while (node.getRightType() == 1) {
//            //输出右节点
//            System.out.println(node);
//            node = node.getRight();
//        }
//        System.out.println(node);
//        return;
//    }

    public void threadedPostNodes () {
        this.threadedPostNodes(root);
    }

    //后序线索化二叉树
    public void threadedPostNodes (HeroNode node) {
        //不能线索化
        if (node == null)
            return;
        //线索化左子树
        if (node.getLeft() != null)
            threadedPostNodes(node.getLeft());
        //线索化右子树
        if (node.getRight() != null)
            threadedPostNodes(node.getRight());
        //线索化本节点
        //前驱节点
        if (node.getLeft() == null) {
            //将左指针指向前驱节点
            node.setLeft(pre);
            //更新类型
            node.setLeftType(1);
        }
        //pre节点的右指针指向node，node为后继节点
        if (pre != null&&pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //移动指针
        pre = node;
    }

    //删除子节点
    public void delNode(int id) {
        if (root != null) {
            //根节点为删除节点，将整个树置空
            if (root.getId() == id)
                root = null;
            else
                root.delete(id);
        } else
            System.out.println("链表为空！");
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null)
            this.root.preOrder();
        else
            System.out.println("二叉树为空，无法遍历！");
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null)
            this.root.infixOrder();
        else
            System.out.println("二叉树为空，无法遍历！");
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null)
            this.root.postOrder();
        else
            System.out.println("二叉树为空，无法遍历！");
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int id) {
        if (this.root != null)
            return this.root.preOrderSearch(id);
        else
            return null;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int id) {
        if (this.root != null)
            return this.root.infixOrderSearch(id);
        else
            return null;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int id) {
        if (this.root != null)
            return this.root.postOrderSearch(id);
        else
            return null;
    }
}

class HeroNode {
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //记录节点连接属性，0表示连接子树，1表示连接前驱/后继节点
    private int leftType;
    private int rightType;

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /*
     * 递归删除节点，删除节点为叶子节点，则删除该节点，若否，删除该子树
     * 单向链表，无法确定上一节点，应该考虑删除节点的左右节点
     */
    public void delete(int id) {
        //当前节点左节点即为对应删除节点
        if (this.left != null&&this.left.id == id) {
            this.left = null;
            return;
        }
        //当前节点右节点即为对应删除节点
        if (this.right != null&&this.right.id == id) {
            this.right = null;
            return;
        }

        //上述两种情况不满足，则开始递归遍历
        //不返回，还有右子树遍历
        if (this.left != null)
            this.left.delete(id);
        if (this.right != null)
            this.right.delete(id);
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this); //先输出父节点

        //左子树遍历
        if (this.left != null)
            this.left.preOrder();

        //右子树遍历
        if (this.right != null)
            this.right.preOrder();
    }

    //中序遍历
    public void infixOrder() {
        //左子树遍历
        if (this.left != null)
            this.left.infixOrder();

        System.out.println(this); //输出父节点

        //右子树遍历
        if (this.right != null)
            this.right.infixOrder();
    }

    //后序遍历
    public void postOrder() {
        //左子树遍历
        if (this.left != null)
            this.left.postOrder();

        //右子树遍历
        if (this.right != null)
            this.right.postOrder();

        System.out.println(this); //输出父节点
    }

    /*
     * null表示没找到，找到则返回对应HeroNode
     */
    //前序遍历查找
    public HeroNode preOrderSearch(int id) {
        //根节点判断
        if (this.id == id)
            return this;

        HeroNode res = null;
        //左树遍历查找
        if (this.left != null)
            res = this.left.preOrderSearch(id);
        //表明左子树找到，直接返回
        if (res != null)
            return res;

        //右树遍历查找
        if (this.right != null)
            res = this.right.preOrderSearch(id);
        return res; //直接返回
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int id) {
        HeroNode res = null;
        //左树遍历查找
        if (this.left != null)
            res = this.left.infixOrderSearch(id);
        //表明左子树找到，直接返回
        if (res != null)
            return res;

        //根节点判断
        if (this.id == id)
            return this;

        //右树遍历查找
        if (this.right != null)
            res = this.right.infixOrderSearch(id);
        return res; //直接返回
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int id) {
        HeroNode res = null;
        //左树遍历查找
        if (this.left != null)
            res = this.left.postOrderSearch(id);
        //表明左子树找到，直接返回
        if (res != null)
            return res;

        //右树遍历查找
        if (this.right != null)
            res = this.right.postOrderSearch(id);
        //表明右子树找到，直接返回
        if (res != null)
            return res;

        //根节点判断
        if (this.id == id)
            return this;
        return res;
    }
}