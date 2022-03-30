package com.arctos.mochabear.leetcode.medium

/**
 * https://leetcode.com/problems/lru-cache/
 */
class UnSolved146 {
    private var capacity: Int = 0
    private var head: ListNode = ListNode(0, 0)
    private var tail: ListNode = ListNode(0, 0)
    private var nodeMap = mutableMapOf<Int, ListNode>()

    init {
        this.capacity = capacity
        head.next = tail
        tail.previous = head
    }

    fun get(key: Int): Int {
        if (nodeMap[key] != null) {
            val target = nodeMap[key]!!

            // remove from current position
            getNodeFromList(target)

            // insert into first position
            addNodeToFirstPosition(target)
            return target.value
        } else {
            return -1
        }
    }

    fun put(key: Int, value: Int) {
        if (nodeMap[key] != null) {
            val updated = nodeMap[key]!!
            updated.value = value
            getNodeFromList(updated)
            addNodeToFirstPosition(updated)
        } else if (nodeMap.size == capacity) {
            // remove last node
            val removed = tail.previous!!
            getNodeFromList(removed)
            nodeMap.remove(removed.key)
        }

        // add new node into first position
        val newFirst = ListNode(key, value)
        nodeMap[key] = newFirst
        addNodeToFirstPosition(newFirst)
    }

    private fun getNodeFromList(node: ListNode): ListNode {
        val previous = node.previous!!
        val next = node.next!!
        previous.next = next
        next.previous = previous
        node.previous = null
        node.next = null
        return node
    }

    private fun addNodeToFirstPosition(node: ListNode) {
        val oldFirst = head.next!!
        head.next = node
        node.next = oldFirst
        oldFirst.previous = node
        node.previous = head
    }

    class ListNode constructor(
        var key: Int,
        var value: Int,
        var previous: ListNode? = null,
        var next: ListNode? = null
    )
}