public class Q1031 {
    /*
     * 因为firstLen和secondLen无所谓谁先谁后
     * 可以有一个抽象函数，返回最大的就可以
     * helper(int[] nums, int firstLen, int secondLen)
     * helper(int[] nums, int secondLen, int firstLen)
     * 
     * helper：
     * 因为是求和，所以很自然的想到prefix
     * 我们只要枚举第一个subarry和第二个subarray开始的坐标就可以
     * 
     * 枚举第二个数组开始的坐标，从firstLen开始，到index + second - 1 < nums.length结束
     * 在这个过程中，可以不断update的第一个数组的最大值和他们和的最大值
     * 
     */
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        return Math.max(helper(prefix, firstLen, secondLen), helper(prefix, secondLen, firstLen));
    }

    public int helper(int[] prefix, int firstLen, int secondLen) {
        int firstMaxSum = 0;
        int res = 0;
        int index = firstLen; // secondLen的起点
        while (index + secondLen - 1 < prefix.length - 1) {
            // index既是secondLen开始的坐标，又是所有firstLen结束的坐标
            // 所以这样就可以得到firstLen的最大值
            firstMaxSum = Math.max(firstMaxSum, prefix[index] - prefix[index - firstLen]);
            res = Math.max(res, firstMaxSum + prefix[index + secondLen] - prefix[index]);
            index++;
        }
        return res;
    }
}
