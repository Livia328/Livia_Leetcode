public class Q8 {
    /*
     * 1.先去掉leading space
     * 2.得到正负号
     * 3.得到数字（注意处理边界条件）
     */
    public static int myAtoi(String s) {
        // corner case
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 去除leading space
        //s = s.trim();
        int index = 0;
        while (index < s.length() && s.charAt(index) == ' ') {
            index++;
        }
        if (index == s.length()) {
            return 0;
        }
        boolean isNegative = false;
        // 得到符号
        // 有可能不出现
        if (s.charAt(index) == '-' || s.charAt(index) == '+') {
            isNegative = s.charAt(index) == '-';
            index++;
        }
        // 得到数字
        long num = 0;
        while (index < s.length() && s.charAt(index) >= '0' && s.charAt(index) <= '9' && s.charAt(index) != ' ') {
            num = num * 10 + (s.charAt(index) - '0');
            if (num > (long)Integer.MAX_VALUE) {
                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            // 处理越界条件
            index++;
        }
        // 得到最后答案
        return isNegative ? (int)-num : (int)num;
    }

    public static void main(String[] args) {
        int res = myAtoi("-91283472332");
        System.out.println(res);

    }
}

