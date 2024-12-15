import java.util.*;

public class Q773 {
    /*
     * 因为是最短路径，所以BFS
     * 将board转化成状态
     * 
     * target状态是123450
     * queue里放的就是这个
     * 
     * 因为只能和相邻的互换
     * 这里面每个格子有moving direction
     * 表示index为0的数字可以和index为1和index为3的数组换
        int[][] neighbor = new int[][]{
            {1, 3},
            {0, 4, 2},
            {1, 5},
            {0, 4},
            {3, 1, 5},
            {4, 2}
        };
     *
     */
    public int slidingPuzzle(int[][] board) {
        int m = 2, n = 3;
        String target = "123450";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]);
            }
        }
        String start = sb.toString();
        // 记录一维字符串的相邻索引
        // 因为只能和相邻的互换
        // 表示index为0的数字可以和index为1和index为3的数组换
        int[][] neighbor = new int[][]{
            {1, 3},
            {0, 4, 2},
            {1, 5},
            {0, 4},
            {3, 1, 5},
            {4, 2}
        };
        Queue<String> queue = new ArrayDeque<>();
        HashSet<String> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (target.equals(cur)) {
                    return step;
                }
                // 找到数字0的index
                int index = 0;
                while (cur.charAt(index) != '0') {
                    index++;
                }
                // 将0和相邻的互换
                for (int nei : neighbor[index]) {
                    String next = swap(cur.toCharArray(), nei, index);
                    if (!visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }
}
