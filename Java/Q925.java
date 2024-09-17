public class Q925 {
    /*
     * 要数个数，比如leelee和lleeele的情况
     *  j
     */
    public static boolean isLongPressedName(String name, String typed) {
        int i = 0, j = 0;
        while(i < name.length() && j < typed.length()) {
            char val = name.charAt(i);
            int i_num = 0, j_num = 0;
            while (i < name.length() && name.charAt(i) == val) {
                i++;
                i_num++;
            }
            while (j < typed.length() && typed.charAt(j) == val) {
                j++;
                j_num++;
            }
            if (i_num > j_num) {
                return false;
            }
        }
        return i == name.length() && j == typed.length();
    }

    public static void main(String[] args) {
        boolean flag = isLongPressedName("leelee", "lleeelee");
    }
}
