package com.arctos.mochabear.leetcode.hard

/**
 * 927. Three Equal Parts
 * https://leetcode.com/problems/three-equal-parts/
 *
 * 解法：如果有解，那么每一部分的1的个数一定相等，所以可以：
 * 1. 统计1的个数，按countOne / 3划分区间
 * 2. 从最后向前统计0的个数，与前面两个区间进行匹配
 * 3. 匹配成功返回结果，否则返回[-1, -1]
 *
 * 时间O(n) 空间O(1)
 */
class ThreeEqualParts {
    fun threeEqualParts(arr: IntArray): IntArray {
        if (arr.size < 3) return NO_RESULT

        // 统计一共有多少个1，如果不是3的倍数，一定无解；如果没有1，则返回[0,2]即可
        var countOne = 0
        for (num in arr) {
            if (num == 1) countOne += 1
        }
        if (countOne % 3 != 0) return NO_RESULT
        if (countOne == 0) return intArrayOf(0, 2)

        // 从后向前数出countOne / 3 个1，此位置之后的就是最后一个区间，也就是要match的pattern
        var right = arr.size - 1
        var count = 0
        while (right >= 0) {
            if (arr[right] == 1) {
                count += 1
                if (count == countOne / 3) {
                    break
                }
            }
            right -= 1
        }

        // 根据pattern来确定第一，第二个区间的结束位置
        val leftResult = findHelper(arr, 0, right)
        if (leftResult < 0) return NO_RESULT
        val rightResult = findHelper(arr, leftResult + 1, right)
        if (rightResult < 0) return NO_RESULT
        return intArrayOf(leftResult, rightResult + 1)
    }

    private fun findHelper(arr: IntArray, start: Int, pattern: Int): Int {
        var currentMatched = start
        var currentPattern = pattern

        // 去掉多余的起始0位
        while (arr[currentMatched] == 0) currentMatched++

        // 开始进行pattern match
        while (currentPattern < arr.size) {
            if (arr[currentMatched] != arr[currentPattern]) return -1
            currentMatched += 1
            currentPattern += 1
        }
        return currentMatched - 1
    }

    companion object {
        private val NO_RESULT = intArrayOf(-1, -1)
    }
}