public class Q74 {
    /**
     * cast 2d array to 1d index
     * [1, m * n - 1]
     * 
     * index / n, index % n
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0, right = m * n - 1;
        // [left, right]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cur = matrix[mid / n][mid % n];
            if (cur == target) {
                return true;
            } else if (cur < target) {
                // find on the right side, shrink pointer left
                // next search ares[mid + 1, right]
                left = mid + 1;
            } else if (cur > target) {
                // find on the left side, shrink pointer right
                // next search area [left, mid - 1]
                right = mid - 1;
            }
        }
        return false;
    }
}
