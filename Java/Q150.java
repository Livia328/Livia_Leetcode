import java.util.Stack;

public class Q150 {
    /**
     * 
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            if (isNumber(s)) {
                stack.push(Integer.parseInt(s));
            } else {
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
