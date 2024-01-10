package Java;

import java.util.*;

public class Q125 {
    public static boolean isPalindrome(String s) {
        // remove all the spaces and make it lower cases
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == ' ' || !Character.isLetterOrDigit(cur)) {
                continue;
            }
            sb.append(Character.toLowerCase(cur));

        }
        System.out.println(sb);
        int i = 0, j = sb.length() - 1;
        while (i <= j) {
            if (sb.charAt(i) != sb.charAt(j)) {
                return false;
            }
            i++; j--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "0P";
        System.out.println(isPalindrome(s));
    }
}