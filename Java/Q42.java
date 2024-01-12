package Java;

public class Q42 {
    /**
     * 对于一个cell来说，这一个格子里能接的水的高度，取决于min(max(左边所有柱子)， max（右边所有柱子）)
     * 因此我们可以先给出一个brute force
     * 
     * time: O(n^2)
     * space: O(1)
     */
    public static int trap(int[] height) {
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            // 找到左边的最大柱子
            int leftMax = 0, rightMax = 0;
            for (int j = 0; j <= i; j++) {
                if (height[j] > leftMax) {
                    leftMax = height[j];
                }
            }
            for (int j = i; j < height.length; j++) {
                if (height[j] > rightMax) {
                    rightMax = height[j];
                }
            }
            ans += Math.min(leftMax, rightMax) - height[i];
        }
        return ans;
    }

    /**
     * Memo, 在brute force上增加
     * 提前记录对于当前cell来说左边和右边最大的柱子
     * 
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public static int trap2(int[] height) {
        int[] lmax = new int[height.length]; //表示i左边最大的柱子
        int[] rmax = new int[height.length];
        lmax[0] = height[0]; rmax[height.length - 1] = height[height.length - 1];
        for (int i = 1; i < height.length; i++) {
            lmax[i] = Math.max(lmax[i-1], height[i]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            rmax[i] = Math.max(rmax[i+1], height[i]); 
        }
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            ans += Math.min(lmax[i], rmax[i]) - height[i];
        }
        return ans;
    }

    /**
     * 终极优化，two pointer
     * 动态地记录左右柱子的最大值和最小值
     * 
     * 用lmax 和 rmax两个柱子分别从左右往中间走
     * 他们代表了当前这个cell左边和右边的最大值
     */
    public static int trap3(int[] height) {
        int n = height.length;
        int L = 0, R = n - 1;
        int lmax = 0, rmax = 0;
        int ans = 0;
        while (L < R) {
            lmax = Math.max(lmax, height[L]);
            rmax = Math.max(rmax, height[R]);
            if (lmax < rmax) {
                ans += lmax - height[L];
                L++;
            } else {
                ans += rmax - height[R];
                R--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] test1 = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap3(test1));
    }
}
