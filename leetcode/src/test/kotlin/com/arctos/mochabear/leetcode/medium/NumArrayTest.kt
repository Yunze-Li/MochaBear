package com.arctos.mochabear.leetcode.medium

import org.junit.Assert.assertEquals
import org.junit.Test

class NumArrayTest {

    @Test
    fun test() {
        val array = NumArray(intArrayOf(7,2,7,2,0))
        array.update(4,6)
        array.update(0,2)
        array.update(0,9)
        assertEquals(6, array.sumRange(4,4))
        array.update(3,8)
        assertEquals(32, array.sumRange(0,4))
    }
}