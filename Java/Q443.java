public class Q443 {
    /*
     * 快慢指针
     * 慢指针表示正在处理的字符，快指针来计数
     * slow
     * ["a","a","b","b","c","c","c"]
     *          fast
     * 
     * 计算出count = 2
     * 
     * 如果count > 1, 把数字放进去
     *     slow
     * ["a",2,"b","b","c","c","c"]
     *          fast
     * 
     * 
     * 再slow = fast
     *          slow
     * ["a","2","b","b","c","c","c"]
     *          fast
     */
    public int compress(char[] chars) {
        int slow = 0, fast = 0;
        while (fast < chars.length) {
            int count = 0;
            while (fast < chars.length && chars[fast] == chars[slow]) {
                fast++;
                count++;
            }
            slow++;
            if (count > 1) {
                for (char c : String.valueOf(count).toCharArray()) {
                    chars[slow++] = c;
                }
            }
            // 把接下来要遍历的数字放到slow的位置
            if(fast < chars.length) {
                chars[slow] = chars[fast];
            }
        }
        return slow;
    }
}
