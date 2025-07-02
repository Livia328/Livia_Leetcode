import java.util.*;

public class Q1260 {
    /*
     * 想像成变成一位数组
     * 然后向右平移了k个
     * 然后再映射回2d array就行了
     * 
     * 1 2 3    9 1 2 
     * 4 5 6 -> 3 4 5
     * 7 8 9    6 7 8
     * 
     * 1 2 3 4 5 6 7 8 9
     * 右移动一位后是
     * 9 1 2 3 4 5 6 7 8
     * 
     * 再放回2d array就行了
     * 
     * 但我们不用真的模拟这个全过程
     * 一个元素，本来映射到一唯数组里是 i*n + j
     * 平移后是i * n + j + k
     * 
     * 总共有m * n 的位置
     * 所以它在1d数组里的新位置是after = (i * n + j + k) % (m * n)
     * 
     * 把它放回新的数组，坐标应该是
     * [after / n][after % n]
     */
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] res = new int[m][n];
        int total = m * n;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int after = (i * n + j + k) % total;
                res[after / n][after % n] = grid[i][j];
            }
        }
        //  变成二维数组
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> cur = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                cur.add(res[i][j]);
            }
            ans.add(cur);
        }
        return ans;
    }
}
