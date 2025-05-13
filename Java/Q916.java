import java.util.ArrayList;
import java.util.List;

public class Q916 {
    /*
     * 首先因为和frequency有关
     * 所以很显然要用hashmap或者array去算frequency
     * 
     * 如果word要是words2的universal
     * 那么word每一个字母的frequency都要比words2的字母的frequency都要大
     * 所以可以首先求出words2中每个字母的frequency
     * 
     * 然后拿words1里的每一个单词的frequency去和words2 max比较
     */
    public List<String> wordSubsets(String[] words1, String[] words2) {
        int[] max2 = new int[26];
        for (String s : words2) {
            int[] tmp = counter(s);
            for (int i = 0; i < 26; i++) {
                max2[i] = Math.max(max2[i], tmp[i]);
            }
        }
        List<String> res = new ArrayList<>();
        for (String s : words1) {
            int[] tmp = counter(s);
            for (int i = 0; i < 26; i++) {
                if (tmp[i] < max2[i]) {
                    break;
                }
                // 如果每个字母都符合，那么就加进答案
                if (i == 25) {
                    res.add(s);
                }
            }
        }
        return res;
    }

    int[] counter(String word) {
        int[] count = new int[26];
        for (char c : word.toCharArray()) count[c - 'a']++;
        return count;
    }
}
