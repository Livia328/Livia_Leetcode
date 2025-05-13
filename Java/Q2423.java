import java.util.*;

public class Q2423 {
    /*
     * word 只由同一个字母组成
     * word 中除了某个字母出现 1 次以外，其他所有字母均出现 k 次(包括abc, abbcc)
     * word 中除了某个字母出现 k + 1 次以外，其他所有字母均出现 k 次
     * 
     * 翻译过来就是
     * 用map记录frequency
     * 
     */
    public boolean equalFrequency(String word) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : word.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // 所有出现的次数
        List<Integer> list = new ArrayList<>(map.values());
        Collections.sort(list);
        int size = list.size();
        if (size == 1) {
            return true;
        }
        if (list.get(0) == 1 && isSame(list.subList(1, size))) {
            return true;
        }
        if (list.get(size - 1) == list.get(size - 2) + 1
          && (isSame(list.subList(0, size - 1)))) {
            return true;
        }
        return false;
    }

    /*
     * 判断一个list里的数是不是全都是一样的
     */
    private boolean isSame(List<Integer> cnt) {
        int c0 = cnt.get(0);
        for (int c : cnt)
            if (c != c0)
                return false;
        return true;
    }
}
