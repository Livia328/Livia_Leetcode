import java.util.*;

public class Q762 {
    public int countPrimeSetBits(int left, int right) {
        Set<Integer> prime = new HashSet<>(Arrays.asList(2,3,5,7,11,13,17,19));
        int res = 0;
        for (int i = left; i <= right; i++) {
            int bits = 0;
            for (int n = i; n > 0; n >>= 1) {
                bits += n & 1;
            }
            res += prime.contains(bits) ? 1 : 0;
        }
        return res;
    }
}
