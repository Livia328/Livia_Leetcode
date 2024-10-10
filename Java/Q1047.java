import java.util.Deque;
import java.util.LinkedList;
import java.util.Deque;
import java.util.ArrayDeque;
public class Q1047 {
    /*
     * 因为是消消乐，所以很自然的想到了stack
     * stack里放之前的元素
     * 
     * 如果cur和stack.peek一样，那么就pop，否则就加入
     */
    public String removeDuplicates(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        // 也可以⬇️
        // String res = "";
        // while (!stack.isEmpty()) {
        //     res = stack.pop() + res;
        // }
        // return res;
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
