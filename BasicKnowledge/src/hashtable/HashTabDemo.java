package hashtable;

import java.util.Scanner;

/**
 * @author vdsklnl
 * @create 2022-06-07 11:26
 * @Description
 */

public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);

        //菜单界面
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("show: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");
            key = scanner.next();

            switch (key) {
                case "add":
                    System.out.print("请输入id：");
                    int id = scanner.nextInt();
                    System.out.print("请输入姓名：");
                    String name = scanner.next();
                    hashTab.add(new Emp(id, name));
                    break;
                case "show":
                    hashTab.show();
                    break;
                case "find":
                    System.out.print("请输入查找id：");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}

//创建Hash表对多条链进行管理
class HashTab {
    private int size; //表示管理多少条链
    private EmpLinkedList[] empLinkedListArray;

    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        //要将每条链表都进行初始化
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加员工到对应链表
    public void add(Emp e) {
        //判断添加位置
        int listNum = hashFunc(e.id);
        //对应链表添加
        empLinkedListArray[listNum].add(e);
    }

    //显示散列表
    public void show() {
        for (int i = 0; i < size; i++)
            empLinkedListArray[i].show(i);
    }

    //查找雇员
    public void findEmpById(int id) {
        int listNum = hashFunc(id);
        Emp emp = empLinkedListArray[listNum].findById(id);
        if (emp != null)
            System.out.printf("在链表 %d 中找到编号为 %d 的雇员。\n", listNum + 1, id);
        else
            System.out.println("没有在哈希表中找到雇员！");
    }

    //散列函数，判断在哪一条链
    private int hashFunc(int id) {
        return id % size;
    }
}

class Emp {
    public int id;
    public String name;
    public Emp next; //默认为null

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList {
    //头指针，默认为null，指向第一个添加对象
    private Emp head;

    //增加新员工，默认自增
    public void add(Emp e) {
        //链表为空
        if (head == null) {
            head = e;
            return;
        }
        //链表不为空
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null)
                break;
            curEmp = curEmp.next;
        }
        curEmp.next = e;
    }

    //遍历链表
    public void show(int no) {
        if (head == null) {
            System.out.println("链表 " + (no + 1) + " 为空！");
            return;
        }
        System.out.print("链表 " + (no + 1) + " 信息");
        Emp curEmp = head;
        while (true) {
            System.out.printf(" => id=%d, name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null)
                break;
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    //根据id查找雇员
    public Emp findById(int id) {
        if (head == null) {
            System.out.println("链表为空！");
            return null;
        }
        Emp curEmp = head;
        while (true) {
            //curEmp指向查找对象
            if (curEmp.id == id)
                break;
            if (curEmp.next == null) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}
