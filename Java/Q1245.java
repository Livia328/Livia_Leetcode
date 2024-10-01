import java.util.*;

public class Q1245 {
    /*
     * HashMap<Integer, List<Integer>> tree
     * key是当前node，List<Integer>是和他相临的节点
     * 
     * 虽然没有Root，但是我们球的是周长，也不一定要经过Root
     * 
     * 就和树的后序位置一样，通过递归得到这个数的所有子树的最大周长后，在进行比较
     * for (child : tree.child) {
     *    int len = helper(child)
     * }
     * 在这个过程中我们记录两个最大的周长
     * 
     * 那么经过当前root的最大周长是max1 + max2
     * 传上去的是最长的边，即Max1 + 1
     */
    int maxDia = 0;
    HashMap<Integer, List<Integer>> tree = new HashMap<>();
    public int treeDiameter(int[][] edges) {
        if (edges.length == 0) {
            return 0;
        }
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            if (!tree.containsKey(a)) {
                tree.put(a, new ArrayList<>());
            }
            if (!tree.containsKey(b)) {
                tree.put(b, new ArrayList<>());
            }
            tree.get(a).add(b);
            tree.get(b).add(a);
        }
        helper(edges[0][0]); // 从任何一个节点开始
        return maxDia;
    }

    HashSet<Integer> visited = new HashSet<>();
    public int helper(int root) {
        if (visited.contains(root)) {
            return 0;
        }
        visited.add(root);
        int max1 = 0, max2 = 0;
        for (int child : tree.get(root)) {
            int len = helper(child);
            if (len >= max1) {
                max2 = max1;
                max1 = len;
            } else if (len > max2) {
                max2 = len;
            }
        }
        // 后序位置
        maxDia = Math.max(maxDia, max1 + max2);
        return max1 + 1;
    }
}
