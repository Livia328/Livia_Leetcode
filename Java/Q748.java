import java.util.*;

public class Q748 {
    /*
    实现过程中有很多细节不要忘了

    比如只要letter，比如case is not sensitive

     * step1: 先根据licensPlate存hashmap
     * setp2：写一个function判断是不是word
     */
    HashMap<Character, Integer> target;
    public String shortestCompletingWord(String licensePlate, String[] words) {
        target = new HashMap<>();
        for (char c : licensePlate.toCharArray()) {
            if (Character.isLetter(c)) {
                target.put(Character.toLowerCase(c), target.getOrDefault(Character.toLowerCase(c), 0) + 1);
            }
        }
        String ans = "";
        int curLength = Integer.MAX_VALUE;
        for (String word : words) {
            if (isCompletingWord(word)) {
                if (word.length() < curLength) {
                    ans = word;
                    curLength = word.length();
                }
            }
        }
        return ans;
    }

    public boolean isCompletingWord(String s) {
        HashMap<Character, Integer> cur = new HashMap<>();
        for (char c : s.toCharArray()) {
            cur.put(Character.toLowerCase(c), cur.getOrDefault(Character.toLowerCase(c), 0) + 1);
        }
        for (Character key : target.keySet()) {
            if (!cur.containsKey(key)) {
                return false;
            }
            if (cur.get(key) < target.get(key)) {
                return false;
            }
        }
        return true;
    }
}
