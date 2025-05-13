public class Q529 {
    /*
     * 如果起点处是M（雷）
     * 那么直接修改为X，结束
     * 
     * 如果是E，向8个方向搜索，
     *     周围8个格子有雷，修改为雷数
     *     没有雷，更新为B
     */
    int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0},
                    {1, 1}, {1, -1}, {-1, 1}, {-1,-1},
                   };
    public char[][] updateBoard(char[][] board, int[] click) {
        int i = click[0], j = click[1];
        if (board[i][j] == 'M') {
            board[i][j] = 'X';
            return board;
        }
        dfs(board, i, j);
        return board;
    }

    public void dfs(char[][] board, int i, int j) {
        // 数一数周边八个格子都多少个雷
        int count = 0;
        for (int[] d : DIRS) {
            int x = i + d[0], y = j + d[1];
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
                continue;
            }
            if (board[x][y] == 'M') {
                count++;
            }
        }
        // 如果有雷，那么就改成数字
        if (count > 0) {
            board[i][j] = (char)(count + '0');
        } else {
            // 否则，recursively打开空白的
            board[i][j] = 'B';
            for (int[] d : DIRS) {
                int x = i + d[0], y = j + d[1];
                // 如果越界且下一个不为E的话
                if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'E') {
                    continue;
                }
                dfs(board, x, y); 
            }
        }

    }
}
