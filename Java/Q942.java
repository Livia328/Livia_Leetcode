public class Q942 {
    /*
     * 贪心，如果是I，那么把当钱未0
     * 如果为D，那么把当前变成n，以此类推
     */
    public int[] diStringMatch(String s) {
        int n = s.length(), low = 0, high = n;
        int[] res = new int[n + 1];
        for (int i = 0; i < n; i++) {
            res[i] = s.charAt(i) == 'I' ? low++ : high--;
        }
        res[n] = low;
        return res;
    }
}
