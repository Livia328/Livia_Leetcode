public class Q495 {
    /*
     * 因为中毒状态不能叠加，所以我们要判断上一次攻击有没有失效
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int res = 0;
        for (int i = 1; i < timeSeries.length; i++) {
            // 两次攻击之间的间隔
            int d = timeSeries[i] - timeSeries[i - 1];
            if (d <= duration) {
                // 攻击时间没有很长
                // 那么就加不到duration，只能加d
                res += d;
            } else {
                res += duration;
            }
        }
        // 要把最后一次的给算上
        return res + duration;
    }
}
