public class Q3453 {
    /*
     * binary search
     * 
     * 因为切割的纵坐标越大，上面的面积越小
     * 满足单调性，所以可以用二分
     * 
     * 那么我们就要知道二分的范围：
     * 对于每个squares[i]，（x, y, l)
     * （x,y）是它的左下角的点
     * 
     * 那么这个正方形的最低的点就是y,最高的点是y + l
     * 所以我们可以不断更新所有正方形里最高的和最低的，
     * 就是二分搜索的左右
     * 
     * 然后我们要知道怎么移动？
     * 有了这个mid线后，我们可以算出上方是否大于下方
     * 
     * 如果上方面积大于下方，那么更新左边界（分割线要往上移）
     * 如果上方面积小于下饭，那么更新右边界（分割线要往下移）
     * 
     * -> 所以我们需要一个方法来判断上方面积都否大于下方
     * boolean check
     */
    public double separateSquares(int[][] squares) {
        double l = Double.MAX_VALUE;
        double r = -1;
        for (int[] square : squares) {
            int x = square[0], y = square[1], len = square[2];
            l = Math.min(l, y);
            r = Math.max(r, y + len);
        }
        // 因为是浮点，所以不能+1，要加double eps = 1e-6
        // 因为他说误差在10^-5之间
        double eps = 1e-6; 
        while (l <= r) {
            double m = l + (r - l) / 2;
            // check表示up面积是否大于bottom面积
            if (check(squares, m)) {
                l = m + eps;
            } else {
                r = m - eps;
            }
        }
        return l;
    }

    public boolean check(int[][] squares, double mid) {
        double up = 0;
        double down = 0;
        for (int[] square : squares) {
            int x = square[0], y = square[1], len = square[2];
            if (y > mid) {
                // 整个square在mid上
                up += (double)len * len;
            } else {
                if (y + len > mid) {// 正方形的最高点大于mid，一半在上面，一半在下面
                    up += (double) len * (y + len - mid);
                    down += (double) len * (mid - y);
                } else {
                    // 整个正方形就在下面
                    down += (double)len * len;
                }
            }
        }
        return up > down;
    }

}