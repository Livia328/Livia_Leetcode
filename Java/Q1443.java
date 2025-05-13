import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Q1443 {
    /*
     * 首先肯定是图论，多叉树
     * 所以我们可以构建graph
     * 
     * 作为一个node，我脚底下有多棵子树
     * 我要去这些子树里面找苹果
     * 
     * 假设我们知道了在其中一颗子树种找苹果的最小步数x
     * 那么加上我进入这个子树的2步，x + 2就是我找到这颗子树苹果的最小步数
     * 
     * 所以我返回 x1 + 2 + x2 + 2 ....
     */
    HashMap<Integer, List<Integer>> graph = new HashMap<>();
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        // 构建graph
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        // 双向图
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        HashSet<Integer> visited = new HashSet<>();
        int res = helper(0, hasApple, visited);
        return res == -1 ? 0 : res;
    }
    /*
     * 遍历以root为根的多叉树
     * 返回收集所有苹果的时间
     * 
     * 如果返回值为-1，表示没有苹果
     */
    public int helper(int root, List<Boolean> hasApple, HashSet<Integer> visited) {
        if (visited.contains(root)) {
            return -1;
        }
        visited.add(root);

        // 去看看子树里是不是有苹果
        int sum = 0;
        for (int child : graph.get(root)) {
            // 先得到子树的子树里有没有苹果
            int subTime = helper(child, hasApple, visited);
            // 如果有苹果，则进入，否则根本不用算步数
            if (subTime != -1) {
                sum += subTime + 2;
            }
        }
        // 处理返回信息
        if (sum > 0) {
            return sum;
        }
        // 虽然它的子树没有苹果，但是root本身是苹果，返回0步
        if (sum == 0 && hasApple.get(root)) {
            return 0;
        }
        // 以root为根的子树根本没苹果，返回-1
        return -1;
    }
}
