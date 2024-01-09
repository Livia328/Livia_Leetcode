package Java;

public class Q36 {
    // 注意事项：记得要处理'.'的部分
    // 不然会out of bound会出现-3啥的

    // validate row
    // 0 1 2 3 4 5 6 7 8
    //0                      [0,0] indicate for row 1, 1 has appear
    //1
    //2

    //validate boxes 3d-array
    // 0 1 2
    //0                      [0,0,0] indicate for the right top boxes, 1 has appear
    //1
    //2
    public boolean isValidSudoku(char[][] board) {
        int[][] row = new int[9][9];
        int[][] col = new int[9][9];
        int[][][] box = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int cur = board[i][j] - '1';
                if (row[i][cur] != 0 || col[j][cur] != 0 || box[i/3][j/3][cur] != 0) {
                    return false;
                }
                row[i][cur] = 1;
                col[j][cur] = 1;
                box[i/3][j/3][cur] = 1;
            }
        }
        return true;
    }
}
