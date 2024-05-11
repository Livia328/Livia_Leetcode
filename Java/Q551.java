public class Q551 {
    public boolean checkRecord(String s) {
        int countA = 0;
        int countL = 0;
        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'L') {
                countL++;
                if (countL >= 3) {
                    return false;
                }
            } else { //如果不是连续，就清空
                countL = 0;
            }
            if (s.charAt(i) == 'A') {
                countA++;
                if (countA >= 2) {
                    return false;
                }
            }
        }
        return true;
    }
}
