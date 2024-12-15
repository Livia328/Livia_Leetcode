import java.util.Arrays;

public class Q498 {
    /*
     * 只有两种方向，向右上方，向左下方 -> 用DIRS数组表示
     * 因为我们要把二维数组map到一维数组，用i + j
     * res[i + j] = mat[i][j]
     * 
     * 从[0,0]开始朝右上方遍历，更新对应res
     * 当遍历到边缘的时候，选择下一个开始的位置
     * 
     * 如果是朝右上方，只可能碰到右边缘或者上边缘
     * a, b, c
     * d, e, f
     * g, h, i
     * 碰到右边缘(比如说f)，那么下一个应该是i，下移一个单位，
     * 碰到上边缘(比如说b)，那么下一个应该是c，右移一个单位，
     * 如果是corner（c），适用于右边缘
     * 
     * 
     * 如果是朝左下，只可能碰到左边缘或者下边缘
     * 碰到左边缘（比如说d），那么下一个应该是g，下移一个单位
     * 碰到下边缘（比如说h），那么下一个应该是i，右移一个单位
     * 如果是corner（g），那么下一个应该是h，适用于右移一个单位
     * 
     */
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] res = new int[m * n];
        int i = 0, j = 0, index = 0;
        res[index++] = mat[i][j];
        // direct[0]指当前方向为右上，direct[1]指当前方向为左下
        int[][] DIRS = {{-1, 1}, {1, -1}};
        // 初始方向为右上
        int curDir = 0;
        while (index < m * n) {
            // 按照当前方向对下个位置进行更新
            i = i + DIRS[curDir][0];
            j = j + DIRS[curDir][1];
            // 如果碰壁，要修正方向
            if (i < 0 || i >= m || j < 0 || j >= n) {
                // 如果当前方向是右上
                if (curDir == 0) {
                    // 如果碰到右壁, 下个位置是[i + 2][j - 1]
                    if (j >= n) {
                        i -= 2 * DIRS[curDir][0];
                        j -= DIRS[curDir][1];
                    } else if (i < 0) {
                        // 碰上壁，下个位置修正成[i + 1, j]
                        i -= DIRS[curDir][0];
                    }
                } else { // 当前方向是左下
                    if (i >= m) {
                        // 碰到下壁，下个位置应该被修正成[i - 1, j + 2]
                        i -= DIRS[curDir][0];
                        j -= 2*DIRS[curDir][1];
                    } else if (j < 0) {
                        // 碰到左壁，下个位置时[i, j + 1]
                        j -= DIRS[curDir][1];
                    }
                }
                // 修正位置后要转向
                curDir = Math.abs(curDir - 1);
            }
            res[index++] = mat[i][j];
        }
        return res;
    }
}
