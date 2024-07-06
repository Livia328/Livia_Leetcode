import java.util.*;
import java.util.stream.*;

public class Q771 {
    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> jewel = jewels.chars()
                           .mapToObj(c -> (char) c)
                           .collect(Collectors.toSet());
        Set<Character> count = new HashSet<>();
        int res = 0;
        for (char cur : stones.toCharArray()) {
            if (jewel.contains(cur)) {
                res++;
            }
        }
        return res;
    }
}
