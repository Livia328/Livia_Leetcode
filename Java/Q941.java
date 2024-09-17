public class Q941 {
    public static boolean validMountainArray(int[] arr) {
        if (arr.length < 3) {
            return false;
        }
        if (arr[0] >= arr[1]) {
            return false;
        }
        int index = 1;
        // 0 2 3 3 
        while (index < arr.length && arr[index - 1] < arr[index]) {
            index++;
        }
        if (index == arr.length) {
            return false;
        }
        while (index < arr.length) {
            if (arr[index - 1] <= arr[index]) {
                return false;
            }
            index++;
        }
        return true;

    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2};
        boolean flag = validMountainArray(arr);
    }
}
