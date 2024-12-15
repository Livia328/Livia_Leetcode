import java.util.*;

public class Q127 {
    /*
     * Q126的进阶版
     * 
     * graph question
     * 有点像graph，beginWord是起点，endWord是终点
     *  
     * 26个字母是所有可能的path
     * 就是对于每一位，如果改了一个字母后
     * 这个字母在wordList中的话，那么这条路就是可以走的
     * 
     * 因为是最短路径，所以BFS
     * 在放入Queue的同时标记visited
     *
     * 找到了之后我们开始从后往前backtrack
     * 从endWord开始往前
     * 所以我们需要知道有这个词是由哪些词生成的
     * 
     * 所以用一个map来记录
     * key是newWord, value是curWord
     * 
     * 这样的话就不能直接加入visited数组
     * 否则这个map会不对
     * 得keep一个visited_level
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        // 把wordList变成set，好算
        Set<String> words = new HashSet<>(wordList);
        // base case
        if (words == null || words.size() == 0) {
            return res;
        }
        if (!words.contains(endWord)) {
            return res;
        }
        Map<String, Set<String>> graph = new HashMap<>();
        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.add(beginWord);
        boolean found = false;

        while (!queue.isEmpty()) {
            int n = queue.size();
            Set<String> curSet = new HashSet<>();
            for (int i = 0; i < n; i++) {
                String cur = queue.poll();
                if (cur.equals(endWord)) {
                    found = true;
                    break;
                }
                // 尝试修改每一位，每一位可以修改成26个字母中任何一个
                for (int index = 0; index < cur.length(); index++) {
                    char[] curWord = cur.toCharArray();
                    // 每一位都可以被改成26个字母
                    for (char c = 'a'; c <= 'z'; c++) {
                        curWord[index] = c;
                        String newWord = String.valueOf(curWord);
                        // cur -> newWord
                        if (words.contains(newWord) && !visited.contains(newWord)) {
                            if (!curSet.contains(newWord)) {
                                queue.offer(newWord);
                                curSet.add(newWord);
                            }
                            Set<String> preWords = graph.getOrDefault(newWord, new HashSet<>());
                            preWords.add(cur);
                            graph.put(newWord, preWords);
                        }
                    }
                }
            }
            visited.addAll(curSet);
            // 已经找到bfs，再往下一层找就不是最短路径了
            if (found) {
                break;
            }
        }
        if (!found) {
            return res;
        }
        List<String> path = new ArrayList<>();
        path.add(endWord);
        dfs(res, graph, endWord, beginWord, path);
        return res;
    }

    public void dfs(List<List<String>> res, Map<String, Set<String>> pathMap, String curWord, String target, List<String> list) {
        if (curWord.equals(target)) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (String next : pathMap.get(curWord)) {
            list.add(0, next);
            dfs(res, pathMap, next, target, list);
            list.remove(0);
        }
    }
}


