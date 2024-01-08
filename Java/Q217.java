package Java;
import java.util.*;

class Q217 {
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int item : nums) {
            if (set.contains(item)) {
                return true;
            }
            set.add(item);
        }
        return false;
    }
}