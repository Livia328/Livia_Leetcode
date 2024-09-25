import java.util.*;

public class Q1335 {
    /*
     * 分析：
     * 将问题抽象成，有一个整数数组，你要将其分成D段
     * 要确保每段的最大值加起来最小
     * 
     * 比如将a,b,c,d分成2段
     * a | b,c,d
     * a,b|c,d
     * a,b,c,| d
     * 
     * 可以是recursion
     * helper function的定义：返回将arr分成d段的最小值，
     * recursive rule: 第一天分了x份工作，接下来只要球n -x的job，recursion(index), d - 1)即可
     * 
     * 因为有依赖顺序，所以直接传入start index即可
     * 定义函数dfs(int i, int d), 表示将jobDifficulty[i, n]划分为d份
     */
    int[][] memo; //memo[i][j]表示[0..i]个任务，分割成J天
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }
        memo = new int[n][d];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);//表示还没计算过
        }
        return dfs(jobDifficulty, n - 1, d - 1);
    }

    // 求的是分割[0...end]在D天
    public int dfs(int[] jobDifficulty, int end, int d) {
        // 查看memo
        if(memo[end][d] != -1) {
            return memo[end][d];
        }
        // 初始状态，只有一天，那么所有任务都要在那天做完
        // 答案就是最大值
        if (d == 0) {
            int max = 0;
            // 因为任务比天数小肯定就没法分割
            for (int i = 0; i <= end; i++) {
                max = Math.max(max, jobDifficulty[i]);
            }
            memo[end][d] = max;
            return memo[end][d];
        }
        int res = Integer.MAX_VALUE;
        int max = 0;
        // 将数组分成[0...newEnd | newEnd + 1...end]两段
        // 搜寻[0...newEnd],每搜寻一次，更新res
        for (int newEnd = end; newEnd >= d; newEnd--) {
            // 在往前移动的时候不断更新[newEnd + 1...end]中最大的
            max = Math.max(max, jobDifficulty[newEnd]);
            res = Math.min(res, max +dfs(jobDifficulty, newEnd - 1, d - 1));
        }
        memo[end][d] = res;
        return res;
    }

    /* -> dp老套路了
     * dp[i][j]表示将jobDifficulty[0, i]划分成j份的最小max difficulty
     * 我们的目标就是dp[n][d]
     * 
     * 初始状态：当j == 1的时候dp[i][j] = max(jobDifficulty[0 : i])
     * 状态转移，当我们已知j天划分了x工作，剩下的i - x份工作要划分成j - 1份, which is dp[i - x][j - 1]
     * 
     * dp = min(dp[i - x][j - 1], )
     * i - x >= 0 且 i - x >= j - 1 => x <= i - j  + 1
     */
    
    public int minDifficulty2(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }   
        int[][] dp = new int[n + 1][d + 1];
        // 初始状态
        int curMax = jobDifficulty[0]; // 用来看jobDiffculty[0, i]之间最大的
        for (int i = 1; i <= n; i++) {
            curMax = Math.max(curMax, jobDifficulty[i - 1]);
            dp[i][1] = curMax;
        }
        // 开始状态转移
        for (int i = 1; i <= n; i++) {
            // 因为J = 1的情况已经考虑过了，从2开始
            // 且如果要要分隔的任务i小于天数J的话就不行，所以有这个条件
            for (int j = 2; j <= i && j <= d; j++) {
                // 这行可以回来再写
                dp[i][j] = Integer.MAX_VALUE; //因为下面要用最小值
                // x为这次要分割多少任务
                for (int x = 1; x <= i - j + 1; x++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - x][j - 1] + getMax(jobDifficulty, i - x, i - 1));
                }
            }
        }
        return dp[n][d];
    }

    public int getMax(int[] jobDifficulty, int start, int end) {
        int res = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            res = Math.max(res, jobDifficulty[i]);
        }
        return res;
    }
}
