import java.util.*;

public class test {
    public static void biggestSum(int[] nums) {
        int index1 = 0; int index2 = 0;
        int curSum = 0;
        int maxSum = 0;
        for (int i = 0; i < nums.length; i++) {
            curSum = 0;
            for (int j = i; j < nums.length; j++) {
                curSum += nums[j];
                if (curSum > maxSum) {
                    maxSum = curSum;
                    index1 = i; index2 = j;
                }
            }
        }
        System.out.println("index1" + index1);
        System.out.println("index2" + index2);
        System.out.println(maxSum);
    }

    public static void main(String[] args) {
        int[] arr = {-1, 1, 3, -5, 7, 9, -11, 13, 15, -17, 19, 21, -23};
        biggestSum(arr);
    }
}