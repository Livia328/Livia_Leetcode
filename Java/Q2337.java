public class Q2337 {
    /*
     * 双指针
     * 
     * 1.start和target去除所有_后应该是一样的
     * 
     * 2.因为L只能向左移，R只能向右移
     * 所以在start种每个位置左边的L个数必然<=target同等位置左边的L个数
     * 否则这些L不能向左移
     * 也就是说，当start.charAt(i) == 'L'时，i <= j
     * 
     * 同理start每个位置右边的R个数必然<=target串相同位置右边R的个数
     * 否则这些数不能右移
     * 也就是说，当start.charAt(i) == 'R'时，i >= j
     * 
     */
    public boolean canChange(String start, String target) {
        int m = start.length();
        int n = target.length();
        if(m != n)return false;     // 两个字符串不等长，肯定不匹配
        int i = 0;  // 遍历start的指针
        int j = 0;  // 遍历target的指针
        while(i < m || j < n){
            // 依次获取start和target的非'_'字符进行比较
            while(i < m && start.charAt(i) == '_') {
                i++;
            }
            while(j < n && target.charAt(j) == '_') {
                j++;
            }
            // 有一个指针到达边界了就退出
            if(i == m || j == n)break;
            // 两个指针的字符不匹配退出
            if(start.charAt(i) != target.charAt(j)) {
                return false;
            }
            // 目标字符串的L在原始字符串L的右侧，无法实现
            // 目标字符串的R在原始字符串R的左侧，无法实现
            if(start.charAt(i) == 'L' && j > i 
               || start.charAt(i) == 'R' && j < i) {
                return false;
            }
            i++;
            j++;
        }
        // 双指针都到达字符串边界说明匹配成功，否则有一个字符串有多余非'_'字符
        return i == m && j == n;
    }
}
