import java.util.*;

public class Q691 {
    /**
     * stickers = ["with","example","science"], target = "thehat"
     * 
     * 
     * BFS way
     * 
     * 对于target，逐个所有stickers， 用filter函数返回这个sticker匹配后剩下的string，
     * 也就是当前sticker匹配不上的，需要另一个sticker来匹配的
     * 
     * 比如thehat 用with匹配，剩下的就是ea，因为这两个字母thehat里没有
     * 也就是需要用另一个sticker来匹配
     * 
     * -> 为了方便寻找，我们可以sort target和每一个stickers
     * 这样相同字母就会连在一起
     * 
     * 然后用BFS
     * 
     * 在queue中放的是还需要拼的string
     * 同时需要set来保证进入queue的状态不重复
     * 
     * 将target入queue
     * queue:[thehat]
     * 
     * 比如thehat 用第一个贴纸with匹配，剩下的就是ea
     * 然后我们就要把ea放入queue，因为下一个我们就要检验它能不能被拼出来
     * queue:[ea]
     * 
     * 再将thehat example匹配：剩下thht，入queue
     * queue:[ea, thht]
     * 
     * filter函数？
     * 用两个array计算frequency
     * 
     * 因为是BFS，第一次remain为0的时候得到的就是最小step
     * 
     */
    public int minStickers(String[] stickers, String target) {
        // sort target and each stickers
        target = sortString(target);
        for (int i = 0; i < stickers.length; i++) {
            stickers[i] = sortString(stickers[i]);
        }
        // start bfs
        Queue<String> queue = new ArrayDeque<>();
        int steps = 0;
        Set<String> set = new HashSet<>();
        queue.add(target);
        set.add(target);
        while (!queue.isEmpty()) {
            steps++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                String cur = queue.poll();
                for (String sticker : stickers) {
                    String remain = filter(sticker, cur);
                    // 如果返回的remain是空的说明匹配上了
                    if (remain.isEmpty()) {
                        return steps;
                    }
                    // 如果有被匹配过，或者这个状态没有出现过，那么加入queue
                    if (!remain.equals(cur) && !set.contains(remain)) {
                        set.add(remain);
                        queue.add(remain);
                    }
                }
            }
        }
        return -1;
    }

    /*
     * sort each character of s
     */
    public String sortString(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

     /**
     * 将sticker里有的字母都贴上，返回这个贴纸里找不到的
     */
    public String filter(String sticker, String target) {
        int[] stickerFreq = new int[26];
        for (char c : sticker.toCharArray()) {
            stickerFreq[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        int[] targetFreq = new int[26];
        for (char c : target.toCharArray()) {
            if (stickerFreq[c - 'a'] > targetFreq[c - 'a']) {
                targetFreq[c - 'a']++;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
