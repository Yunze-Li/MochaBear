package com.arctos.mochabear.leetcode.medium


/**
 * 162. Find Peak Element
 * https://leetcode.com/problems/find-peak-element/
 *
 * 解法： Binary Search
 * 时间O(lgn) 空间O(1)
 */
class FindPeakElement {
    fun findPeakElement(nums: IntArray): Int {

        // 处理一下corner case
        if (nums.size == 1 || nums[0] > nums[1]) return 0

        // 开始二分法
        var left = 0
        var right = nums.size - 1
        while (left < right) {
            val middle = (left + right) / 2

            // nums[middle] < nums[middle + 1]说明在[middle+1, nums.size-1]之间必有一个值为peak element
            if (nums[middle] < nums[middle + 1]) {
                left = middle + 1
            } else {
                right = middle
            }
        }
        return left
    }
}