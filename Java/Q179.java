import java.util.*;

public class Q179 {
    /*
     * 首先不能直接拼接，比如6和45，我们应该把6放在前面
     * 我们可以根据拼接后的大小判断他应该放在前面还是后面
     * 
     * 比如[4,45]，因为454 > 445,所以应该把45放在前面
     * 比如[6,45]，因为645 > 456,所以应该把6放在前面
     * 
     * 可以把数字转化成string用compare to
     * 
     */
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = "" + nums[i];
        }
        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }
        // 要处理前面的0
        // 为什么是到index - 1？因为最多保留一位
        int index = 0;
        while (index < sb.length() - 1 && sb.charAt(index) == '0') {
            index++;
        }
        return sb.substring(index);
    }
}

