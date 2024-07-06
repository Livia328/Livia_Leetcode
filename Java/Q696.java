import java.util.*;

public class Q696 {
    /*
     * 中心扩展法，类似于回文串
     * 当找到“01”或者“10”的时候就可以朝两边扩展
     * 
     */
    int count = 0;
    public int countBinarySubstrings(String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                getCount(s, i - 1, i);
            }
        }
        return count;
    }

    public void getCount(String s, int start, int end) {
        // base case
        char first = s.charAt(start);
        char second = s.charAt(end);
        while (start >= 0 && end < s.length() && s.charAt(start) == first && s.charAt(end) == second) {
            start--;
            end++;
            count++;
        }
    }

}