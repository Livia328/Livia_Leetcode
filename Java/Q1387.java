import java.util.*;

public class Q1387 {
    /*
     * 先写一个函数得到the Power Value
     * 因为得到the Power Value的过程
     * 是在不断地重复那个步骤
     * 所以想到recursion
     * 
     * 因为the power number是变化了多少次
     * 
     * 所以我们可以得到
     * base case：dfs(1) = 0
     * 当x为1的时候不需要变化
     * 
     * x为奇数，dfs(x) = dfs(x/2) + 1
     * x为偶数：dfs(x) = dfs(3x + 1) + 1
     * 
     * 因为可能会碰到重复的情况
     * 所以带memo进行记忆化搜索
     * hashmap, key是数字，value是它对应的the power value
     * 
     * 接下来拿到第k个有两种方案
     * 一种是放入数组排序
     * 一种就是347. Top K Frequent Elements的内容了
     * 用一个pq，里面放int[], {num, the power num}
     * max heap，顶端是最大的
     * 
     * 如果pq的size > k,就poll
     * 最后剩下在pq顶端的就是k th
     */
    // memo, key是数字，value是它对应的the power value
    Map<Integer, Integer> memo = new HashMap<>();

    /*
     * 先写一个函数得到the Power Value
     * 因为得到the Power Value的过程
     * 是在不断地重复那个步骤
     * 所以想到recursion
     * 
     * 因为the power number是变化了多少次
     * 
     * 所以我们可以得到
     * base case：dfs(1) = 0
     * 当x为1的时候不需要变化
     * 
     * x为奇数，dfs(x) = dfs(x/2) + 1
     * x为偶数：dfs(x) = dfs(3x + 1) + 1
     * 
     * 因为可能会碰到重复的情况
     * 所以带memo进行记忆化搜索
     * hashmap, key是数字，value是它对应的the power value
     * 
     * 接下来拿到第k个有两种方案
     * 一种是放入数组排序
     * 一种就是347. Top K Frequent Elements的内容了
     * 
     * 用数组排序：
     * 用一个数组如下
     * index: 0,  1,  2    ...       hi - low
     * value: lo,lo+1,lo+2 ... hi - 1, hi
     * 
     * 然后对着个数组根据它的dfs进行排序
     * 返回arr[k - 1]
     */
    public int getKth1(int lo, int hi, int k) {
        // 要写Integer，不然下面sort
        Integer[] arr = new Integer[hi - lo + 1];
        Arrays.setAll(arr, i -> i + lo);
        Arrays.sort(arr, (x, y) -> dfs(x) - dfs(y)); 
        return arr[k - 1];
    }


    public int getKth(int lo, int hi, int k) {
        // [num, the power value]
        PriorityQueue<int[]> maxHeap = new PriorityQueue<int[]>(
            (a, b) -> (a[1] == b[1]) ? (b[0] - a[0]) : (b[1] - a[1])
        );
        
        for(int num = lo; num <= hi; num++){
            maxHeap.add(new int[]{num, dfs(num)});
            // 如果size大于k了，就把最大的给poll出去
            if(maxHeap.size() > k){
                maxHeap.poll();
            }
        }
        
        return maxHeap.peek()[0];
    }

    /*
     * 给定x，得到它对应的the power value
     */
    public int dfs(int x) {
        // base case
        if (x == 1) {
            return 0;
        }
        // check memo, 如果之前算过了就直接返回
        if (memo.containsKey(x)) {
            return memo.get(x);
        }
        if (x % 2 == 1) {
            memo.put(x, dfs(x * 3 + 1) + 1);
        } else {
            memo.put(x, dfs(x / 2) + 1);
        }
        return memo.get(x);
    }
}
