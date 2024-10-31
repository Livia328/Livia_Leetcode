public class Q1071 {
    /*
     * 为了方便表达，我先以len(str1) > len(str2)来说
     * 因为如果是最大公约数，那么肯定是str2的substring
     * 
     * 我们可以从大到小往前枚举prefix
     * 首先看len1 % i == 0 && len2 % i == 0
     * 如果是，那么这个可以是一个备选项
     * 
     * 然后用n = len / prefix
     * 看看str1 == n * prefex. str2 == n * prefix
     * 
     * 比如
     * 
     */
    public String gcdOfStrings1(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length();
        for (int i = Math.min(len1, len2); i >= 1; i--) {
            if (len1 % i == 0 && len2 % i == 0) {
                // 当前的prefix
                String x = str1.substring(0, i);
                // 因为我们是从后往前枚举，所以碰到的第一个就是最长的
                if (check(x, str1) && check(x, str2)) {
                    return x;
                }
            }
        }
        return "";
    }

    /*
     * 判断t repeat n遍能不能构成s
     */
    public boolean check(String t, String s) {
        int n = s.length() / t.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(t);
        }
        return sb.toString().equals(s);
    }


    /*
     * 如果str1 + str2 = str2 + str1
     * 那么一定存在答案
     * 
     * 然后辗转相除得到答案
     */
    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        // 辗转相除
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
