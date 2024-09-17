import java.util.*;

public class Q989 {
    public static List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> res = new ArrayList<>();
        int i = num.length - 1;
        int carry = 0;
        while (i >= 0 || k != 0) {
            int cur_num = i < 0 ? 0 : num[i];
            int cur_k = k % 10;
            int sum = cur_k + cur_num + carry;
            res.add(sum % 10);
            carry = sum / 10;
            i--;
            k = k / 10;
        }
        if (carry != 0) {
            res.add(carry);
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        List<Integer> res = addToArrayForm(new int[]{2,7,4,}, 181);
    }
}
