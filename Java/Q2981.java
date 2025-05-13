import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Q2981 {
    /*
     * brute force
     * 遍历所有的substring存在map中
     * 
     * key: string, val: count
     * 然后判断count有没有大于3
     */
    public int maximumLength(String s) {
        // key: string, val: count
        HashMap<String, Integer> map = new HashMap<>();
        // 得到所有重复出现连续string
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String subString = s.substring(i, j);
                if (isSpecial(subString)) {
                    map.put(subString, map.getOrDefault(subString, 0) + 1);
                }
            }
        }
        int res = -1;
        for (String key : map.keySet()) {
            if (map.get(key) >= 3) {
                res = Math.max(res, key.length());
            }
        }
        return res;
    }

    public boolean isSpecial(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        return set.size() == 1;
    }
}
