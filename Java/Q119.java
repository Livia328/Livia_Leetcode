import java.util.*;

public class Q119 {
    /**
     * recursion
     * 每次返回index = rowIndex 的row
     */
    public List<Integer> getRow(int rowIndex) {
        // base case
        List<Integer> curRow = new LinkedList<>();
        curRow.add(1);
        if (rowIndex == 0) {
            return curRow;
        }
        // curRow: 1
        // preRow: 1 1
        List<Integer> preRow = getRow(rowIndex - 1);
        for (int i = 0; i < preRow.size() - 1; i++) {
            curRow.add(preRow.get(i) + preRow.get(i + 1));
        }
        curRow.add(1);
        return curRow;
    }
}
