import java.security.cert.TrustAnchor;
import java.util.*;

public class Q733 {
    /*
     * BFS？
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[image.length][image[0].length];
        queue.add(new int[]{sr, sc});
        visited[sr][sc] = true;
        int centerColor = image[sr][sc];
        int[][] DIR = {{1,0},{-1,0},{0,-1},{0,1}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            // modify current letter
            image[cur[0]][cur[1]] = color;
            for (int[] dir : DIR) {
                int x = cur[0] + dir[0], y = cur[1] + dir[1];
                if (x >= 0 && x < image.length && y >= 0 && y < image[0].length && !visited[x][y] && image[x][y] == centerColor) {
                    queue.add(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
        }
        return image;
    }
}
