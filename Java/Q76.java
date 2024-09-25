import java.util.*;

public class Q76 {
    public String minWindow(String s, String t) {
        // count frequency of t
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
        }
        // start sliding window
        int L = 0, R = 0;
        Map<Character, Integer> window = new HashMap<>();
        int match = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0, end = 0;
        while (R < s.length()) {
            char c = s.charAt(R);
            R++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) {
                    match++;
                }
            }
            // shrink left boundary to get better answer
            while  (match == need.size()) {
                if (R - L < minLen) {
                    minLen = R - L;
                    start = L;
                    end = R;
                }
                char d = s.charAt(L);
                L++;
                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.get(d))) {
                        match--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
