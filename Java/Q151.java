import java.util.*;

public class Q151 {
    public String reverseWords(String s) {
        // remover extra space
        StringBuilder sb = removeSpace(s);
        System.out.println(sb.toString());
        // 按照空格分开
        String[] split = sb.toString().split(" ");
        Stack<String> stack = new Stack<>();
        for (String token : split) {
            stack.add(token);
        }
        String res = "";
        while (!stack.isEmpty()) {
            res += stack.pop();
            // 不是最后一个的话，在后面加空格
            if (!stack.isEmpty()) {
                res += " ";
            }
        }
        return res;


    }

    public StringBuilder removeSpace(String s) {
        // remover space from the start or end
        int left = 0;
        while (s.charAt(left) == ' ') {
            left++;
        }
        int right = s.length() - 1;
        while (s.charAt(right) == ' ') {
            right--;
        }
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            if (s.charAt(left) == ' ') {
                // 并且上一个不是空格
                if (sb.charAt(sb.length() - 1) != ' ') {
                    sb.append(s.charAt(left));
                }
                left++;
            } else {
                sb.append(s.charAt(left));
                left++;
            }
        }
        return sb;
    }
}
