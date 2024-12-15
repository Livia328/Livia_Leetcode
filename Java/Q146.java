import java.lang.foreign.ValueLayout;
import java.rmi.Remote;
import java.rmi.server.RemoteServer;
import java.util.*;

public class Q146 {
    /**
     * 因为是k-v结构所以我们应该有hashmap
     * 因为我们想把它很好地sort起来，所以可以是LinkedList的想法
     */
    class LRUCache {
        int cap;
        LinkedHashMap<Integer, Integer> map;

        public LRUCache(int capacity) {
            this.cap = capacity;
            map = new LinkedHashMap<>();
        }
        
        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            makeRecent(key);
            return map.get(key);
        }
        
        //如果key存在，修改值，变为最近常用
        //如果key不存在，检查是否满了
            //- 没满，直接存，变为最近常用
            //- 满了，删掉，插入，变为最近常用
        public void put(int key, int value) {
            if (map.containsKey(key)) {
                map.put(key, value);
                makeRecent(key);
                return;
            }
            if (cap <= map.size()) {
                int deleteKey = map.keySet().iterator().next();
                //int deleteKey = map.pollFirstEntry().getKey();
                map.remove(deleteKey);
            }
            map.put(key, value);
            makeRecent(key);
        }

        // 就是把它重新拿出来再放进去一次，这样就在新LinkedList的头上了
        public void makeRecent(int key) {
            int val = map.get(key);
            map.remove(key);
            map.put(key, val);
        }
    }


    /**
     * 自己实现node
     * 因为需要在O(1)时间内得到key and val，所以需要hashmap
     * 因为每次访问 cache 中的某个 key，需要将这个元素变为最近使用的，
     * 也就是说 cache 要支持在任意位置快速插入和删除元素。
     * 所以可以用node这样一个个接上去
     * 
     * node1 - node2 - node3...node6
     * left                    right
     * LRU                      most recent
     * 
     * 每次更新的时候，都要将这个node从原来的地方删除，然后放到最右边
     */
    class LRUCache2 {

        private class Node {
            private int key;
            private int val;
            Node next;
            Node prev;
            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        int cap;
        Node left; //LRU
        Node right; //most recently
        Map<Integer, Node> cache;

        public LRUCache2(int capacity) {
            this.cap = capacity;
            cache = new HashMap<>();
            this.left = new Node(0, 0);
            this.right = new Node(0, 0);
            this.left.next = this.right;
            this.right.prev = this.left;
        }
        
        public int get(int key) {
            if (cache.containsKey(key)) {
                remove(cache.get(key));
                insert(cache.get(key));
                return cache.get(key).val;
            } else {
                return -1;
            }

        }
        
        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                remove(cache.get(key));
            }
            cache.put(key, new Node(key, value));
            insert(cache.get(key));
            if (cache.size() > cap) {
                Node lru = this.left.next;
                remove(lru);
                cache.remove(lru.key);
            }
        }

        public void remove(Node node) {
            Node prev = node.prev;
            Node aft = node.next;
            prev.next = aft;
            aft.prev = prev;
        }

        public void insert(Node node) {
            Node prev = this.right.prev;
            Node next = this.right;
            prev.next = node;
            next.prev = node;
            node.next = next;
            node.prev = prev;
        }
    }
}
