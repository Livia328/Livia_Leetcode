import java.util.*;

public class Q76 {
    /*
     * sliding window
     * 先计算出need窗口map
     * 
     * 然后再slidingwindow
     * 如果char cur在t中，那么就+1
     * 用一个数track matched
     * 
     * 如果matched == need.size
     * 那么缩小左边界
     */
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        Map<Character, Integer> window = new HashMap<>();
        int L = 0, R = 0;
        int matched = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0, end = 0;
        while (R < s.length()) {
            char c = s.charAt(R);
            R++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                // check match
                // 不知道为啥这里用==会过不了，只能用equals
                if (window.get(c).equals(need.get(c))) {
                    matched++;
                }
            }
            /*
             * 当match == need.size的时候
             * 都可以缩小左边界去看看是否有更短的
             */
            while (matched == need.size()) {
                // 先更新答案
                if (R - L < minLen) {
                    minLen = R - L;
                    start = L;
                    end = R;
                }
                char d = s.charAt(L);
                L++;
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        matched--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, end);
    }
}
