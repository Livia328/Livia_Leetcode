public class Q678 {
    /*
     * 我们可以从左往右keep balance
     * 但是因为*既可以看作是左括号，也可以看作是右括号
     * 所以我们可以用两个数
     * 
     * 一个是minLeftNeeded
     * 一个是maxLeftNeeded
     * 
     * 遇到(,都+1
     * 遇到），都-1
     * 遇到*，
     *     minLeftNeeded--（看作是左括号）
     *     maxLeftNeeded++（看作是右括号）
     * 
     * 如果过程中minLeftNeeded < 0了
     * 那么需要重制为0
     * 这个时候表示左括号过多了，但是未来可以变为合法的
     * 
     * 如果minLeftNeeded > maxLeftNeeded了
     * 说明maxLeftNeeded为负数了，那么说明右括号过多了
     * 那么一定不合法
     *
     * 到最后min应该为0
     */
    public boolean checkValidString(String s) {
        int min = 0, max = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                min++; max++;
            } else if (c == ')') {
                min--; max--;
            } else if (c == '*') {
                min--; max++;
            }
            min = Math.max(min, 0);
            if (min > max) {
                return false;
            }
        }
        return min == 0;
    }
}
