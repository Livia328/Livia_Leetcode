import java.util.Arrays;

public class Q10 {
    /**
    思考方式：recursion + memo

    1.先不考虑*
    s和p匹配就是i,j指针分别游走， s[i] == p[j] || p[j] == '？'即为匹配

    2.考虑*，分情况讨论
    -> 提问，*可能连续出现吗？ 一个星号和多个星号的效果是一样的，我们可以提前remove

    1.当前字母能匹配上，s[i] == p[j] || p[j] == '？'
      当p[j + 1]为*的时候，
        1.1 p[j]匹配多个，s = "aaa", p = a*, 
        1.2 p[j]匹配0个，s = "aaa", p = a*aa
      当p[j + 1]不是*的时候
        递归下一个helper(s, i + 1, p, j + 1)
    2.当前字母匹配不上，s[i] != p[j]
      当p[j + 1]为*的时候，
        p[j]匹配0个
      当p[j + 1]不为*的时候
        直接return false
    因此，我们所需要做出的选择就是，p[j]到底应该匹配0个还是多个？

    recursion函数定义：
    boolean recursion(string s, int i, string p, int j)
    表示s[i...]可以匹配p[j...]
    */
    private int[][] memo;
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return recursion(s, 0, p, 0);
    }

    public boolean recursion(String s, int i, String p, int j) {
        int m = s.length(), n = p.length();
        // base case
        // p走到头了
        if (j == n) {
            // 如果此时s也走完了，那么就是true
            return i == m;
        }
        if (i == m) {
            // 此时j还有剩下的，有可能是true的情况是字符和 * 成对儿出现
            // 先check一下
            if ((n - j) % 2 == 1) {
                return false;
            }
            // 检查是否为x*y*z* 这种形式
            for (; j + 1 < n; j += 2) {
                if (p.charAt(j + 1) != '*') {
                    return false;
                }
            }
            return true;
        }
        // check memo
        if (memo[i][j] != -1) {
            return memo[i][j] == 1;
        }
        boolean res = false;
        // 当前这个字符匹配上了
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
            // 下一个字符是*
            if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
                // 匹配多次,匹配0次
                res = recursion(s, i, p, j + 2) || recursion(s, i + 1, p, j);
            } else {
                // 正常进入下一个
                res = recursion(s, i + 1, p, j + 1);
            }
        } else { // 当前这个字符没有匹配上
            // 下一个字符是*
            if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
                // 只能用在这次
                res = recursion(s, i, p, j + 2);
            } else {
                // 就是匹配不上
                return false;
            }
        }
        memo[i][j] = res ? 1 : 0;
        return res;
    }
}
