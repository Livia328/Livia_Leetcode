import java.util.ArrayList;
import java.util.PriorityQueue;

public class Q1834 {
    /*
     * 这个题目有三个东西需要考虑
     * 第一个是开始时间，只有到了开始时间，才需要开始
     * 其次应该考虑任务的处理时间，在所有可以执行的任务中优先选择处理时间最短的；
     * 如果存在处理时间相同的任务，那么优先选择索引最小的。
     * 
     * 所以可以先按照开始时间排序
     * 维护一个当前的时间线变量now来判断一个任务有没有开始
     * 
     * 然后用一个pq对处理时间和index排序
     */
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        ArrayList<int[]> tasksWithIndex = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tasksWithIndex.add(new int[]{tasks[i][0], tasks[i][1], i});
        }
        tasksWithIndex.sort((a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            // 有限考虑处理时间
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[2] - b[2];
        });
        ArrayList<Integer> res = new ArrayList<>();
        int now = 0;
        int i = 0;
        while(res.size() < n) {
            if (!pq.isEmpty()) {
                // 完成一个任务
                int[] task = pq.poll();
                res.add(task[2]);
                // 推进当前时间线
                now += task[1];
            } else if (i < n && tasksWithIndex.get(i)[0] > now) {
                // 队列为空是因为还没到开始时间
                // 把时间线推进到任务开始的时间
                now = tasksWithIndex.get(i)[0];
            }
            // 由于时间线推进，所以会产生新的可以开始的任务
            // 把他们都加进pq里
            for (; i < n && tasksWithIndex.get(i)[0] <= now; i++) {
                pq.offer(tasksWithIndex.get(i));
            }
        }
        // 把它转化为int[]
        int[] arr = new int[n];
        for (int j = 0; j < n; j++) {
            arr[j] = res.get(j);
        }
        return arr;
    }
}
