import java.util.Stack;

public class Q402 {
    /**
     * 比如说43125，因为4是在前面，所以感觉我们想要去掉它
     * 这样更小的数就可以刀更高的位置
     * 
     * 12345，这样我们就想去掉后面的
     * 否则去掉1，就会变成2345
     * 这样更高位就会被更大的数字取代，反而不好
     * 所以我们应该去掉之后的
     * 
     * 又比如说123621
     * 我们要去除的就是6
     * 因为去掉它，这个位置就可以被更小的数取代，improve
     * 
     * 所以我们要找到下一个更大的数
     * 所以单调栈
     * 
     * 用一个stack，往里面放数字
     * 如果要放进去的数current比stack.peek()小，说明我们要丢掉stack.peek()
     * 直到k用完，或者便利完为止
     * 
     * 在这个之后，这个string会变成全递增的
     * 如果k还没用完，可以删除尾部的数字
     */
    public String removeKdigits2(String num, int k) {
        if (k == num.length()) {
            return "0";
        }
        Stack<Character> stack = new Stack<>();
        int i = 0; //index of number in nums
        while (i < num.length()) {//遍历每一个字母
            // 新数字比peek小，peek就是要扔出去的数
            while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
            i++;
        }
        // corner case
        // 如果还有k没有用完并且还有数字的话，从最后一位开始挪
        // 1111， k = 3
        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        // remover leading 0
        while (sb.toString().startsWith("0")) {
            sb.deleteCharAt(0);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    /*
     * brute force
     * 以防万一
     */
    public String removeKdigits(String num, int k) {
        StringBuilder sb = new StringBuilder(num);
        while (k > 0) {
            int i = 0;
            while (i < sb.length() - 1 && sb.charAt(i) <= sb.charAt(i + 1)) {
                i++;
            }
            // 出循环后i就是要remover的
            // 这里就包含了corner case，当四个数都是一样的1111， 就remove最后一位
            sb.deleteCharAt(i);
            k--;
        }
        // remover leading 0s
        while(sb.toString().startsWith("0")) {
            sb.deleteCharAt(0);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
