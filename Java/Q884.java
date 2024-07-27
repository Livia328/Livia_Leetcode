import java.util.*;

public class Q884 {
    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        String[] arr1 = s1.split(" "); String[] arr2 = s2.split(" ");
        for (String s : arr1) {
            map1.put(s, map1.getOrDefault(s, 0) + 1);
        }
        for (String s : arr2) {
            map2.put(s, map2.getOrDefault(s, 0) + 1);
        }
        List<String> list = new LinkedList<>();
        for (String key : map1.keySet()) {
            if (map1.get(key) == 1 && !map2.containsKey(key)) {
                list.add(key);
            }
        }
        for (String key : map2.keySet()) {
            if (map2.get(key) == 1 && !map1.containsKey(key)) {
                list.add(key);
            }
        }
        return list.toArray(new String[0]);
    }
}
