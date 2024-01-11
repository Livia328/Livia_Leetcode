package Java;

import java.util.*;

class Q167 {
    public static int[] twoSum(int[] numbers, int target) {
        int L = 0, R = numbers.length - 1;
        int[] ans = new int[2];
        while (L <= R) {
            if (numbers[L] + numbers[R] == target) {
                ans[0] = L + 1;
                ans[1] = R + 1;
                return ans;
            } else if (numbers[L] + numbers[R] < target) {
                L++;
            } else if (numbers[L] + numbers[R] > target) {
                R--;
            }
        }
        return ans;
    }

    /**
     * Input: numbers = [2,7,11,15], target = 9
     * Output: [1,2]
     * @param args
     */
    public static void main(String[] args) {
        int[] test1 = {2,7,11,15};
        System.out.println(Arrays.toString(twoSum(test1, 9)));
    }
}