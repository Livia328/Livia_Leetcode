import java.util.*;

public class Q207 {
    /**
     * topological sorting
     * 
     * indegree, start with the inderee 0;
     * 
     * 0 is the precourse
     * prerequisites = [[1,0]]
     * 
     * change to adjacent:
     * precourse, all courses after it
     * 0 -> 1
     * 
     * count the indegree, 0:0, 1:1
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjacent = new HashMap<>();
        int[] indegree = new int[numCourses];
        // 这个要写，不然BFS里面拿到for (int nei : adjacent.get(cur)) 会是null
        // 面试时可以先不写，回头回来补
        for (int i = 0; i < numCourses; i++) {
            adjacent.put(i, new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            int from = pre[1]; //0
            int to = pre[0]; //1
            // count the indegree
            indegree[to]++;
            // generate the map
            if (!adjacent.containsKey(from)) {
                adjacent.put(from, new ArrayList<>());
            }
            // 0: [1, ]
            adjacent.get(from).add(to);
        }
        // start the topological sorting
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            numCourses--;
            for (int nei : adjacent.get(cur)) {
                // minus one of the nei's indegree
                indegree[nei]--;
                if (indegree[nei] == 0) {
                    queue.add(nei);
                }
            }
        }
        return numCourses == 0;
    }


    /**
     * DFS + visited
     * 
     * 类比贪吃蛇游戏，visited 记录蛇经过过的格子，
     * 而 onPath 仅仅记录蛇身。
     * onPath 用于判断是否成环，类比当贪吃蛇自己咬到自己（成环）的场景。
     */
    boolean[] path;
    boolean[] visited;
    boolean hasCycle = false;
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        // build graph
        Map<Integer, List<Integer>> adjacent = new HashMap<>();
        // 这个要写，不然BFS里面拿到for (int nei : adjacent.get(cur)) 会是null
        // 面试时可以先不写，回头回来补
        for (int i = 0; i < numCourses; i++) {
            adjacent.put(i, new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            int from = pre[1]; //0
            int to = pre[0]; //1
            // count the indegree
            // generate the map
            if (!adjacent.containsKey(from)) {
                adjacent.put(from, new ArrayList<>());
            }
            // 0: [1, ]
            adjacent.get(from).add(to);
        }
        //用来存储当前path上的，需要backtra
        path = new boolean[numCourses];
        //用来存储是否遍历过
        visited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            dfs(adjacent, i);
        }
        //如果最后没有环，说明可以上完
        return !hasCycle;
    }

    public void dfs(Map<Integer, List<Integer>> adjacent, int cur) {
        //如果这个点已经出现在这个路径中，那么说明有环
        if (path[cur]) {
            hasCycle = true;
            return;
        }
        //如果已经找到了环或者这个点遍历过，那么直接return；
        if (visited[cur] || hasCycle) {
            return;
        }
        visited[cur] = true;
        path[cur] = true;
        for (int nei : adjacent.get(cur)) {
            dfs(adjacent, nei);
        }
        path[cur] = false;
    }
}
