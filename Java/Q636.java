import java.util.*;

public class Q636 {
    /*
     * 用stack模拟
     * stack的顶端是正在执行的函数
     * 
     * 当函数调用开始的时候，暂停当前运行函数的执行，计算目前执行时间，加入res，将当前函数入栈
     * 函数调用结束，将栈顶元素pop出去，并计算执行时间，如果此时栈内还有元素，则继续开始这个
     */
    public int[] exclusiveTime(int n, List<String> logs) {
        // queue里面放[index, 开始运行时间]
        Deque<int[]> stack = new ArrayDeque<>();
        int[] res = new int[n];
        for (String log : logs) {
            int index = Integer.parseInt(log.substring(0, log.indexOf(':')));
            String type = log.substring(log.indexOf(':') + 1, log.lastIndexOf(':'));
            int timeStamp = Integer.parseInt(log.substring(log.lastIndexOf(':') + 1));
            if ("start".equals(type)) {
                // 如果之前有thread在执行
                // 那么暂停，将目前执行的时间计数，修改开始时间
                if (!stack.isEmpty()) {
                    res[stack.peek()[0]] += timeStamp - stack.peek()[1];
                    stack.peek()[1] = timeStamp;
                }
                stack.push(new int[]{index, timeStamp});
            } else {
                int[] cur = stack.pop();
                res[cur[0]] += timeStamp - cur[1] + 1;
                // 开始之前暂停的时间
                if (!stack.isEmpty()) {
                    stack.peek()[1] = timeStamp + 1;
                }
            }
        }
        return res;
    }
}
