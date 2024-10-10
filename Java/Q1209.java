import java.util.Stack;

public class Q1209 {
    /*
     * stack
     * 
     * 因为是消消乐，所以想到stack
     * 将字母和出现次数一起加入stack
     * 
     * 每次检查cur和stack.peek
     * 如果一样，stack.peek.count++
     * 如果不一样，push {cur, 1}
     */
    class Pair {
        char c;
        int count;

        public Pair(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }
    public String removeDuplicates1(String s, int k) {
        Stack<Pair> stack = new Stack<>();
        // 注意这里是从前往后的
        // 也就意味着最后pop出来的时候是反着的
        // 之后要记得reverse，或者说这里也可以从后往前遍历
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek().c == c) {
                stack.peek().count++;
                // 如果等于k了，那么pop出来，消除掉了
                if (stack.peek().count == k) {
                    stack.pop();
                }
            } else {
                stack.push(new Pair(c, 1));
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            Pair p = stack.pop();
            for (int i = 0; i < p.count; i++) {
                sb.append(p.c);
            }
        }
        return sb.reverse().toString();
    }

    /*
     * 2 pointers
     * 快慢指针
     * 
     * 快的表示正在遍历的
     * 慢的表示应该被保留的部分
     * 
     * 当需要删除k个元素的时候，让slow回退k个
     * -> 用一个boolean来表示有没有找到k个元素
     * 
     * s = "deeedbbcccbdaa", k = 3
     *      s
     *          f
     * 
     */
    public static String removeDuplicates(String s, int k) {
        // 用来保存
        StringBuilder sb = new StringBuilder(s);
        int curCount = 1;
        int slow = 0, fast = 1;
        while (fast < sb.length()) {
            if (sb.charAt(fast) == sb.charAt(slow)) {
                curCount++;
                if (curCount == k) {
                    sb.delete(slow, slow + k);
                    // 如果全都减完了，重制slow回到第一位
                    // 可以写完slow = slow - k + 1
                    // 再反应过来此处需要判断是否大于0，不然index会越界
                    if (slow - k + 1 <= 0) {
                        slow = 0;
                    } else {
                        slow = slow - k + 1;
                    }
                    // 这里一定要重制fast，因为sb的长度一直在减少
                    fast = slow;
                    // curCount重制
                    curCount = 1;
                }
                fast++;
            } else {
                // 是一个新的字母，重制
                curCount = 1;
                slow = fast;
                fast++;
            }
        }
        // 判断最后一个是否需要删除
        if (sb.length() - 1 - slow == k - 1) {
            sb.delete(slow, slow + k + 1);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        removeDuplicates("pbbcggttciiippooaais", 2);
    }
}
