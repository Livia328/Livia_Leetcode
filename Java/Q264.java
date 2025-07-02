public class Q264 {
    /*
     * 丑数肯定可以通过之前的丑数 * 2或者*3 * 5得到
     * 
     * 一开始的丑数列表
     * res[1]
     * 
     * 第一轮：
     * 第一轮： 1 -> 2, 3, 5 ，丑数列表变为 [1, 2, 3, 5]
        第二轮： 2 -> 4, 6, 10 ，丑数列表变为 [1, 2, 3, 4, 6, 10]
        第三轮： 3 -> 6, 9, 15 ，出现重复的丑数 6
     * 
     * 
     * 能被2整除的丑数：
     * 1 - 1*2 - 2*2 - 3*2 - 4*2 - 5*2 - 6*2 - 8*2 。。。
     * p2
     * 1 - 1*3 - 2*3 - 3*3 - ...
     * p3
     * 1 - 1*5 - 2*5 - 3*5
     * p5
     * 
     * 有点像链表合并
     * 用p2表示每个数字的进展到哪儿了
     * 
     * 下一个丑数候选一定是res[p2] * 2, res[p3] * 3, res[p5] * 5
     * 
     * 
     * 
     */
    public int nthUglyNumber(int n) {
        int p2 = 1, p3 = 1, p5 = 1;
        int prod2 = 1, prod3 = 1, prod5 = 1;
        int[] res = new int[n + 1];
        int p = 1;
        while (p <= n ) {
            // 取三个头中的最小的
            int min = Math.min(prod5, Math.min(prod2, prod3));
            res[p] = min;
            p++;
            // 移动对应指针
            if (min == prod2) {
                prod2 = 2 * res[p2];
                p2++;
            }
            if (min == prod3) {
                prod3 = 3 * res[p3];
                p3++;
            }
            if (min == prod5) {
                prod5 = 5 * res[p5];
                p5++;
            }
        }
        return res[n];
    }
}
