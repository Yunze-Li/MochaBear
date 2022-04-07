package com.arctos.mochabear.leetcode.medium

import java.util.Stack

/**
 * https://leetcode.com/problems/decode-string/
 */
class Solved394 {
    fun decodeString(s: String): String {
        if (s.length < 2) {
            return s
        }
        val stack = Stack<String>()
        var result = ""
        for (index in s.indices) {
            when {
                s[index] == '[' -> {
                    stack.push(s[index].toString())
                }
                s[index] == ']' -> {
                    // between this ] and corresponding [, it should be only letters inside
                    var child = ""
                    while (stack.peek()[0] != '[') {
                        child = stack.pop() + child
                    }
                    stack.pop() // should be the corresponding [ here
                    var timeStr = ""
                    while (stack.isNotEmpty() && stack.peek()[0].isDigit()) {
                        timeStr = stack.pop() + timeStr
                    }
                    val times = timeStr.toInt()
                    var finalChildString = ""
                    for (index in 0 until times) {
                        finalChildString += child
                    }
                    stack.push(finalChildString)
                }
                s[index].isDigit() -> {
                    stack.push(s[index].toString())
                }
                else -> {
                    stack.push(s[index].toString())
                }
            }
        }
        while (stack.isNotEmpty()) {
            result = stack.pop() + result
        }
        return result
    }
}