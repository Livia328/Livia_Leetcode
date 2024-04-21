public class Q171 {
    public int titleToNumber(String columnTitle) {
        int ans = 0;
        for (char c : columnTitle.toCharArray()) {
            int cur = c - 'A' + 1;
            ans = ans * 26 + cur;
        }
        return ans;
    }
}
