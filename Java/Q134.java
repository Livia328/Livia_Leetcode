public class Q134 {
    /*
     * 看到题目的第一想法：
     * 遍历每一个station，看看从这个station出发能否回到原点
     * 
     * 注意因为是圆圈，所以要取余数
     * 
     * brute force
     */
    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            int curStation = i;
            int remainGas = gas[i];
            // 当前油量可以到达下一个加油站
            while (remainGas - cost[curStation] >= 0) {
                remainGas = remainGas - cost[curStation] + gas[(curStation + 1) % n]; // 因为是圆，所以取余一下
                curStation = (curStation + 1) % n;
                if (curStation == i) {
                    return i;
                } 
            }
        }
        return -1;
    }
    /*
     * 只要总油量大于总耗油量那么一定可以跑完全程
     * 从头开始遍历，如果中间一旦curSum < 0了，说明到这个点都不行
     * 立刻reset重新开始
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int totalSum = 0;
        int curSum = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            curSum += gas[i] - cost[i];
            totalSum += gas[i] - cost[i];
            if (curSum < 0) {
                res = (i + 1) % n;
                curSum = 0;
            }
        }
        return totalSum < 0 ? -1 : res;
    }
}
