import java.util.*;

public class Q953 {
    /*
     * 两两比较words中的数，看是否满足order
     * 
     * 比较：
     * 逐位比较
     * 
     * O(m×n)，其中 m 为字符串数组的长度，n 为数组中字符串的平均长度，每个字符串需要前一个字符串进行比较，因此时间复杂度为 O(m×n)。
     * 空间复杂度O（26）
     */
    static Map<Character, Integer> map;
    public static boolean isAlienSorted1(String[] words, String order) {
        if (words.length <= 1) {
            return true;
        }
        //build a map, key letter, value index
        map = new HashMap();
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }
        for (int i = 1; i < words.length; i++) {
            String prv = words[i - 1];
            String cur = words[i];
            if (!check(prv, cur)) {
                return false;
            }
        }
        return true;
    }

    public static boolean check(String a, String b) {
        int index = 0;
        while (index < a.length() && index < b.length()) {
            if (map.get(a.charAt(index)) < map.get(b.charAt(index))) {
                return true;
            }
            if (map.get(a.charAt(index)) > map.get(b.charAt(index))) {
                return false;
            }
            index++;
        }
        // 如果一个结束了，比较长短
        return a.length() <= b.length();
    }

    /*
     * 自定义排序，如果排序后和原来一样，那么就说明符合
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(logn)
     */
    public static boolean isAlienSorted(String[] words, String order) {
        int[] ord = new int[26];
        for (int i = 0; i < 26; i++) {
            ord[order.charAt(i) - 'a'] = i;
        }
        String[] clone = words.clone();
        Arrays.sort(clone, (a, b)->{
            int n = a.length(), m = b.length();
            int index = 0;
            while (index < n && index < m) {
                // 找到第一个不一样的字母
                int c1 = a.charAt(index) - 'a', c2 = b.charAt(index) - 'a';
                if (c1 != c2) {
                    return ord[c1] - ord[c2];
                }
                index++;
            }
            // 此时n还有剩下的，说明n比较长
            if (index < n) return 1;
            // 此时m还有剩下的，说明m比较长
            if (index < m) return -1;
            return 0;
        });
        int n = words.length;
        for (int i = 0; i < n; i++) {
            if (!clone[i].equals(words[i])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] words = {"word","world","row"};
        String order = "worldabcefghijkmnpqstuvxyz";
        boolean flag = isAlienSorted(words, order);
    }
}
