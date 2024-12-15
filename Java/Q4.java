public class Q4 {
    /*
     * brute force，2 pointer
     * 如果长度ood -> find the (totalLen/2 + 1)th smallest number
     * 如果长度even -> find the (totalLen/2), (totalLen/2 + 1)th number
     * 
     * 用2 pointer，来记录现在找到哪个位置了
     * 
     * 时间复杂度O(n + m), 空间复杂度O(1)
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int p1 = 0, p2 = 0;
        int count = 1;
        // num at position totalLen/2, and (totalLen/2 + 1)
        double firstNum = 0, secondNum = 0;
        while (p1 < n || p2 < m) {
            int cur;
            int num1 = p1 < n ? nums1[p1] : Integer.MAX_VALUE;
            int num2 = p2 < m ? nums2[p2] : Integer.MAX_VALUE;
            if (num1 < num2) {
                cur = num1;
                p1++;
            } else {
                cur = num2;
                p2++;
            }
            if (count == (m + n) / 2) {
                firstNum = cur;
            }
            if (count == (m + n) / 2 + 1) {
                secondNum = cur;
            }
            count++;
        }
        System.out.println(firstNum + "      " + secondNum);
        return (n + m) % 2 == 1 ? secondNum : (firstNum + secondNum) / 2;
    }

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    }

    /**
     * binary search 
     * 
     * 如果长度ood -> find the (totalLen/2 + 1)th smallest number
     * 如果长度even -> find the (totalLen/2), (totalLen/2 + 1)th number
     * 
     * 有一个helpter function, findKthElement
     * 
     * int index1 = Math.min(p1 + half, n) - 1;
     * int index2 = Math.min(p2 + half, m) - 1;
     * 
     * 比较 num1[index1], nums2[index2]
     *  -> 谁小的话，那么那个数组的那一半就要被舍弃
     * 
     * 如果num1小，那么nums1[p1] - nums[index1]这些元素都要被忽略
     * 更新k，也就是说那一半已经没了
     * 我们要找剩下部分中第 k - (index1 - p1 + 1)大的数
     * 
     * 1   2   3
     * p1
     * 4   5
     * p2
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
