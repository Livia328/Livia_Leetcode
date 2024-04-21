public class Q684 {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UF uf = new UF(n + 1);
        for(int i = 0; i <= n; i++) {
            // 如果在遍历这个边之前就已经联通
            // 说明加上这个边就会成环
            // 那么这个边就是可以去除掉的边
            if (uf.connected(edges[i][0], edges[i][1])) {
                return new int[]{edges[i][0], edges[i][1]};
            } else {
                uf.union(edges[i][0], edges[i][1]);
            }
        }
        return new int[0];
    }
}
