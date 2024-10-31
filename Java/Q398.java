import java.util.*;

public class Q398 {
    /*
     * 用hashmap记录所有的坐标
     * 然后每次随机选择一个数字
     * 
     * 或者就是说在pick的时候，每次碰到了target再加进去也可以
     */
    class Solution {

        Map<Integer, ArrayList<Integer>> map = new HashMap<>();

        public Solution(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                if (!map.containsKey(nums[i])) {
                    map.put(nums[i], new ArrayList<>());
                }
                map.get(nums[i]).add(i);
            }
        }
        
        public int pick(int target) {
            ArrayList<Integer> allIndeices = map.get(target);
            int i = (int)(Math.random() * allIndeices.size());
            return allIndeices.get(i);
        }
    }

    /*
     * 如果followup是data stream或者对不允许用额外空间
     * 水塘抽样法，Reservoir Sampling
     * 
     * 每次 pick 都遍历一遍 nums 数组
     * 
     * 我们在遍历nums[i]之前已经遍历了i-1个元素
     * pick到ith元素的概率应该是 1/i
     * 
     * 因为这样的话，所有的概率加起来是1/i * (1 - 1/(i+1)) *
        * (1 - 1/(i+2)) * .. * (1 - 1/N) = 1 / N.
     * 
     */
    class Solution2 {
        
        int[] nums;
        Random random;


        public Solution2(int[] nums) {
            this.nums = nums;
            this.random = new Random();
        }
        
        public int pick(int target) {
            int count = 0, res = -1;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != target) {
                    continue;
                }
                count++;
                // 1/count的概率
                if (random.nextInt(count) == 0) {
                    res = i;
                }
            }
            return res;
        }
    }
}
