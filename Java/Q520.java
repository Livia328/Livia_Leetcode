public class Q520 {
    /*
     * 其实一个个判断很麻烦，可以逆转思路，先列出已有的答案，再和input匹配
     * 只有三种可能答案
     * word.toUpperCase
     * word.toLowerCase
     * word.charAr[0].toUppercase + 剩下的to lowercase
     * 
     * 如果word等于三者中的任何一个，那么就是对的
     */
    public boolean detectCapitalUse(String word) {
        String a = word.toUpperCase();
        String b = word.toLowerCase();
        String c = Character.toUpperCase(word.charAt(0)) + word.substring(1, word.length()).toLowerCase();
        if (word.equals(a) || word.equals(b) || word.equals(c)) {
            return true;
        }
        return false;
    }
}
