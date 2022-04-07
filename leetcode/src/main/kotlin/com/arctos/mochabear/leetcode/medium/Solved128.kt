package com.arctos.mochabear.leetcode.medium

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/
 */
class Solved128 {
    fun longestConsecutive(nums: IntArray): Int {
        if (nums.isEmpty()) {
            return 0
        }
        val leftMap = mutableMapOf<Int, Interval>()
        val rightMap = mutableMapOf<Int, Interval>()
        val numSet = mutableSetOf<Int>()

        var result = 1
        for (num in nums) {
            if (numSet.contains(num)) {
                continue
            } else {
                numSet.add(num)
            }
            if (leftMap[num + 1] != null && rightMap[num - 1] != null) {
                // connect two intervals
                val leftInterval = rightMap[num - 1]!!
                val rightInterval = leftMap[num + 1]!!
                leftMap.remove(num + 1)
                leftMap.remove(leftInterval.left)
                rightMap.remove(num - 1)
                rightMap.remove(rightInterval.right)
                val newInterval = Interval(leftInterval.left, rightInterval.right)
                leftMap[newInterval.left] = newInterval
                rightMap[newInterval.right] = newInterval
                result = Math.max(result, newInterval.right - newInterval.left + 1)
            } else if (leftMap[num + 1] != null) {
                val interval = leftMap[num + 1]!!
                leftMap.remove(num + 1)
                interval.left = num
                leftMap[num] = interval
                result = Math.max(result, interval.right - interval.left + 1)
            } else if (rightMap[num - 1] != null) {
                val interval = rightMap[num - 1]!!
                rightMap.remove(num - 1)
                interval.right = num
                rightMap[num] = interval
                result = Math.max(result, interval.right - interval.left + 1)
            } else {
                val newInterval = Interval(num, num)
                leftMap[num] = newInterval
                rightMap[num] = newInterval
            }
        }
        return result
    }

    class Interval(
        var left: Int,
        var right: Int
    )
}