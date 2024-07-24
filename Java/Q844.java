import java.util.Stack;

public class Q844 {
    /*
     * stack解法
     */
    public boolean backspaceCompare1(String s, String t) {
        Stack<Character> stack_s = helper(s);
        Stack<Character> stack_t = helper(t);
        while (!stack_s.isEmpty() && !stack_t.isEmpty()) {
            if (stack_s.pop() != stack_t.pop()) {
                return false;
            }
        }
        return stack_s.isEmpty() && stack_t.isEmpty();
    }

    public Stack<Character> helper(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c != '#') {
                stack.add(c);
            } else if (c == '#') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }
        return stack;
    }

    /*
     * 优化：2 pointers optimize space complexity from O(n) -> O(1)
     * 
     * 因为backspace要回退，只影响左边的字符，所以从后往前遍历
     * 总体思路是先消除两个string中的#，然后再比较
     * countS, countT分别记录两个字符中的#数量
     * 
     * 1.若当前字符是#，则count++
     * 2.若当前字符不是#，且count不是0，则count--（说明这个要被消除）
     * 3.若当前字符不是#，却skip == 0，则代表当前字符不会被消除
     */
    public boolean backspaceCompare(String s, String t) {
        int countS = 0, countT = 0;
        int i = s.length() - 1, j = t.length() - 1;
        while (true) {
            while (i >= 0) {
                // 消除# in S
                if (s.charAt(i) == '#') {
                    countS++;
                } else {
                    // 当前字符不是#，且可以被抵消
                    if (countS > 0) {
                        countS--;
                    } else {
                        // 这个字符肯定会在，得比较了
                        break;
                    }
                }
                i--;
            }
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    countT++;
                } else {
                    if (countT > 0) {
                        countT--;
                    } else {
                        break;
                    }
                }
                j--;
            }
            // #消除完了，比较s和t
            if (i < 0 || j < 0) { //其中一个遍历到头了
                break;
            }
            if (s.charAt(i) != t.charAt(j)) {
                return false;
            }
            i--; j--;
        }
        return i == -1 && j == -1;
    }
}
