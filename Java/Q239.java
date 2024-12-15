import java.util.*;

public class Q239 {
    /** 
    * brute force
    keep sliding window, 每次花O(k)的时间找最大值

    O(nK)
    */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        //n = 8, k = 3
        int[] ans = new int[nums.length - k + 1];
        int L = 0, R = k - 1;
        int index = 0;
        while (R <= nums.length - 1) {
            int max = Integer.MIN_VALUE;
            for (int i = L; i <= R; i++) {
                max = Math.max(max, nums[i]);
            }
            ans[index] = max;
            System.out.println("L = " + L + "R = " + R + "max = " + max);
            L++; R++;index++;
        }
        return ans;
    }

    /**
     * 因为是滑动窗口，我们需要
     * 同时从头上pop出去元素
     * 和
     * 同时从尾部加入元素
     * 所以deque
     * 
     * 每次加入的时候，把queue里面小于这个数字的全部移出
     * 因为他们不是max的candidate了
     * 
     * 每次pop的时候，判断这个数字还在不在queue里
     * 因为我们可能之前已经pop出去了
     * 也就是比较cur == queue.peekFirst
     * 
     * 这样就保证了
     * queue里仅包含窗口内元素
     * queue里的元素非严格递减
     * 
     *  0  1  2  3   4
     * [1, 5, 0, -1, 5] k = 2
     * 
     * [未形成窗口]
     * push 0
     * mq: 0
     * 
     * push5
     * mq: 5
     * res: 5
     * 
     * [形成窗口]
     * pop 0
     * 因为 5!= 0，所以不用pop
     * 
     * push 0
     * mq: 0 5
     * res: 5 5
     * 
     * pop5
     * mq: 0
     * 
     * push -1
     * mq: -1, 0
     * 
     * res: 5, 5, 0
     * 
     * pop0
     * mq: -1
     * 
     * push5
     * mq: 5
     * 
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {
        if(nums.length == 0 || k == 0) return new int[0];
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        // 未形成窗口
        for(int i = 0; i < k; i++) {
            // 把所有不是candidate的全都弄走
            while(!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);
        }
        res[0] = deque.peekFirst();
        // 形成窗口后
        for(int i = k; i < nums.length; i++) {
            // 如果等于，才remove
            // 因为有可能这个数之前已经被remove过了
            if(deque.peekFirst() == nums[i - k])
                deque.removeFirst();
            // 去掉所有不可能是candidate的
            while(!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);
            res[i - k + 1] = deque.peekFirst();
        }
        return res;
    }

    /**
     * 因为是滑动窗口，我们需要
     * 同时从头上pop出去元素
     * 和
     * 同时从尾部加入元素
     * 所以deque
     * 
     * 每次加入的时候，把queue里面小于这个数字的全部移出
     * 因为他们不是max的candidate了
     * 
     * 每次pop的时候，判断这个数字还在不在queue里
     * 因为我们可能之前已经pop出去了
     * 也就是比较cur == queue.getFirst
     * 
     * -> 基于这两点，自己实现一个MonotonicQueue
     * 
     *  0  1  2  3   4
     * [1, 5, 0, -1, 5] k = 2
     * 
     * push 0
     * mq: 0
     * 
     * push5
     * mq: 5
     * res: 5
     * 
     * pop 0
     * 因为 5!= 0，所以不用pop
     * 
     * push 0
     * mq: 0 5
     * res: 5 5
     * 
     * pop5
     * mq: 0
     * 
     * push -1
     * mq: -1, 0
     * 
     * res: 5, 5, 0
     * 
     * pop0
     * mq: -1
     * 
     * push5
     * mq: 5
     * 
     * 
     */
    // 单调队列的实现
    class MonotonicQueue {
        LinkedList<Integer> q = new LinkedList<>();
        public void push(int n) {
            // 将小于 n 的元素全部删除
            while (!q.isEmpty() && q.getLast() < n) {
                q.pollLast();
            }
            // 然后将 n 加入尾部
            q.addLast(n);
        }

        public int max() {
            return q.getFirst();
        }

        public void pop(int n) {
            if (n == q.getFirst()) {
                q.pollFirst();
            }
        }
    }
    
    public int[] maxSlidingWindow2(int[] nums, int k) {
        MonotonicQueue window = new MonotonicQueue();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                // 先填满窗口的前 k - 1
                window.push(nums[i]);
            } else {
                // 窗口向前滑动，加入新数字
                window.push(nums[i]);
                // 记录当前窗口的最大值
                res.add(window.max());
                // 移出旧数字
                window.pop(nums[i - k + 1]);
            }
        }
        // 需要转成 int[] 数组再返回
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(maxSlidingWindow(nums, 3)));
    }
}
