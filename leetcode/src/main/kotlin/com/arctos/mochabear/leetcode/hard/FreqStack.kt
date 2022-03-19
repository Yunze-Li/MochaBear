package com.arctos.mochabear.leetcode.hard

import java.util.Stack

class FreqStack {
    val counts = mutableMapOf<Int, Int>()
    val stacks = mutableMapOf<Int, Stack<Int>>()
    var max = 0

    fun push(x: Int) {
        counts[x] = (counts[x] ?: 0) + 1
        stacks.getOrPut(counts[x]!!) { Stack<Int>() }.push(x)
        if (counts[x]!! > max) max = counts[x]!!
    }

    fun pop(): Int {
        val x = stacks[max]!!.pop()
        counts[x] = counts[x]!! - 1
        if (stacks[max]!!.isEmpty()) max--
        return x
    }
}