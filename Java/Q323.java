public class  Q323 {
    public int countComponents(int n, int[][] edges) {
        UF uf = new UF(n);
        for (int i = 0; i < edges.length; i++) {
            int v = edges[i][0], u = edges[i][1];
            if (uf.find(u) != uf.find(v)) {
                uf.union(v, u);
            }
        }
        return uf.count();
    }
}
