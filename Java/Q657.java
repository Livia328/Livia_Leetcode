public class Q657 {
    /*
     * 数一数上下左右的个数，上下一定要一样，左右一定要一样
     */
    public boolean judgeCircle(String moves) {
        int up = 0, down = 0, left = 0, right = 0;
        for (char c : moves.toCharArray()) {
            if (c == 'U') up++;
            if (c == 'D') down++;
            if (c == 'L') left++;
            if (c == 'R') right++; 
        }
        return up == down && left == right;
        
    }
}
