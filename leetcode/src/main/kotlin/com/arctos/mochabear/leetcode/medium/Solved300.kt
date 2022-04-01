package com.arctos.mochabear.leetcode.medium

import kotlin.math.max

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/
 */
class Solved300 {
    fun lengthOfLIS(nums: IntArray): Int {
        val dp = IntArray(nums.size)
        dp[0] = 1
        var maxResult = 1
        for (index in 1 until nums.size) {
            var result = 1
            for (candidateIndex in 0..index) {
                if (nums[candidateIndex] < nums[index]) {
                    result = max(result, dp[candidateIndex] + 1)
                }
            }
            dp[index] = result
            maxResult = max(maxResult, dp[index])
        }
        return maxResult
    }
}