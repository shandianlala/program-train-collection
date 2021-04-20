package club.sdll.ptc.swordoffer.findNumberIn2DArray;

/**
 *
 * https://github.com/doocs/leetcode/blob/main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9804.%20%E4%BA%8C%E7%BB%B4%E6%95%B0%E7%BB%84%E4%B8%AD%E7%9A%84%E6%9F%A5%E6%89%BE/README.md
 * 面试题 04. 二维数组中的查找
 * 题目描述
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回  true。
 *
 * 给定  target = 20，返回  false。
 *
 * 限制：
 *
 * 0 <= n <= 1000
 *
 * 0 <= m <= 1000
 *
 * 解法
 * 从左下角（或右上角）开始查找即可。
 *
 * @author shandianlala@gmail.com
 * @version 1.0
 * @date 2021-04-21 07:18
 */
public class Solution {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int m,n;
        if (matrix == null || (m = matrix.length) == 0 || matrix[0] == null || (n = matrix[0].length) == 0) {
            // 排除一、二维首节点为null或者长度为0的情况
            return false;
        }
        // 从右上角开始遍历
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) return true;
            if (matrix[i][j] > target) j--; else i++;
        }
        return false;
    }


}
