import java.util.*;

public class Q1376 {
    /**
     * tree structure
     * 
     * build the tree based on the manager?
     * dfs, 传上来以node为根的最大informtime
     * 
     */
    Map<Integer, List<Integer>> tree;
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        // build the tree
        tree = new HashMap<>();
        for (int i = 0; i < manager.length; i++) {
            if (manager[i] >= 0) {
                if (!tree.containsKey(manager[i])) {
                    tree.put(manager[i], new ArrayList<>());
                }
                tree.get(manager[i]).add(i);
            }
        }
        // traverse
        return dfs(informTime, headID);
    }

    public int dfs(int[] informTime, int node) {
        // get the inform time form the buttom of the node
        int maxTime = 0;
        if (tree.get(node) != null) {
            List<Integer> child = tree.get(node);
            for (int i = 0; i < child.size(); i++) {
                maxTime = Math.max(maxTime, dfs(informTime, child.get(i)));
            }
        }
        return maxTime + informTime[node];
    }

    /**
     * 优化空间，其实不需要建树，因为manager[]里已经有父节点的信息了
     * 记录一个memo，递归到node，就返回memo里的数值
     */
    public int numOfMinutes2(int n, int headID, int[] manager, int[] informTime) {
        int[] memo = new int[n];
        Arrays.fill(memo, -1); //-1表示没有便利过
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dfs(manager, informTime, memo, i));
        }
        return res;
    }

    public int dfs(int[] manager, int[] informTime, int[] memo, int cur) {
        if (manager[cur] < 0) {
            return informTime[cur];
        }
        if (memo[cur] > 0) {
            return memo[cur];
        }
        memo[cur] = dfs(manager, informTime, memo, manager[cur]) + informTime[cur];
        return memo[cur];
    } 
}
