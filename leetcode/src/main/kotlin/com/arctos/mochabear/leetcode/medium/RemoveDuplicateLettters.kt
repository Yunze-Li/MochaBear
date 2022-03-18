package com.arctos.mochabear.leetcode.medium

import java.util.Stack

// https://leetcode.com/problems/remove-duplicate-letters/
// 316. Remove Duplicate Letters
class RemoveDuplicateLettters {
    fun removeDuplicateLetters(s: String): String {
        val count = IntArray(26) { 0 }
        val selected = mutableSetOf<Char>()
        val stack = Stack<Char>()

        for (index in s.indices) {
            count[s[index] - 'a']++
        }

        for (index in s.indices) {
            if (!selected.contains(s[index])) {
                while (stack.isNotEmpty() && s[index] < stack.peek() && count[stack.peek() - 'a'] > 0) {
                    val temp = stack.pop()
                    selected.remove(temp)
                }
                stack.push(s[index])
                selected.add(s[index])
            }
            count[s[index] - 'a']--
        }

        val stringBuilder = StringBuilder()
        while (stack.isNotEmpty()) {
            stringBuilder.append(stack.pop())
        }
        return stringBuilder.reverse().toString()
    }
}