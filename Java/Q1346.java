public class Q1346 {
    /*
     * hashset
     * 遍历每个数字的时候检查
     * 这个数字的一半或者两倍在不在set里
     */
    public boolean checkIfExist(int[] arr) {
        //哈希表
        Set<Integer> set = new HashSet<>();
        for (int i : arr) {
            // 检查当前元素的两倍或一半是否在Set中
            if (set.contains(i * 2) || (i % 2 == 0 && set.contains(i / 2))) {
                return true;
            }
            // 将当前元素添加到Set中
            set.add(i);
        }
        return false;
    }
}
