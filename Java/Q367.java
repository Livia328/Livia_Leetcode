public class Q367 {
    public boolean isPerfectSquare(int num) {
        if (num == 1) {
            return true;
        }
        for (int i = 1; i <= num / 2; i++) {
            if (i * i == num) {
                return true;
            }
            if (i * i > num) {
                return false;
            }
        }
        return false;
    }
}
