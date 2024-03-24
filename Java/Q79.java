public class Q79 {
    int[][] DIR = {{1,0}, {-1,0}, {0, 1}, {0, -1}};
    boolean found = false;
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    backtrack(board, word, visited, 0, i, j);
                    if (found) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void backtrack(char[][] board, String word, boolean[][] visited, int index, int i, int j) {
        // add the answer
        // 这个return条件一定要写在第一个！
        // 不然可能word只有一个单词，如果把out of boundary放前边，还没有把found标记成true就直接返回了
        if (index == word.length()) {
            found = true;
            return;
        }
        // out of bound
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }
        // base case
        if (visited[i][j] || board[i][j] != word.charAt(index)) {
            return;
        }
        if (found) {
            return;
        }
        visited[i][j] = true;
        // 所有的option
        for (int[] d : DIR) {
            int x = i + d[0], y = j + d[1];
            backtrack(board, word, visited, index + 1, x, y);
        }
        visited[i][j] = false;
    }
}
