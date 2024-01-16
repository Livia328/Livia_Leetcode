package Java;

import java.util.*;

class Q424 {
    public int characterReplacement(String s, int k) {
        int L = 0, R = 0;
        int curMaxCount = 0;
        int res = 0;
        Map<Character, Integer> window = new HashMap<>();
        while (R < s.length()) {
            char c = s.charAt(R);
            R++;
            window.put(c, window.getOrDefault(c, 0) + 1);
            curMaxCount = Math.max(curMaxCount, window.get(c));
            while (curMaxCount + k < R - L) {
                char d = s.charAt(L);
                window.put(d, window.get(d) - 1);
                L++;
            }
            res = Math.max(res, R - L);
        }
        return res;
    }
}