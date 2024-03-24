import java.util.*;

public class Q131 {
    /**
     * 如果s[0...i]是回文，就把它加到track中
     */
    List<List<String>> res = new LinkedList<>();
    List<String> path = new LinkedList<>();
    public List<List<String>> partition(String s) {
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
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
