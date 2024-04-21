import java.util.*;

public class Q169 {
    /**
     * 标准解法：hashmap计数
     * 但是这道题的限制其实很多，比如这个数一定存在且唯一，
     */
    public int majorityElement1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (int i : map.keySet()) {
            if (map.get(i) > nums.length / 2) {
                return i;
            }
        }
        return -1;
        
    }

    /**
     * 假设是正电子和负电子
     * 如果是target，那么就加，
     * 如果不是targe，那么就减
     */
    public int majorityElement(int[] nums) {
        int target = 0; // the number we want
        int count = 0; // count
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                // 计数器为0， 假设nums[i]为众数
                target = nums[i];
                // 众数出现了一次
                count++;
            } else if (nums[i] == target) {
                count++;
            } else {
                count--;
            }
        }
        // 此时count必然大于0， 此时的target必然就是目标
        return target;
    }
}
