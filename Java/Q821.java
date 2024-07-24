import java.util.*;

public class Q821 {
    /*
     * 两次遍历，一次找左边的，一次找右边的
     */
    public int[] shortestToChar(String s, char c) {
        int[] res = new int[s.length()];
        Arrays.fill(res, s.length() + 1);
        int index = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                index = i;
            }
            if (index != -1) {
                res[i] = i - index;
            }
        }
        // second loop
        index = -1;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == c) {
                index = i;
            }
            if (index != -1) {
                res[i] = Math.min(res[i], index - i);
            }
        }
        return res;
    }
}
