public class Q29 {
    /*
     * 比如11 / 3
     * 首先11 > 3， 除数肯定大于1， res = 1
     * 
     * 然后我就翻倍，11 > 6，那么可以再翻倍， res = 2
     * 
     * 然后再翻倍，11 < 12，我就知道不能翻倍了，最终答案肯定在2-4之间
     * 
     * 然后我们再让11 - 6 = 5， 接下来就要知道5是3的几倍，又是除法，所以是递归
     */
    public int divide(int dividend, int divisor) {
        // 处理一下corner case
        if (dividend == 0) {
            return 0;
        }
        if (divisor == 1) {
            return dividend;
        }
        // 当除数为-1且被除数为Integer.MIN_VALUE时，将会溢出，返回Integer.MAX_VALUE
        if (divisor == -1 && dividend == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        // 把被除数与除数调整为正数,为防止被除数Integer.MIN_VALUE转换为正数会溢出，使用long类型保存参数
        if (dividend < 0 && divisor < 0) {
            return recursion(-(long) dividend, -(long) divisor);
        } else if (dividend < 0 || divisor < 0) {
            return -recursion(Math.abs((long) dividend), Math.abs((long) divisor));
        } else {
            return recursion((long) dividend, (long) divisor);
        }

    }

    public int recursion(long a, long b) {
        // base case
        // 如果a比b大了就结束
        if (a < b) {
            return 0;
        }
        int count = 1;
        // 因为不能用乘法
        long copyB = b;
        while (copyB + copyB < a) {
            count =  count + count;
            copyB = copyB + copyB;
        }
        return count + recursion(a - copyB, b);
    }
}
