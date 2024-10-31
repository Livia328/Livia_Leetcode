import java.util.ArrayList;
import java.util.List;

public class Q282 {
    /*
     * backtracing
     * 往里面加符号
     * 
     * 乘法的优先级高于+和-，怎么处理
     * 记录pre
     * 
     * 当前符号是+，当前部分的值为next，cur = cur + next, pre = nex
     * 当前符号是-，当前部分的值为next，cur = cur - next, pre = -next
     * 当前符号是*，当前部分的值为next，此时cur已经累加了prv的影响，所以先剪去pre
     *            cur = cur - prv + prv * next, next = pre * next
     */
    List<String> res = new ArrayList<>();
    public List<String> addOperators(String num, int target) {
        backtrack(0, 0, "", num, 0, target);
        return res;
    }

    public void backtrack(long pre, long cur, String s, String num, int index, int target) {
        if (index == num.length()) {
            if (cur == target) {
                res.add(s);
            }
            return;
        }
        // 当前数字（next），可以是s.substring(index, i + 1)
        for (int i = index; i < num.length(); i++) {
            // 不可以有leading 0s
            if (i != index && num.charAt(index) == '0') {
                break;
            }
            long next = Long.parseLong(num.substring(index, i + 1));
            // 如果是第一个
            if (index == 0) {
                backtrack(next, next, "" + next, num, i + 1, target);
            } else {
                backtrack(next, cur + next, s + "+" + next, num, i + 1, target);
                backtrack(-next, cur - next, s + "-" + next, num, i + 1, target);
                long tmp = pre * next;
                backtrack(tmp, cur - pre + tmp, s + "*" + next, num, i + 1, target);
            }
        }
    }
} 
