package com.arctos.mochabear.leetcode

import java.util.Stack

class Solution {
    fun validateStackSequences(pushed: IntArray, popped: IntArray): Boolean {
        if (pushed.isEmpty() && popped.isEmpty()) {
            return true
        } else if (pushed.isEmpty() || popped.isEmpty() || pushed.size != popped.size) {
            return false
        }

        val stack = Stack<Int>()
        var poppedIndex = 0
        for (pushedIndex in pushed.indices) {
            stack.push(pushed[pushedIndex])
            while (stack.isNotEmpty() && stack.peek() == popped[poppedIndex]) {
                stack.pop()
                poppedIndex++
            }
        }
        return stack.isEmpty()
    }
}

fun main(args: Array<String>) {
    val solution = Solution()
}
