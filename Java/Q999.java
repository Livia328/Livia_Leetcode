public class Q999 {
    public int numRookCaptures(char[][] board) {
        int x = -1, y = -1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'R') {
                    x = i; y = j;
                }
            }
        }
        int res = 0;
        int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] d : DIRS) {
            int newX = x + d[0], newY = y + d[1];
            while (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length && board[newX][newY] == '.') {
                newX += d[0]; newY += d[1];
            }
            if (newX < 0 || newX >= board.length || newY < 0 || newY >= board[0].length) {
                continue;
            }
            if (board[newX][newY] == 'p') {
                res++;
            }
        }
        return res;
    }
}
