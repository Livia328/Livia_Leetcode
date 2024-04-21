public class Q258 {
    public int addDigits(int num) {
        while (num / 10 != 0) {
            int cur = num;
            int sum = 0;
            while (cur != 0) {
                sum += cur % 10;
                cur /= 10;
            }
            num = sum;
        }
        return num;
    }
}
