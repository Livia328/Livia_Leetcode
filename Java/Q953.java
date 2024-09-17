import java.util.*;

public class Q953 {
    static Map<Character, Integer> map;
    public static boolean isAlienSorted(String[] words, String order) {
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
            System.out.println(check(prv, cur));
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
            } else if (map.get(a.charAt(index)) > map.get(b.charAt(index))) {
                return false;
            }
            index++;
        }
        // 如果一个结束了，比较长短
        return a.length() <= b.length();
    }

    public static void main(String[] args) {
        String[] words = {"word","world","row"};
        String order = "worldabcefghijkmnpqstuvxyz";
        boolean flag = isAlienSorted(words, order);
    }
}
