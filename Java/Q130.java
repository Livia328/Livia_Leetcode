public class Q130 {
    /**
     * DFS解法，把上下左右的岛屿淹掉
     * 剩下的就都是封闭岛屿了
     * 和Q1254一样
     */
    public void solve(char[][] board) {
        // 先找出周围的，对他们特殊标记
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            dfs(i, 0, board);
            dfs(i, n - 1, board);
        }
        for (int j = 0; j < n; j++) {
            dfs(0, j, board);
            dfs(m - 1, j, board);
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果是之前特殊标记的，把他们变成O
                if (board[i][j] == 'Z') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    // 如果是中间的，把它变成x
                    board[i][j] = 'X';
                }
            }
        }
    }

    static int[][] DIR = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void dfs(int i, int j, char[][] board) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }
        if (board[i][j] == 'X' || board[i][j] == 'Z') {
            return;
        }
        board[i][j] = 'Z';
        for (int[] d : DIR) {
            int x = i + d[0], y = j + d[1];
            dfs(x, y, board);
        }
    }

    /**
     * Union Find
     */
    public void solve2(char[][] board) {
        int m = board.length, n = board[0].length;
        // 有一个dummy node，让边上的岛都和dummy连起来
        UF uf = new UF(m * n + 1);
        int dummy = m * n;
        // 将首列和末列的 O 与 dummy 连通
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O')
                uf.union(i * n, dummy);
            if (board[i][n - 1] == 'O')
                uf.union(i * n + n - 1, dummy);
        }
        // 将首行和末行的 O 与 dummy 连通
        for (int j = 0; j < n; j++) {



            if (board[0][j] == 'O')
                uf.union(j, dummy);
            if (board[m - 1][j] == 'O')
                uf.union(n * (m - 1) + j, dummy);
        }

        for (int i = 1; i < m -1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == 'O') {
                    for (int[] d : DIR) {
                        int x = i + d[0], y = j + d[1];
                        if (board[x][y] == 'O') {
                            uf.union(x * n + y, i * n + j);
                        }
                    }
                }
            }
        }

        // 所有不和dummy连起来的都要替换
        for (int i = 1; i < m -1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (!uf.connected(dummy, i * n + j)) {
                    board[i][j] = 'X';
                }
            }
        }


    }

    class UF {
        // 联通数量
        private int count;
        // 每个节点的parent
        private int[] parent;
        // 记录树的size
        private int[] size;

        public UF(int n) {
            this.count = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = i;
            }
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                return;
            }
            // 小树接在大树下面，保持平衡
            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
            count--;
        }

        public int find(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public boolean connected(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            return rootP == rootQ;
        }

        public int count() {
            return count;
        }
    }
}
