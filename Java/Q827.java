import java.util.HashMap;
import java.util.HashSet;

public class Q827 {
    /*
     * 两边dfs
     * 第一遍dfs,去找所有岛的面积，并将其标为编号
     * 用一个map记录所有编号，和岛的面积
     * 
     * 第二遍dfs
     * 对于所有的海洋格子，去找它周围的岛
     * 然后看加上这个海洋格子后
     */
    public int largestIsland(int[][] grid) {
        // base casie
        if (grid == null || grid.length == 0) {
            return 1;
        }
        // key：岛屿编号，val：岛屿面积
        HashMap<Integer, Integer> islandMap = new HashMap<>();
        // 因为0和1都有代表了，从2开始
        int islandIndex = 2;
        int res = 0;
        // 第一遍dfs
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int curArea = getArea(grid, i, j, islandIndex);
                    islandMap.put(islandIndex, curArea);
                    islandIndex++;
                    // 要更新res为当前岛屿面积
                    // 有可能整个图都是岛
                    res = Math.max(res, curArea);
                }
            }
        }
        // 如果没有岛屿，那么直接return1
        // 就是造一块新的
        if (islandMap.size() == 0) {
            return 1;
        }
        // 第二遍遍历海洋
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 如果是海洋
                if (grid[i][j] == 0) {
                    // 将四周的岛放进hashset中
                    HashSet<Integer> neiIsland = findNeighbour(grid, i, j);
                    // 如果四周一个岛也没有，那么也不用继续了
                    if (neiIsland.size() < 1) {
                        continue;
                    }
                    // 看两个岛加起来和当前海洋的面积的值
                    int combine = 1;
                    for (Integer cur : neiIsland) {
                        combine += islandMap.get(cur);
                    }
                    res = Math.max(res, combine);
                }
            }
        }
        return res;
    }

    int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int getArea(int[][] grid, int i, int j, int index) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return 0;
        }
        if (grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = index;
        int res = 1;
        for (int[] d : DIRS) {
            int x = i + d[0], y = j  + d[1];
            res += getArea(grid, x, y, index);
        }
        return res;
    }

    private boolean inArea(int[][] grid, int r, int c) {
        return r>= 0 && r<grid.length && c>=0 && c<grid[0].length;
    }

    //对每一个海洋寻找他四周是否有相邻的岛屿
    public HashSet<Integer> findNeighbour(int[][] grid, int i, int j) {
        HashSet<Integer> hashset = new HashSet<>();
        if(inArea(grid,i-1,j) && grid[i-1][j] != 0 )
            hashset.add(grid[i-1][j]);
        if(inArea(grid,i+1,j) && grid[i+1][j] != 0 )
            hashset.add(grid[i+1][j]);
        if(inArea(grid,i,j-1) && grid[i][j-1] != 0 )
            hashset.add(grid[i][j-1]);
        if(inArea(grid,i,j+1) && grid[i][j+1] != 0 )
            hashset.add(grid[i][j+1]);
        return hashset;
    }
}
