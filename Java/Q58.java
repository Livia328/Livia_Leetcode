public class Q58 {
    public int lengthOfLastWord(String s) {
        // "   fly me   to   the moon  "
        //                           end
        int end = s.length() - 1;
        while (s.charAt(end) == ' ') {
            end--;
        }
        int start = end - 1;
        while (start >= 0 && s.charAt(start) != ' ') {
            start--;
        }
        return end - start;
    }
}
