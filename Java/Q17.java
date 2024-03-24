import java.util.*;

public class Q17 {
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
        // base case, add ans
        if (path.length() == s.length()) {
            res.add(path.toString());
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String candidates = mapping[s.charAt(i) - '0'];
            for (char c : candidates.toCharArray()) {
                path.append(c);
                backtrack(s, i + 1);
                path.deleteCharAt(path.length()- 1);
            }
        }
    }
}
