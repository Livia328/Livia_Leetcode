import java.util.ArrayList;
import java.util.List;

public class Q728 {
    /*
     * 注意对这位数是0的时候的特殊处理
     * 要和面试官确认
     */
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isSelfDevisingNum(i)) {
                res.add(i);
            }
        }
        return res;
    }

    public boolean isSelfDevisingNum(int num) {
        List<Integer> devisors = new ArrayList<>();
        int copy = num;
        while (copy != 0) {
            devisors.add(copy % 10);
            copy /= 10;
        }
        for (int devisor : devisors) {
            if (devisor == 0) {
                return false;
            }
            if (num % devisor != 0) {
                return false;
            }
        }
        return true;
    }
}
