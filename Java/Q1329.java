import java.util.*;

public class Q1329 {
    /*
     * 理解题目意思
     * 
     * 3 3 3
     * 2 3 1
     * 1 2 3
     * 
     * 这道题目中的对角线是从左上到右下
     * 1
     * 2 2
     * 3 3 3
     * 3 1   -》 排序之后是1, 3所以这两个数要调换位置
     * 3
     * 这样
     * 
     * 
     * 首先要做的是：
     * 怎么判断他们在一个对角线上
     * 
     * 1 (0, 2)
     * 2 2  (1,0), (2,1)
     * 3 3 3 (0,0),(1,1),(2,2)
     * 3 1  (0,1) (1, 2)
     * 3 (0,2)
     * 
     * 在同一个对角线上的元素，其横纵坐标之差是相同的
     * 
     * 所以想到可以用hashmap
     * key是diff，value事list,是这个对角线上的所有元素
     * 
     */
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int curDiff = i - j;
                map.putIfAbsent(curDiff, new ArrayList<>());
                map.get(curDiff).add(mat[i][j]);
            }
        }
        // 把每一个对角线排序
        // 注意，这里如果要提高时间复杂度，可以倒叙排序
        // 因为arraylist从末尾删除效率比较高
        for (List<Integer> list : map.values()) {
            Collections.sort(list);
            // Collections.sort(list, Collections.reverseOrder());
        }
        // 把结果填回去
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ArrayList<Integer> list = map.get(i - j);
                mat[i][j] = list.remove(0);
            }
        }
        return mat;
    }
}
