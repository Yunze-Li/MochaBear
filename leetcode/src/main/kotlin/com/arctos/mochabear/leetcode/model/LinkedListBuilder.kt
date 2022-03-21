package com.arctos.mochabear.leetcode.model

class LinkedListBuilder {

    fun buildLinkedList(nums: IntArray): ListNode? {
        if (nums.isEmpty()) return null

        val head = ListNode(nums[0])
        var current: ListNode = head
        for (i in 1 until nums.size) {
            current.next = ListNode(nums[i])
            current = current.next!!
        }
        return head
    }
}