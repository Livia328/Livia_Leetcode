import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Q1424 {
    /*
     * 因为实在同一对角线上的话 i + j是一样的
     * 可以将i+j作为key，用map来存
     * 
     * 要linkedhasmap，不然integer的顺序会打乱
     * Map<Integer, List<Integer> 
     * 
     * 1 2 3
     * 4 5 6
     * 7 8 9
     * 
     * 从上到下遍历
     * map:
     * key val
     *  0   1
     *  1   2, 4
     *  2   3, 5
     *  3   6
     *  
     * 
     * 这样存在map里的是反过来的，我们可以最后输出的时候reverse一下
     * 
     */
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        // 因为下面要生成res array
        // 所以需要知道有多少个元素
        // 这一行可以先不写
        int size = 0;
        Map<Integer, List<Integer>> map = new LinkedHashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            size += nums.get(i).size();
            for (int j = 0; j < nums.get(i).size(); j++) {
                map.putIfAbsent(i + j, new LinkedList<>());
                map.get(i + j).add(nums.get(i).get(j));
            }
        }
        int[] res = new int[size];
        int index = 0;
        for (int key : map.keySet()) {
            List<Integer> list = map.get(key);
            for (int j = list.size() - 1; j >= 0; j--) {
                res[index] = list.get(j);
                index++;
            }
        }
        return res;
    }
}
