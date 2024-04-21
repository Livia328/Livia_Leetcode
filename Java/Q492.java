public class Q492 {
    /*
     * 可以从sqrt(area)开始寻找
     * 
     */
    public int[] constructRectangle(int area) {
        int w = (int)Math.sqrt(area);
        while (area % w != 0) {
            w--;
        }
        return new int[]{area / w, w};
    }
}
