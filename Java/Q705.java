import java.util.Iterator;
import java.util.LinkedList;

public class Q705 {
    /*
     * 遵循hashset的原则
     */
    class MyHashSet {

        private static final int SIZE = 1000;
        private LinkedList[] data;

        public MyHashSet() {
            data = new LinkedList[SIZE];
            for (int i = 0; i < SIZE; i++) {
                data[i] = new LinkedList<>();
            }

        }
        
        public void add(int key) {
            if (contains(key)) {
                return;
            }
            int index = hash(key);
            data[index].addFirst(key);
        }
        
        public void remove(int key) {
            if (!contains(key)) {
                return;
            }
            int index = hash(key);
            data[index].remove(Integer.valueOf(key));
        }
        
        public boolean contains(int key) {
            int index = hash(key);
            Iterator<Integer> it = data[index].iterator();
            while (it.hasNext()) {
                Integer cur = it.next();
                if (cur == key) {
                    return true;
                }
            }
            return false;
        }

        public int hash(int key) {
            return key % 1000;
        }
    }
}
