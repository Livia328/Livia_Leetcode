public class Q37 {
    /*
     * backtracking
     * 
     * 穷举，对每一个空着的格子穷举1-9
     * 如果遇到不合法的格子就下一个
     * 
     * 如果找到了一个合适的答案就接着穷举下一个空格子
     * 
     * 需要的参数i, j，表示到了第几行第几列
     * basecase：
     * j == 9；表示到了最后一列，那么要换行开始
     * i == 9；表示所有的都格子都穷举完了，找到了一个可行解
     * 
     * isValid: 检查每一行，检查每一列，检查每一个3*3的格子
     * 
     * 3*3的格子怎么判断？
     * 因为 i=0 -> 8， 所以 i / 3 在每一次循环的变化是 0, 0, 0, 1, 1, 1, 2, 2, 2
     * 因为 i=0 -> 8， 所以 i % 3 在每一次循环的变化是 0, 1, 2, 0, 1, 2, 0, 1, 2
     * 
     * 这样可以遍历3*3格子里的每一个格子
     * 
     * 1   2   3
     * 4   5   6
     * 7   8   9
     * 
     * 同时我们要知道它隶属于哪一个3*3
     * [(i/3) * 3] -> 
     *       i如果是0，1，2， 那么很显然对应的是1，所以是0，
     *       i如果是3，4，5， 那么很显然对应的是2，所以是1 * 3开始的坐标
     * 
     * 所以综合来看是board[(r/3)*3 + i/3][(c/3)*3 + i%3]
     */
    public void solveSudoku(char[][] board) {
        backtrack(board, 0, 0);
    }
    

    boolean backtrack(char[][] board, int i, int j) {
        int n = 9;
        if (j == n) {
            return backtrack(board, i + 1, 0);
        }
        if (i == n) {
            return true;
        }
        // 如果有数字，那么不用我们填
        if (board[i][j] != '.') {
            backtrack(board, i, j + 1);
        }
        for (char c = '1'; c <= '9'; c++) {
            // 如果遇到不合法的跳过
            if (!isValid(board, i, j, c)) {
                continue;
            }
            // 这个空格可以填入这个数字
            board[i][j] = c;
            // 如果找到一个可行解，那么直接结束
            if (backtrack(board, i, j + 1)) {
                return true;
            }
            // 撤销选择
            board[i][j] = '.';
        }
        return false;
    }

    /*
     * 检查每一行，检查每一列，检查每一个3*3的格子
     * 
     * 3*3的格子怎么判断？
     * 因为 i=0 -> 8， 所以 i / 3 在每一次循环的变化是 0, 0, 0, 1, 1, 1, 2, 2, 2
     * 因为 i=0 -> 8， 所以 i % 3 在每一次循环的变化是 0, 1, 2, 0, 1, 2, 0, 1, 2
     * 
     * 这样可以遍历3*3格子里的每一个格子
     * 
     * 1   2   3
     * 4   5   6
     * 7   8   9
     * 
     * 同时我们要知道它隶属于哪一个3*3
     * [(i/3) * 3] -> 
     *       i如果是0，1，2， 那么很显然对应的是1，所以是0，
     *       i如果是3，4，5， 那么很显然对应的是2，所以是1 * 3开始的坐标
     * 
     * 所以综合来看是board[(r/3)*3 + i/3][(c/3)*3 + i%3]
     */
    public boolean isValid(char[][] board, int i, int j, char c) {
        for (int x = 0; x < 9; x++) {
            if (board[x][j] == c) {
                return false;
            }
            if (board[i][x] == c) {
                return false;
            }
            if (board[(i/3) * 3 + x / 3][(j/3) * 3 + i % 3] == c) {
                return false;
            }
        }
        return true;
    }
}
