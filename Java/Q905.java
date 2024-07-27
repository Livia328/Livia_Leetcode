public class Q905 {
    /*
     * swap？
     * i表示all number before i is even
     * j表示all number after j is odd
     * 4，1，2，3
     * i    j
     */
    public int[] sortArrayByParity(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            // even
            if (nums[i] % 2 == 0) {
                i++;
            } else {
                // swap with j
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                j--;
            }
        }
        return nums;
    }
}
