public class Q1614 {
    /*
     * (1+(2*3)+((8)/4))+1
     * 1  2   1 23 2 1 0
     * 
     * 遍历一遍，计算balance
     * 遇到（，balance++
     * 遇到），balance--
     * 
     * 然后用一个res
     * 每次都math.max(res, balance)
     */
    public int maxDepth(String s) {
        int balance = 0;
        int res = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
            }
            res = Math.max(balance, res);
        }
        return res;
    }
}
