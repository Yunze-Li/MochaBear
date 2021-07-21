package com.arctos.mochabear.leetcode.medium

import kotlin.random.Random

/**
 * 384. Shuffle An Array
 * https://leetcode.com/problems/shuffle-an-array/
 *
 * 解法：Fisher–Yates shuffle (https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle)
 * 第i个元素有 1 / i+1的概率留在本来位置，有(1 - (1 / i+1)) * (1 / i) = 1 / i+1的概率与前i个位置中任意一个进行交换
 * 所以最终会成为平均概率的shuffle方法
 *
 * 时间O(n) 空间O(n)
 */
class ShuffleAnArray(nums: IntArray) {

    private val originals = nums

    /** Resets the array to its original configuration and return it. */
    fun reset(): IntArray {
        return originals
    }

    /** Returns a random shuffling of the array. */
    fun shuffle(): IntArray {
        val shuffled = originals.clone()
        for (i in 1 until shuffled.size) {
            val position = Random.nextInt(i + 1)
            if (position != i) {
                swap(shuffled, i, position)
            }
        }
        return shuffled
    }

    private fun swap(nums: IntArray, position1: Int, position2: Int) {
        val temp = nums[position1]
        nums[position1] = nums[position2]
        nums[position2] = temp
    }

}