import java.util.*;

public class Q20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            //碰到左括号，把相应的右括号放进stack中
            if (cur == '(') {
                stack.push(')');
            } else if (cur == '[') {
                stack.push(']');
            } else if (cur == '{') {
                stack.push('}');
            } else {
                // 意味着有一个close parentheses但是没有对应的open
                // 直接false
                if (stack.isEmpty()) {
                    return false;
                }
                if (cur != stack.peek()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
