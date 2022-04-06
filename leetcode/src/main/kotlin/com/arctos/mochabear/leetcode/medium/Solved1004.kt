package com.arctos.mochabear.leetcode.medium

import kotlin.math.max

/**
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 */
class Solved1004 {
    fun longestOnes(nums: IntArray, k: Int): Int {
        if (k >= nums.size) {
            return nums.size
        }
        var left = 0
        var right = 0
        var maxLength = 0
        var remains = k
        while (right < nums.size) {
            while (right < nums.size && remains >= 0) {
                if (nums[right] == 0) {
                    remains -= 1
                }
                right += 1
            }
            maxLength = max(maxLength, right - left - 1)
            while (left < right && remains < 0) {
                if (nums[left] == 0) {
                    remains += 1
                }
                left += 1
            }
        }
        maxLength = max(maxLength, right - left)
        return maxLength
    }
}