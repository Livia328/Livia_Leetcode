package Java;

import javax.naming.ldap.ManageReferralControl;

public class Q11 {
    public static int maxArea(int[] height) {
        int L = 0, R = height.length - 1;
        int ans = 0;
        while (L < R) {
            ans = Math.max(ans, (R - L) * Math.min(height[L], height[R]));
            if (height[L] < height[R]) {
                L++;
            } else {
                R--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] test1 = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(test1));
    }
}
