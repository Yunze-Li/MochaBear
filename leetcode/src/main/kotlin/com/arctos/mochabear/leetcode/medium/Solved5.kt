package com.arctos.mochabear.leetcode.medium

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 */
class Solved5 {
    fun longestPalindrome(s: String): String {
        if (s.length == 1) {
            return s
        }
        val dp = IntArray(s.length) { 1 }
        dp[0] = 1
        var result = s[0].toString()
        var maxLength = 1
        for (index in 1 until dp.size) {
            if (index - dp[index - 1] - 1 >= 0 && s[index] == s[index - dp[index - 1] - 1]) {
                dp[index] = dp[index - 1] + 2
            } else {
                for (start in 0 until index) {
                    if (isPalindrome(s, start, index)) {
                        dp[index] = index - start + 1
                        break
                    }
                }
            }

            if (dp[index] > maxLength) {
                maxLength = dp[index]
                result = s.substring(index - dp[index] + 1, index + 1)
            }
        }
        return result
    }

    private fun isPalindrome(s: String, start: Int, end: Int): Boolean {
        var left = start
        var right = end
        while (left < right) {
            if (s[left] != s[right]) {
                return false
            } else {
                left += 1
                right -= 1
            }
        }
        return true
    }
}