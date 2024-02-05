import java.util.*;

public class Q981 {
    /**
     * 不用二分 + Map<String, List<String[]>> db;
     * 会TLE
     * 
     * to store the key and the value and timestamp
     * Map<key, [timestamp, value]> -> Map<String, List<String[]>> 
     * 
     * key: String, timestamp: int, value: String
     * -> convert the timestamp to string, store in the map
     */
    class TimeMap {
        Map<String, List<String[]>> db;

        public TimeMap() {
            db = new HashMap<>();
        }
        
        public void set(String key, String value, int timestamp) {
            if (!db.containsKey(key)) {
                db.put(key, new ArrayList<>());
            }
            String[] pair = {String.valueOf(timestamp), value};
            db.get(key).add(pair);
        }
        
        public String get(String key, int timestamp) {
            if (!db.containsKey(key)) {
                return "";
            }
            List<String[]> list = db.get(key);
            // find the biggest time while time < timestamp
            int index = 0;
            while (index < list.size()){
                // equals, move forward, to see if there are bigger
                if (Integer.valueOf(list.get(index)[0]) > timestamp) {
                    break;
                }
                index++;
            }
            index = Math.max(index - 1, 0);
            return Integer.valueOf(list.get(index)[0]) <= timestamp ? list.get(index)[1] : "";
        }
    }

    /**
     * 二分+List<String[]
     */
    class TimeMap3 {
        Map<String, List<String[]>> db;

        public TimeMap3() {
            db = new HashMap<>();
        }
        
        public void set(String key, String value, int timestamp) {
            if (!db.containsKey(key)) {
                db.put(key, new ArrayList<>());
            }
            String[] pair = {String.valueOf(timestamp), value};
            db.get(key).add(pair);
        }
        
        public String get(String key, int timestamp) {
            if (!db.containsKey(key)) {
                return "";
            }
            List<String[]> list = db.get(key);
            // find the biggest time while time < timestamp
            // the right boundary
            int L = 0, R = list.size() - 1;
            while (L <= R) {
                int M = L + (R - L) / 2;
                int curTime = Integer.valueOf(list.get(M)[0]);
                if (curTime > timestamp) {
                    R = M - 1;
                } else if (curTime < timestamp) {
                    L = M + 1;
                } else if (curTime == timestamp){
                    // right bound, so shrink the left boundary to see if there are bigger
                    L = M + 1;
                }
            }
            // could not find
            if (L - 1 < 0) return "";
            return Integer.valueOf(list.get(L - 1)[0]) <= timestamp ? list.get(L - 1)[1] : "";
        }
    }




    /**
     * 首先需要存同一个key的不同value，所以肯定是需要map来存的
     * Map<String, List<int[]>()>
     * key: key
     * value: list of int[], representing a pair of the timestamp and value int[timestamp, value]
     */
    class TimeMap2 {

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

        public TimeMap2() {
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
