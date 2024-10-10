import java.util.List;
import java.util.LinkedList;

public class Q658 {
    /*
     * 因为arr是sorted的
     * 如果是找到离x最近的数，毫无疑问是binary search
     * 
     * 最近的k个数，我们可以先找到最近的数的index，然后从这个index开始朝左右扩展
     * 因为要求最后结果有序
     * 
     * 我们可以用linkedlist这样可以从两边向其中加入元素
     * res.addFirst
     * res.addLast
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int index = binarySearch(arr, x);
        LinkedList<Integer> res = new LinkedList<>();
        //(L, R)
        int L = index - 1, R = index;
        while (R - L - 1 < k) {
            // 如果左边已经到头的话，只能拓展右边界
            if (L == -1) {
                res.addLast(arr[R]);
                R++;
            } else if (R == arr.length) {
                // 如果右边已经到头了话，只能拓展左边界
                res.addFirst(arr[L]);
                L--;
            } else if (x - arr[L] <= arr[R] - x) {
                // 左边更近
                res.addFirst(arr[L]);
                L--;
            } else {
                // 右边
                res.addLast(arr[R]);
                R++;
            }
        }
        return res;
    }

    /*
     * 搜索左边界
     * 因为可能有重复的，所以我们要找到最左边的那个
     */
    public int binarySearch(int[] arr, int x) {
        // [L, R)
        int L = 0, R = arr.length;
        // 结束条件L = R
        while (L < R) {
            int M = L + (R - L) / 2;
            if (arr[M] == x) {
                R = M;
            } else if (arr[M] < x) {
                L = M + 1;
            } else if (arr[M] > x) {
                R = M;
            }
        } 
        return L;
    }
}
