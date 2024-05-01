import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

public class Q506 {
    /*
     * 要记录score和index的关系
     * num, index
     * 
     * 然后将score排序，找到这个数对应的index，然后在res array的相应部分填上
     */
    public String[] findRelativeRanks(int[] score) {
        Map<Integer, Integer> numToIndex = new HashMap<>();
        for (int i = 0; i < score.length; i++) {
            numToIndex.put(score[i], i);
        }
        Arrays.sort(score);
        String[] res = new String[score.length];
        for(int i = 0; i < score.length; i++) {
            int index = numToIndex.get(score[i]);
            if (i == score.length - 1) {
                res[index] = "Gold Medal";
            } else if (i == score.length - 2) {
                res[index] = "Silver Medal";
            } else if (i == score.length - 3) {
                res[index] = "Bronze Medal";
            } else {
                res[index] = String.valueOf(score.length - i);
            }
        }
        return res;
    }
}
