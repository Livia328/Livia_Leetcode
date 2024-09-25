import java.util.Arrays;

public class Q72 {
    /*
     * 子问题：比较三种操作哪个最少
     * memo优化
     * memo[i][j]表示word1[0, i]和word2[0,j]的最小距离
     */
    int[][] memo;
    public int minDistance(String word1, String word2) {
        memo = new int[word1.length()][word2.length()];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(word1, word1.length() - 1, word2, word2.length() - 1);
    }

    public int dp(String word1, int i, String word2, int j) {
        // base case
        // 如果有一个string走完了，那么要把剩下的所有都删掉
        if (i == -1) {
            return j + 1;
        }
        if (j == -1) {
            return i + 1;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (word1.charAt(i) == word2.charAt(j)) {
            memo[i][j] = dp(word1, i - 1, word2, j - 1); // 这种情况不用做任何操作
        } else {
            memo[i][j] = min(
                dp(word1, i, word2, j - 1) + 1, // insert，假装word2[j]被匹配了，j往前移一个
                dp(word1, i - 1, word2, j) + 1, //delete
                dp(word1, i - 1, word2, j - 1) + 1 //replace
            );
        }
        return memo[i][j];
    }

    public int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
