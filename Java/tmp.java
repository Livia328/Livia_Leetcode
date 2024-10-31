import java.util.*;

public class tmp {
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
