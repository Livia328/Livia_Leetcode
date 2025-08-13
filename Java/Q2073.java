import java.util.ArrayDeque;

public class Q2073 {
    /*
     * 可以用队列来模拟这一过程
     * 
     * 时间复杂度
     * O(n * m)
        n: size of array
        m : max number within the array
     */
    public int timeRequiredToBuy1(int[] tickets, int k) {
        int res = 0;
        while (true) {
            for (int i = 0; i < tickets.length; i++) {
                if (tickets[i] > 0) {
                    tickets[i]--;
                    res++;
                }
                if (i == k && tickets[k] == 0) {
                    return res;
                }
            }
        }
    }

    /*
     * 可以简化这一过程
     * ticket[k]要变成0，那么会循环ticket[k]次
     * 
     * 在k之前的人，如果tickets[i] < tickets[k]
     * 那么他会买ticket[i]个
     * 否则，他最多买ticket[k]个
     * 
     * ticket[k]之后的人最多买了 tickets[k] - 1 张票
     */
    public int timeRequiredToBuy(int[] tickets, int k) {
        int res = 0;
        for (int i = 0; i < tickets.length; i++) {
            if (i <= k) {
                res += Math.min(tickets[i], tickets[k]);
            } else {
                res += Math.min(tickets[i], tickets[k] - 1);
            }
        }
        return res;
    }
}
