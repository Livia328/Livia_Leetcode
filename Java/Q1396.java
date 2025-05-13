import java.util.*;
public class Q1396 {
    /*
     * 我们可以有一个average map
     * key是startStation - endStation
     * value是{所有时间sum， 有多少个人经过}
     * 
     * 这样当要getAverageTime
     * 直接拿出来，用所有时间sum/有多少个人经过即可
     * 
     * 还需要知道用户和他checkin的站和时间
     * 用另一个map
     * key是用户ID，value是{checkin Station, timestamp}
     * 
     * 这样flow就是：
     * 有用户checkin的时候，更新checkin表，记录checkin的station和time
     * 有用户checkout的时候，拿到用户checkin的时间，更新average表
     * 
     */
    class UndergroundSystem {

        public class TimeNode {
            public String startStationName;
            public int time;
            TimeNode(String startStationName, int time) {
                this.startStationName = startStationName;
                this.time = time;
            }
        }

        // 用来存avg值，key是startStation@endStation, value: 是{所有时间sum， 有多少个人经过}
        Map<String, Long[]> avgMap;
        // key是用户ID，value是{checkin Station, timestamp}
        Map<Integer, TimeNode> startMap;
        public UndergroundSystem() {
            avgMap = new HashMap<>();
            startMap = new HashMap<>(); 
        }
        
        public void checkIn(int id, String stationName, int t) {
            startMap.put(id, new TimeNode(stationName, t));
        }
        
        public void checkOut(int id, String stationName, int t) {
            TimeNode node = startMap.get(id);
            startMap.remove(id);
            String startStation = node.startStationName;
            String endStation = stationName;
            String key = startStation+"@"+endStation;
            Long[] record = avgMap.getOrDefault(key, new Long[]{0L,0L});
            record[0] += t-node.time;
            record[1]++;
            avgMap.put(key, record);
        }
        
        public double getAverageTime(String startStation, String endStation) {
            String key = startStation+"@"+endStation;
            Long[] record = avgMap.get(key);
            return ((double)(record[0]))/record[1];
        }
    }
}
