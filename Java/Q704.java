public class Q704 {
    /**
     * 左右都闭合的binary search
     * 终止条件：left <= right, left == right+1时才跳出循环
     */
    public int search(int[] nums, int target) {
        //[left, right]
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                //去左边找, 下一个区间时[left, mid - 1]
                right = mid - 1;
            } else {
                //去右半边找，下一个区间是[mid + 1, right]
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * [left, right)的binary search
     * 终止条件，while (left < right)， 此时区间为[right, right)，搜索区间为空，可以结束
     */
    public int search2(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                // 去左半边找，修改right
                //下一个搜索区间[left, mid)
                //因为mid已经检查过了，所以右边到mid截止不包含
                right = mid;
            } else if (nums[mid] < target) {
                // 去右半边找，修改left
                // 下一个搜索区间为[mid + 1, right)
                // 因为mid已经检查过了，左边边界从mid + 1开始
                left = mid + 1;
            }
        }
        return -1;
    }
}
