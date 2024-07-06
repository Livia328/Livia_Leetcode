public class Q744 {
    /*
     * 一般解法
     */
    public char nextGreatestLetter1(char[] letters, char target) {
        for (char c : letters) {
            if (c > target) {
                return c;
            }
        }
        return letters[0];
    }

    /*
     * binary search
     * [0, letter.length]
     * 
     */
    public char nextGreatestLetter2(char[] letters, char target) {
        int L = 0, R = letters.length - 1;
        while (L <= R) {
            int M = L + (R - L) / 2;
            if (letters[M] > target) {
                // 缩小右边界
                R = M - 1;
            } else if (letters[M] < target) {
                L = M + 1;
            } else {
                // 相等的情况，因为要求的是比target大的，所以还是在右边
                L = M + 1;
            }
        }
        // 判断L边界是否还在letters中
        if (L == letters.length) {
            return letters[0];
        }
        // 比较letters[L]是不是符合条件的
        return letters[L] > target ? letters[L] : letters[0];
    }
}
