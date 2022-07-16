package tree;

/**
 * @author vdsklnl
 * @create 2022-06-07 16:16
 * @Description
 */

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        HeroNode root = new HeroNode(1, "宋江");
        HeroNode heroNode1 = new HeroNode(2, "吴用");
        HeroNode heroNode2 = new HeroNode(3, "卢俊义");
        HeroNode heroNode3 = new HeroNode(4, "林冲");
        HeroNode heroNode4 = new HeroNode(5, "关胜");

        root.setLeft(heroNode1);
        root.setRight(heroNode2);
        heroNode2.setLeft(heroNode4);
        heroNode2.setRight(heroNode3);
        binaryTree.setRoot(root);

//        System.out.println("前序遍历"); //1 2 3 5 4
//        binaryTree.preOrder();
//        System.out.println("中序遍历"); //2 1 5 3 4
//        binaryTree.infixOrder();
//        System.out.println("后序遍历"); //2 5 4 3 1
//        binaryTree.postOrder();

        //与遍历顺序一样
//        //前序遍历查找 4
//        HeroNode res1 = binaryTree.preOrderSearch(5);
//        if (res1 != null)
//            System.out.println("找到！" + res1);
//        else
//            System.out.println("未找到！");
//
//        //中序遍历查找 3
//        HeroNode res2 = binaryTree.infixOrderSearch(5);
//        if (res2 != null)
//            System.out.println("找到！" + res2);
//        else
//            System.out.println("未找到！");
//
//        //后序遍历查找 2
//        HeroNode res3 = binaryTree.postOrderSearch(5);
//        if (res3 != null)
//            System.out.println("找到！" + res3);
//        else
//            System.out.println("未找到！");

        System.out.println("前序遍历"); //1 2 3 5 4
        binaryTree.preOrder();
        binaryTree.delNode(3);
        System.out.println("删除");
        binaryTree.preOrder();
    }
}

class BinaryTree {
    private HeroNode root; //根节点

    public void setRoot(HeroNode root) {
        this.root = root;
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

