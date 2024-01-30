import java.util.*;

import javax.sound.midi.Track;

public class Q22 {
    /**
     * backtracking
     * used right, used left
     * 
     * left < right, return 
     * left > n, right > n, return 
     * left == n, right == n, genetrate answer
     * 
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        StringBuilder sb = new StringBuilder();
        backtrack(res, sb, 0, 0, n);
        return res;
    }

    public void backtrack(List<String> res, StringBuilder sb, int left, int right, int n) {
        if (left < right) {
            return;
        }
        if (left > n || right > n) {
            return;
        }
        if (left == n && right == n) {
            res.add(sb.toString());
            return;
        }
        sb.append('(');
        backtrack(res, sb, left + 1, right, n);
        sb.deleteCharAt(sb.length() - 1);
        sb.append(')');
        backtrack(res, sb, left, right + 1, n);
        sb.deleteCharAt(sb.length() - 1);
    }
}
