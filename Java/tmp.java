import java.rmi.Remote;
import java.util.*;

public class tmp {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        // base case
        if (words == null || words.size() == 0) {
            return 0;
        }
        if (!words.contains(endWord)) {
            return 0;
        }
        // start bfs
        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.add(beginWord);
        visited.add(beginWord);
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                String cur = queue.poll();
                if (cur.equals(endWord)) {
                    return step;
                }
                // 尝试修改每一位，每一位可以修改成26个字母中任何一个
                for (int index = 0; index < cur.length(); index++) {
                    char[] curWord = cur.toCharArray();
                    // 每一位都可以被改成26个字母
                    for (char c = 'a'; c <= 'z'; c++) {
                        curWord[index] = c;
                        String newWord = String.valueOf(curWord);
                        if (!newWord.equals(cur) && !visited.contains(newWord) && words.contains(newWord)) {
                            queue.add(newWord);
                            visited.add(newWord);
                        }
                    }
                }
            }
        }
        return 0;
    }
}
