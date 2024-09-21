public class Q1009 {
    public int bitwiseComplement(int n) {
        if (n == 0) {
            return 1;
        }
        int res = 0;
        int fra = 1;
        while (n > 0) {
            res += fra * (n % 2 == 0 ? 1 : 0);
            fra *= 2;
            n = n / 2;
        }
        return res;
    }
}
