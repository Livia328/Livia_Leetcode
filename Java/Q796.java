public class Q796 {
    /*
     * 模拟
     * 外层循环控制s shift几位
     * 内存循环遍历全部字符串判断两个string是否相同
     */
    public boolean rotateString1(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) { // 旋转i位
            boolean flag = true;
            // 便利所有字符，判断两个string是否相同
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt((i + j) % s.length()) != goal.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

    /*
     * 如果能通过shift得到
     * 那么goal 一定存在于s + s中
     */
    public boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }
}
