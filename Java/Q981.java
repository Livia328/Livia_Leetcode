import java.util.*;

public class Q981 {
    /**
     * 首先需要存同一个key的不同value，所以肯定是需要map来存的
     * Map<String, List<int[]>()>
     * key: key
     * value: list of int[], representing a pair of the timestamp and value int[timestamp, value]
     */
    class TimeMap {

        class Pair {
            String key;
            String value;
            int timestamp;
            public Pair(String key, String value, int timestamp) {
                this.key = key;
                this.value = value;
                this.timestamp = timestamp;
            }
        }

        Map<String, List<Pair>> database;

        public TimeMap() {
            database = new HashMap<>();
        }
        
        public void set(String key, String value, int timestamp) {
            if (!database.containsKey(key)) {
                database.put(key, new ArrayList<>());
            }
            Pair cur = new Pair(key, value, timestamp);
            database.get(key).add(cur);
        }
        
        public String get(String key, int timestamp) {
            List<Pair> list = database.getOrDefault(key, new ArrayList<>());
            if (list.isEmpty()) {
                return "";
            }
            // using binary search find the nearest
            // 可以这么做的原因是因为timestamp一直是递增的
            // 寻找右边界？因为要找比timestamp小的最大值
            int n = list.size();
            int L = 0, R = n - 1;
            while (L <= R) {
                int M = L + (R - L) / 2;
                // 搜索右半边，缩小左边界
                if (list.get(M).timestamp < timestamp) {
                    L = M + 1;
                } else if (list.get(M).timestamp > timestamp) {
                    // 搜索左半边，缩小右边界
                    R = M - 1;
                } else if (list.get(M).timestamp == timestamp) {
                    // 因为我们要找右边界
                    L = M + 1;
                }
            }
            // 最后返回的应该是L-1
            if (L - 1 < 0) return "";
            return list.get(L - 1).timestamp <= timestamp ? list.get(L - 1).value : "";
        }
    }
}
