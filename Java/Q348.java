public class Q348 {
    /*
     * 就是直接模拟
     */
    class TicTacToe {
        int[][] board;
        int n;
        public TicTacToe(int n) {
            this.n = n;
            board = new int[n][n];
        }
        
        public int move(int row, int col, int player) {
            board[row][col] = player;
            boolean checkRow = true;
            boolean checkCol = true;
            for (int i = 0; i < n; i++) {
                if (board[row][i] != player) {
                    checkCol = false;
                }
                if (board[i][col] != player) {
                    checkRow = false;
                }
            }
            if (checkRow || checkCol) {
                return player;
            }
            boolean checkDiagonal1 = false;
            boolean checkDiagonal2 = false;
            if (row == col) {
                checkDiagonal1 = true;
                for (int i = 0; i < n; i++) {
                    if (board[i][i] != player) {
                        checkDiagonal1 = false;
                        break;
                    }
                }
            }
            if (row == (n - col - 1)) {
                checkDiagonal2 = true;
                for (int i = 0; i < n; i++) {
                    if (board[i][n - i - 1] != player) {
                        checkDiagonal2 = false;
                        break;
                    }
                }
            }
            if (checkDiagonal1 || checkDiagonal2) {
                return player;
            }
            return 0;
        }
    }
}
