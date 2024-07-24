import java.util.ArrayList;
import java.util.List;

public class Q93 {
    List<String> res = new ArrayList<>();
    List<String> path = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        backtrack(s, 0);
        return res;
    }

    public void backtrack(String s, int start) {
        // base case, add ans
        if (start == s.length() && path.size() == 4) {
            res.add(String.join(".", path));
            return;
        }
        // 枚举一个valid的ip item, s[start, i]
        for (int i = start; i < s.length(); i++) {
            if (!isValid(s, start, i)) {
                continue;
            }
            // 找到了一个合法的item
            // 先判断有没有四个了
            if (path.size() >= 4) {
                break;
            }
            //s[start, i]可以被加入path
            // choose
            path.add(s.substring(start, i + 1));
            // backtrack
            backtrack(s, i + 1);
            // undo choice
            path.remove(path.size() - 1);
        }
    }

    public boolean isValid(String s, int start, int end) {
        int length = end - start + 1;
        if (length == 0 || length > 3) {
            return false;
        }
        if (length == 1) {
            return true;
        }
        // 多于一位数，但是开头是0，肯定不对
        if (s.charAt(start) == '0') {
            return false;
        }
        if (length <= 2) {
            return true;
        }
        // 到了三位数的情况
        if (Integer.parseInt(s.substring(start, end + 1)) > 255) {
            return false;
        }
        return true;
    }
}
