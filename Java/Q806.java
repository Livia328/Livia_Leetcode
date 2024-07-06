public class Q806 {
    /*
     * 注意不能够简单地 lines += count / 100
     * 他是算个数的
     */
    public int[] numberOfLines(int[] widths, String s) {
        int count = 0;
        int lines = 0;
        for (char c : s.toCharArray()) {
            if (count + widths[c - 'a'] > 100) {
                lines++;
                count = widths[c - 'a'];
            } else if (count + widths[c - 'a'] == 100) {
                lines++;
                count = 0;
            } else {
                count += widths[c - 'a'];
            }
        }
        if (count == 0) {
            return new int[]{lines, 100};
        }
        return new int[]{lines + 1, count};
    }
}
