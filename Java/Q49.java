package Java;

import java.util.*;

public class Q49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            // 一定不要用to string， 这样是它的地址
            // String key = arr.toString();
            String key = Arrays.toString(arr);
            // System.out.println(key);

            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(s);
        }

        List<List<String>> ans = new ArrayList<>();
        for (String key : map.keySet()) {
            ans.add(map.get(key));
        }
        return ans;
    }

    public static void main(String[] args) {
        Q49 q49 = new Q49();
        String[] test1 = {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> ans1 = q49.groupAnagrams(test1);
        for (List<String> item : ans1) {
            System.out.println(item.toString());
        }
    }
}
