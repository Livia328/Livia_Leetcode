package Oracel;

import java.util.Arrays;

/**
 * https://ledarryl.medium.com/find-the-minimum-number-of-move-backs-required-to-sort-an-array-7bb2bb47ebf6
 * 
 * 
 * Find the minimum number of ‘move-to-back’ required to sort an array.
 * You are given an array of integers. 
 * Return the minimum number of ‘move-to-back’ required to sort the array in ascending order.
 * 
 * Sample Input: [1,4,2,5,3] | Expected Output: 2
 * move 4
 * move 5
 */
public class MinimumStepsToGetSortedList {
    /**
     * find the number in the correct relatively order
     * 
     * 1,4,2,5,3
     */
    public static int countMoveToBack(int[] nums) {
        int[] sortedArr = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortedArr);
        int sortedIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == sortedArr[sortedIndex]) {
                sortedIndex++;
            }
        }
        return nums.length - sortedIndex;
    }

    public static void main(String[] args) {
        int[] test1 = {1,4,2,5,3};
        System.out.println(countMoveToBack(test1));
    }

    
}
