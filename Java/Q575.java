import java.util.*;

public class Q575 {
    public int distributeCandies(int[] candyType) {
        Set<Integer> set = new HashSet<>();
        for (int type : candyType) {
            set.add(type);
        }
        return candyType.length / 2 > set.size() ? set.size() : candyType.length / 2;
    }
}
