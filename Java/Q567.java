import java.util.*;

public class Q567 {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            need.put(s1.charAt(i), need.getOrDefault(s1.charAt(i), 0) + 1);
        }
        int L = 0, R = 0;
        Map<Character, Integer> window = new HashMap<>();
        int match = 0;
        while (R < s2.length()) {
            char c = s2.charAt(R);
            R++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) {
                    match++;
                }
            }
            while (R - L > s1.length()) {
                char d = s2.charAt(L);
                L++;
                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.get(d))) {
                        match--;
                    }
                    window.put(d, window.get(d) - 1);  
                }
            }
            if (need.size() == match) {
                return true;
            }
        }
        return false;

    }
}
