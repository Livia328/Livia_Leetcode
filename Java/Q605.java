public class Q605 {
    /*
     * 贪心，能种花的时候立刻种花
     * 
     * 即：前一个，当前，后一个都是零
     * 对于第一个和最后一个位置要特殊判断
     * ->也可以在前后补0
     * 
     */
    public boolean canPlaceFlowers1(int[] flowerbed, int n) {
        int[] copy = new int[flowerbed.length + 2];
        System.arraycopy(flowerbed, 0, copy, 1, flowerbed.length);
        for (int i = 1; i < copy.length - 1; i++) {
            if (copy[i - 1] == 0 && copy[i] == 0 && copy[i + 1] == 0) {
                copy[i] = 1; // 种花
                n--;
            }                
        }
        return n <= 0;
    }

    /*
     * 如果不想copy，就要对第一个和最后一个位置特殊判断
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            if ((i == 0 || flowerbed[i - 1] == 0) && flowerbed[i] == 0 && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                n--;
                i++; // 下一个肯定不种花，跳过
            }
        }
        return n <= 0;
    }
}
