public class Q240 {
    /*
     * 这道题是从左到右递增，从上到下递增
     * 所以最小值在左上角，最大值在右下角
     * 
     * 如果我们从左上角开始，无论向右还是向下走，元素大小都会增加
     * 所以我们很难判断
     * 
     * 所以我们可以从右上角开始
     * 如果需要减小，就向左移动
     * 如果需要增大，就向右移动
     * 
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] < target) {
                // 需要大一点，向下移动
                i++;
            } else {
                // 需要小一点，向左移动
                j--;
            }
        }
        return false;
    }
}
