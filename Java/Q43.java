public class Q43 {
    /**
问题：
1.input是两个string， 返回值是一个string？
2.给的值可以确保是数字吗，会有其他的符号吗
3.会是负数或者0吗
4.num1, num2的范围？
5.前面会有0吗，比如说000982这样？

123 * 456

imitate整个乘法的过程

最多的位数是6位，m + n位数，所以用array来存
res的长度是m + n位数

res: 0 1 2 3 4 5
         0 7 3 8
 



   j = 1
 1 2 3
     i = 2
 4 5 6
 - - -
     
-> i = 2, j = 2, 6 * 3 = 18  res[4] = 1, res[5] = 8
-> i = 2, j = 1, 6 * 2 = 12  res[3] = 1, res[4] = 2 + 1 = 3 
-> i = 2, j = 0, 6 * 1 = 6.  res[2] = 0, res[3] = 6 + 1 = 7

-> i = 1, j = 2, 5 * 3 = 15 res[3] = 1, res[4] = 5
-> i = 1, j = 1, 5 * 2 = 10 res[2] = 1, res[3] = 0
-> i = 1, j = 0, 5 * 1 = 5. res[1] = 0, res[2] = 5

-> i = 0, j = 2, 4 * 3 = 12
-> i = 0, j = 1, 4 * 2 = 8
-> i = 0, j = 0, 4 * 1 = 4

-> res[i + j] = mul / 10 res[i + j + 1] = mul % 10;
 */
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length(); // num1 = 456, num2 = 123
        // 结果最多为 m + n 位数
        int[] res = new int[m + n];
        // 从个位数开始逐位相乘
        for (int i = m - 1; i >= 0; i--) { // 控制下面这个数 456, fix 每一个digit和上面的乘
            for (int j = n - 1; j >= 0; j--) { // 控制上面这个数123
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                // 乘积在 res 对应的索引位置
                int p1 = i + j, p2 = i + j + 1;
                // 叠加到 res 上
                int sum = mul + res[p2];
                res[p2] = sum % 10;
                res[p1] += sum / 10;
            }
        }
        // 结果前缀可能存的 0（未使用的位）
        int i = 0;
        while (i < res.length && res[i] == 0)
            i++;
        // 将计算结果转化成字符串
        StringBuilder sb = new StringBuilder();
        for (; i < res.length; i++)
            sb.append(res[i]);
        
        String str = sb.toString();
        return str.length() == 0 ? "0" : str;
    }
}
