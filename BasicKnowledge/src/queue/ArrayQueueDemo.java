package queue;

import java.util.Scanner;

/**
 * @author vdsklnl
 * @create 2022-05-28 16:04
 * @Description
 */

public class ArrayQueueDemo {
    //测试ArrayQueue
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("a(add):添加队列");
            System.out.println("g(get):获取头部数据");
            System.out.println("h(head):显示头部数据");
            System.out.println("e(exit):退出队列");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    try {
                        arrayQueue.showQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    System.out.print("请输入添加数据：");
                    int value = scanner.nextInt();
                    try {
                        arrayQueue.addQueue(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出数据为：%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int headQueue = arrayQueue.headQueue();
                        System.out.printf("队列头部数据为：%d\n", headQueue);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    System.out.println("无效字符，请重新输入！");
                    break;
            }
        }
        System.out.println("程序结束~~");
    }


}

class ArrayQueue {
    private int maxSize; //数组最大容量
    private int front; //队列头：在队列中第一个数据之前
    private int rear; //队列尾：指向最后一个数据
    private int[] arr; //存放数据，模拟队列

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    //判断队列是否已满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列中
    public void addQueue(int n) {
        if (isFull()) {
            throw new RuntimeException("队列已满，添加失败！");
        }
        arr[++rear] = n;
    }

    //获取数据，出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法获取！");
        }
        return arr[++front];
    }

    //显示队列全部数据
    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法显示！");
        }
        //arr中数据不更新，显示全部数据
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }
    }

    //显示队列头部数据，不获取
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法获取！");
        }
        return arr[front + 1];
    }

}
