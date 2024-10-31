import java.util.*;

public class Q480 {
    /*
    * 基本思想和Q295一样，本题最难的是remove的逻辑
    * priorityQueue
    * 
    * 因为要找的是中位数，通过brute force我们可以发现我们只是想要k/2和(k + 1)/2的两个数
    * 所以核心思想是把这个数组分成两半
    * 因为如果是奇数需要快速获得两半的第一个数，所以想到可以用priorityqueue
    * 
    * 前半段数用一个max heap，
    * 后半段用一个min heap，
    * 这样顶上的数就是前半段的最大值，和后半段的最小值，很方便取中位数
    * 同时我们要确保max heap的peek（左半段的最大值）比min heap的peek（右半段的最小值）
    * 
    * 
    * 加入的逻辑：
    * 我们怎么知道新的数应该放到哪边呢？
    * 
    * 比较size
    * 如果max heap size大，那么就先放到max heap，然后把maxheap poll出来的放到min heap里
    * 反之，就放过来搞一下
    *
    * 
    * 得到median的逻辑：
    * 如果元素不一样多，那么多出来的就是中位数
    * 如果元素一样多，那么就拿两个heap顶上的元素取平均数
    * 
    * 本题最难的是remove的逻辑：
    * 如果直接用priorityqueue的remove的话，那么还是o(n)
    * 所以用延迟删除
    * 
    * 用一个hashmap记录待删除的元素
    * 决定元素应该从哪个队列被删除：
    * 如果num <= left.peek, 从left删除，否则从右边删除
    * 因为这个时候我们没有真的删除，所以我们需要额外维护两个变凉
    * validLeft，validRight，表示左右pq中还有小的元素的多少


    */

    // 左边是maxheap
    // 注意这里不能用(a, b) -> b - a这种，会越界
    // 一定要写的话，写⬇️
    // PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(
    //     new Comparator<Integer>() {
    //         public int compare(Integer i1, Integer i2) {
    //             return i2.compareTo(i1);
    //         }
    //     }
    // );
    PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> right = new PriorityQueue<>();
    int validLeft, validRight = 0;
    Map<Integer, Integer> removeMap = new HashMap<>();
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            addNum(nums[i]);
        }
        res[0] = findMedian();
        for (int i = k; i < nums.length; i++) {
            removeNum(nums[i - k]);
            addNum(nums[i]);
            res[i - k  + 1] = findMedian();
        }
        return res;
    }

    private void addNum(int num) {
        if (left.isEmpty() || num <= left.peek()) {
            left.offer(num);
            validLeft++;
        } else {
            right.offer(num);
            validRight++;
        }
        adjustPriorityQueues();
    }
    

    private void removeNum(int num) {
        removeMap.put(num, removeMap.getOrDefault(num, 0) + 1);
        if (num <= left.peek()) {
            // 那么需要删除的是在左边
            validLeft--;
        } else {
            validRight--;
        }
        adjustPriorityQueues();
    }

    private double findMedian() {
        return validLeft == validRight ? ((double)left.peek() + right.peek()) / 2 : left.peek();
    }

    /*
     * 永远保持left == right 或者left == right + 1
     * 去除invalid的peek
     */
    private void adjustPriorityQueues() {
        if (validLeft > validRight + 1) {
            right.offer(left.poll());
            validLeft--;
            validRight++;
        } else if (validLeft < validRight) {
            left.offer(right.poll());
            validLeft++;
            validRight--;
        }
        while (!left.isEmpty() && removeMap.containsKey(left.peek())) {
            int num = left.poll();
            removeMap.put(num, removeMap.get(num) - 1);
            if (removeMap.get(num) == 0) {
                removeMap.remove(num);
            }
        }
        while (!right.isEmpty() && removeMap.containsKey(right.peek())) {
            int num = right.poll();
            removeMap.put(num, removeMap.get(num) - 1);
            if (removeMap.get(num) == 0) {
                removeMap.remove(num);
            }
        }
    }

}
