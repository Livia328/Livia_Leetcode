import java.util.HashSet;
import java.util.Set;

public class Q888 {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int sumA = 0, sumB = 0;
        Set<Integer> a = new HashSet<>();
        for (int cur : aliceSizes) {
            sumA += cur;
            a.add(cur);
        }
        for (int cur : bobSizes) {
            sumB += cur;
        }
        int dif = (sumA - sumB) / 2;
        for (int b : bobSizes) {
            int target = b + dif;
            if (a.contains(target)) {
                return new int[]{target, b};
            }
        }
        return null;
    }
}
