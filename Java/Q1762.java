import java.util.ArrayList;
import java.util.List;

public class Q1762 {
    /*
     * 从右往左遍历，遍历的过程中记录目前为止右边最高的
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
