public class Q168 {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            // 因为excel是从1 index开始的
            columnNumber--;
            int cur = columnNumber % 26;
            columnNumber /= 26;
            sb.append((char)(cur + 'A'));
        }
        return sb.reverse().toString();
    }
}
