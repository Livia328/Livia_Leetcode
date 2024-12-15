package Java;

import java.util.*;

public class Q125 {
    /*
     * 确认input
     * 是否全是字母？有没有空格？大小写算一样的吗？
     */
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        // 去除所有非字母
        // 将全部都变成小写
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (Character.isLetterOrDigit(cur)) {
                sb.append(Character.toLowerCase(cur));
            }
        }
        // 左右双指针
        int left = 0, right = sb.length() - 1;
        while (left < right) {
            if (sb.charAt(left) != sb.charAt(right)) {
                return false;
            }
            left++; right--;
        }
        return true;
    }
}