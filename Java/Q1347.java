public class Q1347 {
    /*
     * 计算次数
     * 
     */
    public int minSteps(String s, String t) {
        int[] count_s = new int[26];
        int[] count_t = new int[26];
        for (char c : s.toCharArray()) {
            count_s[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            count_t[c - 'a']++;
        }
        int sum = 0;
        for (int i = 0; i < )

    }
}