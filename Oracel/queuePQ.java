package Oracel;
import java.util.*;

public class queuePQ {
    class Node {
        int value;
        int priority;
    
        Node(int value, int priority) {
            this.value = value;
            this.priority = priority;
        }
    }
    
    public class PriorityQueueCustom {
        LinkedList<Node> queue;
        int capacity;
    
        public PriorityQueueCustom(int capacity) {
            this.capacity = capacity;
            this.queue = new LinkedList<>();
        }
    
        public void enqueue(int value, int priority) {
            // 如果没满的话，找到地方位置放
            if (queue.size() < capacity) {
                int i = 0;
                while (i < queue.size() && queue.get(i).priority >= priority) {
                    i++;
                }
                queue.add(i, new Node(value, priority));
            } else {
                //满了，查看是否有需要poll出的priority
                for (int i = queue.size() - 1; i >= 0; i--) {
                    if (queue.get(i).priority < priority) {
                        queue.remove(i);
                        enqueue(value, priority);
                        return;
                    }
                }
            }
        }
    
        public void dequeue() {
            if (!queue.isEmpty()) {
                queue.removeFirst();
            }
        }

        public void printQueue() {
            for (Node cur : queue) {
                System.out.println("Value: " + cur.value + ", Priority: " + cur.priority);
            }
        }
    }

  public static void main(String[] args){
    queuePQ instance = new queuePQ();
    Scanner scanner = new Scanner(System.in);
    int capacity = Integer.parseInt(scanner.nextLine());
    PriorityQueueCustom pq = instance.new PriorityQueueCustom(capacity);

    while (scanner.hasNextLine()) {
        String[] input = scanner.nextLine().split(" ");
        if (input[0].equals("enqueue")) {
            int value = Integer.parseInt(input[1]);
            int priority = Integer.parseInt(input[2]);
            pq.enqueue(value, priority);
        } else if (input[0].equals("dequeue")) {
            pq.dequeue();
        }
    }

    pq.printQueue();
  }
}
