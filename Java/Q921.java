public class Q921 {
    /*
     * 首先是只能加不能减
     * 
     * 从左到右遍历，如果碰到'('，balance++
     * 如果碰到')'， balance--
     * 
     * 如果balance为-1，说明出现了这样的情况
     * ())
     * 
     * 这个时候就说明需要插入左括号了
     * res++；
     * int balance = 0
     * 
     * 结束后，balance可能为0，或者正数
     * 如果是正数说明（（（ ）
     * 需要添加右括号
     * 
     */
    public int minAddToMakeValid(String s) {
        int balance = 0;
        int res = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
                if (balance == -1) {
                    balance = 0;
                    res++;
                }
            }
        }
        // 结束后
        return res + balance;
    }
}
