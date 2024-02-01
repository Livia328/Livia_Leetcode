import java.util.*;

public class Q210 {
    /**
     * topological sorting
     * 
     * in degree
     * 
     * 0 -> 1
     * adjacent<integer, integer>
     * 
     * 一个课从queue里poll出来的时候就是
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // build graph
        int[] indegree = new int[numCourses];
        Map<Integer, List<Integer>> adjacent = new HashMap<>();
        // for (int i = 0; i < numCourses; i++) {
        //     adjacent.put(i, new ArrayList<>());
        // }
        for (int[] cur : prerequisites) {
            // cur [1,0]
            int from = cur[1];
            int to = cur[0];
            // update indegree
            indegree[to]++;
            if (!adjacent.containsKey(from)) {
                adjacent.put(from, new ArrayList<>());
            }
            adjacent.get(from).add(to);
        }
        // start bfs
        Queue<Integer> queue = new ArrayDeque<>();
        // add the indegree 0 into it
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res.add(cur);
            if (adjacent.containsKey(cur) && adjacent.get(cur).size() != 0) {
                for (int nei : adjacent.get(cur)) {
                    // change indegree
                    indegree[nei]--;
                    if (indegree[nei] == 0) {
                        queue.offer(nei);
                    }
                }
            }
        }
        if (res.size() != numCourses) {
            return new int[0];
        }
        int[] ans = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
