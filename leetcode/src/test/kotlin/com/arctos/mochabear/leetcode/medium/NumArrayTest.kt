package com.arctos.mochabear.leetcode.medium

import org.junit.Assert.assertEquals
import org.junit.Test

class NumArrayTest {

    @Test
    fun test() {
        val array = NumArray(intArrayOf(1,7,3,0,7,8,3,2,6,2,1,1,4,5))
        assertEquals(31, array.sumRange(0,7))
        array.update(4,9)
        assertEquals(33, array.sumRange(0,7))
    }
}