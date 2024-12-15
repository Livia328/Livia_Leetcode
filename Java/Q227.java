import java.util.Stack;

public class Q227 {
    /*
     * 这个是简单版本的Q224，不同处理括号
     * stack
     * 
     * 3-2*2
     * 将它分成 +3， -2， *2
     * 
     * -> 有一个default的sign为+，sign是previous sign
     * 
     * dry run
     * 
     * sign是+
     * num是0
     * stack：
     * 然后我们碰到3，num = 3
     * 然后我们再碰到+
     * 此时我们可以计算出前面的部分，也就是根据sign来分，因为sign是+，所以我们把+3放入stack
     * set sign为当前sign（-），num为0
     * 
     * 然后我们再碰到2，num = 2
     * 然后我们再碰到*
     * 此时我们可以计算前面部分，因为sign是-，所以我们把-2放入stack
     * 此时stack：+3， -2，
     * 然后重制sign为当前sign（*），num为0
     * 
     * 然后我们再碰到2，因为此时是最后一个，所以我们也要计算
     * 因为sign是*，所以我们取出stack.pop，也就是-2，计算得到-2*2 = -4
     * 再把-4放回stack
     * 
     * 此时stack为+3， -4
     * 
     * 然后最后再把stack里面的所有数都加起来得到最后答案
     * 
     * 
     * 如何获取数字？
     * 有一个num，只要c是数字，就num = num * 10 + (c - '0');
     * 
     * 如果是正负，直接放入stack
     * 如果是乘除，从stack中pop出一个数，将结果再放入stack
     * -> 这样可以体现出乘除的优先性
     */
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        // 前一个sign
        char sign = '+';
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            // 如果是加减乘除
            // 或者是最后一个数
            if (c == '+' || c == '-' || c == '/' || c == '*' || i == s.length() - 1) {
                int pre;
                // 注意这里是sign，因为当前数字的操作是根据前一个sign来的
                // + 3 + 2
                // 当我们碰到第二个+的时候会触发“+3”
                switch (sign) {
                    case '+':
                        stack.push(num); break;
                    case '-':
                        stack.push(-num); break;
                    // 只要拿出前一个数字做对应运算即可
                    case '*':
                        pre = stack.pop();
                        stack.push(pre * num);
                        break;
                    case '/':
                        pre = stack.pop();
                        stack.push(pre / num);
                        break;
                }
                // 更新符号为当前符号，数字清零
                sign = c;
                num = 0;
            }
        }
        // 将栈中所有结果求和就是答案
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
