public class Q709 {
    public String toLowerCase(String s) {
       // return s.toLowerCase();
       StringBuilder sb = new StringBuilder();
       for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                sb.append((char)(c - 'A' + 'a'));
            } else {
                sb.append(c);
            }
       }
       return sb.toString();
    }
}
