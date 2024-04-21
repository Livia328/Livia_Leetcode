import java.util.*;

public class tmp {
    /**
     * prerequisites: [a, b] b -> a
     * 
     * indegree:
     * 
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for(int i = 0; i < numCourses; i++) {
            adj.put(i, new ArrayList<>());
        }
        for (int[] r : prerequisites) {
            int curCourse = r[0];
            int preCourse = r[1];
            indegree[curCourse]++;
            adj.get(preCourse).add(curCourse);
        }
        // start bfs
        List<Integer> res = new LinkedList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int cur = queue.poll();
                res.add(cur);
                for (int nei : adj.get(cur)) {
                    indegree[nei]--;
                    if (indegree[nei] == 0) {
                        queue.add(nei);
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
