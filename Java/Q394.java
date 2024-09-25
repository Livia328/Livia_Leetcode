import java.util.Stack;

public class Q394 {
    /*
     * 注意读题，3[a2[c]] -> accaccacc
     * 
     * 括号：所以想到stack
     * 可以有两个stack
     * numStack:
     * StringStack: 存放当前string要接在什么后面，
     *              比如作为第一个数字3，它是刚开头，对应的应该是“”
     *              这样我们之后创建的string才可以接在它的后面
     * 
     * 碰到数字，放入numStack
     * 碰到[，当前num入栈，并回归初始值
     * 碰到字符：拼接到当前sting后面
     * 碰到]，开始解码，弹出数字栈的元素作为循环此处，弹出字符的元素，在这个字符后面还是拼新的元素
     * 
     * 3[a2[c]]
     * numStack: 3, 2,
     * StringStack:"", "a",
     * 
     * num: 
     * curString: c
     * 
     * pop2, pop"a"
     * curString: acc
     * 
     * pop3, pop""
     * accaccacc
     */
    public String decodeString(String s) {
        Stack<Integer>numStack = new Stack<>();
        Stack<String>strStack = new Stack<>();
        int num = 0;
        String curString = "";
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                numStack.push(num);
                strStack.push(curString);
                num = 0; curString = "";
            } else if (c == ']') {
                // 得到当前字符要循环的次数
                int times = numStack.pop();
                // 得到当前这个字符要接在什么后面
                StringBuilder sb = new StringBuilder(strStack.pop());
                for (int i = 0; i < times; i++) {
                    sb.append(curString);
                }
                curString = sb.toString();
            } else {
                curString += c;
            }
        }
        return curString;
    }
}
