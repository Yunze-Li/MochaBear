package com.arctos.mochabear.leetcode.medium

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
class Solved3 {
    fun lengthOfLongestSubstring(s: String): Int {
        val countMap = mutableMapOf<Char, Int>()
        var result = -1
        var startIndex = 0
        for (index in s.indices) {
            if (countMap[s[index]] == null) {
                countMap[s[index]] = index
                result = Math.max(result, index - startIndex + 1)
            } else if (countMap[s[index]]!! < startIndex) {
                result = Math.max(result, index - startIndex + 1)
                countMap[s[index]] = index
            } else {
                result = Math.max(result, index - countMap[s[index]]!!)
                startIndex = countMap[s[index]]!! + 1
                countMap[s[index]] = index
            }
        }
        if (result == -1) {
            return s.length
        } else {
            return Math.max(result, s.length - startIndex)
        }
    }
}