import java.util.*;
import java.util.ArrayList;
import java.util.ArrayList;

public class Q249 {
    /*
     * 找到pattern
     * 找到第一个char和‘a'的距离
     * 将所有字母都减去这个距离
     * 
     * 然后用hashmap存起来
     * key是pattern，value是所有的string
     * 
     * ["abc","bcd","acef","xyz","az","ba","a","z"]
     * 
     * abc -> abc
     * bcd 
     * b - a = 1, 所有bcd都减1
     * bcd -> abc
     * 
     */
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strings) {
            int shift = s.charAt(0) - 'a';
            char[] shifedArr = s.toCharArray();
            for (int i = 0; i < shifedArr.length; i++) {
                char newChar = (char) (shifedArr[i] - shift);
                if (newChar < 'a') {
                    newChar += 26;
                }
                shifedArr[i] = newChar;
            }
            String pattern = new String(shifedArr);
            map.putIfAbsent(pattern, new ArrayList<>());
            map.get(pattern).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
