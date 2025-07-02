import java.util.ArrayList;

public class Q1352 {
    /*
     * 就和前缀和一样
     * 我们可以记录prefix product
     * preProduct[i] / product[j]就是[i,j]的product
     * 
     * 但是需要特殊处理0
     * 如果出现了一个0
     * 就可以把preProduct给清空
     * 因为前面的元素积都作废了
     * 
     * 这样在get prod的时候就要check元素个数是不是k
     * 如果是的话，说明last k个元素里有0
     * 
     *  0  1  2  3
     * [2, 3, 4, 5]
     *           0  1  2  3   4
     * preProd: [1, 2, 6, 24, 120]
     * 
     * getProd[2] = 4 * 5 = 120 / 6 = 20
     * 
     */
    class ProductOfNumbers {

        ArrayList<Integer> preProduct = new ArrayList<>();

        public ProductOfNumbers() {
            preProduct.add(1);
        }
        
        public void add(int num) {
            if (num == 0) {
                preProduct.clear();
                preProduct.add(1);
                return;
            }
            int n = preProduct.size();
            preProduct.add(preProduct.get(n - 1) * num);
        }
        
        public int getProduct(int k) {
            int n = preProduct.size();
            if (k > n - 1) { // 不足k个元素，说明last k个元素里有0
                return 0;
            }
            return preProduct.get(n - 1) / preProduct.get(n - 1 - k);
        }
    }
}
