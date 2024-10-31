import java.util.ArrayList;
import java.util.List;

public class Q386 {
    /*
     * 其实是dfs
     *           1
     *   10  11  12  13 ...19
     * 100 101....
     */
    List<Integer> res = new ArrayList<>();
    public List<Integer> lexicalOrder(int n) {
        for (int i = 1; i < 10; i++) {
            dfs(i, n);
        }
        return res;
    }

    public void dfs(int root, int n) {
        if (root > n) {
            return;
        }
        res.add(root);
        for (int child = root * 10; child < root * 10 + 10; child++) {
            dfs(child, n);
        }
    }
}
