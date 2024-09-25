import java.util.*;

public class Q767 {
    /*
     * 贪心，先得到每个字母出现的次数
     * 按照次数排序
     * 从最大的开始考虑起
     * 
     * 注意要考虑最后剩下在Pq里的情况
     */
    public String reorganizeString(String s) {
        // corner case
        if (s.length() < 2) {
            return s;
        }
        // 先count
        int[] count = new int[26];
        int max = 0;
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
            max = Math.max(max, count[c - 'a']);
        }
        // edge case 如果最多出现的次数已经比len的一半还要长了，那么肯定就不能做到有空隙了
        if (max > (s.length() + 1) / 2) {
            return "";
        }
        // 按照出现次数排序
        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> count[b - 'a'] - count[a - 'a']);
        for (char c = 'a'; c <= 'z'; c++) {
            if (count[c - 'a'] > 0) {
                pq.offer(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (pq.size() > 1){ //因为我们要poll两个
            char c1 = pq.poll();
            char c2 = pq.poll();
            sb.append(c1); sb.append(c2);
            count[c1 - 'a']--; count[c2 - 'a']--;
            if (count[c1 - 'a'] > 0) {
                pq.offer(c1);
            }
            if (count[c2 - 'a'] > 0) {
                pq.offer(c2);
            }
        }

        if (pq.size() > 0) {
            sb.append(pq.poll());
        }
        return sb.toString();
    }
}
