package Java;

public class Q242 {
    /**
     * Anagram means s and t have exactly same number of each letters appear
     * anagram nagaram
     * 
     * anagram: a3,n1, 1g, 1r, 1m
     * nagaram: 3a, 1n, 1g, 1r, 1m
     * 
     * input:
     * s and t only contain english letter
     * is lowcase or capital cases matters?
     * 
     * corner cases:
     * both string is empty, return true -> not do any extra operations
     * 
     * 0 1 2 ....25
     * 
     * 
     */
    public boolean isAnagram(String s, String t) {
        // if s and t do not have same length
        // they cannot be anagrams
        if (s.length() != t.length()) {
            return false;
        }
        // because s and t only contains lowercase english letters
        int[] seen = new int[26];
        for (int i = 0; i < s.length(); i++) {
            seen[s.charAt(i) - 'a']++;
            seen[t.charAt(i) - 'a']--;
        }
        // examine every entry in seen array
        // if it is not 0, return false
        for (int i = 0; i < 26; i++) {
            if (seen[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
