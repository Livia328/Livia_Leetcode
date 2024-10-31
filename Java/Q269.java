import java.util.*;

public class Q269 {
    /*
     * 拓扑排序
     * Input: words = ["wrt","wrf","er","ett","rftt"]
     * Output: "wertf"
     * 
     * 我们要两两比较word里的字符
     * wrt
     * wrf
     * 
     * 对于第一个出现不一样的字母，t和f，我们可以确认t < f
     * 
     * 因为我们要检查是否valid，也就是说我们可以把每一个字母看成graph里的node
     * 如果t < f, 那么 t -> f
     * 如果有顺序和之前的不一样（not valid），那么就是说明图中有环
     * 
     * 检查graph中是否有环，可以用拓扑排序做，同时可以得到顺序
     * 
     * 拓扑排序，就需要adjacent list和indegree
     */
    public String alienOrder(String[] words) {
        Map<Character, List<Character>> adj = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();
        // 初始化
        for (String word : words) {
            for (char c : word.toCharArray()) {
                indegree.put(c, 0);
                adj.put(c, new LinkedList<>());
            }
        }
        /*
         * 比较nums[i]和nums[i + 1]
         * 找出第一个不一样的字母，我们可以判断这两个字母的关系
         * 
         * wrt
         * wrf
         * 对于第一个出现不一样的字母，t和f，我们可以确认t < f
         */
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            // 如果直接不符合条件，可以直接返回“”
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            for (int j = 0; j < word1.length(); j++) {
                // 找到了第一个不一样的字母
                // t -> f
                // 所以把f加入t的adjecnt list中
                // increate j的indegree
                if (word1.charAt(j) != word2.charAt(j)) {
                    adj.get(word1.charAt(j)).add(word2.charAt(j));
                    indegree.put(word2.charAt(j), indegree.get(word2.charAt(j)) + 1);
                    break;
                }
            }
        }
        // 拓扑排序
        Queue<Character> queue = new ArrayDeque<>();
        // 将所有in degree为0的放入queue中
        for (Character key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                queue.add(key); 
            }
        }
        // 不断append上遍历过的字符上去
        StringBuilder res = new StringBuilder();
        while (!queue.isEmpty()) {
            Character cur = queue.poll();
            res.append(cur);
            for (Character nei : adj.get(cur)) {
                // 减小它每一个下一个的字母的indegree
                indegree.put(nei, indegree.get(nei) - 1);
                if (indegree.get(nei) == 0) {
                    queue.add(nei);
                }
            }
        }
        // 不能遍历每一个字符
        if (res.length() < indegree.size()) {
            return "";
        }
        return res.toString();
    }
}
