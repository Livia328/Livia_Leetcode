import java.util.*;

public class Q804 {
    public int uniqueMorseRepresentations(String[] words) {
        Set<String> set = new HashSet<>();
        for (String word : words) {
            String curMode = getMorseMode(word);
            if (!set.contains(curMode)) {
                set.add(curMode);
            }
        }
        return set.size();
    }

    public String getMorseMode(String s) {
        String[] morseMap = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(morseMap[c - 'a']);
        }
        return sb.toString();
    }
}
