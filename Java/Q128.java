package Java;

import java.util.HashSet;
import java.util.Set;

public class Q128 {

    // brute force
    // sort the iterative 
    // time complexity: O(n log n)

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
        System.out.println(longestConsecutive(test1));
    }
}
