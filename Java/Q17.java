import java.util.*;

public class Q17 {
    /*
     * backtracking
     * 对于每一个数都可以一个个选过去
     * candidiate就是这个数字对应的字母
     * 
     * Input: digits = "23"
     *           []
     *     [a]    [b]    [c]
     * [ad] [ae]
     */

    List<String> res = new ArrayList<>();
    StringBuilder path = new StringBuilder();
    String[] mapping = new String[] {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return res;
        }
        backtrack(digits, 0);
        return res;
    }

    public void backtrack(String s, int start) {
        // base case
        if (start == s.length()) {
            res.add(path.toString());
            return;
        }
        // 选择
        String candidates = mapping[s.charAt(start) - '0'];
        for (char c : candidates.toCharArray()) {
            path.append(c);
            backtrack(s, start + 1);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
