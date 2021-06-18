package com.arctos.mochabear.leetcode.medium

/**
 * 307. Range Sum Query - Mutable
 * https://leetcode.com/problems/range-sum-query-mutable/
 *
 * update()方法 时间O(n)
 * sumRange()方法 时间O(1)
 */
class NumArray(nums: IntArray) {

    private val numbers = nums
    private var sums = mutableListOf<Int>()

    init {
        var sum = 0
        for (i in nums.indices) {
            sum += nums[i]
            sums.add(sum)
        }
    }

    fun update(index: Int, `val`: Int) {
        val diff = `val` - numbers[index]
        numbers[index] = `val`
        for (i in index until numbers.size) {
            sums[i] += diff
        }
    }

    fun sumRange(left: Int, right: Int): Int {
        if (left == 0) return sums[right]
        return sums[right] - sums[left - 1]
    }

}