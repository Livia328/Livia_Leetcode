import java.util.*;

public class Q389 {
    /**
     * hashmap
     */
    public char findTheDifference1(String s, String t) {
        Map<Character, Integer> mapS = new HashMap<>();
        Map<Character, Integer> mapT = new HashMap<>();
        for (char c : s.toCharArray()) {
            mapS.put(c, mapS.getOrDefault(c, 0) + 1);
        }
        for (char c : t.toCharArray()) {
            mapT.put(c, mapT.getOrDefault(c, 0) + 1);
        }
        for (char key : mapT.keySet()) {
            if (!mapS.containsKey(key)) {
                return key;
            }
            if (mapS.get(key) != mapT.get(key)) {
                return key;
            }
        }
        return 'a';
    }

    /**
     * 全部求和再做减法
     */
    public char findTheDifference(String s, String t) {
        int sumS = 0, sumT = 0;
        for (char c : s.toCharArray()) {
            sumS += c;
        }
        for (char c : t.toCharArray()) {
            sumT += c;
        }
        return (char)(sumT - sumS);
    }
}
