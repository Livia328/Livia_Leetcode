public class Q273 {
    /*
     * 3位3位的check
     * 
     * 一个额外的function getThreeDigitNum
     * 如果<20,直接找到对应的
     * 如果[20, 99] 
     *    -> 找到对应的几十(num / 10)，再append num & 10
     * 如果是[100,999]
     *    -> 先找到对应的几百（num / 100），再看num & 100
     * 
     * 所以从下往上，先num % 100, 再num % 10
     * 
     * 如果是大于billion，million，thousand，三位三位的看
     * 比如12,899
     * 先整除1000，12，用getThreeDigitNum(12), 12 thousand, 在看899
     * 
     * 比如18,129,899
     * 先整除million， 18，用getThreeDigitNum(12), 12 million
     * 在看129,899
     */
    private static final String[] nt = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
        "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] bmt = {"Billion","Million","Thousand"};
    private static final int BILLION = (int)1e9, MILLION = 1000000, THOUSAND = 1000;

    public String numberToWords(int num) {
        if(num == 0)
            return nt[num];
        StringBuilder sb = new StringBuilder();
        if(num >= BILLION){
            sb.append(num2str(num/BILLION));
            sb.append(bmt[0]);
            sb.append(" ");
            num %= BILLION;
        }
        if(num >= MILLION){
            sb.append(num2str(num/MILLION));
            sb.append(bmt[1]);
            sb.append(" ");
            num %= MILLION;
        }
        if(num >= THOUSAND){
            sb.append(num2str(num/THOUSAND));
            sb.append(bmt[2]);
            sb.append(" ");
            num %= THOUSAND;
        }
        sb.append(num2str(num));
        if(sb.charAt(sb.length()-1) == ' ')
            sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    /*
     * 将三位数得到对应的表达
     */
    private static String num2str(int num) {
        StringBuilder sb = new StringBuilder();
        if(num >= 100){
            sb.append(nt[num/100]);
            sb.append(" Hundred");
            sb.append(" ");
            num %= 100;
        }
        if(num >= 20){
            sb.append(tens[num/10]);
            sb.append(" ");
            num %= 10;
        }
        if(num > 0){
            sb.append(nt[num]);
            sb.append(" ");
        }
        return sb.toString();
    }
}
