public class Q824 {
    public String toGoatLatin(String sentence) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            char first = Character.toLowerCase(word.charAt(0));
            StringBuffer sb = new StringBuffer();
            if (first == 'a' || first == 'e' || first == 'i' || first == 'o' || first == 'u') {
                sb.append(word).append("ma");
            } else {
                sb.append(word.substring(1)).append(word.charAt(0)).append("ma");
            }
            for (int j = 0; j <= i; j++) {
                sb.append("a");
            }
            words[i] = sb.toString();
        }
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < words.length; i++) {
            res.append(words[i]).append(' ');
        }
        return res.toString().substring(0, res.length() - 1);
    }
}
