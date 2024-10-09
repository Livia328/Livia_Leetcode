import java.util.HashSet;
import java.util.Set;
import java.util.Deque;
import java.util.ArrayDeque;
public class Q1778 {
    /**
     * This is the GridMaster's API interface.
     * You should not implement it, or speculate about its implementation
     * class GridMaster {
     *     boolean canMove(char direction);
     *     void move(char direction);
     *     boolean isTarget();
     * }
     */
    /*
     * 题目意思就是要找到从begin到target的最短路径，但是只能向gridmaster问问题
     * 因为是最短路径问题所以肯定是bfs
     * 
     * 但在bfs前我们需要获得一些值
     * 我们可以通过dfs先获得整个地图，block的区域，和target
     * 
     */
    static final char[] DIRECTIONS = {'U', 'R', 'D', 'L'};
    // 之前backtrack的时候我们知道是+1还是-1，现在就是用之前操作的对面值来恢复原来的状态
    static final char[] OPPOSITE_DIRECTIONS = {'D', 'L', 'U', 'R'};
    // 同时我们要知道目前col和row分别怎么变化
    // 分别对应U, R, D, L
    static final int[][] DIRECTIONS_CHANGE = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static final int gridSize = 1010;
    // 因为不知道size，所以只能用set来做visited
    // 并且到最后，visited里面就是所有可以访问的坐标
    // 因为set不能放int[]
    // 放入x * gridSize + y
    Set<Integer> visited;
    int[] targetPosition;

    public int findShortestPath(GridMaster master) {
        targetPosition = null;
        visited = new HashSet<>();
        visited.add(0);
        dfs(0, 0, master);
        if (targetPosition == null) {
            return -1;
        }
        // 开始bfs寻找最短路径
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        // 此时visited里面是所有可以访问的坐标
        // 去掉{0.0}，不然下面会一直循环
        visited.remove(0);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.pollFirst();
                // 找到了target
                // 这里可以看出step要放到后面加加，如果开头就是end的话，应该return0
                if (cur[0] == targetPosition[0] && cur[1] == targetPosition[1]) {
                    return step;
                }
                for (int k = 0; k < 4; k++) {
                    int x = cur[0] + DIRECTIONS_CHANGE[k][0];
                    int y = cur[1] + DIRECTIONS_CHANGE[k][1];
                    if (visited.contains(x * gridSize + y)) {
                        queue.offer(new int[]{x, y});
                        visited.remove(x * gridSize + y);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public void dfs(int i, int j, GridMaster master) {
        // base case: 找到target了
        if (master.isTarget()) {
            targetPosition = new int[]{i, j};
        }
        for (int k = 0; k < 4; k++) {
            char d = DIRECTIONS[k];
            char opposite_d = OPPOSITE_DIRECTIONS[k];
            int x = i + DIRECTIONS_CHANGE[k][0];
            int y = j + DIRECTIONS_CHANGE[k][1];
            if (master.canMove(d) && !visited.contains(x * gridSize + y)) {
                visited.add(x * gridSize + y);
                master.move(d);
                dfs(x, y, master);
                // 撤销选择
                master.move(opposite_d);
            }
        }
    }
}
