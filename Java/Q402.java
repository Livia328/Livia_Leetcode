import java.util.Stack;

public class Q402 {
    /**
     * pattern是找到first peak
     * 157654
     * 第一个应该被remove的应该是7
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

    /**
     * improve: stack
     * 因为我们要找的是连续递增数列的最后一个数
     * 
     * stack，往里面放数，如果要放进去的数current比stack.peek()小，说明我们要丢掉stack.peek()
     */
    public String removeKdigits2(String num, int k) {
        if (k == num.length()) {
            return "0";
        }
        Stack<Character> stack = new Stack<>();
        int i = 0; //index of number in nums
        while (i < num.length()) {
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
}
