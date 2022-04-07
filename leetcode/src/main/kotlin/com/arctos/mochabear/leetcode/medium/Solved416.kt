package com.arctos.mochabear.leetcode.medium

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/
 */
class Solved416 {
    fun canPartition(nums: IntArray): Boolean {
        if (nums.size < 2) {
            return false
        }

        var sum = 0
        for (num in nums) {
            sum += num
        }
        if (sum % 2 == 1) {
            return false
        }
        val target = sum / 2
        val dp = Array(nums.size) { BooleanArray(target + 1) }
        for (index in nums.indices) {
            if (nums[index] > target) {
                return false
            }
            dp[index][nums[index]] = true
        }
        for (rowIndex in 1 until nums.size) {
            for (columnIndex in 0 until target + 1) {
                dp[rowIndex][columnIndex] = false
                if (columnIndex - nums[rowIndex] >= 0) {
                    dp[rowIndex][columnIndex] =
                        dp[rowIndex][columnIndex] || dp[rowIndex - 1][columnIndex - nums[rowIndex]]
                }
                if (dp[rowIndex][columnIndex]) {
                    continue
                }
                for (step in 1..rowIndex) {
                    dp[rowIndex][columnIndex] =
                        dp[rowIndex][columnIndex] || dp[rowIndex - step][columnIndex]
                }
            }
        }
        return dp[nums.size - 1][target]
    }
}