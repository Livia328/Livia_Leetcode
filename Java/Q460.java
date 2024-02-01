import java.util.*;

public class Q460 {
    /**
     * 根据需求分析：
     * 因为我们要有kv结构，所以需要hashmap<Integer, Integer> key: key, value: vlaue
     * 同时因为我们需要记录每一个key的frequency，所以有一个map Map<key, frequency> 
     * 
     * 因为我们要删除最小的freq，所以还需要一个fre -> val的map Map<frequency, List<key>>
     * 对于这个表，我们有这么些要求：
     *  1. 1对多，1个frequency有多个key
     *  2. 存在时间顺序，这样就可以快速查找并删除最旧的key
     *  3. 希望能快速删除一个key，这样当一个key更新的时候我们就可以迅速更改它，从frequency的表里删除，从frequency+1的表放进去
     * 
     * -》 LinkedHashSet
     */
    class LFUCache {

        Map<Integer, Integer> key2val;
        Map<Integer, Integer> key2freq;
        Map<Integer, LinkedHashSet> freq2key;
        int minFreq;
        int capacity;

        public LFUCache(int capacity) {
            key2val = new HashMap<>();
            key2freq = new HashMap<>();
            freq2key = new HashMap<>();
            this.capacity = capacity;
            this.minFreq = 0;
        }
        
        public int get(int key) {
            if (!key2val.containsKey(key)) {
                return -1;
            }
            increaseFreq(key);
            return key2val.get(key);
        }
        
        public void put(int key, int value) {
            if (capacity <= 0) return;
            if (key2val.containsKey(key)) {
                key2val.put(key, value);
                increaseFreq(key);
                return;
            }
            // 新插入
            // 先判断有没有到最大值
            if (this.capacity <= key2val.size()) {
                removeMinFreqKey();
            }
            // 插入
            key2val.put(key, value);
            key2freq.put(key, 1);
            freq2key.putIfAbsent(1, new LinkedHashSet<>());
            freq2key.get(1).add(key);
            this.minFreq = 1;
        }


        public void increaseFreq(int key) {
            // 先把现在的拿出来，以免有错
            int freq = key2freq.get(key);
            // update key2freq
            key2freq.put(key, freq + 1);

            // 更新freq2key
            // 从当前所属的freq中拿走
            freq2key.get(freq).remove(key);
            // 如果这个空了，remove它
            if (freq2key.get(freq).isEmpty()) {
                freq2key.remove(freq);
                if (freq == this.minFreq) {
                    minFreq++;
                }
            }
            // 放到新的freq里
            freq2key.putIfAbsent(freq + 1, new LinkedHashSet<>());
            freq2key.get(freq + 1).add(key);
        }

        public void removeMinFreqKey() {
            LinkedHashSet<Integer> list = freq2key.get(minFreq);
            // 拿出这个list里面最前的那个
            // 这两句话都可以
            int d = list.getFirst();
            //int d = list.iterator().next();
            // 从freq2key里删掉
            list.remove(d);
            // 防止删完之后这个没了
            if (list.isEmpty()) {
                freq2key.remove(minFreq);
            }
            // 从key2val里删掉
            key2val.remove(d);
            // 从key2freq删掉
            key2freq.remove(d);
        }
    }
}
