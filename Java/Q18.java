import java.util.*;;

public class Q18 {
    /*
     * 从3sum来
     * 3sum是固定一个数，然后l, r双指针
     * 
	 * 先排序
	 * 固定第一个数，然后L和R头尾
	 * -1,0,1,2,-1,-4
	 * 排序：
	 * -4,-1,-1,0,1,2
	 *  i  L        R
	 * 
	 * 每次进入while loop的时候都去重
	 * 让i, L, R到相同的数序列的最后一个位置上
	 * 如果curSum = 0,那么加入答案
	 * 如果curSum > 0，那么需要缩小，R--
	 * 如果curSum > 0，那么需要增大，R++
     * 
     * 4sum就是在先固定第一个数，再调用3sum
     * 但是对于这个3sum，需要传入start
     * 
     * 3sum从start开始固定
     * 
     * 3sum又可以变成2sum
     * 就是l 和 r
     * 所以可以抽象成一个4sum函数
     * 
     * base case
     * 是n == 2的情况
     * 只要l和r即可
     * 
     * 别的情况就是固定第一个数，然后递归调用n - 1
	 */
    // public List<List<Integer>> fourSum(int[] nums, int target) {
    //     // 数组需要排序
    //     Arrays.sort(nums);
    //     int n = nums.length;
    //     List<List<Integer>> res = new ArrayList<>();
    //     // 穷举 fourSum 的第一个数
    //     for (int i = 0; i < n; i++) {
    //         // 对 target - nums[i] 计算 threeSum
    //         List<List<Integer>> triples = threeSum(nums, i + 1, target - nums[i]);
    //         // 如果存在满足条件的三元组，再加上 nums[i] 就是结果四元组
    //         for (List<Integer> triple : triples) {
    //             triple.add(nums[i]);
    //             res.add(triple);
    //         }
    //         // fourSum 的第一个数不能重复
    //         while (i < n - 1 && nums[i] == nums[i + 1]) {
    //             i++;
    //         }
    //     }
    //     return res;
    // }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        // n 为 4，从 nums[0] 开始计算和为 target 的四元组
        return nSumTarget(nums, 4, 0, target);
    }

    // 注意：调用这个函数之前一定要先给 nums 排序
    // n 填写想求的是几数之和，start 从哪个索引开始计算（一般填 0），target 填想凑出的目标和
    private List<List<Integer>> nSumTarget(int[] nums, int n, int start, long target) {
        int size = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        // 至少是 2Sum，且数组大小不应该小于 n
        if (n < 2 || size < n) return res;
        // 2Sum 是 base case
        if (n == 2) {
            // 双指针那一套操作
            int l = start, r = size - 1;
            while (l < r) {
                int sum = nums[l] + nums[r];
                int left = nums[l], right = nums[r];
                if (sum < target) {
                    // 减枝
                    while (l < r && nums[l] == left){
                        l++;
                    }
                } else if (sum > target) {
                    while (l < r && nums[r] == right) {
                        r--;
                    }
                } else {
                    res.add(new ArrayList<>(Arrays.asList(left, right)));
                    while (l < r && nums[l] == left) {
                        l++;
                    }
                    while (l < r && nums[r] == right) {
                        r--;
                    }
                }
            }
        } else {
            // n > 2 时，固定一个数，递归计算 (n-1)Sum 的结果
            for (int i = start; i < size; i++) {
                List<List<Integer>> sub = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> arr : sub) {
                    // (n-1)Sum 加上 nums[i] 就是 nSum
                    arr.add(nums[i]);
                    res.add(arr);
                }
                while (i < size - 1 && nums[i] == nums[i + 1]) i++;
            }
        }
        return res;
    }
}
