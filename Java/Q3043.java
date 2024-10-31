import java.util.HashSet;
import java.util.Set;

public class Q3043 {
    /*
     * 枚举arr1的所有前缀
     * [1,10,100]
     * 
     * set：1, 10, 1, 100, 10, 1
     * 然后对arr2中的每个数字枚举前缀，看是不是在arr1中
     * 
     * 在set中放入数字，最后Integer.toString(res).length()得到长度
     */
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr1) {
            while (num > 0) {
                set.add(num);
                num = num / 10;
            }
        }
        int res = 0;
        for (int num : arr2) {
            while (num > 0) {
                if (set.contains(num)) {
                    res = Math.max(res, num);
                    break;
                }
                num = num / 10;
            }
        }
        return res > 0 ? Integer.toString(res).length() : 0;
    }
}
