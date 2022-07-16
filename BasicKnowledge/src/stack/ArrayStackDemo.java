package stack;

import java.util.Scanner;

/**
 * @author vdsklnl
 * @create 2022-05-30 20:18
 * @Description
 */

public class ArrayStackDemo {
    public static void main(String[] args) {
        //Test
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show:显示");
            System.out.println("push:压栈");
            System.out.println("pop:弹栈");
            System.out.println("exit:退出");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.show();
                    break;
                case "push":
                    System.out.println("请输入一个数值：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("弹出数据为：%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    System.out.println("输入无效，请重新输入！");
            }

        }
        System.out.println("程序结束！");
    }
}

class ArrayStack {
    private int maxSize; //栈容量
    private int[] arr; //数组模拟栈
    private int top = -1; //栈顶位置，默认为-1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    //是否满栈
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //是否空栈
    public boolean isEmpty() {
        return top == -1;
    }

    //压栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满，无法添加！");
            return;
        }
        arr[++top] = value;
    }

    //弹栈
    public int pop() {
        if (isEmpty())
            throw new RuntimeException("空栈，请先添加数据！");
        return arr[top--];
    }

    //遍历，从栈顶开始
    public void show() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据！");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, arr[i]);
        }
    }
}
