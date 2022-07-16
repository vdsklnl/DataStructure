package linked_list;

import java.util.Stack;

/**
 * @author vdsklnl
 * @create 2022-05-28 21:12
 * @Description
 */

public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //测试
        SingleLinkedList list = new SingleLinkedList();
        list.addByOrder(new HeroNode(1, "宋江", "及时雨"));
        list.addByOrder(new HeroNode(3, "吴用", "智多星"));
        list.addByOrder(new HeroNode(2, "lujunyi", "yuqilin"));
        list.addByOrder(new HeroNode(4, "林冲", "豹子头"));
        list.addByOrder(new HeroNode(2, "卢俊义", "玉麒麟"));
        list.showHeros();

        System.out.println("====================");
        list.update(new HeroNode(2, "卢俊义", "玉麒麟"));
        list.showHeros();

        System.out.println("====================");
        SingleLinkedList.reverse(list.getHead());
        list.showHeros();

        System.out.println("====================");
        SingleLinkedList.reversePrint(list.getHead());

        System.out.println("====================");
        list.delete(1);
//        list.delete(1)
        list.delete(4);
//        list.delete(3);
//        list.delete(2);
        list.showHeros();

        System.out.println("====================");
        SingleLinkedList.reverse(list.getHead());
        list.showHeros();
        System.out.println("有效数据长度为：" + SingleLinkedList.getLength(list.getHead()));
        HeroNode res = SingleLinkedList.getLastIndexNode(list.getHead(), 2);
        System.out.println("res = " + res);

        System.out.println("====================");
        SingleLinkedList list1 = new SingleLinkedList();
        list1.addByOrder(new HeroNode(1, "宋江", "及时雨"));
        list1.addByOrder(new HeroNode(4, "林冲", "豹子头"));
        list1.addByOrder(new HeroNode(5, "武松", "行者"));
        list1.showHeros();

        System.out.println("====================");
        SingleLinkedList list2 = new SingleLinkedList();
        SingleLinkedList.combine(list.getHead(), list1.getHead(), list2.getHead());
        System.out.println("有效数据长度为：" + SingleLinkedList.getLength(list2.getHead()));
        list2.showHeros();

    }

}

//定义SingleLinkedList管理英雄对象
class SingleLinkedList {
    //初始化头节点，不改变，相当于链表锚点
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加Hero进入单链表
    //不考虑编号顺序时，找到当前链表尾节点，并将其next指向添加节点
    public void addHero(HeroNode heroNode) {
        //头节点不能改变，需要添加辅助节点
        HeroNode temp = head;
        while (true) {
            if (temp.next == null)
                break;
            temp = temp.next;
        }
        //退出while循环，temp即为最后节点，将next指向新节点
        temp.next = heroNode;
    }

    //按照排名添加英雄，如果存在则提示添加错误
    public void addByOrder(HeroNode heroNode) {
        //头节点不能改变，需要添加辅助节点
        //单链表temp应该位于添加位置前一节点，否则无法添加
        HeroNode temp = head;
        //判断添加英雄序号是否合理
        if (temp.no >= heroNode.no)
            throw new RuntimeException("添加序号错误，请重新添加！");
        boolean flag = false; //标志添加序号是否存在，默认为false
        while (true) {
            //已经到达末尾，添加位置即为末尾
            if (temp.next == null)
                break;
            //找到需要插入的位置
            if (temp.next.no > heroNode.no)
                break;
            //添加英雄序号已存在，添加失败
            if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("添加的序号 %d 已经存在，添加失败！\n", heroNode.no);
        } else {
            //可以添加，将heroNode.next指向下一节点，temp.next指向heroNode
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //显示链表，遍历
    public void showHeros() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空，请添加数据！");
            return;
        }
        //头节点不能改变，需要添加辅助节点
        HeroNode temp = head;
        while (true) {
            temp = temp.next;
            if (temp == null) {
                break;
            }
            System.out.println(temp);
        }
    }

