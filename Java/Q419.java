public class Q419 {
    /*
     * 因为战舰是水平或者垂直的
     * 1 * N 或者 N * 1
     * 所以不用dfs
     * 
     * 直接找到一个x后
     * 将以 (i,j) 为起点的战舰的所有位置均设置为空位
     */
    public int countBattleships1(char[][] board) {
        int m = board.length, n = board[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    board[i][j] = '.';
                    // 遍历所有纵向的
                    for (int k = j + 1; k < n && board[i][k] == 'X'; k++) {
                        board[i][k] = '.';
                    }
                    // 遍历所有横向的
                    for (int k = i + 1; k < m && board[k][j] == 'X'; k++) {
                        board[k][j] = '.';
                    }
                    res++;
                }
            }
        }
        return res;
    }

    /*
     * 如果面试官觉得不能修改原数组
     * 或者提升时间复杂度
     * 
     * 因为题目设定两个战舰之间至少有一个
     * 枚举战舰top left的起点
     * 也就是说这个X的左边是'.''
     * 上面也是'.''
     */
    public int countBattleships(char[][] board) {
        int m = board.length, n = board[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    if (i > 0 && board[i - 1][j] == 'X') {
                        continue;
                    }
                    if (j > 0 && board[i][j - 1] == 'X') {
                        continue;
                    }
                    res++;
                }
            }
        }
        return res;
    }
}
