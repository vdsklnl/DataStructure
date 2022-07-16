package linked_list;

/**
 * @author vdsklnl
 * @create 2022-05-30 15:43
 * @Description
 */

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        DoubleLinkedList list = new DoubleLinkedList();
        list.addByOrder(new HeroNodeNew(1, "宋江", "及时雨"));
        list.addByOrder(new HeroNodeNew(3, "吴用", "智多星"));
        list.addByOrder(new HeroNodeNew(2, "lujunyi", "yuqilin"));
        list.addByOrder(new HeroNodeNew(4, "林冲", "豹子头"));
        list.addByOrder(new HeroNodeNew(2, "卢俊义", "玉麒麟"));
        list.showHeros();

        System.out.println("====================");
        list.update(new HeroNodeNew(2, "卢俊义", "玉麒麟"));
        list.showHeros();

        System.out.println("====================");
        list.delete(1);
        list.delete(1);
        list.delete(4);
//        list.delete(3);
//        list.delete(2);
        list.showHeros();

    }
}

//双向链表类
class DoubleLinkedList {

    //初始化头节点，不改变，相当于链表锚点
    private HeroNodeNew head = new HeroNodeNew(0, "", "");

    public HeroNodeNew getHead() {
        return head;
    }

    //按照排名添加英雄，如果存在则提示添加错误
    public void addByOrder(HeroNodeNew heroNode) {
        //头节点不能改变，需要添加辅助节点
        //单链表temp指向添加位置前一节点
        HeroNodeNew temp = head;
        //判断添加英雄序号是否合理
        if (temp.no >= heroNode.no) {
            System.out.println("添加序号错误，请重新添加！");
            return;
        }
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
            //可以添加
            if (temp.next != null) {
                temp.next.prev = heroNode;
                heroNode.next = temp.next;
            }
            temp.next = heroNode;
            heroNode.prev = temp;
        }
    }

    //根据编号来修改英雄信息
    public void update(HeroNodeNew heroNode) {
        //预先判断链表为空的情况
        if (head.next == null) {
            System.out.println("链表为空，请先添加英雄~~");
            return;
        }
        //头节点不能改变，需要添加辅助节点
        HeroNodeNew temp = head;
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
        //此时辅助节点直接指向待删除节点
        HeroNodeNew temp = head.next;
        boolean flag = false; //表示是否找到修改节点
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.prev.next = temp.next;
            //此时若处于最后一个节点，会报空指针异常，需进行判断
            if (temp.next != null)
                temp.next.prev = temp.prev;
        }
        else
            System.out.printf("没有找到编号 %d 的节点，不能删除！\n", no);
    }

    //添加Hero进入单链表
    //不考虑编号顺序时，找到当前链表尾节点，并将其next指向添加节点
    public void addHero(HeroNodeNew heroNode) {
        //头节点不能改变，需要添加辅助节点
        HeroNodeNew temp = head;
        while (true) {
            if (temp.next == null)
                break;
            temp = temp.next;
        }
        //退出while循环，temp即为最后节点，将next指向新节点，并将新节点prev指向temp
        temp.next = heroNode;
        heroNode.prev = temp;
    }

    //显示链表，遍历
    public void showHeros() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空，请添加数据！");
            return;
        }
        //头节点不能改变，需要添加辅助节点
        HeroNodeNew temp = head;
        while (true) {
            temp = temp.next;
            if (temp == null) {
                break;
            }
            System.out.println(temp);
        }
    }

}

//定义HeroNode，每个对象为一个节点
class HeroNodeNew {
    public int no;
    public String name;
    public String nickname;
    public HeroNodeNew prev; //指向前一节点
    public HeroNodeNew next; //指向后一节点

    public HeroNodeNew(int no, String name, String nickname) {
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
