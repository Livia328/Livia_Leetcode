import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Q118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> first = new ArrayList<>();
        first.add(1);
        res.add(first);
        for (int i = 2; i <= numRows; i++) {
            List<Integer> prv = res.get(res.size() - 1);
            res.add(generateNextRow(prv));
        }
        return res;
    }

    public List<Integer> generateNextRow(List<Integer> prv) {
        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        for (int i = 0; i < prv.size() - 1; i++) {
            cur.add(prv.get(i) + prv.get(i + 1));
        }
        cur.add(1);
        return cur;
    }
}
