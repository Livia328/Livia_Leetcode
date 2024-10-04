import java.util.*;

import com.apple.laf.resources.aqua;

public class Q1338 {
    public int minSetSize(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        List<Integer> count = new ArrayList<>(map.values());
        Collections.sort(count, (a, b) -> b - a);
        int halfLength = arr.length / 2;
        int removeCount = 0;
        int minSize = 0;
        for (int c : count) {
            removeCount += c;
            minSize++;
            if (removeCount >= halfLength) {
                break;
            }
        }
        return minSize;
    }
}
