import java.util.*;

public class Q1941 {
    /*
     * 用hashmap计算每个字母的频率
     * 
     * 计算理论上每个数出现的频率
     * 看是不是一样的
     * 
     * s.length / map.count
     */
    public boolean areOccurrencesEqual(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int freq = s.length() / map.size();
        for (int val : map.values()) {
            if (val != freq) {
                return false;
            }
        }
        return true;
    }
}
