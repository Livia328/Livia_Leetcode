import java.util.*;

public class Q246 {
    /*
     * 只有几对是可以的，用map存
     * 69
     * 96
     * 00
     * 11
     * 88
     * 
     * 双指针从两边到中间
     * 如果不在map里，直接false
     * 在map里，num[l]和num[r]是不是对应的
     */
    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = new HashMap<>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        int L = 0, R = num.length() - 1;
        while (L <= R) {
            if (!map.containsKey(num.charAt(L))) {
                return false;
            }
            if (map.get(num.charAt(L)) != num.charAt(R)) {
                return false;
            }
            L++;
            R--;
        }
        return true;
    }
}
