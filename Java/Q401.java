import java.util.*;

public class Q401 {
    /**
     * 一开始没想到，但是因为是穷举，应该想到回溯的
     * 重点还是分析，选择，base case，什么时候加答案
     * index是num, min, hour，不断往上加
     * 
     * 为了可以同时遍历hours 和 mintues，在前后加0
     * 
     * int[] hours = new int[]{1, 2, 4, 8, 0, 0, 0, 0, 0, 0};
     * int[] minutes = new int[]{0, 0, 0, 0, 1, 2, 4, 8, 16, 32};
     * 这个真的很巧妙！
     */
    int[] hours = new int[]{1, 2, 4, 8, 0, 0, 0, 0, 0, 0};
    int[] minutes = new int[]{0, 0, 0, 0, 1, 2, 4, 8, 16, 32};
    List<String> res = new ArrayList<>();
    public List<String> readBinaryWatch(int turnedOn) {
        backtrack(turnedOn, 0, 0, 0);
        return res;
    }

    public void backtrack(int turnedOn, int index, int hour, int min) {
        // base case
        if (hour > 11 || min > 59) {
            return;
        }
        // add ans
        if (turnedOn == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(hour).append(':');
            if (min < 10) {
                sb.append('0');
            }
            sb.append(min);
            res.add(sb.toString());
            return;
        }
        for (int i = index; i < 10; i++) {
            backtrack(turnedOn - 1, i + 1, hour + hours[i], min + minutes[i]);
        }
    }
}
