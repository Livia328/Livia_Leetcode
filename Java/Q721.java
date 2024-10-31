import java.util.*;

public class Q721 {
    /*
     * 因为是根据email来分的，所以我们可以以email为key创建hashmap
     * key: email, val: 出现该 email 的 account 的索引列表
     * 然后再遍历所有的email来合并账户
     * 
     * 注意不能简单的合并
     * 比如：
     * 
     * 当我们确定email1, email2, email3共同出现过，我们可以确定这三个邮箱是一个人的
     * 但如果email2又和email4, email5共同出现过
     * 这个人的邮箱应该是email1, email2, email3, email4, email5
     * 所以要用bfs遍历所有共同的情况
     * 
     * Input: accounts = [
     * ["John","johnsmith@mail.com","john_newyork@mail.com"],
     * ["John","johnsmith@mail.com","john00@mail.com"],
     * ["Mary","mary@mail.com"],
     * ["John","johnnybravo@mail.com"]]
     * 
     * 
     * emailToIndex：
     * johnsmith@mail.com: 0, 1
     * john_newyork@mail.com: 0
     * john00@mail.com: 1
     * mary@mail.com: 2
     * johnnybravo@mail.com: 3
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // key: email, val: 出现该email的account index
        Map<String, List<Integer>> emailToIndex = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> emails = accounts.get(i);
            // 因为emails[0]是名字，所以从1开始遍历
            for (int j = 1; j < emails.size(); j++) {
                String email = emails.get(j);
                List<Integer> indexes = emailToIndex.getOrDefault(email, new ArrayList<>());
                indexes.add(i);
                emailToIndex.put(email, indexes);
            }
        }
        // merge account
        List<List<String>> res = new ArrayList<>();
        HashSet<String> visited = new HashSet<>();
        for (String email : emailToIndex.keySet()) {
            if (visited.contains(email)) {
                continue;
            }
            LinkedList<String> mergedEmail = new LinkedList<>();
            Queue<String> queue = new ArrayDeque<>();
            queue.offer(email);
            visited.add(email);
            while (!queue.isEmpty()) {
                String cur = queue.poll();
                mergedEmail.addLast(cur);
                List<Integer> indices = emailToIndex.get(cur);
                for (int index : indices) {
                    List<String> account = accounts.get(index);
                    // 因为emails[0]是名字，所以从1开始遍历
                    for (int j = 1; j < account.size(); j++) {
                        String nextEmail = account.get(j);
                        if (!visited.contains(nextEmail)) {
                            queue.offer(nextEmail);
                            visited.add(nextEmail);
                        }
                    }
                }
            }
            String userName = accounts.get(emailToIndex.get(email).get(0)).get(0);
            Collections.sort(mergedEmail);
            mergedEmail.addFirst(userName);
            res.add(mergedEmail);
        }
        return res;
    }
}
