public class Q859 {
    /*
     * brute force 会超时
     */
    public boolean buddyStrings1(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (swap(s, i, j).equals(goal)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String swap(String s, int i, int j) {
        int small = Math.min(i, j), max = Math.max(i, j);
        char[] arr = s.toCharArray();
        char tmp = arr[small];
        arr[small] = arr[max];
        arr[max] = tmp;
        return String.valueOf(arr);
    }

    /*
     * 反向思考，要交换两个数字
     * 说明s和goal只能有两个diff，且这两个位置的字母要相同
     * 
     * 同时，还有一种情况是s == goal，且有一个字母出现两次（可以交换这个重复字母）
     */
    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        int small = -1, big = -1, diff = 0;
        int[] count = new int[26];
        boolean canSwitch = false; // whether can swap the same char, e.g. s == goal
        for (int i = 0; i < s.length(); i++) {
            if (++count[s.charAt(i) - 'a'] >= 2) {
                canSwitch = true;
            }
            if (s.charAt(i) != goal.charAt(i)) {
                diff++;
                if (small == -1) { // first index
                    small = i;
                } else if (big == -1) {
                    big = i;
                } else {
                    // 已经有三个diff了，直接FAlSE
                    return false;
                }
            }
        }
        return (diff == 0 && canSwitch) || (diff == 2 && s.charAt(small) == goal.charAt(big) && s.charAt(big) == goal.charAt(small));
    }
}
