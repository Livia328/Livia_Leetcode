public class Q1011 {
    /*
     * [max(weights), sum(weights)]
     * l r
     * 
     * getDays -> return how many days we need
     * 
     * if getDays(cur) < days -> shrink the right boundary
     * if getDays(cur) > days -> shrink the left boundary
     * if getDays(cur) == days -> shrink the right boundary
     */
    public int shipWithinDays(int[] weights, int days) {
        int l = 0, r = 0;
        for (int w : weights) {
            l = Math.max(w, l);
            r += w;
        }
        // [l, r], [l, l - 1]
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (getDays(m, weights) <= days) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    public int getDays(int cap, int[] weights) {
        int sum = 0;
        int count = 1;
        for (int w : weights) {
            if (sum + w > cap) {
                count++;
                sum = 0;
            }
            sum += w;
        }
        return count;
    }
}
