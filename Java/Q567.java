package Java;

import java.util.*;

public class Q567 {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            need.put(s1.charAt(i), need.getOrDefault(s1.charAt(i), 0) + 1);
        }
        Map<Character, Integer> window = new HashMap<>();
        int L = 0, R = 0;
        int match = 0;
        while (R < s2.length()) {
            char c = s2.charAt(R);
            R++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    match++;
                }
            }
            while (R - L == s1.length()) {
                if (match == need.size()) {
                    return true;
                }
                char d = s2.charAt(L);
                L++;
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        match--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
            
        }
        return false;
    }
}
