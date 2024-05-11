public class Q557 {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            while (i < s.length() && s.charAt(i) == ' ') {
                i++;
                sb.append(' ');
            }
            int start = i;
            while (i < s.length() && s.charAt(i) != ' ') {
                i++;
            }
            for (int j = start; j < i; j++) {
                sb.append(s.charAt(start + i - 1 - j));
            }
        }
        return sb.toString();
    }
}
