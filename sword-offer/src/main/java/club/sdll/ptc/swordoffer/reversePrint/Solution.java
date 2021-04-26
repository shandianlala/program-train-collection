package club.sdll.ptc.swordoffer.reversePrint;

/**
 * description
 *
 * @author shandianlala@gmail.com
 * @version 1.0
 * @date 2021-04-23 07:49
 */
public class Solution {
    public int fib(int n) {
        int a = 0, b = 1;
        for (int i = 0; i < n; ++i) {
            int c = (a + b) % 1000000007;
            a = b;
            b = c;
        }
        return a;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.fib(2);
        int[] numbers = {3,4,5,1,2};
        solution.minArray(numbers);
    }

    public int minArray(int[] numbers) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (numbers[m] > numbers[r]) {
                l = m + 1;
            } else if (numbers[m] < numbers[r]) {
                r = m;
            } else {
                --r;
            }
        }
        return numbers[l];
    }

}
