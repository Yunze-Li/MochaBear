package com.arctos.mochabear.leetcode.hard

import kotlin.math.pow

/**
 * 629. K Inverse Pairs Array
 * https://leetcode.com/problems/k-inverse-pairs-array/
 *
 * DP解法
 * https://leetcode.com/problems/k-inverse-pairs-array/discuss/1282496/Python-Short-O(nk)-solution-explained
 * 时间O(nk) 空间O(nk)
 */

class KInversePairsArray {

    fun kInversePairs(n: Int, k: Int): Int {
        val rows = n + 1
        val columns = k + 1

        // 状态转移方程: dp[i][j]表示在1...i中有j个inverse pairs的数量
        // dp[i][j] = dp[i-1][j] + dp[i-1][j-1] + dp[i-1][j-2] + ... + dp[i-1][j-i+1] (j-i+1<0时取0)
        val dp = Array(rows) { DoubleArray(columns) { 1.0 } }

        // sp数组用于记录dp数组的和
        // sp[i][j] = dp[i][0] + dp[i][1] + ... + dp[i][j]
        val sp = Array(rows) { DoubleArray(columns) { 1.0 } }

        for (i in 1 until rows) {
            for (j in 1 until columns) {
                dp[i][j] = if (j - i + 1 > 0) {
                    modulo(sp[i - 1][j] - sp[i - 1][j - i])
                } else {
                    modulo(sp[i - 1][j])
                }

                sp[i][j] = modulo(sp[i][j - 1] + dp[i][j])
            }
        }
        return dp[n][k].toInt()
    }

    private fun modulo(value: Double): Double {
        val mod = (10.0).pow(9) + 7
        var result = value.rem(mod)
        if (result < 0) result += mod
        return result
    }
}