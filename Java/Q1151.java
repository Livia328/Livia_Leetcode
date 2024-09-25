public class Q1151 {
    /*
     * Minimum Swaps to Group all 1's Together
     * 
     * Given a binary array data, return the minimum number of swaps required 
     * to group all 1’s present in the array together in any place in the array.
     * 
     * Input: data = [1,0,1,0,1]
        Output: 1
        Explanation: There are 3 ways to group all 1's together:
        [1,1,1,0,0] using 1 swap.
        [0,1,1,1,0] using 2 swaps.
        [0,0,1,1,1] using 1 swap.
        The minimum is 1.
     */
    /*
     * 最后我们要得到的是一个window，里面有所有的1
     * 其实就是fixed length的sliding window
     * 看哪个window里的1最多，剩下的就是要swap的
     */
    public int minSwaps(int[] data) {
        // 先算出所有1的个数
        int ones = 0;
        for (int num : data) {
            if (num == 1) {
                ones++;
            }
        }   
        int l = 0, r = 0, count1 = 0, tempRes = Integer.MIN_VALUE;
        // [l, r)
        while (r < data.length) {
            int c = data[r]; r++;
            if (c == 1) {
                count1++;
            }
            if (r - l == ones) {
                tempRes = Math.max(tempRes, count1);
                int d = data[l]; l++;
                if (d == 1) {
                    count1--;
                }
            }
        }
        return tempRes == Integer.MIN_VALUE ? 0 : ones - tempRes;   
    }
}
