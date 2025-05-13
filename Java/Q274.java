public class Q274 {
    /*
     * brute force
     * 有h篇引用数超过h的文章
     * 排序，从大到小遍历
     */
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0, i = citations.length - 1; 
        while (i >= 0 && citations[i] > h) {
            h++; 
            i--;
        }
        return h;
    }

    /*
     * 计数排序
     * 时间复杂度O(n)
     */
    public int hIndex2(int[] citations) {
        int n = citations.length, tot = 0;
        // 用数组表示当前引用次数的论文有几篇
        // 其实一般的count排序是需要得到citation的最大值
        // 但是根据h排序的定义，最大的 h 不超过 n
        // 所以我们可以设定citation的长度为h
        int[] counter = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (citations[i] >= n) {
                counter[n]++;
            } else {
                counter[citations[i]]++;
            }
        }
        // 从大到小，把出现次数加起来
        for (int i = n; i >= 0; i--) {
            tot += counter[i];
            if (tot >= i) {
                return i;
            }
        }
        return 0;
    }
}
