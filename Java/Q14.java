public class Q14 {
    public String longestCommonPrefix1(String[] strs) {
        int minLen = strs[0].length();
        for (String s : strs) {
            minLen = Math.min(minLen, s.length());
        }
        int index = 0;
        StringBuilder sb = new StringBuilder();
        boolean valid = true;
        while (valid && index < minLen) {
            char c = strs[0].charAt(index);
            for (String s : strs) {
                if (s.charAt(index) != c) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                sb.append(c);
            }
            index++;
        }
        return sb.toString();
    }

    /**
     * Labuladong
     * 看成一个二维数组，
     * 用一个嵌套 for 循环计算这个二维数组前面有多少列的元素完全相同即可。
     */
    public String longestCommonPrefix(String[] strs) {
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
}
