public class Q2109 {
    /*
     * 遍历spaces
     * 每次append s[start, space]
     * 加入空格
     * 更新start为space
     */
    public static String addSpaces(String s, int[] spaces) {
        StringBuilder stringBuilder  = new StringBuilder();
        int start = 0;
        for (int space : spaces){
            stringBuilder.append(s.substring(start,space));
            //新增spaces.size()个空格！
            stringBuilder.append(" ");
            start = space;
        }
        //最后这一步不能忘记了！还要新增最后一个空格后的字符串！
        stringBuilder.append(s.substring(start));
        return stringBuilder.toString();
    }
}
