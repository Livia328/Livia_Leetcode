import java.util.PriorityQueue;

public class Q295 {
    /*
     * priorityQueue
     * 
     * 因为要找的是中位数，所以核心思想是把这个数组分成两半
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
     * 
     * 反之，就放过来搞一下
     * 
     * 
     * 得到median的逻辑：
     * 如果元素不一样多，那么多出来的就是中位数
     * 如果元素一样多，那么就拿两个heap顶上的元素取平均数
     */
    class MedianFinder {
        // 用来存左半段的数
        PriorityQueue<Integer> maxHeap;
        PriorityQueue<Integer> minHeap;

        public MedianFinder() {
            maxHeap = new PriorityQueue<>((a, b) -> b - a);
            minHeap = new PriorityQueue<>();
        }
        
        public void addNum(int num) {
            if (maxHeap.size() >= minHeap.size()) {
                maxHeap.offer(num);
                minHeap.offer(maxHeap.poll());
            } else {
                minHeap.offer(num);
                maxHeap.offer(minHeap.poll());
            }
        }
        
        public double findMedian() {
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            }
            if (maxHeap.size() < minHeap.size()) {
                return minHeap.peek();
            }
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }
}
