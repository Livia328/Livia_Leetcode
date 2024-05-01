import java.util.*;

public class Q500 {
    // 表明每个字母在哪一行
    // a - 1, b -2, c -2
    // 12210111011122000010020202
    // 然后只要检查每个字母对应的是否是同一行即可
    public String[] findWords(String[] words) {
        List<String> ans = new ArrayList<>();
        for (String c : words) {
            if (isOneRow(c)) {
                ans.add(c);
            }
        }
        String[] res = new String[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

    public boolean isOneRow(String c) {
        String check = "12210111011122000010020202";
        int curType = check.charAt(Character.toLowerCase(c.charAt(0)) - 'a');
        for (int i = 1; i < c.length(); i++) {
            int type = check.charAt(Character.toLowerCase(c.charAt(i)) - 'a');
            if (type != curType) {
                return false;
            }
        }
        return true;
    }
}