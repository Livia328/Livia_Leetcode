public class Q791 {
    /*
     * calarification:
     * 只出现英文小写字母吗？
     * 所有出现在s中的string都会在order里出现吗？
     * 如果没有出现在order里怎么排序？
     * 
     * 
     * 先数一数s中每个字母出现的frequency
     * 然后遍历order，按照order里出现的顺序，
     * 如果map[t] != 0, 那么就append map[t]次t到res上
     * 然后遍历map，看看还有哪些不为0的，这些就是没有出现的order里的
     * append上去
     * 
     * 比如Input: order = "bcafg", s = "abbcd"
     * 先得到map：a:1, b:2, c:1, d:1
     * 
     * 然后再遍历order
     * 先是b，check map b出现了两次：
     * res: bb
     * 
     * 然后是c，check map c出现了1次
     * res: bbc
     * 
     * 然后是a，check map a出现了一次
     * res: bbca,
     * 
     * 然后是f：check map没有
     * res： bbca
     * 
     * 然后是g： check map 没有
     * res： bbca
     * 
     * 
     * 然后遍历map，发现还有d没有append上去，append到最后
     * bbcad
     */
    public static String customSortString(String order, String s) {
        int[] map = new int[26];
        // count frequency
        for(char c : s.toCharArray()) {
            map[c - 'a']++;
        }
        // traverse order
        StringBuilder res = new StringBuilder();
        for (char c : order.toCharArray()) {
            while (map[c - 'a'] != 0) {
                res.append(c);
                map[c - 'a']--;
            }
        }
        // traverse map
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < map[c - 'a']; i++) {
                res.append(c);
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = customSortString("cba", "abcd");
    }
}
