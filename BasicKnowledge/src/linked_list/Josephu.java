package linked_list;

/**
 * @author vdsklnl
 * @create 2022-05-30 16:56
 * @Description
 */

public class Josephu {
    public static void main(String[] args) {
        //test
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.addBoys(25);
        list.josephu(1, 2, 25);
    }
}

class CircleSingleLinkedList {
    //定义初始位置，当前没有编号
    private Boy first = null;

    /*
     * Josephu问题，输出出圈顺序
     * 第k个小孩开始，报m个数，第m号出圈，从下一位重新数
     */
    public void josephu(int start, int count, int num) {
        //数据校验(已生成环形链表)
        if (first == null||start > num||start < 1) {
            System.out.println("数据有误，请重新输入");
        }
        //辅助节点，指向first前一位置，first指向出圈编号，出圈完，指向新的起始编号
        Boy help = first;
        while (true) {
            if (help.getNext() == first)
                break;
            help = help.getNext();
        }
        //先移动k-1次到起始位置
        for (int i = 0; i < start - 1; i++) {
            first = first.getNext();
            help = help.getNext();
        }
        while (true) {
            //表示已经出圈完，仅剩一个
            if (help == first)
                break;
            //先移动k-1次到起始位置
            for (int i = 0; i < count - 1; i++) {
                first = first.getNext();
                help = help.getNext();
            }
            System.out.printf("出圈小孩编号：%d\n", first.getNo());
            first = first.getNext();
            help.setNext(first);
        }
        System.out.printf("最后的幸运小孩是：%d\n", first.getNo());
    }

    //添加节点构成环形链表
    public void addBoys(int num) {
        //数据校验，num合理性
        if (num < 1) {
            System.out.println("输入数字错误，请重新输入！");
            return;
        }
        //辅助指针，帮助构建环形链表
        Boy curBoy = null;
        //使用for循环构建环形链表
        for (int i = 1; i <= num; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first); //构成环链
                curBoy = first; //辅助指针指向定位节点
            } else {
                boy.setNext(first);
                curBoy.setNext(boy);
                curBoy = boy;
            }
        }
    }

    public void showBoys() {
        if (first == null) {
            System.out.println("链表为空，请先添加数据~~");
            return;
        }
        //first不能移动，引入辅助节点
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号：%d\n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

}

//Boy
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

}
