import java.util.*;

public class Q2402 {
    /*
     * 根据题目要求：
     * 我们需要知道此时空的会议室中，编号最小的会议室（这样知道接下来要放进哪个会议室）
     * 还要知道所有正在进行中的会议（结束时间，和会议室编号），需要支持获得结束时间最早且会议室编号最小的会议。
     * 
     * 两个priorityQueue
     * PQ:meetingRoom
     * PQ:meetingInProgress
     * 
     * 每次遍历meeting cur[start, end]:
     * 1. 检查meetingInProgress，如果peek().endTime <= cur.start
     *     -> peek已经结束，poll, 并将这个peek.meetingRoom加入meetingRoom
     *    while loop，直到meetingInProgress为空，或者meetingInProgress.endTime > cur.start
     * 2.为这个meeting安排会议室
     *   2.1 如果meetingRoom里有元素就直接用
     *   2.2 如果meetingRoom.size == 0说明需要延期
     * 
     *       
     */
    public int mostBooked(int n, int[][] meetings) {
        // 根据开始时间来排序
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        // 用来数每个会议室排了多少会议
        int[] count = new int[n];
        // 所有可用的会议室
        PriorityQueue<Long> meetingRoom = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            meetingRoom.add((long)i);
        }
        // int[endTime, index]
        PriorityQueue<long[]> meetingInProgress = new PriorityQueue<>((a, b) -> {
            // 返回结束时间早的
            if (a[0] != b[0]) {
                return Long.compare(a[0], b[0]);
            }
            // 返回会议室小的
            return Long.compare(a[1], b[1]);
        });
        for (int[] meeting : meetings) {
            long start = meeting[0];
            long end = meeting[1];
            long duration = end - start;
            while (!meetingInProgress.isEmpty() && meetingInProgress.peek()[0] <= meeting[0]) {
                long[] poll = meetingInProgress.poll();
                // 释放会议室
                meetingRoom.add(poll[1]);
            }
            if (meetingRoom.isEmpty()) {
                long[] firstFinish = meetingInProgress.poll();
                long finishTime = firstFinish[0];
                long meetingRoomRelease = firstFinish[1];
                // delay
                start = finishTime;
                end = finishTime + duration;
                meetingRoom.add(meetingRoomRelease);
            }
            // 使用会议室
            long roomToUse = meetingRoom.poll();
            count[(int)roomToUse]++;
            meetingInProgress.add(new long[] {end, roomToUse});
        }
        int resIndex = 0; int max = count[resIndex];
        for (int i = 1; i < n; i++) {
            if (count[i] > max) {
                max = count[i];
                resIndex = i;
            }
        }
        return resIndex;
    }
}
