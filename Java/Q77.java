import java.util.ArrayList;
import java.util.List;

public class Q77 {
    /*
     * distinct number, only once
     * 
     *    1   2   3     4
     *  1,2  2 3 3 4
     * 
     * 
     * index, where from
     * 
     */
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        backtrack(1, n, k);
        return res;
    }

    public void backtrack(int start, int n, int k) {
        // base case
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i <= n; i++) {
            path.add(i);
            backtrack(i + 1, n, k);
            path.remove(path.size() - 1);
        }
    }
}
