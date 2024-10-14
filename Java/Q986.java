import java.util.*;
public class Q986 {
    /*
     * 模拟：
     * 用两个指针分别指向每个list当前遍历的元素
     * 
     * A:[0,2], [5,10], [13,23],[24,25]
     *    a
     * B:[1,5], [8,12], [15,24],[24,24]
     *    b
     * 
     * 有交集的情况是以下四种情况
     * a1        a2
     * [_________]
     *   [___]  [____]
     *   b1  b2      -> 移动b2，因为长的那个还有可能和下一个相交
     * 
     * a1      a2
     * [_______] [___]
     *    [_______]    -> 移动a2,
     *    b1      b2
     * 
     *  a1   a2
     *   [___]
     * [________]
     * b1      b2
     * 
     *     a1       a2
     *     [________]
     * [________]
     * b1       b2
     * 
     * 
     * -> 交叉的条件： b1 <= a2 && b2 >= a1
     * 
     * 算出重合面积：对max(a1, b1), min(a2, b2)
     * 
     * 移动的时候移动结束早的那个
     * 
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int i = 0, j = 0;
        List<int[]> list = new LinkedList<>();
        while (i < firstList.length && j < secondList.length) {
            int a1 = firstList[i][0], a2 = firstList[i][1];
            int b1 = secondList[j][0], b2 = secondList[j][1];
            if (b1 <= a2 && b2 >= a1) {
                list.add(new int[]{Math.max(a1, b1), Math.min(a2, b2)});
            }
            if (b2 < a2) {
                j++;
            } else {
                i++;
            }
        }
        int[][] res = new int[list.size()][2];
        for (int k = 0; k < list.size(); k++) {
            res[k] = list.get(k);
        }
        return res;
    }
}
