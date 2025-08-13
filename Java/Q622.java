public class Q622 {
    /*
     * 底层用数组实现队列
     * 要用到环形数组的技巧
     * 
     */
    class MyCircularQueue {
        private int[] queue;
        private int front;
        private int rear;
        private int size;

        public MyCircularQueue(int k) {
            queue = new int[k];
            front = 0;
            rear = -1;
            size = 0;
        }
        
        // 从rear放入
        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            rear = (rear + 1) % queue.length;
            queue[rear] = value;
            size++;
            return true;
        }
        
        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            front = (front + 1) % queue.length;
            size--;
            return true;
        }
        
        public int Front() {
            return isEmpty() ? -1 : queue[front];
        }
        
        public int Rear() {
            return isEmpty() ? -1 : queue[rear];
        }
        
        public boolean isEmpty() {
            return size == 0;
        }
        
        public boolean isFull() {
            return size == queue.length;
        }
    }
}
