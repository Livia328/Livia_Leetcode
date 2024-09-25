import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * Given an array of meeting time intervals intervals where 
 * intervals[i] = [starti, endi], 
 * return the minimum number of conference rooms required.
 * 
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 */
public class Q253 {
    /*
     * 先按照开始时间排序
     * 比较prv[end] < cur[start] -> 可以合并
     * 
     * -> 因为涉及到合并的问题，所以感觉需要一个Pq
     * pq按照结束时间排序，pq.peek是结束时间最早的会议室
     * 这样每次将当前的和pq.peek比较就可以
     */
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        // 里面放的是结束时间
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        int res = 0;
        for (int i = 0; i < intervals.length; i++) {
            // 如果pq peek的结束时间小于当前会议的开始时间
            // 说明pq.peek可以和当前会议室用一个
            if (!pq.isEmpty() && pq.peek() <= intervals[i][0]) {
                pq.poll();
            }
            pq.add(intervals[i][1]);
            // 要记录全局过程中最多的一次
            res = Math.max(res,pq.size());
        }
        return res;
    }
}
