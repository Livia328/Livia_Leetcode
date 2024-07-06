public class Q661 {
    public int[][] imageSmoother1(int[][] img) {
        int[][] res = new int[img.length][img[0].length];
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[0].length; j++) {
                int num = 0, sum = 0;
                for (int x = i - 1; x <= i + 1; x++) {
                    for (int y = j - 1; y <= j + 1; y++) {
                        if (x >= 0 && x < img.length && y >= 0 && y < img[0].length) {
                            sum += img[x][y];
                            num += 1;
                        }
                    }
                }
                res[i][j] = sum / num;
            }
        }
        return res;
    }

    /*
     * prefix sum
     * 因为每次都要便利9个格子
     * 对于res[i][j]，我们可以计算其左上角[a, b] [i - 1, j - 1]和右下角[c, d] [i + 1, j + 1]
     * 因为有可能超出边界，所以我们要取max和Min
     * 
     * 
     * 
     */
    public int[][] imageSmoother(int[][] img) {
        int m = img.length, n = img[0].length;
        int[][] res = new int[m][n];
        int[][] sum = new int[m + 10][n + 10];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + img[i - 1][j - 1];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int a = Math.max(0, i - 1), b = Math.max(0, j - 1);
                int c = Math.min(m - 1, i + 1), d = Math.min(n - 1, j + 1);
                int count = (c - a + 1) * (d - b + 1);
                int total = sum[c + 1][d + 1] - sum[a][d + 1] - sum[c + 1][b] + sum[a][b];
                res[i][j] = total / count;
            }
        }
        return res;
    }
}
 