import java.util.ArrayList;
import java.util.List;

public class Q1762 {
    /*
     * 从右往左遍历，遍历的过程中记录目前为止右边最高的
     * 比如：1，2，5，4，3
     * 
     * 一开始max设定成Integer.MIN_VALUE;
     * 然后将3和max比较，3大，所以它可以看到海景view
     * 然后更新max为3
     * 
     * 4和max比较，4 > 3，所以也valida
     * 更新max为4
     * 
     * 5和max比较，5大，所以也valid
     * 更新max为5
     * 
     * 2和max比较，max大，所以不valide
     * 
     * 1和max比较，max大，所以不valid
     */
    public int[] findBuildings(int[] heights) {
        // list中是倒着的
        List<Integer> list = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights[i] > max) {
                list.add(i);
                max = heights[i];
            }
        }
        // 生成答案int[]
        int[] res = new int[list.size()];
        int index = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            res[index++] = list.get(i);
        }
        return res;
    }
}
