import java.util.*;

public class Q414 {
    /**
     * treeset
     */
    public int thirdMax1(int[] nums) {
        // 从大到小排列
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
        }
        if (set.size() >= 3) {
            set.pollLast();
            set.pollLast();
        }
        return set.last();
    }

    /**
     * 一次遍历
     * 用a, b, c来维护数组中的最大值，次大值和第三大值
     * 一开始要初始化成Long.min
     * 
     * if num > a, c->b, b->a, a-> num
     * if a > num > b, c->b, b-> num
     * if b > num > c, c-> num
     */
    public int thirdMax(int[] nums) {
        long a = Long.MIN_VALUE, b = Long.MIN_VALUE, c = Long.MIN_VALUE;
        for (long num : nums) {
            if (num > a) {
                c = b;
                b = a;
                a = num;
            } else if (a > num && num > b) {
                c = b;
                b = num;
            } else if (b > num && num > c) {
                c = num;
            }
        }
        if (c == Long.MIN_VALUE) {
            return (int)a;
        }
        return (int)c;
    }
}
