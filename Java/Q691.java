import java.util.*;

public class Q691 {
    /**
     * stickers = ["with","example","science"], target = "thehat"
     * 
     * 
     * BFS way
     * 
     * 将target和sticker sort
     * 
     * 对于target，遍历所有stickers， 用filter函数返回这个sticker匹配后剩下的string，
     * 也就是当前sticker匹配不上的，需要另一个sticker来匹配的
     * 
     * queue中放的是还需要拼的string
     * 同时需要set来保证进入queue的状态不重复
     * 
     * 将target入queue
     * queue:[thehat]
     * 
     * 比如thehat 用with匹配，剩下的就是ea，因为这两个字母thehat里没有
     * 然后我们就要把ea放入queue，因为下一个我们就要检验它能不能被拼出来
     * queue:[ea]
     * 
     * 再将thehat example匹配：剩下thht，入queue
     * queue:[ea, thht]
     * 
     * 因为一个贴纸最多有两个t，那它也只能匹配掉target里的两个t
     * 所以我们用index来找，index一直增加，如果后面找不到，也就是没了
     * 
     *
     * 
     * 因为是BFS，第一次remain为0的时候
     * 
     */
    public int minStickers(String[] stickers, String target) {
        // 将target和每一个stickers都sort好
        target = sortString(target);
        for (int i = 0; i < stickers.length; i++) {
            stickers[i] = sortString(stickers[i]);
        }
        // queue中装的是还需要贴纸的string
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(target);
        int steps = 0;
        // 用来存放状态，以免同一个状态进入queue多次
        Set<String> visited = new HashSet<>();
        // start bfs
        while (!queue.isEmpty()) {
            // 每进入一层step就加
            steps++;
            int n = queue.size();
            // 遍历这一层里的所有元素
            for (int i = 0; i < n; i++) {
                // 当前需要贴贴纸的string
                String cur = queue.poll();
                // 遍历每一个贴纸
                for (String sticker : stickers) {
                    String remain = filter(cur, sticker);
                    // 如果返回的时候remain是空的，说明都匹配上了
                    // 因为是BFS说明它肯定是第一个匹配上的，那肯定就是最短的
                    if (remain.isEmpty()) {
                        return steps;
                    }
                    // 如果有被匹配过，并且这个状态没有出现过，那么久放入
                    if (!remain.equals(cur) && !visited.contains(remain)) {
                        visited.add(remain);
                        queue.offer(remain);
                    }
                }
            }
        }
        return -1;
    }


    /**
     * 将sticker里有的字母都贴上，返回这个贴纸里找不到的
     */
    public String filter(String target, String sticker) {
        StringBuilder remain = new StringBuilder();
        int index = 0;
        // 遍历每一个字母
        for (char c : target.toCharArray()) {
            boolean found = false;
            // 看看当前sticker中可不可以找到这个字母
            while (index < sticker.length() && sticker.charAt(index) <= c) {
                if (sticker.charAt(index++) == c) {
                    found = true;
                    break;
                }
            }
            // 如果找不到，说明这个贴纸里没有
            if (!found) {
                remain.append(c);
            }
        }
        return remain.toString();
    }

    /**
     * sort a string
     */
    private String sortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
