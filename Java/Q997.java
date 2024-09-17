public class Q997 {
    public int findJudge(int n, int[][] trust) {
        int[] count = new int[n + 1];
        for (int[] cur : trust) {
            count[cur[0]]--;
            count[cur[1]]++;
        }
        for (int i = 1; i <= n; i++) {
            if (count[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }
}
