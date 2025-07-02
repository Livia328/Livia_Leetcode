public class Q370 {
    /*
     * 差分数组
     * diff[i] = nums[i] - nums[i - 1];
     * 
     * 我们也可以根据diff Array还原
     * 
     * 比如想对[i,j]区间的元素全都+3
     * 那么可以让diff[i] += 3, diff[j] -= 3
     * 
     * diff[i] += 3让所有i之后的元素都加了3
     * diff[j] -= 3让所以j之后的元素都减了3
     * 所以最后只有[i..j]加了3
     * 
     * 所以这个题目只要遵循这一个原则即可
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        int diff[] = new int[length];
        for (int[] update : updates) {
            int start = update[0];
            int end = update[1];
            int val = update[2];
            increment(start, end, val, diff);
        }
        //返回结果数组
        int[] res = new int[length];
        res[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            res[i] = res[i - 1] + diff[i];
        }
        return res;
    }

    public void increment(int start, int end, int val, int[] diff) {
        diff[start] += val;
        if (end + 1 < diff.length) {
            diff[end + 1] -= val;
        }
    }
}
