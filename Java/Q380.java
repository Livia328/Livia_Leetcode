import java.util.*;

public class Q380 {
    /*
     * 连续的存储，这样可以直接生成一个随机数然后找到
     * int[], list?
     * 
     * 但是insert和remove的操作又要是O(1)
     * 可以记录index，把它和最后一个数交换，然后再删掉
     * 
     */
    class RandomizedSet {
        private List<Integer> nums;
        // key: value, integer: index
        private Map<Integer, Integer> map;

        public RandomizedSet() {
            nums = new ArrayList<>();
            map = new HashMap<>();
        }
        
        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            nums.add(val);
            map.put(val, nums.size() - 1);
            return true;
        }
        
        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            // get index
            int index = map.get(val);
            int lastElement = nums.get(nums.size() - 1);
            // 交换lastElement和val
            map.put(lastElement, index);
            Collections.swap(nums, index, nums.size() - 1);
            nums.remove(nums.size() - 1);
            map.remove(val);
            return true;
        }
        
        public int getRandom() {
            Random random = new Random();
            return nums.get(random.nextInt(nums.size()));
        }
    }
}
