import java.util.*;

public class Q126 {
    /*
     * 有点像graph，beginWord是起点，endWord是终点
     * 26个字母是所有可能的path
     * 就是对于每一位，如果改了一个字母后，这个字母在wordList中的话，那么这条路就是可以走的
     * 
     * 因为是最短路径，所以BFS
     * 在放入Queue的同时标记visted
     * 
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 变成set方便查找
        Set<String> words = new HashSet<>(wordList);
        // corner case
        if (words == null || words.size() == 0) {
            return 0;
        }
        if (!words.contains(endWord)) {
            return 0;
        }
        // bfs preparation
        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.add(beginWord);
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                String cur = queue.poll();
                if (cur.equals(endWord)) {
                    return step;
                }
                // 更改path
                // 遍历当前单词的每一位
                for (int j = 0; j < cur.length(); j++) {
                    char[] word = cur.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        word[j] = ch;
                        String next = String.valueOf(word);
                        // 如果不是当前的，并且是没有被访问过的单词
                        if (!next.equals(cur) && !visited.contains(next) && words.contains(next)) {
                            queue.add(next);
                            visited.add(next);
                        }
                    }
                }
            }
        }
        return 0;
    }
}
