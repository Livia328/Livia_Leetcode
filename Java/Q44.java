import java.util.Arrays;

public class Q44 {
    /*
     * recursion + memo
     * s和p匹配就是i,j指针分别游走， s[i] == p[j] || p[j] == '？'即为匹配
     * 
     * 分情况讨论：
     * 如果匹配上了：
     *   recursion(s, i + 1, p, j + 1)
     * 如果没匹配上：
     *   如果p[j]是*，
     *      res = recursion(s, i + 1, p, j) || recursion(s, i, p, j + 1);
     *               j和当前这个匹配，匹配多个       匹配0个 比如s: a  p:*
     * 
     * 用memo去重
     * memp[i][j]表示s[i...]可以匹配p[j...]
     */
    private int[][] memo;
    public boolean isMatch(String s, String p) {
        String pp = removeAdjStar(p);
        int m = s.length(), n = p.length();
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return recursion(s, 0, pp, 0);
    }

    public boolean recursion(String s, int i, String p, int j) {
        // 两个都到头了
        if (j == p.length() && i == s.length()) {
            return true;
        }
        // j到头了，但是i没有到头
        if (j == p.length()) {
            return false;
        }
        // i到头了，如果j后面是*，那么还是可以满足的
        if (i == s.length()) {
            for (int k = j; k < p.length(); k++) {
                if (p.charAt(k) != '*') {
                    return false;
                }
            }
            return true;
        }
        if (memo[i][j] != -1) {
            return memo[i][j] == 1;
        }
        boolean res = false;
        // 匹配上了
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
            res = recursion(s, i + 1, p, j + 1);
        } else { // 当前没匹配上
            if (p.charAt(j) == '*') { // 但是j是*
                // 可以匹配0个或多个s中的字符
                // 只要有一种情况可以完成即可
                res = recursion(s, i + 1, p, j) || recursion(s, i, p, j + 1);
            }
        }
        memo[i][j] = res ? 1 : 0;
        return res;

    }

    // 删除相邻的 * 号，返回删除后的字符
    private String removeAdjStar(String p) {
        if (p.isEmpty()) {
            return "";
        }
        StringBuilder pp = new StringBuilder();
        pp.append(p.charAt(0));
        for (int i = 1; i < p.length(); i++) {
            if (p.charAt(i) == '*' && p.charAt(i - 1) == '*') {
                continue;
            }
            pp.append(p.charAt(i));
        }
        return pp.toString();
    }
}
