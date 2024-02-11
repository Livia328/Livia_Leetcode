import java.util.regex.Pattern;
public class Q468 {
    public String validIPAddress(String queryIP) {
        // 如果string里有'.' 那么就是ipv4
        if (queryIP.indexOf('.') >= 0) {
            return isIpV4(queryIP) ? "IPv4" : "Neither";
        } else {
            return isIpV6(queryIP) ? "IPv6" : "Neither";
        }
    }

    public boolean isIpV4(String queryIP) {
        // split the queryIP according to '.'
        // 重要： 后面要加-1， 比如192.168.1.1. 后边多了一个点，不加-1会被忽略后边的空串
        String[] ip = queryIP.split("\\.",-1);
        // 如果长度不是4，返回
        if (ip.length != 4) {
            return false;
        }
        for (String cur : ip) {
            if (cur.length() > 3 || cur.length() == 0) {
                return false;
            }
            // 如果有前面的0，并且长度不为1，就是012的类型，那么不行
            if (cur.charAt(0) == '0' && cur.length() != 1) {
                return false;
            }
            int ans = 0;
            for (int i = 0; i < cur.length(); i++) {
                char c = cur.charAt(i);
                // 如果不是数字
                if (!Character.isDigit(c)) {
                    return false;
                }
                ans = ans * 10 + (c - '0');
            }
            if (ans > 255) {
                return false;
            }
        }
        return true;
    }

    public boolean isIpV6(String queryIP) {
        String[] ip = queryIP.split(":",-1);
        if (ip.length != 8) {
            return false;
        }
        for (String cur : ip) {
            if (cur.length() > 4 || cur.length() == 0) {
                return false;
            }
            for (int i = 0; i < cur.length(); i++) {
                char c = cur.charAt(i);
                if (!Character.isDigit(c) && !(Character.toLowerCase(c) >= 'a') || !(Character.toLowerCase(c) <= 'f')) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * regex
     * 
     * import java.util.regex.Pattern;
     * 
     * Decimal Number: A number between 0 and 255 can be divided into ranges:
     * 250-255: 25[0-5]
     * 200-249: 2[0-4][0-9]
     * 100-199: 1[0-9][0-9]
     * 10-99: [1-9][0-9]
     * 0-9: [0-9]
     * 
     * repeat the pattern 3 times
     * \\.){3}
     * 
     * ipv6
     * Hexadecimal Digit: Matches using [0-9a-fA-F].
     * repeat7 times
     */
    
    public String validIPAddres2(String queryIP) {
        String IPV4_PATTERN = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        String IPV6_PATTERN = "^((([0-9a-fA-F]{1,4}):){7}([0-9a-fA-F]{1,4}))$";
        if (Pattern.matches(IPV4_PATTERN, queryIP)) {
            return "IPv4";
        } else if (Pattern.matches(IPV6_PATTERN, queryIP)) {
            return "IPv6";
        } else {
            return "Neither";
        }
    }
}
