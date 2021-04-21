package club.sdll.ptc.swordoffer.findRepeatNumber;

/**
 * https://github.com/doocs/leetcode/blob/main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9803.%20%E6%95%B0%E7%BB%84%E4%B8%AD%E9%87%8D%E5%A4%8D%E7%9A%84%E6%95%B0%E5%AD%97/README.md
 *
 * 面试题 03. 数组中重复的数字
 * 题目描述
 * 找出数组中重复的数字。
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0 ～ n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 * 限制：
 *
 * 2 <= n <= 100000
 * 解法
 * 0 ～ n-1 范围内的数，分别还原到对应的位置上，如：数字 2 交换到下标为 2 的位置。
 *
 * 若交换过程中发现重复，则直接返回。
 *
 * @author shandianlala@gmail.com
 * @version 1.0
 * @date 2021-04-21 07:33
 */
public class Solution {

    public int findRepeatNumber(int[] nums) {
        // 核心点：长度为 n 的数组 nums 里的所有数字都在 0 ～ n-1 的范围内
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) return nums[i];
                swap(nums, i, nums[i]);
            }
        }
        return -1;
    }

    public void swap(int[] nums, int sourceIndex, int targetIndex) {
        int temp = nums[sourceIndex];
        nums[sourceIndex] = nums[targetIndex];
        nums[targetIndex] = temp;
    }

}
