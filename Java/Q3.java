import java.util.*;


public class Q3 {
    public static int lengthOfLongestSubstring(String s) {
        int L = 0, R = 0;
        HashMap<Character, Integer> window = new HashMap<>();
        int res = 0;
        while (R < s.length()) {
            char c = s.charAt(R);
            window.put(c, window.getOrDefault(c, 0) + 1);
            R++;
            // shrink the left bound
            while (window.get(c) > 1) {
                char d = s.charAt(L);
                window.put(d, window.get(d) - 1);
                L++;
            }
            res = Math.max(res, R - L);
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
