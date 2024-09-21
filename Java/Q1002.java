import java.util.*;

public class Q1002 {
    public List<String> commonChars(String[] words) {
        int[] res = new int[26];
        Arrays.fill(res, Integer.MAX_VALUE);
        for (String word : words) {
            int[] cur = new int[26];
            for (char c : word.toCharArray()) {
                cur[c - 'a']++;
            }
            System.out.println(Arrays.toString(cur));
            for (int i = 0; i < 26; i++) {
                if (cur[i] == 0) {
                    res[i] = 0;
                } else {
                    res[i] = Math.min(res[i], cur[i]);
                }
            }
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (res[i] == 0) {
                continue;
            }
            for (int j = 0; j < res[i]; j++) {
                ans.add(Character.toString('a' + i));
            }
        }
        return ans;
    }
}
