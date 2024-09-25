import java.util.*;

public class Q140 {
    /*
     * 先写backtrack，再在基础上加memo
     * 找到一个词后，先选了看看，再撤销选择
     */
    List<String> res1 = new LinkedList<>();
    List<String> track1 = new LinkedList<>();
    public List<String> wordBreak1(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        backtrack1(s, set, 0);
        return res1;
    }

    public void backtrack1(String s, Set<String> set, int index) {
        // base case
        if (index == s.length()) {
            res1.add(String.join(" ", track1));
            return;
        }
        // 选择
        for (int j = index + 1; j <= s.length(); j++) {
            String word = s.substring(index, j);
            if (set.contains(word)) {
                track1.add(word);
                backtrack1(s, set, j);
                track1.remove(track1.size() - 1);
            }
        }
    }

    /*
     * 优化，加memo
     * 因为要经过同一个index好几遍
     * 记录同一个index下所有可能的拆分
     * 用map来记录：Map<Integer, List<List<String>> memo
     * 含义是：以s[index, end]的string可以被分割的长度
     * 
     * base case
     * index == s.len
     * 返回一个空的，因为此时s[index, end]没有答案
     * 
     * 对于每一个index,在找到一个可能的词word后，递归得到S[index + word.len, end]的所有可能答案
     * 然后对每一个List<String> 逐一加入word
     * 
     * catsanddog
     * 先找到cats
     * 那么就要递归找anddog]的答案
     *  找到and，-> [[and, dog], [and, ans2]]
     *      再找dog的答案 -> return ["dog", "ans2"]
     *        base case -> return []
     * 
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<Integer, List<List<String>>> memo = new HashMap();
        Set<String> set = new HashSet<>(wordDict);
        List<List<String>> ans = backtrack(s, 0, set, memo);
        List<String> res = new ArrayList<>();
        for (List<String> list : ans) {
            res.add(String.join(" ", list));
        }
        return res;
    }

    public List<List<String>> backtrack(String s, int index, Set<String> set, Map<Integer, List<List<String>>> map) {
        // check memo
        if (map.containsKey(index)) {
            return map.get(index);
        }
        // base case
        List<List<String>> curPath = new LinkedList<>();
        if (index == s.length()) {
            curPath.add(new LinkedList<>());
        }
        for (int j = index + 1; j <= s.length(); j++) {
            String word = s.substring(index, j);
            if (set.contains(word)) {
                List<List<String>> nextWordBreak = backtrack(s, j, set, map);
                for (List<String> list : nextWordBreak) {
                    LinkedList<String> allAns = new LinkedList<>(list);
                    allAns.offerFirst(word);
                    curPath.add(allAns);
                }
            }
        }
        map.put(index, curPath);
        return map.get(index);
    }
}
