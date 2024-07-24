import java.util.*;

public class Q89 {
    /*
     * backtracking
     *              00
     *      01              10
     *   00   11         11   00
     *      10  01    10  01
     *    11 00         00  11
     * 
     */
    public List<Integer> grayCode(int n) {
        traverse(0, n);
        return res;
    }

    // 用来保存已经使用过的grayCode
    Set<Integer> used = new HashSet<>();
    // path
    List<Integer> path = new LinkedList<>();
    // res
    List<Integer> res = null;

    /*
     * 多叉树的遍历
     */
    public void traverse(int root, int n) {
        //减枝，如果已经找到答案，那么可以返回了
        if (res != null) {
            return;
        }
        // 找到答案
        if (path.size() == (1 << n)) {
            res = new LinkedList<>(path);
            return;
        }
        // 出现重复的，结束当前分支
        if (used.contains(root)) {
            return;
        }
        used.add(root);
        path.addLast(root);
        // candidate是所有和当前数differs by exactly one bit的数
        // 也就是尝试把某一位取反
        for (int i = 0; i < n; i++) {
            int next = flipBit(root, i);
            traverse(next, n);
        }
        used.remove(root);
        path.removeLast();
    }

    /*
     * 把第i位取反
     */
    int flipBit(int root, int i) {
        return root ^ (1 << i);
    }
}
