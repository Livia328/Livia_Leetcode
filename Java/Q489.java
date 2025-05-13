import java.util.*;

public class Q489 {
    /*
     * 就是dfs，但是不是用DIR数组来表示方向
     * 而是用它给的api
     * 
     * 虽然我们不知道机器人所在位置坐标
     * 但我们可以假设机器人现在所在的位置是(0,0)
     * 用robot.move()来判断下一个格子有没有越界/是不是空格子
     * 
     * 用1234来表示4个方向
     * 初始化方向为0，向上
     * 
     *     0
     * 3  -|-  1
     *     2
     * 
     * move up - move; orientation d
     * move right - turnRight, move; orientation (d + 1) % 4
     * move down - turnRight, turnRight, move; orientation (d + 2) % 4
     * move left - turnRight, turnRight, turnRight, move;  orientation (d + 3) % 4
     */
    interface Robot {
        public boolean move();
        public void turnLeft();
        public void turnRight();
        public void clean();
    }

    private int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    // “x + y"
    private Set<String> vis = new HashSet<>();
    private Robot robot;

    public void cleanRoom(Robot robot) {
        this.robot = robot;
        // 我们假设机器人现在所在的位置是(0,0)
        dfs(0, 0, 0);
    }
    // (i,j)为当前坐标，d为当前的directions
    private void dfs(int i, int j, int d) {
        robot.clean();
        // 加入visited
        vis.add(i + "," + j);
        for (int k = 0; k < 4; ++k) {
            int nextDir = (d + k) % 4;
            // 得到下一个坐标
            int x = i + DIRS[nextDir][0], y = j + DIRS[nextDir][1];
            if (!vis.contains(x + "," + y) && robot.move()) {
                dfs(x, y, nextDir);
                // 回到原来的位置
                // 转180度，比如本来是up，现在是down
                robot.turnRight();
                robot.turnRight();
                robot.move();
                // 再调整方向
                robot.turnRight();
                robot.turnRight();
            }
            // 下一个方向orientation
            robot.turnRight();
        }
    }
}
