public class Q392 {
    /*
     * s.length > t.length return false
     * 双指针
     * 
     * s: abc
     *    i
     * 
     * t: ahbgdc
     *    j
     * 
     * 
     * 碰到一样的就是i和j都++
     * 否则只有j++
     * 
     * 最后看i是否走完
     */
    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        if (n > m) {
            return false;
        }
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }
}
