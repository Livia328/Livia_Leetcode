// public class Q374 {
//     /** 
//      * Forward declaration of guess API.
//      * @param  num   your guess
//      * @return 	     -1 if num is higher than the picked number
//      *			      1 if num is lower than the picked number
//     *               otherwise return 0
//     * int guess(int num);
//     */

//     public class Solution extends GuessGame {
//         public int guessNumber(int n) {
//             if (guess(n) == 0) {
//                 return n;
//             }
//             // L, R
//             int L = 0, R = n;
//             while (L <= R) {
//                 int M = L + (R - L) / 2;
//                 if (guess(M) == 0) {
//                     return M;
//                 }
//                 if (guess(M) == -1) {
//                     // higher, shrink the right boundary
//                     R = M - 1;
//                 } else {
//                     L = M + 1;
//                 }
//             }
//             return -1;
//         }
//     }
// }
