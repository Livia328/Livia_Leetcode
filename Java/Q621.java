import java.util.Arrays;

public class Q621 {
    /**
     * 贪心，优先排出现最多的，让他们的间隔正好是cold interview
     * 然后再在中间排别的任务
     * 
     * 参考答案：https://leetcode.cn/problems/task-scheduler/solutions/385952/tian-tong-si-lu-you-tu-kan-wan-jiu-dong-by-mei-jia/
     */
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            count[tasks[i] - 'A']++;
        }
        Arrays.sort(count);
        int maxCount = count[25];
        for (int c : count) {
            maxCount = Math.max(maxCount, c);
        }
        // 有几个是最大值
        // 决定了最后一行有几个
        int lastLine = 0;
        for (int c : count) {
            if (c == maxCount) {
                lastLine++;
            }
        }
        // 如果每一行都超过了n+1，那就和冷冻时间无关了，就是tasks.length的答案
        return Math.max((n + 1) * (maxCount - 1) + lastLine, tasks.length);
    }
}