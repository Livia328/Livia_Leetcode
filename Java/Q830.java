import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q830 {
    public static List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> res = new ArrayList<>();
        int curLen = 1;
        for (int i = 0; i < s.length(); i++) {
            if (i == s.length() - 1 || s.charAt(i) != s.charAt(i + 1)) {
                if (curLen >= 3) {
                    res.add(Arrays.asList(i - curLen + 1, i));
                }
                curLen = 1;
            } else {
                curLen++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> res = largeGroupPositions("abbxxxxzzy");
    }
}
 