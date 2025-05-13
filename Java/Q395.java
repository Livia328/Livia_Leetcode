import java.util.*;

public class Q395 {
    /*
     * subSrting中的每个字母count都要大于k
     * 也就是说如果有一个字母的count小于k
     * 那么含有这个字母的substring都不是答案
     * 
     * 我们可以以这个字母为边界将string分为几段
     * 然后再进行recursion
     * 
     * 比如aaabcccbddd，k = 3
     * b只出现了两次
     * 
     * 将他们分为aaa, ccc, ddd
     * 
     * 用map来计算frequency
     * key: character, value: count
     */
    public int longestSubstring(String s, int k) {
        // base case
        if (s.length() < k) {
            return 0;
        }
        // 得到frequency
        // key: character, value: frequency
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // 开始分治
        for (char key : map.keySet()) {
            if (map.get(key) < k) {
                int res = 0;
                // 所有包含key的字符串都不和要求
                // 寻找key两遍的字母串有没有符合要求的
                for (String next : s.split(String.valueOf(key))) {
                    res = Math.max(res, longestSubstring(next, k));
                }
                return res;
            }
        }
        // 在这之中没有返回的话，说明整串都行
        return s.length();
    }
}
