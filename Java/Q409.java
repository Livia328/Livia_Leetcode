import java.util.*;

public class Q409 {
    /*
     * 理解一下题目意思，可以rebuild
     * 先hashmap计数
     * 
     * 如果是偶数，可以直接计入答案，因为两边对称着放就行
     * 如果是奇数，减一变成偶数计入答案
     * 
     * 同时要记录整个过程中有没有出现过奇数
     * 如果有，最后加1，因为最中间可以有一个奇数
     */
    public int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int res = 0;
        boolean hasOdd = false;
        for (Map.Entry<Character, Integer> e : map.entrySet()) {
            if (e.getValue() % 2 == 0) {
                res += e.getValue();
            } else {
                res += e.getValue() - 1;
                hasOdd = true;
            }
        }
        if (hasOdd) {
            res += 1;
        }
        return res;
    }
}
