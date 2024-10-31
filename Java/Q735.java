import java.util.ArrayDeque;
import java.util.Deque;

public class Q735 {
    /*
     * 因为我们要将对向进行消消乐，并且持续不断的消消乐
     * 
     * 比如 15  5     <- -10
     * 我们要先和5消消乐，再和15消消乐
     * 
     * 所以我们可以用stack存目前还存在的行星
     * 
     * 如果current是正的，直接放入stack
     * 如果current是负的，和stack顶端的元素比较
     * -> current will explode -> 不操作
     * -> current 更大，放入stack
     * 
     * 一些细节，可以在写的时候提出来：
     * 要判断stack顶端的元素是不是正的，如果是负的，说明同向，不用爆炸
     * 
     * 要有一个boolean去判断当前这个current还在不在
     */
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int cur : asteroids) {
            if (cur > 0) {
                stack.push(cur);
                continue;
            }
            // cur < 0
            // check whetrher the cur asteroids exist
            boolean alive = true;
            while (alive && !stack.isEmpty() && stack.peek() > 0) {
                int curPeek = stack.peek();
                if (-cur > curPeek) {
                    stack.pop();
                } else if (-cur == curPeek) {
                    // 两个都死了
                    stack.pop();
                    alive = false;
                } else {
                    alive = false;
                }
            }
            if (alive) {
                stack.push(cur);
            }
        }
        int n = stack.size();
        int[] res = new int[n];
        while (!stack.isEmpty()) {
            res[--n] = stack.pop();
        }
        return res;
    }
}
