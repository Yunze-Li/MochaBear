package com.arctos.mochabear.leetcode.medium

/**
 * https://leetcode.com/problems/lru-cache/
 */

class LRUCache(capacity: Int) {

    private val capacity = capacity
    private val nodeMap = mutableMapOf<Int, CacheNode>()
    private val head = CacheNode()
    private val tail = CacheNode()

    init {
        head.next = tail
        tail.previous = head
    }

    fun get(key: Int): Int {
        if (nodeMap[key] != null) {
            val target = nodeMap[key]!!
            removeNode(target)
            insertNodeToFirst(target)
            return target.value!!
        } else {
            return -1
        }
    }

    fun put(key: Int, value: Int) {
        if (nodeMap[key] != null) {
            // update node Map value
            val target = nodeMap[key]!!
            target.value = value

            // move node to first position
            removeNode(target)
            insertNodeToFirst(target)
        } else if (nodeMap.size == capacity) {
            // remove node at last position (list and map)
            val target = tail.previous!!
            nodeMap.remove(target.key)
            removeNode(target)

            // add new node to first position and update node map
            val newNode = CacheNode(key, value)
            nodeMap[key] = newNode
            insertNodeToFirst(newNode)
        } else {
            // add new node to first position and update node map
            val newNode = CacheNode(key, value)
            nodeMap[key] = newNode
            insertNodeToFirst(newNode)
        }
    }

    private fun insertNodeToFirst(node: CacheNode) {
        val temp = head.next!!
        head.next = node
        node.next = temp
        temp.previous = node
        node.previous = head
    }

    private fun removeNode(node: CacheNode) {
        val before = node.previous!!
        val after = node.next!!
        node.previous = null
        node.next = null
        before.next = after
        after.previous = before
    }

    class CacheNode constructor(
        var key: Int? = null,
        var value: Int? = null,
        var previous: CacheNode? = null,
        var next: CacheNode? = null
    )

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * var obj = LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */