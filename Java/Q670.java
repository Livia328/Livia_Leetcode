public class Q670 {
    /*
     * 因为要交换得到最大的数
     * 所以我们肯定希望把前面一个小的数和后面一个大的数交换
     * 
     * 用一个数组，arr[i]表示index i之后最大的数的index
     * 然后从前往后遍比较nums[i]和nums[arr[i]]
     * 如果nums[i]小就换
     */
    public int maximumSwap1(int num) {
        char[] numArr = String.valueOf(num).toCharArray();
        int[] maxArr = new int[numArr.length];
        // 先初始化一下
        for (int i = 0; i < numArr.length; i++) {
            maxArr[i] = i;
        }
        // 因为maxArr已经是i + 1之后最大的数了
        // 所以只要比较nums[i]和maxArr[i + 1]就行了
        for (int i = numArr.length - 2; i >= 0; i--) {
            if (numArr[i] <= numArr[maxArr[i + 1]]) {
                maxArr[i] = maxArr[i + 1];
            }
        }
        // 从前往后遍历得到答案
        for (int i = 0; i < numArr.length; i++) {
            int biggestIndex = maxArr[i];
            if (numArr[i] < numArr[biggestIndex]) {
                char tmp = numArr[i];
                numArr[i] = numArr[biggestIndex];
                numArr[biggestIndex] = tmp;
                break;
            }
        }
        return Integer.parseInt(String.valueOf(numArr));
    }

    /*
     * 我们可以不用数组记录
     * 而是用两个index来不断更新需要交换的index就可以
     * 
     * 可以有一个maxindex表示从后往前的最大的数的坐标
     * 如果当前数小于最大数，那么就更新index1和index2
     * 
     * 我们要持续不断地更新index1和index2，直到结束
     * 这样就可以找到最左边的这一对要交换的数
     */
    public int maximumSwap(int num) {
        char[] numArr = String.valueOf(num).toCharArray();
        int maxIndex = numArr.length - 1;
        int index1 = -1, index2 = -1;
        for (int i = numArr.length - 2; i >= 0; i--) {
            if (numArr[i] > numArr[maxIndex]) {
                // 更新maxIndex
                maxIndex = i;
            } else if (numArr[i] < numArr[maxIndex]) {
                index1 = maxIndex;
                index2 = i;
            }
        }
        // 如果结束的时候index2 == -1
        // 说明没有一个数需要交换，说明每个数都比它之后的所有数都要大
        // 说明本来就是倒叙
        if (index2 == -1) {
            return num;
        }
        swap(numArr, index1, index2);
        return Integer.parseInt(String.valueOf(numArr));
    }

    private void swap(char[] chars, int index, int index2) {
        char temp = chars[index];
        chars[index] = chars[index2];
        chars[index2] = temp;
    }

}
