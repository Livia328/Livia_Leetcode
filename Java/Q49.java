package Java;

import java.util.*;

public class Q49 {
    public List<List<String>> groupAnagrams1(String[] strs) {
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

        /*
     * 最后生成的list答案顺序有要求吗？
     * 
     * 因为如果是anagram后
     * sort后的string应该是一样的
     * 可以根据这个作为key然后排序
     * 
     * 同时也可以用一个array来记录每个字母的个数
     * eat生成a1e1t1这样的key
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            // generate key
            String key = generateKey(s);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        // 所有的map list就是答案
        List<List<String>> res = new ArrayList<>();
        for (String key : map.keySet()) {
            res.add(map.get(key));
        }
        return res;
    }

    public String generateKey(String s) {
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 26; i++) {
            if (arr[i] != 0) {
                sb.append('a' + i).append(arr[i]);
            }
        }
        return sb.toString();
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
