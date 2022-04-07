package com.arctos.mochabear.leetcode.medium

import kotlin.math.abs

/**
 * https://leetcode.com/problems/target-sum/
 */
class UnSolved494 {
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        if (nums.size == 1) {
            if (nums[0] == target || nums[0] == -target) {
                return 1
            } else {
                return 0
            }
        }
        val side = nums.size
        var sum = 0
        for (num in nums) {
            sum += abs(num)
        }
        val dp = Array(side) { IntArray(sum * 2 + 1) }

        dp[0][sum + nums[0]] += 1
        dp[0][sum - nums[0]] += 1
        for (rowIndex in 1 until side) {
            for (columnIndex in 0 until sum * 2 + 1) {
                if (columnIndex - nums[rowIndex] >= 0) {
                    dp[rowIndex][columnIndex] += dp[rowIndex - 1][columnIndex - nums[rowIndex]]
                }
                if (columnIndex + nums[rowIndex] <= sum * 2) {
                    dp[rowIndex][columnIndex] += dp[rowIndex - 1][columnIndex + nums[rowIndex]]
                }
            }
        }
        return dp[side - 1][sum + target]
    }
}