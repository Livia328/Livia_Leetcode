public class Q680 {
    /*
     * 双指针，左右，往中间逼近
     * 如果一样就继续，不然就判断
     * 
     * validPalindrome(s, left + 1, right) || validPalindrome(s, left, right - 1)
     * 是否可以是palindrome
     */
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return validPalindrome(s, left + 1, right) || validPalindrome(s, left, right - 1);
            }
        }
        return true;
    }

    /*
     * overload
     */
    public boolean validPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
