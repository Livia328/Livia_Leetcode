class Q287 {
    /**
     * 转化成判断有无环的问题
     * 并且要找到确切环开始的点
     * 
     * slow fast指针
     * 
     * 3 step, head - cycle entrance - meet point 
     * head - cycle entrance x
     * cycle entrance - meet point y
     * meet point - cycle z
     * 
     * 慢指针走的: x + y
     * 快指针: x + y + n(y + z)
     * 
     * 因为快指针走的是慢指针的2倍
     * 2*(x + y) = x + y + n(y + z)
     * -> x + y = n(y + z)
     * -> x = (n - 1)y + z
     * 
     * 当n = 1的时候, x就等于z了
     * 所以从头节点出发一个slow，从相遇节点出发一个fast，当它们再次相遇的时候，就是入口的节点
     * 
     */
    public static int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        // 直到相遇停下
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                break;
            }
        }
        slow = 0;
        while (nums[slow] != nums[fast]) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return nums[slow];

    }
}