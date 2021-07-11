package com.arctos.mochabear.leetcode.hard

import kotlin.math.pow

class DecodeWays {

    /**
     * 639. Decode Ways II
     * https://leetcode.com/problems/decode-ways-ii/
     *
     * 解法：Dynamic Programming 动态规划
     * 创建一个含有三个元素的数组， dp[0]表示到当前位置的所有decode方法的总数，dp[1], dp[2]分别表示若s[i]为1，2时的decode方法数，否则为0
     * 不需要维护整个数组，仅维护当前位置和上一位置即可
     *
     *                          --- 9 * current[0] + 9 * current[1] + 6 * current[2],    (s[i] == '*')
     *                        /
     *                       /  --- current[1] + current[2],  (s[i] == 0)
     * 状态转移方程：next[0] =
     *                       \  --- current[0] + current[1] + current[2],  (s[i] > 0 && s[i] <= 6)
     *                        \
     *                          --- current[0] + current[1],  (s[i] > 6)
     *
     *                      / --- current[0],  (s[i] == '*' || s[i] == '1')
     *             next[1] =
     *                      \ --- 0,  (s[i] != '1')
     *
     *                      / --- current[0],  (s[i] == '*' || s[i] == '2')
     *             next[2] =
     *                      \ --- 0,  (s[i] != '2')
     *
     * 时间O(n) 空间O(1)
     */
    fun numDecodings(s: String): Int {

        // 第一个数为0时一定无法decode成功，所以排除
        if (s[0] == '0') return 0

        val current = intArrayOf(1, 0, 0)
        val mod = 10.0.pow(9) + 7

        // 遍历s字符进行dynamic programming
        for (i in s.indices) {
            val next = longArrayOf(0, 0, 0)
            if (s[i] == '*') {
                next[0] =
                    9 * current[0].toLong() + 9 * current[1].toLong() + 6 * current[2].toLong()
                next[1] = current[0].toLong()
                next[2] = current[0].toLong()
            } else {
                if (s[i] != '0') {
                    next[0] = next[0] + current[0].toLong()
                }
                next[0] = next[0] + current[1]
                if (s[i] - '0' <= 6) {
                    next[0] += current[2].toLong()
                }
                if (s[i] == '1') {
                    next[1] = current[0].toLong()
                } else if (s[i] == '2') {
                    next[2] = current[0].toLong()
                }
            }
            current[0] = (next[0] % mod).toInt()
            current[1] = (next[1] % mod).toInt()
            current[2] = (next[2] % mod).toInt()
        }
        return current[0]
    }

    /**
     * 91. Decode Ways I
     * https://leetcode.com/problems/decode-ways/
     *
     * 解法：Dynamic Programming 动态规划
     *
     *                        --- dp[i - 2] + dp[i - 1],    (s[i - 1] != 0 && s[i] != 0 && s(i - 1, 1) <= 26)
     *                      /
     *                     /  ---  dp[i - 1],   (s[i - 1] == 0 && s[i] != 0) 或者（s[i - 1] != 0 && s[i] != 0 && s(i - 1, 1) > 26)
     * 状态转移方程：dp[i] =
     *                     \  ---  dp[i - 2],   (s[i] == 0 && s[i - 1] = 1 || 2)
     *                      \
     *                        ---  0    (s[i] == 0 && s[i - 1] != 1 || 2)
     *
     * 时间O(n) 空间O(n)（空间可优化为O(1)，只维护dp[i - 2], dp[i - 1]两个值即可）
     */
    fun numDecodingsI(s: String): Int {

        // 第一个数为0时一定无法decode成功，所以排除
        if (s[0] == '0') return 0

        // dp[i]表示到s[i - 1]为止的substring的decode方法数
        val dp = IntArray(s.length + 1) { 0 }
        dp[0] = 1
        dp[1] = 1
        for (i in 1 until s.length) {
            if (s[i] == '0') {
                if (s[i - 1] == '1' || s[i - 1] == '2') {
                    dp[i + 1] = dp[i - 1]
                } else {
                    return 0
                }
            } else if (s[i - 1] != '0' && s.substring(i - 1, i + 1).toInt() <= 26) {
                dp[i + 1] = dp[i - 1] + dp[i]
            } else {
                dp[i + 1] = dp[i]
            }
        }
        return dp[s.length]
    }
}