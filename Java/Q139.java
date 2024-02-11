import java.util.*;

public class Q139 {
    /**
     * 可以reuse
     * 
     * leetcode
     * 
     * backtracking
     * 结束条件：i == s.length
     * 
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        return backtrack(s, 0, wordDict);
    }

    public boolean backtrack(String s, int i, List<String> wordDict) {
        if (i == s.length()) {
            return true;
        }
        boolean flag = false;
        // leetcode i = 0, n = 4
        for (String cur : wordDict) {
            int len = cur.length();
            if (i + len > s.length()) {
                continue;
            }
            // match上了一个
            if (s.substring(i, i + len).equals(cur)) {
                if (backtrack(s, i + len, wordDict)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * memo数组优化
     * 
     * memo数组，-1表示没算过，1表示可以，0表示不可以
     */
    int[] memo;
    public boolean wordBrea2k(String s, List<String> wordDict) {
        memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return backtrack2(s, 0, wordDict);
    }

    /**
     * 返回s[i...]能不能被拼出
     */
    public boolean backtrack2(String s, int i, List<String> wordDict) {
        // base case
        if (s.length() == i) {
            return true;
        }
        // avoid duplicates
        if (memo[i] != -1) {
            return memo[i] == 1 ? true : false;
        }
        for (String cur : wordDict) {
            int len = cur.length();
            if (i + len > s.length()) {
                continue;
            }
            if (s.substring(i, i + len).equals(cur)) {
                if (backtrack2(s, i + len, wordDict)) {
                    memo[i] = 1;
                    return true;
                }
            }
        }
        memo[i] = 0;
        return false;
    }

    /**
     * BFS
     * Queue里放的是状态
     */
    public boolean wordBreak3(String s, List<String> wordDict) {
        // 避免重复状态进入queue
        Set<String> set = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(s);
        while(!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                String target = queue.poll();
                for (String cur : wordDict) {
                    if (cur.length() > target.length()) {
                        continue;
                    }
                    if (target.substring(0,cur.length()).equals(cur)) {
                        if (cur.length() == target.length()) {
                            return true;
                        }
                        String next = target.substring(cur.length(), target.length());
                        if (!set.contains(next)) {
                            set.add(next);
                            queue.offer(next);
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        
    }
}


