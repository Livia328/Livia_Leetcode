public class Q459 {
    /**
     * brute force
     * 外面一个for 循环，从第一个字符到中间，枚举所有可能的子串
     * 1.只要从第一个字符开始，因为第一个pattern肯定是从index = 0开始的
     * 2.到中间截止即可，因为超过中间了，肯定不能是子串了
     * 
     * 
     * 里面内嵌的for循环以pattern为substring,构建(s.length - pattern.length)个
     * 如果构建出来的和s是一样的，那么说明是他
     */
    public boolean repeatedSubstringPattern1(String s) {
        // i的意义是pattern的最右边
        for (int i = 1; i <= s.length() / 2; i++) {
            // 如果可以整除这个长度，说明可能是pattern
            if (s.length() % i == 0) {
                String pattern = s.substring(0, i);
                // 这个pattern一共会出现几次
                int occ = s.length() / i;
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < occ; j++) {
                    sb.append(pattern);
                }
                if (sb.toString().equals(s)) {
                    return true;
                }
            }
            
        }
        return false;
    }
}