import java.util.ArrayList;
import java.util.List;

public class Q54 {
    /*
     * 仿佛剥洋葱，从外向里缩减
     * 用top, botton, left, right来表示边界
     * 每次遍历完一行，缩减边界
     *    l  r
     * 1  2  3  4  top
     * 5  6  7  8
     * 9  10 11 12
     * 13 14 15 16 bottom
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int top = 0, botton = m - 1, left = 0, right = n - 1;
        List<Integer> res = new ArrayList<>();
        while (res.size() < m * n) {
            // 遍历最上面一行
            if(top <= botton) {
                for (int j = left; j <= right; j++) {
                    res.add(matrix[top][j]);
                }
                top++;
            }
            // 遍历最右列
            if (left <= right) {
                for (int i = top; i <= botton; i++) {
                    res.add(matrix[i][right]);
                }
                right--;
            }
            // 遍历最下面一行
            if (top <= botton) {
                for (int j = right; j >= left; j--) {
                    res.add(matrix[botton][j]);
                }
                botton--;
            }
            // 遍历最左边一列
            if (left <= right) {
                for (int i = botton; i >= top; i--) {
                    res.add(matrix[i][left]);
                }
                left++;
            }
        }
        return res;
    }
}
