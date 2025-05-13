import java.util.*;

public class Q1169 {
    /*
     * 根据','split
     * 因为信息太多了，感觉可以用个class
     * 
     */
    private class Trade {
        private String s;
        private String name;
        private int time;
        private int money;
        private String city;
        private boolean valid;

        public Trade(String s, String name, int time, int money, String city) {
            this.s = s;
            this.name = name;
            this.time = time;
            this.money = money;
            this.city = city;
            this.valid = this.money > 1000 ? false : true;
        }

        @Override
        public String toString() {
            return s;
        }

        // 判断两个trade是不是invalid的
        private boolean invalid(Trade another) {
            return this.name.equals(another.name) && !this.city.equals(another.city)
                    && Math.abs(this.time - another.time) <= 60;
        }
    }

    public List<String> invalidTransactions1(String[] transactions) {
        List<Trade> trades = new ArrayList<>();
        // 把所有string变成trade
        for (String s : transactions) {
            String[] arr = s.split(",");
            Trade cur = new Trade(s, arr[0], 
                    Integer.parseInt(arr[1]), 
                    Integer.parseInt(arr[2]), 
                    arr[3]);
            trades.add(cur);
        }
        // 双重for loop，判断两两之间是不是invalid
        for (int i = 0; i < trades.size(); i++) {
            Trade tradeI = trades.get(i);
            // 如果当前trade invalid
            // 直接下一个
            if (!tradeI.valid) {
                continue;
            }
            for (int j = 0; j < trades.size(); j++) {
                if (i == j) {
                    continue;
                }
                Trade tradeJ = trades.get(j);
                if (tradeI.invalid(tradeJ)) {
                    tradeI.valid = false;
                    tradeJ.valid = false;
                }
            }
        }
        List<String> res = new ArrayList<>();
        for (Trade t : trades) {
            if (!t.valid) {
                res.add(t.s);
            }
        }
        return res;
    }

    public List<String> invalidTransactions(String[] transactions) {
        // 用来去重
        HashSet<Integer> invalid = new HashSet<>();
        for(int i = 0; i < transactions.length; i++) {
            if (invalid.contains(i)) {
                continue;
            }
            String[] str1 = transactions[i].split(",");
            String name1 = str1[0];
            int time1 = Integer.parseInt(str1[1]);
            int money1 = Integer.parseInt(str1[2]);
            String city1 = str1[3];
            if (money1 > 1000) {
                invalid.add(i);
                continue;
            }
            for(int j = i + 1; j < transactions.length; j++) {
                if (invalid.contains(j)) {
                    continue;
                }
                String[] str2 = transactions[j].split(",");
                String name2 = str2[0];
                int time2 = Integer.parseInt(str2[1]);
                int money2 = Integer.parseInt(str2[2]);
                String city2 = str2[3];
                if (money2 > 1000) {
                    invalid.add(j);
                    continue;
                }
                if (name1.equals(name2) && Math.abs(time1 - time2) <= 60 && (!city1.equals(city2))) {
                    invalid.add(i);
                    invalid.add(j);
                }
            }
        }
        List<String> res = new ArrayList<>();
        for (int index : invalid) {
            res.add(transactions[index]);
        }
        return res;
    }
}
