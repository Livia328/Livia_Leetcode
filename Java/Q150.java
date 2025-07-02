import java.util.Stack;

public class Q150 {
    /**
     * 因为polish notation符号是写在后面的
     * 所以想到可以用stack
     * 
     * 如果是数字，就放进stack里
     * 如果是符号，那么从stack里pop出来
     * 然后把结果push进去
     * 
     * 比如 (1 + 2) * 3
     * 1 2 + 3 *
     * stack: 1 2
     * stack: 3 (1 + 2的和)
     * stack: 3 3 
     * 碰到乘法，把
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            if (isNumber(s)) {
                stack.push(Integer.parseInt(s));
            } else {
                // 可以写到除法的时候回来想
                // 这里的先后顺序matters
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch(s) {
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                        break;
                    default:

                }
            }
        }
        return stack.pop();
    }

    public boolean isNumber(String s) {
        if (!(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))) {
            return true;
        }
        return false;
    }
}
