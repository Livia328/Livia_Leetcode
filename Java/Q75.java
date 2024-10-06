public class Q75 {
    /*
     * 记录个数
     */
    public void sortColors(int[] nums) {
        int c0 = 0, c1 = 0, c2 = 0;
        for (int num : nums) {
            if (num == 0) {
                c0++;
            } else if (num == 1) {
                c1++;
            } else {
                c2++;
            }
        }
        for (int i = 0; i < c0; i++) {
            nums[i] = 0;
        }
        for (int i = c0; i < c0 + c1; i++) {
            nums[i] = 1;
        }
        for (int i = c0 + c1; i < c0 + c1 + c2; i++) {
            nums[i] = 2;
        }
    }
    
    /**
     * 巧妙解法
     * 
     * i表示0 + 1 + 2 的个数
     * n1表示0 + 1的个数
     * n2表示0的个数
     * 
     * 等于第一次直接把所有数set成2
     * 22222222222 先全变成2
     * 11111122222 再变成1
     * 00011122222 再变成0
     */
    public void sortColors2(int[] nums) {
        int n0 = 0, n1 = 0;
        for(int i = 0; i < nums.length; i++){
            int num = nums[i];
            nums[i] = 2;
            if(num < 2){
                nums[n1++] = 1;
            }
            if(num < 1){
                nums[n0++] = 0;
            }
        }
    }
    /*
     * 2 pointer
     * [2,0,2,1,1,0]
     *  L         R
     *  M
     * 
     * L的物理意义是，L之前的都是0
     * R的物理意义是，R之后的都是2
     * 
     * M 正在travese的，以及M之前的都是1
     * 
     * [0, L) = 0
     * [L, m) = 1
     * [R, length - 1] = 2
     */
    public void sortColors1(int[] nums) {
        int L = 0, M = 0, R = nums.length - 1;
        // 跳出条件是M = R - 1也就是M和R之间没有元素了，都遍历完了
        while (M <= R) {
            if (nums[M] == 2) {
                // 需要换到最后去
                nums[M] = nums[R]; 
                nums[R] = 2;
                //此时M确定是2，但是R还不确定是什么
                //未来还需要再检查R（现在在M的位置上了）
                // 所以只挪动R，不挪动M
                R--;
            } else if (nums[M] == 0) {
                nums[M] = nums[L];
                nums[L] = 0;
                L++; 
                M++; // 因为L的值不是1就是0，所以M也要++
            } else {
                M++;
            }
        }
    }
}
