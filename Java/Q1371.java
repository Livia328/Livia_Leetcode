import java.util.*;

public class Q1371 {
    /*
     * 暴力解法，遍历所有的substring，再检查其中的vowels的个数
     * 但这样的时间复杂度会很大
     * —> 想到前缀和，这样就可以很快的得到某个区间内的个数
     * 
     * int[][]，int[i][k]表示前i个数组中，第k个原因字母出现了几次
     * 
     * 可以用hashmap来压缩，因为我们要求偶数，所以两个前缀和 pre[i][k] 和 pre[j][k] 的奇偶性一定是相同的
     * 所以其实我们只需要维护他的奇偶性状态就可以
     * 可以用0或者1表示,用一个bit位数来表示，0表示偶数，1表示奇数
     * 
     * 比如ab
     * u o i e a
     * 0 0 0 0 1        
     * 
     * 因为奇数-奇数=偶数，偶数-偶数=偶数，
     * 所以如果每个字母都出现了偶数次，那么两个index对应的状态是一样的
     * 
     * 所以用map存状态和对应的index
     * map, key: int, state, val, index
     * 一边遍历一边存
     * 
     */
    public int findTheLongestSubstring(String s) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // 表示在字符串-1的位置，所有原音都出现0次，所以是00000
        map.put(0, -1);
        int state = 0, res = 0;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == 'a' || cur == 'e' || cur == 'i' || cur == 'o' || cur == 'u') {
                state ^= (1 << ((int)(cur - 'a')));
            }
            if (map.containsKey(state)) {
                res = Math.max(res, i - map.get(state));
                // 此时不更新index，因为我们想要更长的，所以存的肯定是最早遇到的那个index
            } else {
                map.put(state, i);
            }
        }
        return res;
    }
}
