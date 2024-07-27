import java.util.Arrays;

public class Q917 {
    /*
     * 2 pointer
     * db-ca
     *    i
     *  j
     * 
     * 
     */
    public static String reverseOnlyLetters(String s) {
        char[] arr = s.toCharArray();
        int i = 0, j = arr.length - 1;
        while (i < j) {
            while (i < j && !Character.isLetter(arr[i])) {
                i++;
            }
            while (i < j && !Character.isLetter(arr[j])) {
                j--;
            }
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;j--;
        }
        return String.valueOf(arr);
    }

    public static void main(String[] args) {
        String s  = reverseOnlyLetters("ab-cd");
    }
}
