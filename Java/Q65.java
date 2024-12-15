public class Q65 {
    /*
     * 模拟：
     * 将字符串以e或者E区分
     * 
     * 如果存在e/E
     * 左边可以是整数或者浮点数，右边必须是整数
     * 
     * 如果不存在e/E
     * 整段可以是整数或者浮点数
     * 
     * 所以可以用很多个flag来表示
     * 
     * boolean pointSeen = false;
     * boolean eSeen = false;
     * boolean numberSeen = false;
     * 
     * 如果我们碰到了e
     *    如果之前碰到过e（重复），eSeen = true，那么return false
     *    如果之前没碰到过number，numberSeen = false，return false
     *  要把numberSeen重新变回false，因为
     * 
     * 如果我们碰到了.
     *    如果碰到过e，return false，因为e后面的number应该是整数
     *    如果number seen = false，return false，因为小数点前面应有个数字
     * 
     * 如果碰到+/-
     *    判断是否是第一位或者是否正好在e后面
     */
    public boolean isNumber(String s) {
        s = s.trim();
    
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('0' <= c && c <= '9') {
                numberSeen = true;
            } else if (c == 'e' || c == 'E') {
                if (eSeen || !numberSeen) {
                    return false;
                }
                // 因为e的后面也要有number
                numberSeen = false;
                eSeen = true;
            } else if (c == '.') {
                if (eSeen || pointSeen) {
                    return false;
                }
                pointSeen = true;
            } else if (c == '-' || c == '+') {
                // 5047e+6 is true
                if (i != 0 && !(s.charAt(i - 1) == 'e' || s.charAt(i - 1) == 'E')) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return numberSeen;
    }
}
