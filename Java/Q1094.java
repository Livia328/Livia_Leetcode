public class Q1094 {
    /*
     * 意思是每个数组里面表示
     * 有几个passager，从哪一站上来，到哪一站下去
     * 
     * 差分数组
     * diff[i] = nums[i] - nums[i - 1];
     * 
     * 我们也可以根据diff Array还原
     * 
     * 比如想对[i,j]区间的元素全都+3
     * 那么可以让diff[i] += 3, diff[j] -= 3
     * 
     * diff[i] += 3让所有i之后的元素都加了3
     * diff[j] -= 3让所以j之后的元素都减了3
     * 所以最后只有[i..j]加了3
     * 
     * 所以就类似于array里是每一个车站
     * 然后乘客上上下下
     * 只要最后所有车站的乘客人数都小于capacity即可
     * 
     * 所以要问车站一共有多少个，就是差分数组的长度
     */
    public boolean carPooling(int[][] trips, int capacity) {
        //构建差分方程，初始都为0
        //最多有1001个站所以长度是1001
        int[] diff = new int[1001];
        for(int[] trip : trips) {
            int val = trip[0];
            int start = trip[1];
            //注意乘客在trip[2]站已经下车，所以在车上的区间是[trip[1], trip[2] - 1]
            int end = trip[2] - 1;
            //进行区间增加
            diff[start] += val;
            if (end + 1 < diff.length) {
                diff[end + 1] -= val;
            }
        }
        //构造结果数组
        int[] res = new int[diff.length];
        res[0] = diff[0];
        for(int i = 1; i < diff.length; i++) {
            res[i] = res[i - 1] + diff[i];
        }
        //如果res数组中从始自终没有超载，就可以返回true
        for (int i = 0; i < res.length; i++) {
            if (res[i] > capacity) {
                return false;
            }
        }
        return true;
    }
}
