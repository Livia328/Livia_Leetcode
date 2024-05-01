public class Q504 {
    /*
     * 记得要处理负数和0等corner cases啊！
     */
    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        boolean isNeg = num < 0;
        num = Math.abs(num);
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(num % 7);
            num = num / 7;
        }
        if (isNeg) {
            sb.append('-');
        }
        return sb.reverse().toString();
    }
}
 