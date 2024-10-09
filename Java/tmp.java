import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class tmp {
    /*
     * 因为涉及到上一级的目录，所以stack
     * 将path根据’/‘split
     * 
     * 如果是正常的目录，就会被放进stack里
     * 如果是’..‘，如果stack里有东西，就把东西从stack里pop出来
     * 
     * 如果是空的，或者是’。‘，那么就continue
     * 空的，'///'这样按照'/'split就会是空的
     * '.'说明是当前的目录，也不用加
     * 
     * 最后再把它们组装起来
     */
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] parts = path.split("/");
        for (String part : parts) {
            if (part.isEmpty() || part.equals(".")) {
                continue;
            }
            if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                continue;
            }
            stack.push(part);
        }
        // 这里得用res，因为是把string append到res前面
        String res = new String();
        while (!stack.isEmpty()) {
            res = "/" + stack.pop() + res;
        }
        return res.isEmpty() ? "/" : res;
    }
}
