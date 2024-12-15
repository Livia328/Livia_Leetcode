public class Q48 {
    /*
     * 每一层一层地rotate
     * 像洋葱，一开始处理最外面一层，然后再处理里面一层
     * 
     * l   r
     * 1 2 3 top
     * 4 5 6
     * 7 8 9 bottom
     * 
     * 对于每一层，要转换的元素就是[0, r - l]
     * 
     * 这一层要遍历的元素
     * for(int i = 0; i < r - l; i++)
     * 
     * i = 0
     * 就是把7弄到1的位置，9弄到7的位置，3弄到9的位置，1弄到3的位置
     * 
     * i = 1
     * 把4弄到2的位置，8弄到4的位置，6弄到8的位置，2弄到9的位置
     * 
     * 先记录一下
     * topLeft = matrix[top, l + i] = 1
     * 然后把bottom left（9）弄到top left的位置
     * 7 -> 1
     * 
     * l   r
     * [7] 2 3 top
     * 4 5 6
     * 7 8 9 bottom
     * 
     * 然后把bottom right的数字弄到bottom left
     * l   r
     * 7 2 3 top
     * 4 5 6
     * [9] 8 9 bottom
     * 
     * 以此类推
     */
    public void rotate(int[][] matrix) {
        int l = 0, r = matrix.length - 1;
        while (l < r) {
            for (int i = 0; i < r - l; i++) {
                int top = l, bottom = r;
                //save the topleft, [1]
                int topLeft = matrix[top][l + i];
                //move bottom left into top left
                // 7弄到1的位置
                matrix[top][l + i] = matrix[bottom - i][l];
                // move bottom right into bottom left
                // 9弄到7的位置
                matrix[bottom - i][l] = matrix[bottom][r - i];
                // move top right into bottom right
                // 3弄到9的位置
                matrix[bottom][r - i] = matrix[top + i][r];
                // move top left into top right
                // 1弄到3的位置
                matrix[top + i][r] = topLeft;
            }
            r--;
            l++;
        }
    }
}
