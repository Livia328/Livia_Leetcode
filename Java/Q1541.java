public class Q1541 {
    /**
     * 思路还是遵循balance来表示需要的右括号数
     * 如果balance = -1
     * 意味着出现了右括号比左括号多的情况
     * ")"
     * 
     * 我们需要增加一个左括号
     * 我们用res来记录 res+1
     * 但是因为一个左括号需要两个右括号
     * balance = 1，表示此时还需要一个右括号
     * 
     * 当遇到左括号的时候
     * balance += 2
     * 
     * 因为一个左括号对应两个有括号
     * 所以此时balance应该为偶数
     * 
     * 如果balance为奇数
     * 那么可以decrease balance
     * 因为我们想要知道最少的step
     * 
     * 最后返回res+balance
     * 
     */
    public int minInsertions(String s) {
        int res = 0; int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            // 左括号，需要check balance是不是奇数
            if (s.charAt(i) == '(') {
                balance += 2;
                if (balance % 2 == 1) {
                    res++; // 增加一个有括号
                    balance--;
                }
            }
            if (s.charAt(i) == ')') {
                balance--;
                if (balance == -1) { //有太多右括号了
                    res++; // 增加左括号
                    balance = 1;                    
                }
            }
        }
        return res + balance;
    }
}