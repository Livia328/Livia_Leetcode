import java.util.LinkedList;

public class Q1670 {
    /*
     * 因为要在中间插入，所以很自然地想到分成两个队列
     * 
     * 因为把6加入【1，2，3，4，5】的结果是【1，2，6，3，4，5】
     * 所以当有奇数个元素的时候
     * 我们要维护左边少，右边多
     * 
     * 如果是偶数个元素，pushMiddle优先从右边添加
     * 如果是奇数个元素，pushMiddle优先从右边删除
     * 如果只有元素，popFront要去右边删除
     */
    class FrontMiddleBackQueue {

        LinkedList<Integer> left;
        LinkedList<Integer> right;

        public FrontMiddleBackQueue() {
            left = new LinkedList<>();
            right = new LinkedList<>();
        }

        // 维护左边少右边多的状态，每次增删元素之后都要执行一次
        private void balance() {
            // 右边最多比左边多一个元素
            if (right.size() > left.size() + 1) {
                // 右边多，匀一个给左边
                left.addLast(right.removeFirst());
            }
            if (left.size() > right.size()) {
                // 左边多，匀一个给右边
                right.addFirst(left.removeLast());
            }
        }
        
        public void pushFront(int val) {
            left.addFirst(val);
            balance();
        }
        
        public void pushMiddle(int val) {
            if ((left.size() + right.size()) % 2 == 0) {
                // 如果有偶数个元素时，pushMiddle 优先向右边添加
                right.addFirst(val);
            } else {
                left.addLast(val);
            }
            balance();
        }
        
        public void pushBack(int val) {
            right.addLast(val);
            balance();
        }
        
        public int popFront() {
            if ((left.size() + right.size()) == 0) {
                return -1;
            }
            if ((left.size() + right.size()) == 1) {
                // 如果只有 1 个元素，popFront 的时候，要去右边删除
                return right.removeFirst();
            }
            int e = left.removeFirst();
            balance();
            return e;
        }
        
        public int popMiddle() {
            if ((left.size() + right.size()) == 0) {
                return -1;
            }
            int e;
            if ((left.size() + right.size()) % 2 == 0) {
                e = left.removeLast();
            } else {
                // 如果有奇数个元素时，popMiddle 优先从右边删除
                e = right.removeFirst();
            }
            balance();
            return e;
        }
        
        public int popBack() {
            if ((left.size() + right.size()) == 0) {
                return -1;
            }
            int e = right.removeLast();
            balance();
            return e;
        }
    }
}
