import java.util.*;

public class Q215 {
    /*
     * 维护大小为k个的min heap
     * 每次加入元素后，如果个数超过了k
     * 那么就poll出一个
     * 
     * 到最后pq.peek就是第k个元素
     */
    public int findKthLargest1(int[] nums, int k) {
        // min heap
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        for (int num : nums) {
            pq.add(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return (int)pq.peek();
    }

    /*
     * quick select
     * 
     * 有一个pivot，
     * 每次将比pivot小的数都移动到它的左边
     * 将比pivot大的数都移动到它的右边
     * 
     * 这样我们就确定了pivot的位置
     * 
     * 然后对左边右边分别递归，重复执行以上操作
     * 
     * 原地quick select
     * 
     * 每次先将pivot的数放在最右, pivotNum = 4
     *  l       p r
     * [5,3,2,9,4,0]
     * swap(pIndex, right)
     * 
     * 然后从最左开始找
     *  p
     *  i         r
     * [5,3,2,9,0,4]     5 > pivotNum, 不动
     * 
     *  p
     *     i         
     * [5,3,2,9,0,4]     3 <= pivotNum,和p位置交换,p++
     * 
     *    p
     *      i         
     * [3,5,2,9,0,4]
     * 
     *      p
     *        i         
     * [3,2,5,9,0,4]  2 <= pivotNum,和p位置交换,p++
     * 
     *      p
     *          i         
     * [3,2,5,9,0,4]  9 > pivotNum, 不动
     * 
     * 
     *        p
     *          i         
     * [3,2,0,9,5,4]  0 <= pivotNum,和p位置交换,p++
     * 
     * 最后再把right和p交换
     * 结束
     *        p
     *          i         
     * [3,2,0,4,5,5]  
     */
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length-1, nums.length-k);
    }
    
    private int quickSelect(int[] nums, int left, int right, int k) {
        // 只剩下一个数了，返回
        if (left == right) {
            return nums[left];
        }
    
        // 用随机的方法在[left, right]中找一个数
        int pIndex = new Random().nextInt(right - left + 1) + left;
        // partition函数返回sort好后这个数的坐标
        pIndex = partition(nums, left, right, pIndex);
    
        // 如果正好是第k个，那么直接返回
        if (pIndex == k) {
            return nums[k];
        }
        // k在当前pivot的右边，那肯定要去右边找
        if (pIndex < k) {
            return quickSelect(nums, pIndex+1, right, k);
        }
        return quickSelect(nums, left, pIndex-1, k);
    }
    
    /*
     * 返回sort后这个数的坐标
     */
    private int partition(int[] nums, int left, int right, int pIndex) {
        int pivot = nums[pIndex];
        swap(nums, pIndex, right);
        pIndex = left;
    
        for (int i=left; i<=right; i++) 
            if (nums[i] <= pivot) 
                swap(nums, i, pIndex++);
    
        return pIndex - 1;
    }
    
    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
