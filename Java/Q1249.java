public class Q1249 {
    /*
     * int balance
     * openSeen = 0
     * 
     * 从前往后遍历，去除多余的)：
     * 如果是(: balance++
     * 如果是)：1. balance == 0: not append
     *         2. balance > 0: append, balance--
     * 
     * 应该保留的open：openToKeep = openSeen - balance
     * 从前往后遍历
     * 
     */
    public String minRemoveToMakeValid(String s) {
        int balance = 0;
        int openSeen = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                openSeen++;
                balance++;
                sb.append(c);
            } else if (c == ')'){
                if (balance > 0) {
                    sb.append(')');
                    balance--;
                }
            } else {
                sb.append(c);
            }
        }
        StringBuilder res = new StringBuilder();
        int openToKeep = openSeen - balance;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '(') {
                if (openToKeep > 0) {
                    res.append('(');
                    openToKeep--;
                }
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }
}
