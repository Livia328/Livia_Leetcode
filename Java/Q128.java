package Java;

import java.util.*;

public class Q128 {
    // brute force
    // sort the iterative 
    // time complexity: O(n log n)

    public static int longestConsecutive2(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return 0;
        }
        Arrays.sort(nums);
        int curLen = 1;
        int maxLen = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                continue;
            }
            if (nums[i - 1] + 1 == nums[i]) {
                curLen++;
                maxLen = Math.max(maxLen, curLen);
            } else {
                curLen = 1;
            }
        }
        return maxLen;
    }

    // O(n)
    // the most important thing: find the head of the consecutive sequence
    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int ans = 0;
        for (int num : nums) {
            set.add(num);
        }
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                // try to find num + 1
                int curlen = 0;
                while (set.contains(num)) {
                    num++;
                    curlen++;
                }
                ans = Math.max(ans, curlen);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] test1 = {100,4,200,1,3,2};
        System.out.println(longestConsecutive2(test1));
    }
}
