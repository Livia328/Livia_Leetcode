import java.util.*;

public class Q131 {
    /**
     * backtrack + memo
     * 如果s[0...i]是回文，就把它加到track中
     * 
     *                aab
     *         a/     |aa   \aab(x)
     *      ab         b       empty
     *    a/  \ab(x)   |b
     *   b             null
     *   |b
     *   null
     */
    List<List<String>> res = new LinkedList<>();
    List<String> path = new LinkedList<>();
    // memo[i][j]表示s[i..j]是不是回文串
    int[][] memo;
    public List<List<String>> partition(String s) {
        memo = new int[s.length()][s.length()];
        backtrack(s, 0);
        return res;
    }

    public void backtrack(String s, int index) {
        if (index == s.length()) {
            res.add(new ArrayList<String>(path));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            // 不是回文串，就不是
            if (!isPalindrome(s, index, i)) {
                continue;
            }
            // s[index,i]是回文串,分割
            path.add(s.substring(index, i + 1));
            backtrack(s, i + 1);
            path.remove(path.size() - 1);
        }
    }

    public boolean isPalindrome(String s, int start, int end) {
        if (memo[start][end] != 0) {
            return memo[start][end] == 1 ? true : false;
        }
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                memo[start][end] = -1;
                return false;
            }
            start++;
            end--;
        }
        memo[start][end] = 1;
        return true;
    }
}
