public class Q4 {
    /*
     * brute force，用2 pointer把两个数组合并起来
     * 拿一个新数组存起来
     * 寻找中位数
     * 
     * 时间复杂度O(n + m), 空间复杂度O(n + m)
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        int[] arr = new int[nums1.length + nums2.length];
        int index = 0; // index for arr
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                arr[index++] = nums1[i++];
            } else {
                arr[index++] = nums2[j++];
            }
        }
        while (i < nums1.length) {
            arr[index++] = nums1[i++];
        }
        while (j < nums2.length) {
            arr[index++] = nums2[j++];
        }
        if ((nums1.length + nums2.length) % 2 == 1) {
            return 1.0 * arr[(nums1.length + nums2.length) / 2];
        } else {
            int num1 = arr[(nums1.length + nums2.length) / 2 - 1];
            int num2 = arr[(nums1.length + nums2.length) / 2];
            return (num1 + num2) / 2.0;
        }
        
    }


    /**
     * binary search 
     * 
     * if totalLen is ood -> find the (totalLen/2 + 1)th smallest number
     * if totalLen is even -> find the (totalLen/2), (totalLen/2 + 1)th number
     * 
     * 有一个helpter function, findKthElement
     * 
     * 比较 num1[p1 + k / 2 - 1], nums2[p2 + k / 2 - 1] -> 谁小的话，那么那个数组的那一半就要被舍弃
     * 然后每次更新k，现在要找的就是剩下部分中第 k - (index1 - p1 + 1)大的数
     * 
     * 1   2   3
     * p1
     * 4   5
     * p2
     * 
     * 
     * 
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int totalLen = n + m;
        if (totalLen % 2 == 1) {
            return findKthElement(nums1, nums2, totalLen/2 + 1);
        } else {
            return (findKthElement(nums1, nums2, totalLen/2) + findKthElement(nums1, nums2, totalLen/2 + 1)) / 2.0;
        }
    }

    public int findKthElement(int[] nums1, int[] nums2, int k) {
        int n = nums1.length, m = nums2.length;
        int p1 = 0, p2 = 0;
        while (true) {
            // nums1 越界
            if (p1 == n) {
                return nums2[p2 + k - 1]; 
            }
            // nums2越界
            if (p2 == m) {
                return nums1[p1 + k - 1];
            }
            // corner case
            if (k == 1) {
                return Math.min(nums1[p1], nums2[p2]);
            }
            int half = k / 2;
            int index1 = Math.min(p1 + half, n) - 1;
            int index2 = Math.min(p2 + half, m) - 1;
            int num1 = nums1[index1];
            int num2 = nums2[index2];
            // 如果num1小，那么nums1[0] - nums[index1]这些元素都要被忽略
            // 更新k，也就是说那一半已经没了，我们要找剩下部分中第 k - (index1 - p1 + 1)大的数
            if (num1 <= num2) {
                k -= index1 - p1 + 1;
                p1 = index1 + 1; //现在nums1从p1开始了
            } else {
                k -= index2 - p2 + 1;
                p2 = index2 + 1;
            }
        }
    }
}
