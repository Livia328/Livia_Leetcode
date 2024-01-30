import java.util.*;

public class Q20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == '(') {
                stack.push(')');
            } else if (cur == '[') {
                stack.push(']');
            } else if (cur == '{') {
                stack.push('}');
            } else {
                // close
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
