import java.util.*;

public class Q365 {
    /*
     * dfs搜索所有的情况：
     * 
     * dfs(x, y, curX, curY, visited)
     * 
     * base case:
     * curX + curY = target -> true
     * 
     * 去重
     * visited[curX][curY] -> return false
     * 出现和之前一样的状态了，那么就不能得到答案
     * 
     * 我们只有四个操作
     * 1. 把水壶x装满：x, y, x, curY
     * 2. 把水壶y装满：x, y, curX, y
     * 3. 把水壶x的水倒入水壶y中，
     *    此时水壶y能接受的水是yCanAccecpt = y - curY
     *    newX = curX - yCanAccecpt >= 0 ? curX - yCanAccecpt : 0
     *    newY = curY + curX <= y ? curY + curX : y
     * 4. 把水壶y的水倒入x中，和上面一样
     * 5. 清空x的水 -> x,y,0,curY
     * 6. 清空y的水 -> x,y,curX,0
     * 
     * 这其中只要有一个是true就是true
     */
    public boolean canMeasureWater1(int x, int y, int target) {
        boolean[][] visited = new boolean[x + 1][y + 1];
        return dfs(x, y, 0, 0, target, visited);
    }

    public boolean dfs(int x, int y, int curX, int curY, int target, boolean[][] visited) {
        // base case
        if (curX + curY == target) {
            return true;
        }
        if (visited[curX][curY]) {
            return false;
        }
        visited[curX][curY] = true;
        // 把x装满
        boolean res1 = dfs(x, y, x, curY, target, visited);
        // 把y装满
        boolean res2 = dfs(x, y, curX, y, target, visited);
        // 把x中的水加入y中
        int yCanAccecpt = y - curY;
        int newX = curX - yCanAccecpt >= 0 ? curX - yCanAccecpt : 0;
        int newY = curY + curX <= y ? curX + curY : y;
        boolean res3 = dfs(x, y, newX, newY, target, visited);
        // 把y中的水加入x中
        int xCanAccept = x - curX;
        int newX2 = curX + curY <= x ? curX + curY : x;
        int newY2 = curY - xCanAccept >= 0 ? curY - xCanAccept : 0;
        boolean res4 = dfs(x, y, newX2, newY2, target, visited);
        // 清空x里的水
        boolean res5 = dfs(x, y, 0, curY, target, visited);
        // 清空y里的水
        boolean res6 = dfs(x, y, curX, 0, target, visited);
        return res1 || res2 || res3 || res4 || res5 || res6;
    }

    /*
     * bfs
     * curX和curY是状态，把这个状态加入queue中
     * 同时记录visited过的状态
     */
    public boolean canMeasureWater(int x, int y, int target) {
        Queue<int[]> queue = new ArrayDeque<>();
        HashSet<String> visited = new HashSet<>();
        queue.offer(new int[]{0,0});
        visited.add("0,0");
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            // 如果任何一个桶的含水量等于target
            // 或者他们的和等于target
            if (cur[0] == target || cur[1] == target || cur[0] + cur[1] == target) {
                return true;
            }
            List<int[]> nextStates = new LinkedList<>();
            // 把x灌满
            nextStates.add(new int[]{x, cur[1]});
            // 把y灌满
            nextStates.add(new int[]{cur[0], y});
            // 把x中的水加入y中
            int yCanAccecpt = y - cur[1];
            nextStates.add(new int[]{cur[0] - yCanAccecpt >= 0 ? cur[0] - yCanAccecpt : 0, cur[1] + cur[0] <= y ? cur[0] + cur[1] : y});
            // 把y中的水加入x
            int xCanAccept = x - cur[0];
            nextStates.add(new int[]{cur[0] + cur[1] <= x ? cur[0] + cur[1] : x, cur[1] - xCanAccept >= 0 ? cur[1] - xCanAccept : 0});
            // 把x倒空
            nextStates.add(new int[]{0, cur[1]});
            // 把y倒空
            nextStates.add(new int[]{cur[0], 0});
            // 检查所有可能的，放入队列和set中
            for (int[] state : nextStates) {
                String hashKey = state[0] + "," + state[1];
                if (!visited.contains(hashKey)) {
                    queue.add(state);
                    visited.add(hashKey);
                }
            }
        }
        return false;
    }
}
