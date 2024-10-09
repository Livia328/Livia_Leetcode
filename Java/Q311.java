import java.util.List;

import Q314.Pair;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayList;
public class Q311 {
    /*
     * 矩阵乘法：
     * mat1的col数和mat2的row数应该要相同
     * 因为我们要用mat1的每一行去乘mat2的每一列
     * 
     * mat1:
     *  1  0  0
     *  -1 0  3
     * 
     * mat2:
     * 7  0  0
     * 0  0  0
     * 0  0  1
     * 
     * res是一个2 * 3的matrix
     * res[0,0]
     * 用mat1的第一行 * mat2的第一列的和 
     * [1, 0, 0] * 7 = 1 * 7 + 0 * 0 + 0 * 0 = 7
     *             0
     *             0
     * 
     * res[0,1]:
     * [1, 0, 0] * [0, 0, 0] = 0
     * 
     * res[0, 2]:
     * [1, 0, 0] * [0, 0, 1] = 0
     * 
     * 所以res第一行是
     * [7, 0, 0]
     * 
     * res[1, 0]
     * mat1的第二行 * mat2的第一列的和
     * [-1, 0, 3] * [7, 0, 0] = -7 + 0 + 0 = 7
     * 
     * 所以我们可以看出暴力解法就是模拟上述过程
     * 
     */
    public int[][] multiply1(int[][] mat1, int[][] mat2) {
        int m = mat1.length, n = mat2[0].length;
        int shared = mat1[0].length;
        int[][] res = new int[m][n];
        // res[i][j] = SUM(mat第i行 和 mat2第j列)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < shared; k++) {
                    res[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
        }
        return res;
    }

    /*
     * 因为是sparse matrix
     * 所以只要记录不是0的就可以的，这样可以省下时间
     * 用List<int[]>[] 来记录
     * mat1:
     *  1  0  0
     *  -1 0  3
     * ->
     * [
        * [(0, 1)],
        * [(0, -1), (2, 3)]
     * ]
     * mat2:
     * 7  0  0
     * 0  0  0
     * 0  0  1
     * 
     * [
     * [(0, 7)],
     * [],
     * [(2, 1)],
     * ]
     * 
     * 重要的点在于：res[i][j] += mat1[i][k] * mat2[k][j];
     * 只是用不同的方法获得了i,j,k而已
     */
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int m = mat1.length, n = mat2[0].length;
        int[][] res = new int[m][n];
        List<int[]>[] mat1List = helper(mat1);
        List<int[]>[] mat2List = helper(mat2);
        for (int i = 0; i < m; i++) { //遍历每一行
            for (int[] pair : mat1List[i]) { //遍历每一行里不为0的数
                int k = pair[0], x = pair[1];  
                // 元素为：mat1[i][k]，它应该和mat[k][j]相乘
                // 所以去找mat2List[k]的所有不为0的元素，和它相乘
                for (int[] pair2 : mat2List[k]) {
                    int j = pair2[0], y = pair2[1];
                    res[i][j] += x * y;
                }
            }
        }
        return res;
    }

    public List<int[]>[] helper(int[][] mat) {
        int row = mat.length, col = mat[0].length;
        List<int[]>[] res = new List[row];
        Arrays.setAll(res, i -> new ArrayList<>());
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mat[i][j] != 0) {
                    res[i].add(new int[]{j, mat[i][j]});
                }
            }
        }
        return res;
    }
}
