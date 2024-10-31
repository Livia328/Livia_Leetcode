import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Q539 {
    /*
     * 先都转换成时间，再排序，再俩俩相减
     * 注意不要忘了比较首位时间因为可能跨天
     * 比如0:00   12:00   23:59这样的
     */
    public int findMinDifference1(List<String> timePoints) {
        int n = timePoints.size();
        if (n > 1440) {
            return 0;
        }
        int[] minutes = new int[n];
        for (int i = 0; i < n; i++) {
            String cur = timePoints.get(i);
            minutes[i] = Integer.parseInt(cur.substring(0,2)) * 60 + Integer.parseInt(cur.substring(3));
        }
        Arrays.sort(minutes);
        int mindiff = 1440;
        for (int i = 1; i < n; i++) {
            mindiff = Math.min(mindiff, minutes[i] - minutes[i-1]);
            if(mindiff == 0) {
                return 0;
            }
        }
        // 最后比较首尾时间，因为可能跨天
        // 比如0:00   12:00   23:59这样的
        mindiff = Math.min(mindiff, 1440 - minutes[n-1] + minutes[0]);
        return mindiff;
    }

    /*
     * 计数
     * 因为一天最多只可能有1440个时间节点，如果有一个时间出现过两次，diff肯定是0
     * 否则寻找最近两个不为0，相减即可
     */
    public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size();
        int[] map = new int[1440];
        for (int i = 0; i < n; i++) {
            String cur = timePoints.get(i);
            int time = Integer.parseInt(cur.substring(0,2)) * 60 + Integer.parseInt(cur.substring(3));
            if (map[time] > 0) {
                return 0;
            }
            map[time]++;
        }
        int minDiff = 1440, pre = -1, first = -1; //first是第一个数的坐标
        for (int i = 0; i < 1440; i++) {
            if (map[i] == 1) {
                if (pre != -1) {
                    minDiff = Math.min(i - pre, minDiff);
                } else {
                    first = i;
                }
                pre = i;
            }
        }
        // 比较首尾
        minDiff = Math.min(minDiff, 1440 - pre + first);
        return minDiff;
    }
}
