package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author vdsklnl
 * @create 2022-06-01 14:32
 * @Description
 */

public class PolandNotation {
    public static void main(String[] args) {
        //将中缀表达式转化为后缀表达式
//        String infixExpression = "1+((2+3)*4)-5";
        String infixExpression = "1+((2+3)*4+5)*6-78";
        List<String> infixList = getInfixList(infixExpression);
        System.out.println(infixList);

        //将中缀集合转化为后缀表达式
        List<String> suffixList = parseInToSufList(infixList);
        System.out.println(suffixList);

        //计算表达式
        int result = calculate(suffixList);
        System.out.println("计算结果：" + result);

        /*
        //定义逆波兰表达式，数字符号用空格隔开
        //(3+4)*5-6 -> 3 4 + 5 x 6 -
//        String suffixExpression = "3 4 + 5 * 6 -";
//        String suffixExpression = "30 4 + 5 * 6 -";
        //4*5-8+60-8/2 -> 68
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / -";
        //将表达式放在List集合中方便遍历
        List<String> list = getListString(suffixExpression);
        System.out.println("rpnList=" + list);
        int res = calculate(list);
        System.out.println("计算结果：" + res);
         */

    }

    //转换中缀表达式至集合
    public static List<String> getInfixList(String infix) {
        ArrayList<String> list = new ArrayList<>();
        int index = 0; //遍历指针
        String str; //拼接多位数
        char ch = ' '; //记录每次遍历字符
        while (index < infix.length()) {
            ch = infix.charAt(index);
            if (ch < 48||ch > 57) { //表示为符号，直接添加
                list.add(ch + "");
                index++;
            } else {
                //多位数拼接
                str = "";
                while (ch <= 57&&ch >= 48) {
                    str += ch;
                    index++;
                    if (index >= infix.length())
                        break;
                    ch = infix.charAt(index);
                }
                list.add(str);
            }
        }
        return list;
    }

    //将中缀集合转化为后缀表达式
    //[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5] -> [1,2,3,+,4,*,+,5,-]
    public static List<String> parseInToSufList(List<String> infixList) {
        //定义两个栈，符号栈与结果栈
        //结果栈仅压入不弹出，可以由ArrayList代替，更加方便
        Stack<String> stack = new Stack<>();
        ArrayList<String> suffixList = new ArrayList<>();
        //遍历infixList
        for (String item:infixList) {
            if (item.matches("\\d+")) //正则表达式匹配多位数
                suffixList.add(item);
            else if ("(".equals(item)) //左括号压入符号栈
                stack.push(item);
            else if (")".equals(item)) {
                //右括号则依次弹出符号栈符号并压入结果栈，直到栈顶为左括号
                while (!"(".equals(stack.peek()))
                    suffixList.add(stack.pop());
                //将一对括号丢弃
                stack.pop();
            } else {
                //需判断优先级，定义类Operation
                //当item优先级小于等于栈顶优先级时，依次压入栈顶运算符，直至栈空
                while (stack.size() != 0&&Operation.getValue(item) <= Operation.getValue(stack.peek()))
                    suffixList.add(stack.pop());
                //最后将此操作符压入符号栈
                stack.push(item);
            }
        }
        //当扫描完成，依次弹出符号栈并压入
        while (stack.size() != 0)
            suffixList.add(stack.pop());
        return suffixList;
    }

    //将一个逆波兰表达式存入ArrayList
    public static List<String> getListString(String suffix) {
        //分割字符串
        String[] split = suffix.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String ele:split) {
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的计算
    /*
     * 与中缀表达式类似
     */
    public static int calculate(List<String> list) {
        //仅需一个栈
        Stack<String> stack = new Stack<>();
        int num1 = 0;
        int num2 = 0;
        int res = 0;
        for (String item:list) {
            //使用正则表达式匹配数字
            if (item.matches("\\d+"))  //匹配多位数
                stack.push(item);
            else {
                num1 = Integer.parseInt(stack.pop());
                num2 = Integer.parseInt(stack.pop());
                switch (item) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num2 - num1;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num2 / num1;
                        break;
                    default:
                        throw new RuntimeException("运算符有误！");
                }
                stack.push(String.valueOf(res));
//                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation) {
        int res = 0;
        switch (operation) {
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
//            default:
//                System.out.println("运算符不合理！");
        }
        return res;
    }
}