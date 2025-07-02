public class Q870 {
    /*
     * 可以从特殊情况开始思考
     * 先考虑nums2中的最大数
     * 很显然我们应该拿nums1中的最大数和他比较
     * 
     * 如果nums1中的最大数比nums2小
     * 那么没有必要浪费他，直接拿nums1中的最小数消极对待
     * 
     * 如果nums1中的最大数比nums2大
     * 那么我们就派nums1中的最大数迎战
     * （注意此时不用考虑节约一下nums1中的最大数，派第二大的上）
     * （因为已经是对面最大的数了，节约了没有用）
     * 
     * 以此类推
     * 
     * 所以可以将两个数组排序后，进行比较
     * 但是因为nums2要keep index
     * 解决办法有两种
    1. 用priorityqueue, 里面再封装{i, nums2[i]}
    2. 新建一个新数组，里面存放nums2排序后的index
     */
    public int[] advantageCount(int[] nums1, int[] nums2) {
        // 根据nums2的大小从大到小排序
        PriorityQueue<int[]> maxpq = new PriorityQueue<>((pair1, pair2) -> pair2[1] - pair1[1]);
        for (int i = 0; i < nums1.length; i++) {
            maxpq.offer(new int[]{i, nums2[i]});
        }
        Arrays.sort(nums1);
        // 用来指
        int right = nums1.length - 1, left = 0;
        int[] res = new int[nums1.length];
        while (!maxpq.isEmpty()) {
            // 找到nums2里的最大值
            int[] cur = maxpq.poll();
            int i = cur[0], val = cur[1];
            if (nums1[right] > val) {
                // 那么就自己上
                res[i] = nums1[right];
                right--;
            } else {
                res[i] = nums1[left];
                left++;
            }
        }
        return res;
    }

    public int[] advantageCount(int[] nums1, int[] nums2) {
        int len = nums1.length;
        // 用来记录index
        Integer[] sortedIndex = new Integer[len];
        for (int i = 0; i < len; i++) {
            sortedIndex[i] = i;
        }
        // sorteing sortedIndex by nums2[i]
        // sorting in descending order
        // 这样sortedIndex中就是nums2从大到小的index了
        Arrays.sort(sortedIndex, (a, b) -> nums2[b] - nums2[a]);
        Arrays.sort(nums1);
        int[] res = new int[len];
        int left = 0, right = len - 1;
        for (int i = 0; i < len; i++) {
            // nums2[sortedIndex[i]] is the biggest in nums2
            if (nums2[sortedIndex[i]] < nums1[right]) {
                res[sortedIndex[i]] = nums1[right];
                right--;
            } else {
                res[sortedIndex[i]] = nums1[left];
                left++;
            }
        }
        return res;
    }
}
