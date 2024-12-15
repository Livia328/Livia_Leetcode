import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Q1249 {
    /*
     * stack用来消消乐
     * stack中放index
     * 
     * 同时用一个set，记录所有invalid的index
     * 从左向右遍历，如果是（，就放入stack
     * 如果是），就看是否有对应的，pop出去
     * 否则就加入invalid set
     * 
     * 结束后，如果stack中还有（
     * 那么说明这个就是invalid的
     * 也都加入invalid set中
     * 
     * 然后再遍历一遍，构建答案
     */
    public String minRemoveToMakeValid(String s) {
        // stack用来消消乐
        Stack<Integer> stack = new Stack<>();
        // set记录所有invalid 的index
        Set<Integer> invalid = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                // 说明可以找到对应的（
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    // 说明这个)是多余的，我们不应该append它
                    invalid.add(i);
                }
            }
        }
        // 如果遍历结束，stack中还有index
        // 说明那就是多余的（ 
        // 也都通通加入set
        while (!stack.isEmpty()) {
            invalid.add(stack.pop());
        }
        // 开始append
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!invalid.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    /*
     * int balance
     * openSeen = 0
     * 
     * 从前往后遍历，去除多余的)：
     * 如果是(: balance++
     * 如果是)：1. balance == 0: not append
     *         2. balance > 0: append, balance--
     * 
     * 应该保留的open：openToKeep = openSeen - balance
     * 从前往后遍历
     * 
     */
    public String minRemoveToMakeValid2(String s) {
        int balance = 0;
        int openSeen = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                openSeen++;
                balance++;
                sb.append(c);
            } else if (c == ')'){
                if (balance > 0) {
                    sb.append(')');
                    balance--;
                }
            } else {
                sb.append(c);
            }
        }
        StringBuilder res = new StringBuilder();
        int openToKeep = openSeen - balance;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '(') {
                if (openToKeep > 0) {
                    res.append('(');
                    openToKeep--;
                }
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }
}
