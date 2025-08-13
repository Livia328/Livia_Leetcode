import java.util.HashMap;
import java.util.Stack;

public class Q496 {
    /*
     * Monotonic Stack 单调栈
     * 想像成排队，2 1 2 4 3
     * 站在2往后看，第一个比他高的，就是下一个最大数
     * 
     * 所以要倒着往栈里放（因为这样就可以正着出栈了）
     * 每次判断栈里元素和当前元素，比当前元素小就可以弹出去
     * 反正也会被挡住
     * 
     * 这题因为需要map到num2，所以多一个map环节
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 找到下一个最大元素的数组
        int[] greater = nextGreaterElement(nums2);
        // 转变为hashmap让nums1中的元素前去查表
        // key: nums2[i], value: 这个元素的下一个最大元素
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], greater[i]);
        }
        int[] res = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    // 返回记录每一个最大元素的数组
    public int[] nextGreaterElement(int[] nums2) {
        int[] res = new int[nums2.length];
        Stack<Integer> stack = new Stack<>();
        // 从后往前放
        for (int i = nums2.length - 1; i >= 0; i--) {
            // 判定个子高矮，矮的可以直接弹走，因为反正很矮
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.add(nums2[i]);
        }
        return res;
    }
}
