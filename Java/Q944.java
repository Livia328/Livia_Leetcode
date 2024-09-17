import java.util.ArrayList;
import java.util.List;

public class Q944 {
    public int minDeletionSize(String[] strs) {
        int res = 0;
        for (int i = 0; i < strs[0].length(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < strs.length; j++) {
                sb.append(strs[j].charAt(i));
            }
            System.out.println(sb.toString());
            if (!isSorted(sb.toString())) {
                res++;
            }
        }
        return res;
    }

    public boolean isSorted(String s) {
        if (s.length() == 1) {
            return true;
        }
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
