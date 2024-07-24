import java.util.*;

public class Q819 {
    /*
     * 因为还有别的标点，所以不能直接split
     */
    public String mostCommonWord(String paragraph, String[] banned) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>(Arrays.asList(banned));
        StringBuilder sb = new StringBuilder();
        int maxFreq = 0;
        for (int i = 0; i <= paragraph.length(); i++) {
            // find the word
            if (i < paragraph.length() && Character.isLetter(paragraph.charAt(i))) {
                sb.append(Character.toLowerCase(paragraph.charAt(i)));
            } else if (sb.length() > 0){
                String cur = sb.toString();
                if (!set.contains(cur)) {
                    int curFreq = map.getOrDefault(cur, 0) + 1;
                    map.put(cur, curFreq);
                    maxFreq = Math.max(maxFreq, curFreq);
                }
                sb.setLength(0);
            }

        }
        String ans = "";
        for (String key : map.keySet()) {
            if (map.get(key) == maxFreq) {
                ans = key;
                break;
            }
        }
        return ans;
    }
}
