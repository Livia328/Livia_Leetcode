import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Q1191 {
    /*
     * 首先先讲怎么模拟knight的步骤
     * {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
     * 
     * 然后因为我们想知道最短的路径，所以bfs
     */
    private final int[][] DIRECTIONS = new int[][] {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
    public int minKnightMoves(int x, int y) {
        // 因为是无限的，所以对称一下都是一样的
        // 可以先不说这个，等写到下面的时候再说
        x = Math.abs(x);
        y = Math.abs(y);
        // queue里面放坐标
        Queue<int[]> queue = new ArrayDeque<>();
        Set<String> visisted = new HashSet<>();
        queue.add(new int[]{0, 0});
        visisted.add("0,0");
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                if ((cur[0] == x) && cur[1] == y) {
                    return res;
                }
                for (int[] d : DIRECTIONS) {
                    int newX = cur[0] + d[0];
                    int newY = cur[1] + d[1];
                    if (newX >= -1 && newY >= -1 && !visisted.contains(newX + "," + newY)) {
                        queue.add(new int[]{newX, newY});
                        visisted.add(newX + "," + newY);
                    }
                }
            }
            res++;
        }
        return -1;
    }
}
