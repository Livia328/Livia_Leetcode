import java.rmi.Remote;
import java.util.*;
import java.util.ArrayList;

public class tmp {
    /*
     * backtracking(start)
     * 
     * candidats: [start, n]
     * when to add ans: path.size == k
     * 
     *                   []
     *     [1]          [2]   [3]
     *  [1,2] [1,3]    [2, 3]
     */
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        backtracking(1, n, k);
        return res;
    }

    public void backtracking(int start, int n, int k) {
        // base case
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i <= n; i++) {
            path.add(i);
            backtracking(i + 1, n, k);
            path.remove(path.size() - 1);
        }
    }
}
