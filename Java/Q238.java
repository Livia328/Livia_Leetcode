import java.util.Arrays;

public class Q238 {
    // brute force
    // time complex: O(n2)
    // space complex: O(n)
    public static int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int res = 1;
            for (int j = 0; j < i; j++) {
                res *= nums[j];
            }
            for (int j = i + 1; j < nums.length; j++) {
                res *= nums[j];
            }
            ans[i] = res;
        }
        return ans;
    }

    // prefix 
    // [1, 2, 3, 4]
    // prefix: the product of all numbers before it
    // [1, 1, 2, 6]
    // suffix: the product of all numbers after it
    // [24, 12, 4, 1]
    // 

    // time complexity: O(n)
    // space complexity: O(n)
    public static int[] productExceptSelf2(int[] nums) {
        int len = nums.length;
        int[] prefix = new int[len];
        prefix[0] = 1;
        for (int i = 1; i < len; i++) {
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }
        int[] suffix = new int[len];
        suffix[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = prefix[i] * suffix[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] test1 = {1,2,3,4};
        // [24,12,8,6]
        System.out.println(Arrays.toString(productExceptSelf2(test1)));
    }
}
