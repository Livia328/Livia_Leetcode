public class Q922 {
    public int[] sortArrayByParityII(int[] nums) {
        int[] res = new int[nums.length];
        // the latest index odd and even can use
        int i = 0, j = 1;
        for (int num : nums) {
            if (num % 2 == 0) {// it is even
                res[i] = num;
                i += 2;
            } else {
                res[j] = num;
                j += 2; 
            }
        }
        return res;
    }
}
