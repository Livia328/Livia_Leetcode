import java.util.*;
import java.util.stream.Collectors;

public class Q51 {
    /**
     * 一行行检测，每一行里每个col都可以是candidates
     * 逐一检测是否可以，可以的话backtrack
     * 
     * 检测是否可以
     * 检测每一行是否有冲突的
     */
    List<List<String>> res = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        backtrack(board, 0);
        return res;
    }

    public void backtrack(char[][] board, int row) {
        if (row == board.length) {
            res.add(Arrays.stream(board).map(String::valueOf).collect(Collectors.toList()));
            return;
        }
        int n = board.length;
        for (int col = 0; col < n; col++) {
            if (!isValid(board, row, col)) {
                continue;
            }
            board[row][col] = 'Q';
            backtrack(board, row + 1);
            board[row][col] = '.';
        }
    }

    public boolean isValid(char[][] board, int row, int col) {
        int n = board.length;
        // 同列有Q
        for (int i = 0; i < n; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
}
