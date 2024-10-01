public class Q339 {
    /*
     * 理解题意：权重是他是nested的第几层
     * 因此可以用递归，每往下遍历一层，depth++
     * 
     * helper(nestedList, depth) {
     *     如果是int, + int * depth
     *     不是int, + helper(getList, depth++)
     * }
     */

    public int depthSum(List<NestedInteger> nestedList) {
        return helper(nestedList, 1);
    }

    public int helper(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for (NestedInteger n : nestedList) {
            if (n.isInteger()) {
                sum += n.getInteger() * depth;
                continue;
            }
            sum += helper(n.getList(), depth+1);
        }
        return sum;
    }
}
