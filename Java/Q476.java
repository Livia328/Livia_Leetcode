public class Q476 {
    /**
     * 思路很简单
        输入为 5 是 101
        输出为 2 是 010
        上下两个加起来就是 111 即2的3次方-1

        输入为 8 是 1000
        输出为 7 是 0111
        上下两个加起来就是 1111 即2的4次方-1

        同理
        就可以知道
        找到一个比num大的 2的n次幂的数 本题为a
        然后结果就是a - num - 1

     */
    public int findComplement(int num) {
        long a  = 1;
        while (true) {
            if (num >= a) {
                a <<= 1; //就是a*=2
            } else {
                return (int)a - num - 1;
            }
        }
    }
}
