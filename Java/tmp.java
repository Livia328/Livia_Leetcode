import java.util.*;

public class tmp {
    /*
     * 按照开始时间排序
     * List<int[]> 放前一个时间
     * 每次和List里的最后一个比较，更新
     */
    public int[][] merge(int[][] intervals) {
        // sort by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> tmp = new ArrayList<>();
        tmp.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            int[] prv = tmp.get(tmp.size() - 1);
            if (cur[0] <= prv[1]) {
                // can be merged
                int newEnd = Math.max(cur[1], prv[1]);
                tmp.get(tmp.size() - 1)[1] = newEnd;
            } else {
                tmp.add(cur);
            }
        }
        int[][] res = new int[tmp.size()][2];
        for (int i = 0; i < tmp.size(); i++) {
            res[i] = tmp.get(i);
        }
        return res;
    }
        StringBuilder sb = new StringBuilder();
        int[] targetFreq = new int[26];
        for (char c : target.toCharArray()) {
            if (stickerFreq[c - 'a'] > targetFreq[c - 'a']) {
                targetFreq[c - 'a']++;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
}
