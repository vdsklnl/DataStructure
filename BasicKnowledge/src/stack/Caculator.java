package stack;

/**
 * @author vdsklnl
 * @create 2022-05-30 20:55
 * @Description
 */

public class Caculator {
    public static void main(String[] args) {
        //测试计算式
        String expression = "(6+40*3-60/2)/6-10";
        //创建两个栈，数栈与符号栈
        ArrayStackForCal numStack = new ArrayStackForCal(10);
        ArrayStackForCal opeStack = new ArrayStackForCal(10);
        //定义所需辅助元素
        int index = 0; //扫描计算式用
        int num1 = 0;
        int num2 = 0;
        int oper = 0; //记录符号
        int res = 0; //记录计算结果
        char ch = ' '; //将扫描到的记录到ch
        char next = ' ';
        String keepNum = ""; //处理多位数
        //while循环扫描expression
        while (true) {
            //依次得到每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //根据ch进行相应操作
            if (ch == '(')
                opeStack.push(ch);
            else if (ch == ')') {
                //计算')'前所有计算式
                while (true) {
                    //如果符号栈顶部为'('，结束计算
                    if (opeStack.peek() == '(') {
                        opeStack.pop();
                        break;
                    }
                    num1 = numStack.pop();
                    num2 = numStack.pop();
                    oper = opeStack.pop();
                    res = numStack.cal(num1, num2, oper);
                    numStack.push(res);
                }
            } else if (opeStack.isOper(ch)) {
                //空栈直接压入
                if (!opeStack.isEmpty()) {
                    //符号栈中有符号，先比较待入与栈顶优先级，小于等于则进行计算
                    //结果压入数栈，待入符号压入符号栈；否则直接压入符号栈
                    if (opeStack.priority(ch) <= opeStack.priority(opeStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = opeStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                        opeStack.push(ch);
                    } else
                        opeStack.push(ch);
                }
                else
                    opeStack.push(ch);
            } else {
                //先判断是否为多位数
                keepNum += ch;
                //到最后一位直接压入
                if (index == expression.length() - 1)
                    numStack.push(Integer.parseInt(keepNum));
                else {
                    //下一位为符号才入栈
                    next = expression.substring(index + 1, index + 2).charAt(0);
                    if (opeStack.isOper(next)||next == '('||next == ')') {
                        numStack.push(Integer.parseInt(keepNum));
                        //清空keepNum
                        keepNum = "";
                    }

                }
            }
            index++;
            if (index >= expression.length())
                break;
        }
        //扫描完成后，按照顺序进行计算
        while (true) {
            //如果符号栈为空，结束计算，此时数栈中仅剩一个数，即为结果
            if (opeStack.isEmpty())
                break;
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = opeStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        //输出结果
        System.out.printf("计算式：%s\n结果：%d", expression, numStack.pop());
    }
}

class ArrayStackForCal {
    private int maxSize; //栈容量
    private int[] arr; //数组模拟栈
    private int top = -1; //栈顶位置，默认为-1

    public ArrayStackForCal(int maxSize) {
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

    //返回栈顶元素
    public int peek() {
        return arr[top];
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

    //返回运算符优先级，数值越大优先级越高
    public int priority(int oper) {
        //暂时考虑简单情况
        if (oper == '*'||oper == '/')
            return 1;
        else if (oper == '+'||oper == '-')
            return 0;
        else
            return -1;
    }

    //判断是否是运算符
    public boolean isOper(char val) {
        return val == '*'||val == '/'||val == '+'||val == '-';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0; //存放计算结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return res;
    }

}
