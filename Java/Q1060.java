import java.util.ArrayList;
import java.util.List;

public class Q1060 {
    /*
     * brute force way
     * 注意读题，不光是数组里的数，这个是无限延伸的
     * Input: nums = [1,2,4], k = 3
     * Output: 6
     * 
     * 先将数组里缺的数放进list，如果k <= list.size()，就直接返回list.get(k-1)，
     * 否则返回nums[nums.length - 1] + k - list.size()
     * 
     * 这个会memory limit
     * 所以比起真的放进一个list，可以不断递减k，判断有没有到第k个
     */
    public static int missingElement1(int[] nums, int k) {
        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != pre + 1) {
                for (int j = pre + 1; j < nums[i]; j++) {
                    k--;
                    if (k == 0) {
                        return j;
                    }
                }
            }
            pre = nums[i];
        }
        // 剩下的情况就是第k个是在nums[nums.length - 1]之后
        return nums[nums.length - 1] + k;
    }

    /*
     * 正如上面所说，有两种情况
     * 一种是missing number的个数小于k
     * 一种是missing number的个数大于k
     * 
     * 如果小于k，那么直接return  nums[n - 1] + k - missingNum;
     * 
     * 如果大于k，那么这个数肯定在数组中
     * 我们可以重复以上步骤
     * 
     * 将数组分成两半，如果前半段的missingNum >= k，那么说明k肯定在前半段
     * 否则一定在后半段
     * 
     * 这样我们就要有一个方法去快速知道在一段数组中缺了多少个元素
     * -> helper function遍历去数一共有多少个元素
     * -> 可以直接用和去减missingNum = nums[n - 1] - nums[0] + 1 - n;
     * 
     * 比如[4,7,9,10], 10 - 4 + 1= 7,说明里面应该有7个元素
     * 但实际只有4个，所以缺的就是 7 - 4 = 3个
     */
    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        int missingNum = getMissingCount(nums, n - 1);
        if (k > missingNum) {
            return nums[n - 1] + k - missingNum;
        }
        // 开始binary search
        int left = 0, right = n - 1;
        while (left < right) {
            int m = left + (right - left) / 2;
            // [L, M]
            int missingFirstPart = getMissingCount(nums, m);
            // 在前半段, 右边是inclusive的[L, M]
            if (missingFirstPart >= k) {
                right = m;
            } else {
                // [M + 1, R]
                left = m + 1;
            }
        }
        return nums[left - 1] + k - getMissingCount(nums, left - 1);
    }

    /*
     * 返回nums[0, index]之间缺少了多少个
     */
    public int getMissingCount(int[] nums, int index) {
        return nums[index] - nums[0] + 1 - (index + 1);
    }
}

    public static void main(String[] args) {
        missingElement(new int[]{1, 2, 4}, 3);
    }
}
