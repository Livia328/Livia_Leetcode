public class Q493 {
    /*
     * 也就是我们要知道在i之后有多少个数是 < nums[i] / 2的
     * 这让我想起merge sort
     * 因为merge sort在merge的过程中，我们可以知道后面有多少个元素是小于i的
     * 
     * 比如
     * low     i      j high
     * 1  1 3  5  2 4 6 7
     *            |
     *          mid + 1
     * 
     * nums: 1 1 2 3 4
     *                 p
     * 
     * 接下来p上面要放的位置是nums[i]，因为i < j
     * 同时隐藏的信息是5后面比5小的数字是[mid + 1, j)
     */
    public int reversePairs(int[] nums) {
        sort(nums);
        return count;
    }

    private int[] tmp;
    public void sort(int[] nums) {
        tmp = new int[nums.length];
        sort(nums, 0, nums.length - 1);
    }

    public void sort(int[] nums, int low, int high) {
        if (low == high) {
            return;
        }
        int mid = low + (high - low) / 2;
        sort(nums, low, mid);
        sort(nums, mid + 1, high);
        merge(nums, low, mid, high);
    }

    private int count = 0;
    private void merge(int[] nums, int low, int mid, int high) {
        for (int i = low; i <= high; i++) {
            tmp[i] = nums[i];
        }
        // 这样会超时
        // 遍历每一个mid + 1到high之间的数，看是不是nums[i]的两倍
        // for (int i = low; i <= mid; i++) {
        //     for (int j = mid + 1; j <= high; j++) {
        //         if ((long)nums[i] > (long)nums[j] * 2) {
        //             count++;
        //         }
        //     }
        // }
        // 因为mid + 1到high也是sort好的
        // 所以只要找到第一个nums[j] * 2 >= nums[i]
        // 前面的都是符合条件的
        int end = mid + 1;
        for (int i = low; i <= mid; i++) {
            // nums 中的元素可能较大，乘 2 可能溢出，所以转化成 long
            while (end <= high && (long)nums[i] > (long)nums[end] * 2) {
                end++;
            }
            count += end - (mid + 1);
        }
        // 数组双指针技巧，合并两个有序数组
        int i = low, j = mid + 1;
        for (int p = low; p <= high; p++) {
            if (i == mid + 1) {
                nums[p] = tmp[j++];
            } else if (j == high + 1) {
                nums[p] = tmp[i++];
            } else if (tmp[i] > tmp[j]) {
                nums[p] = tmp[j++];
            } else {
                nums[p] = tmp[i++];
            }
        }
        
    }

}
