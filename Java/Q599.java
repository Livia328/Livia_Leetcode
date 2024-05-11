import java.util.*;

public class Q599 {
    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            if (!map1.containsKey(list1[i])) {
                // 只要放第一个就可以，因为我们要算index sum最小，如果重复出现，肯定是取第一个index
                map1.put(list1[i], i);
            }
        }
        for (int i = 0; i < list2.length; i++) {
            if (!map2.containsKey(list2[i])) {
                // 只要放第一个就可以，因为我们要算index sum最小，如果重复出现，肯定是取第一个index
                map2.put(list2[i], i);
            }
        }
        List<String> res = new ArrayList<>();
        int indexMin = Integer.MAX_VALUE;
        for (String key : map1.keySet()) {
            if (map2.containsKey(key)) {
                int curIndex = map1.get(key) + map2.get(key);
                if (curIndex == indexMin) {
                    res.add(key);
                } else if (curIndex < indexMin) {
                    res.clear();
                    res.add(key);
                    indexMin = curIndex;
                }
            }
        }
        String[] ans = res.toArray(new String[0]);
        return ans;
    }
}
