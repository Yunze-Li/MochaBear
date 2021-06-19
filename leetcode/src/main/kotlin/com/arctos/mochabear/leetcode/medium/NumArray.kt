package com.arctos.mochabear.leetcode.medium

/**
 * 307. Range Sum Query - Mutable
 * https://leetcode.com/problems/range-sum-query-mutable/
 *
 * 一般解法: 累加,求和,记录
 * initialize()初始化 时间O(n) 空间O(n)
 * update()方法 时间O(n)
 * sumRange()方法 时间O(1)
 *
 * 最优解法: Binary Indexed Tree (Fenwick Tree)
 * https://blog.csdn.net/Yaokai_AssultMaster/article/details/79492190
 * initialize()初始化 时间O(n) 空间O(n)
 * update()方法 时间O(logN)
 * sumRange()方法 时间O(logN)
 */
class NumArray(nums: IntArray) {

    private var numbers = nums
    private var tree = IntArray(numbers.size + 1)

    init {
        for (i in 1 until tree.size) {
            tree[i] = nums[i - 1]
        }

        for (parent in 1 until tree.size) {
            val lowbit = (parent and (-parent))   // 利用补码特性获得lowbit
            val child = parent + lowbit  // BIT子节点坐标 = 父节点坐标 + lowbit
            if (child < tree.size) {
                tree[child] += tree[parent]
            }
        }
    }

    fun update(index: Int, `val`: Int) {
        val diff = `val` - numbers[index]
        numbers[index] = `val`

        var current = index + 1
        while (current < tree.size) {
            tree[current] += diff
            val lowbit = (current and (-current))   // 利用补码特性获得lowbit
            current += lowbit   // 从当前节点开始更新所有子节点
        }
    }

    fun sumRange(left: Int, right: Int): Int {
        return if (left == 0) {
            getSum(right)
        } else {
            getSum(right) - getSum(left - 1)
        }
    }

    /**
     * 获得从 0 到 index (包括index)的所有值的和 (0 based)
     * Note: 这是一个从子节点迭加到父节点的过程
     */
    private fun getSum(index: Int): Int {
        var current = index + 1
        var sum = 0
        while (current > 0) {
            sum += tree[current]
            val lowbit = (current and (-current))   // 利用补码特性获得lowbit
            current -= lowbit  // 父节点坐标 = 子节点坐标 - lowbit
        }
        return sum
    }

}