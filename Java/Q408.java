public class Q408 {
    /*
     * 双指针模拟
     * clarify：确认word的长度，abbr的长度'
     * 
     * 模拟：如果是字母，判断是否和word[i]一样
     * 如果是数字，判断下一个是否是数字，
     * i + num > word.length() -> return false
     * i = i + num;
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0;
        for (int j = 0; j < abbr.length(); j++) {
            char c = abbr.charAt(j);
            // 判断是否是字母
            if (c >= 'a' && c <= 'z') {
                // 如果是字母，判断是否和word[j]一样
                if (i >= word.length() || word.charAt(i) != c) {
                    return false;
                }
                i++;
            } else {
                // 是数字
                int num = c - '0';
                // no leading 0
                if (num == 0) {
                    return false;
                }
                // 计算数字
                while (j < abbr.length() - 1 && abbr.charAt(j + 1) >= '0' && abbr.charAt(j + 1) <= '9') {
                    char nextC = abbr.charAt(j + 1);
                    num = num * 10 + (nextC - '0');
                    j++;
                }
                if (i + num > word.length()) {
                    return false;
                }
                i += num;
            }
        }
        return i == word.length();
    }
}
