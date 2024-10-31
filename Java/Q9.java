public class Q9 {
    /*
     * 将x翻转一下，也就是从高位生成数字y
     * 如果x和y相等，那么就是palindrome
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int tmp = x;
        // y是x翻转之后的数
        int y = 0;
        while (tmp > 0) {
            int lastNum = tmp % 10;
            tmp = tmp / 10;
            y = y * 10 + lastNum;
        }
        return y == x;
    }
}