    //根据编号来修改英雄信息
    public void update(HeroNode heroNode) {
        //预先判断链表为空的情况
        if (head.next == null) {
            System.out.println("链表为空，请先添加英雄~~");
            return;
        }
        //头节点不能改变，需要添加辅助节点
        HeroNode temp = head;
        boolean flag = false; //表示是否找到修改节点
        while (true) {
            temp = temp.next;
            if (temp == null)
                break;
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
        }
        if (flag) {
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        } else
            System.out.printf("没有找到编号 %d 的节点，不能修改！\n", heroNode.no);
    }

    //根据序号删除相应英雄，定义到待删节点前一节点
    public void delete(int no) {
        //头节点不能改变，需要添加辅助节点
        HeroNode temp = head;
        boolean flag = false; //表示是否找到修改节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag)
            temp.next = temp.next.next;
        else
            System.out.printf("没有找到编号 %d 的节点，不能删除！\n", no);
    }

    /*
     * 合并两个链表，所有链表均存在顺序，以第一个头节点为新链表头节点
     * 递归方法
     */
    public static void combine(HeroNode head1, HeroNode head2, HeroNode head3) {
        //头节点不能改变，需要添加辅助节点
        HeroNode temp1 = head1.next;
        HeroNode temp2 = head2.next;
        if (temp1 == null) {
            head3.next = temp2;
        }
        if (temp2 == null) {
            head3.next = temp1;
        }
        //其他情况
        HeroNode temp3 = head3;
        while (temp1 != null||temp2 != null) {
            if (temp1 == null||temp1.no > temp2.no) {
                temp3.next = temp2;
                temp2 = temp2.next;
            } else if (temp2 == null||temp1.no < temp2.no) {
                temp3.next = temp1;
                temp1 = temp1.next;
            }
            temp3 = temp3.next;
        }
    }

    /*
     * 百度面试题：从头到尾打印单链表
     * 方式一：反转单链表并打印，但破坏原结构
     * 方式二：使用栈(stack)，实现逆序打印
     */
    public static void reversePrint(HeroNode head) {
        //链表为空或仅有一个元素
        if (head.next == null)
            return;
        if (head.next.next == null)
            System.out.println(head.next);
        //使用栈结构，遍历并压栈
        //头节点不能改变，需要添加辅助节点
        HeroNode temp = head.next;
        Stack<HeroNode> stack = new Stack<>();
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        //打印
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /*
     * 腾讯面试题：单链表反转
     */
    public static void reverse(HeroNode head) {
        //链表为空或仅有一个元素
        if (head.next == null||head.next.next == null)
            return;
        //定义辅助节点帮助遍历，辅助节点保存取出节点，反转头节点定位
        HeroNode cur = head.next;
        HeroNode node = null;
        HeroNode reserveHead = new HeroNode(0, "", "");
        //遍历链表，取出并按顺序连接在反转头节点
        while (cur != null) {
            node = cur.next;
            cur.next = reserveHead.next;
            reserveHead.next = cur;
            cur = node;
        }
        //将新链表赋值给原head
        head.next = reserveHead.next;
    }

    /*
     * 新浪面试题：返回链表倒数第K个节点
     * 先获取链表有效长度size，然后返回(size-k)个节点，无则返回null
     */
    public static HeroNode getLastIndexNode(HeroNode head, int index) {
        //空链表
        if (head.next == null)
            return null;
        //获取有效长度
        int length = getLength(head);
        //index可能超过有效数据长度
        if (length < index||index < 0)
            return null;
        //定义辅助变量
        HeroNode cur = head.next;
        for (int i = 0; i < length - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /*
     * 获取链表有效长度（不统计头节点）
     */
    public static int getLength(HeroNode head) {
        //空链表
        if (head.next == null)
            return 0;
        int length = 0;
        //定义辅助变量
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

}

//定义HeroNode，每个对象为一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
