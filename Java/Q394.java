import java.util.Stack;

public class Q394 {
    /**
     * recursion
     * 
     * recursion函数的参数？
     * 3[a2[c]]
     * 遍历过去，先记录3，然后碰到[，我们知道要进入下一层递归
     * 我们需要知道此时开始的index recursion（a2[c]]）
     * 同样从下一层传上来的时候我们需要更新这一层的index，表示从[start, end]的数组已经被遍历过了
     * 
     * 返回：string，end index？
     * String[], or we could have a new class?
     * which one is better?
     * 
     * string[] dfs(String s, int i):返回从i开始到结尾的生成string
     * string[0]: index
     * string[1]: string
     * 
     * 
     * 如果是数字：
     *   记录当前数次
     * 当碰到'[''的时候，开始新一轮的递归
     *   记录【。。。】中的字符串tmp和递归后的最新index，并执行res + num * tmp拼接
     * 
     * 碰到']'的时候，
     *   返回当前括号内记录的res字符串和】的index
     */
    public String decodeString1(String s) {
        return dfs(s, 0)[1];
    }

    private String[] dfs(String s, int i) {
        StringBuilder sb = new StringBuilder();
        int num = 0; //记录需要重复几次
        while (i < s.length()) {
            // 是数字的话
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                num = num * 10 + (s.charAt(i) - '0');
            } else if (s.charAt(i) == '[') {
                // '['开始递归
                String[] tmp = dfs(s, i + 1);
                // 结束的index，表示在递归函数里已经便利到i了
                i = Integer.parseInt(tmp[0]);
                // 拼接num次tmp
                while (num > 0) {
                    sb.append(tmp[1]);
                    num--;
                }
            } else if (s.charAt(i) == ']') {
                // return回上一层
                return new String[]{String.valueOf(i), sb.toString()};
            } else {
                sb.append(s.charAt(i));
            }
            i++;
        }
        return new String[]{String.valueOf(i), sb.toString()};
    }
    
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
