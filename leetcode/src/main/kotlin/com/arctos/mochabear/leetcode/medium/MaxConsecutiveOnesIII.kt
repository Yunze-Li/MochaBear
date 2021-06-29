package com.arctos.mochabear.leetcode.medium

/**
 * 1004. Max Consecutive Ones III
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 *
 * 解法：Sliding Window
 * https://leetcode.com/problems/max-consecutive-ones-iii/discuss/1304761/Java-or-Easy-or-Sliding-Window
 * 时间O(n) 空间O(1)
 */

class MaxConsecutiveOnesIII {
    fun longestOnes(nums: IntArray, k: Int): Int {
        var left = 0    // sliding window的左边界
        var right = 0   // sliding window的右边界
        var zeroCount = 0
        var result = 0

        while (right < nums.size) {
            if (nums[right] == 0) {
                zeroCount += 1
            }

            // 当zeroCount到k时，更新result；当zeroCount超过k时，左边进行收缩
            if (zeroCount == k) {
                result = maxOf(result, right - left + 1)
            } else if (zeroCount > k) {
                // left side 收缩
                while (zeroCount > k) {
                    if (nums[left] == 0) {
                        zeroCount -= 1
                    }
                    left += 1
                }
            }
            result = maxOf(result, right - left + 1)
            // right side 扩张
            right += 1
        }
        return result
    }
}