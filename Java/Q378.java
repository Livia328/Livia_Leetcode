import java.util.PriorityQueue;

public class Q378 {
    /*
     * 有点类似于merget sort和sort k list的感觉
     * 每次比较头节点，如果poll出去了，就将下一个入列
     * 
     * 所以我们需要一个pq
     * 第k个poll出来的就是我们要的target
     * 
     * 所以我们要知道这个数的值，它的坐标
     * int[val, row, col]的方式存在queue里
     * 
     *  1  5  9
        10 11 13
        12 13 15

        先将每一行的第一列入pq
        pq<[1,0,0],[10,1,0], [12,2,0]>
     * 
     * poll出[1,0,0], 然后将[5,0,1]入列
     */
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        for (int i = 0; i < matrix.length; i++) {
            pq.offer(new int[]{matrix[i][0], i, 0});
        }
        while (!pq.isEmpty() && k > 1) {
            int[] cur = pq.poll();
            k--;
            int i = cur[1], j = cur[2];
            // 这一行没有到头
            if (j + 1 < matrix[i].length) {
                pq.add(new int[]{matrix[i][j + 1], i, j + 1});
            }
        }
        return pq.poll()[0];
    }
}
