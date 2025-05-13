public class Q825 {
    /*
     * 枚举所有年龄的人数
     * 然后枚举所以所有年龄对（x, y)
     * 如果满足条件，那么他们就可以互相发送好友了
     * 
     * 如果x == y,那么发送好友请求数为
     * count[x] * (count[x] - 1)
     * 
     * 否则为
     * count[x] * count[y]
     */
    public int numFriendRequests(int[] ages) {
        // 最大年龄数，面试的时候要问面试官最大年龄的范围
        int m = 121;
        int[] count = new int[m];
        for (int x : ages) {
            count[x]++;
        }
        int res = 0;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < m; j++) {
                if (!(j <= 0.5 * i + 7 || j > i || (j > 100 && i < 100))) {
                    if (i == j) {
                        res += count[i] * (count[i] - 1);
                    } else {
                        res += count[i] * count[j];
                    }
                } 
            }
        }
        return res;
    }
}
