import java.util.*;;

public class Q14 {
    /**
     * 看成一个二维数组，
     * 用一个嵌套 for 循环计算这个二维数组前面有多少列的元素完全相同即可。
     * 
     * input: strs = ["flower","flow","flight"]
     * col
     * |
     * flower   row
     * flow
     * flight
     * 
     * 一旦碰到不符合条件的直接返回已经遍历过的数组、
     * 时间复杂度：O(mn)
     * 空间复杂度：O(1)
     */
    public String longestCommonPrefix1(String[] strs) {
        int m = strs.length;
        int n = strs[0].length(); //以第一行的列为基准
        for (int col = 0; col < n; col++) {
            for (int row = 1; row < m; row++) {
                String cur = strs[row], prv = strs[row - 1];
                if (col >= cur.length() || col >= prv.length() || cur.charAt(col) != prv.charAt(col)) {
                    // 直接return
                    return strs[row].substring(0, col);
                }
            }
        }
        return strs[0];
    }

    /*
     * 排序之后双指针只比较第一个和最后一个
     * 时间复杂度：O(mlogm + n)
     * 空间复杂度：O(1)
     */
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        int index = 0;
        String s1 = strs[0];
        String s2 = strs[strs.length - 1];
        while (index < s1.length() && index < s2.length()) {
            if (s1.charAt(index) == s2.charAt(index)) {
                index++;
            } else {
                break;
            }
        }
        return s1.substring(0, index);
    }
}
