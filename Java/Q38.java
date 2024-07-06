public class Q38 {
    /*
     * recursive
     * base case == 1, return 1
     * recursive rule (n-1)
     * 
     * things to do: make it RLE
     */
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String previous = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();
        int count = 1; char prev = previous.charAt(0);
        for (int i = 1; i < previous.length(); i++) {
            if (previous.charAt(i) == prev) {
                count++;
                continue;
            } else {
                // 前面的数可以count出来了
                sb.append(count);
                sb.append(prev);
                // set count and prev
                count = 1;
                prev = previous.charAt(i);
            }
        }
        // 把最后两个放进去
        sb.append(count); sb.append(prev);
        return sb.toString();
    }
}
