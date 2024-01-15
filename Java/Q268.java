package Java;

class Q268 {
    public static int missingNumber(int[] nums) {
        int n = nums.length;
        long expect =  (0 + n) * (n + 1) / 2;
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        return (int)(expect - sum);
    } 
}