import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q1331 {
    /*
     * clone array后排序
     * 用hashmap记录val和rank的映射
     * 
     * map, key: val, value: rank
     */
    public int[] arrayRankTransform(int[] arr) {
        int[] clone = arr.clone();
        Arrays.sort(clone);
        Map<Integer, Integer> map = new HashMap<>();
        int n = arr.length, rank = 1;
        for (int curNum : clone) {
            if (!map.containsKey(curNum)) {
                map.put(curNum, rank++);
            }
        }
        int res[] = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = map.get(arr[i]);
        }
        return res;
    }
}
