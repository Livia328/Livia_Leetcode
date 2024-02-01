import java.util.*;

public class Q56 {
    /**
     * sort according to start time
     * [1,3],[2,6],[8,10],[15,18]
     * 
     * compare a[1] >= b[0]: merge
     * update the end time of a, Math.max(a[1], b[1])
     * 
     * 
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null) {
            return new int[][]{};
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            // compare
            if (intervals[i][0] <= res.get(res.size() - 1)[1]) {
                // merge
                res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], intervals[i][1]);
            } else {
                res.add(intervals[i]);
            }
        }
        int n = res.size();
        int[][] ans = new int[n][2];
        for (int i = 0; i < n; i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
