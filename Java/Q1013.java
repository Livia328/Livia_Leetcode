public class Q1013 {
    public boolean canThreePartsEqualSum(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        if (sum % 3 != 0) {
            return false;
        }
        int target = sum / 3;
        int curSum = 0;
        int found = 0;
        for (int num : arr) {
            curSum += num;
            if (curSum == target) {
                curSum = 0;
                found++;
            }
        }
        return found == 3;
    }
}
