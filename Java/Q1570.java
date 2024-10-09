import java.util.*;

public class Q1570 {
    /*
     * 因为是spars vector
     * 所以我们将不为0的数存在hashmap里即可
     * map: key是index, val是值
     */
    class SparseVector1 {

        Map<Integer, Integer> map;
    
        SparseVector1(int[] nums) {
            map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.put(i, nums[i]);
            }
        }
        
        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector1 vec) {
            // 遍历小的可以减少时间复杂度，但是这里一开始也可以不写
            // Map<Integer, Integer> smallerMap = map;
            // Map<Integer, Integer> largerMap = vec.map;
            // if (smallerMap.size() > largerMap.size()) {
            //     Map<Integer, Integer> tmp = smallerMap;
            //     smallerMap = largerMap;
            //     largerMap = tmp;
            // }
            int prodSum = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int index = entry.getKey();
                int val = entry.getValue();
                if (vec.map.containsKey(index)) {
                    prodSum += val * vec.map.get(index);
                }
            }
            return prodSum;
        }
    }


    /*
     * 双指针
     */
    class SparseVector {

        int[] nums;
    
        SparseVector(int[] nums) {
            this.nums = nums;
        }
        
        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vec) {
            int p1 = 0;
            int p2 = 0;
            int prodSum = 0;
            while (p1 < nums.length && p2 < vec.nums.length) {
                // 有一个是为0的，那么就不用算
                if (nums[p1] == 0 || nums[p2] == 0) {
                    p1++;
                    p2++;
                    continue;
                }
                prodSum += nums[p1] * vec.nums[p2];
                p1++;
                p2++;
            }
            return prodSum;
        }
    }
}
