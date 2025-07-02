import java.util.ArrayList;

class Q792 {
    /*
     * 简单想法是在Q392的基础上改变
     * * s.length > t.length return false
     * * 双指针
     * 
     * s: abc
     *    i
     * 
     * t: ahbgdc
     *    j
     * 
     * 
     * 碰到一样的就是i和j都++
     * 否则只有j++
     * 
     * 最后看i是否走完
     * 
     * 在这个外面套用for loop
     * 这样的时间复杂度是O(n^2)
     */
    public int numMatchingSubseq1(String s, String[] words) {
        int res = 0;
        for (String word : words) {
            if (isSubsequence(word, s)) {
                res++;
            }
        }
        return res;
    }

    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        if (n > m) {
            return false;
        }
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }

    /*
     * 但是这样，每次一个新的string都要从头开始
     * 会TLE
     * 
     * 我们可以把 s 中的字符预处理一下，把每个字符出现的的索引列表算出来：
     * 比如
     * ababcdcdc
     * 
     * a:0,2
     * b:1,3
     * c:4,6,8
     * 
     * 然后假设我们要匹配abc
     *   i
     * abc
     *   j 
     * ababc
     * 
     * 我们可以通过二分去找index[c]中比j大的索引
     * 也就是在4,6,8中寻找
     * 
     * 这样就转换成了寻找左边界，可以找到大于等于j的index
     */
    public int numMatchingSubseq(String s, String[] words) {
        // 进行string s预处理
        // 因为都是英文小写字母，所以用一个list array
        ArrayList<Integer>[] charToIndex = new ArrayList[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charToIndex[c - 'a'] == null) {
                charToIndex[c - 'a'] = new ArrayList<>();
            }
            charToIndex[c - 'a'].add(i);
        }
        int res = 0;
        for (String word : words) {
            // word上的指针i
            int i = 0;
            // s上的指针
            int j = 0;
            // 判断word是否是s的子序列
            // 借助charToIndex找到word中每个字符在s中的index
            while (i < word.length()) {
                char c = word.charAt(i);
                // 整个s压根没有c
                if (charToIndex[c - 'a'] == null) {
                    break;
                }
                // 二分搜索大于等于j的最小index
                // 即在s[j...]中搜索等于c的最小index
                int pos = left_bound(charToIndex[c - 'a'], j);
                // 说明也没有搜到
                if (pos == charToIndex[c - 'a'].size()) {
                    break;
                }
                j = charToIndex[c - 'a'].get(pos);
                // 找到了j，继续往后匹配
                j++;
                i++;
            }
            // 如果word匹配完成，那么是s的子序列
            if (i == word.length()) {
                res++;
            }
        }
        return res;
    }

    public int left_bound(ArrayList<Integer> arr, int target) {
        // 左闭右开
        // 左右都闭不好写，因为那样的话最后就要check l的位置到底有没有找到
        int l = 0, r = arr.size();
        // 跳出条件l == r
        while (l < r) {
            int m = l + (r - l) / 2;
            if (target > arr.get(m)) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }
}