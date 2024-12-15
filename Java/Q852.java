public class Q852 {
    /*
     * 和Q162一样
     * 
     * binary search
     * 根据num[mid]和nums[mid +- 1]的关系
     
     * 如果走势下行（nums[mid] > nums[mid+1]）
     * 说明 mid 本身就是峰值或其左侧有一个峰值，所以需要收缩右边界（right = mid）；
     * 
     * 如果走势上行（nums[mid] < nums[mid+1]）
       则说明 mid 右侧有一个峰值，需要收缩左边界（left = mid + 1）
     
     Input: nums = [1,2,1,3,5,6,4]
     Output: 5
      0 1 2 3 4 5 6
      l.          r.   -> m = 3, nums[m] = 3
     [1,2,1,3,5,6,4]
     
     因为nums[3] < nums[4], 所以nums[3]肯定不可能是答案
     l = m + 1 = 4
     
     0 1 2 3 4 5 6
             l.  r.   -> m = 5, nums[5] = 
     [1,2,1,3,5,6,4]
     
     */
    public int peakIndexInMountainArray(int[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int m = l + (r - l ) / 2;
            // 走势下行
            // 说明答案是m或者在m的左边
            if (arr[m] > arr[m + 1]) {
                r = m; //m自己也可能是答案
            } else {
                // 因为arr[m] <= arr[m + 1]
                // 说明答案在右边，且m肯定不是答案
                l = m + 1;
            }
        }
        return l;
    }
}
