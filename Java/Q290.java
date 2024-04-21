import java.util.*;

public class Q290 {
    /**
     * pattern = "abba", s = "dog cat cat dog"
     * 
     * 
     * hashmap:
     * k: v
     * a: dog
     * b: cat
     * 
     * 如果应该是a的地方，有了一个新的词，那么说明就不对了
     * 
     * 
     * 可以有一个set来检查这个词是不是已经有对应的词了
     */
    public boolean wordPattern(String pattern, String s) {
        String[] ss = s.split(" ");
        // corner case
        if (ss.length != pattern.length()) {
            return false;
        }
        // hashmap, Key: pattern letter, Value: workds
        Map<Character, String> map = new HashMap<>();
        // set, all words that already has a pattern
        Set<String> set = new HashSet<>();
        for (int i = 0; i < pattern.length(); i++) {
            char p = pattern.charAt(i);
            String w = ss[i];
            // 这个pattern还没出现过
            if (!map.containsKey(p)) {
                // 如果这个单词已经有了其他的映射，返回false
                if (set.contains(w)) {
                    return false;
                }
                // 添加映射
                map.put(p, w);
                set.add(w);
            } else {
                // 检查pattern
                // 如果map里对应的不是这个word，返回false
                if (!map.get(p).equals(w)) {
                    return false;
                }
            }

        }
        return true;
    }
}
