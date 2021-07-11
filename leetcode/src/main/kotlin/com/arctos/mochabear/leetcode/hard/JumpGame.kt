package com.arctos.mochabear.leetcode.hard

import java.lang.Integer.max
import java.util.LinkedList
import java.util.PriorityQueue

class JumpGame {

    /**
     * 55. Jump Game
     * https://leetcode.com/problems/jump-game/
     *
     * 解法：遍历数组，更新最远位置
     * 时间O(n) 空间O(1)
     */
    fun canJump(nums: IntArray): Boolean {
        var current = 0     // 当前位置
        var maxReach = 0    // 最远可到达位置
        while (current <= maxReach) {
            // 检查是否到达数组最后一个位置
            if (current == nums.size - 1) {
                return true
            }

            // 更新能到达的最远位置，并向前走一步
            maxReach = maxOf(maxReach, current + nums[current])
            current += 1
        }

        // 到达maxReach，但不是数组最后一位，返回false
        return false
    }

    /**
     * 45. Jump Game II
     * https://leetcode.com/problems/jump-game-ii/
     *
     * 解法：Greedy，每次选择能到达的最远位置来跳下一步
     * 时间O(n) 空间O(1)
     */
    fun jump(nums: IntArray): Int {
        var current = 0     // 当前位置
        var stepCount = 0   // 步数计数器
        while (current < nums.size - 1) {
            var maxReach = current + nums[current]
            var nextPosition = current

            // 更新步数计数，找到当前位置所能到达的最远位置，作为下一步
            stepCount += 1
            for (i in current..current + nums[current]) {

                // 如果下一步直接能到数组最后一位，直接返回结果，否则更新下一步位置
                if (i >= nums.size - 1) {
                    return stepCount
                } else if (i + nums[i] > maxReach) {
                    maxReach = i + nums[i]
                    nextPosition = i
                }
            }
            current = nextPosition
        }
        return stepCount
    }

    /**
     * 1306. Jump Game III
     * https://leetcode.com/problems/jump-game-iii/
     *
     * 解法：DFS，从起始开始向左右分别进行DFS搜索，注意判断是否重复到达某一位置
     * 时间O(n) 空间O(n)
     */
    private var canReach = false
    fun canReach(arr: IntArray, start: Int): Boolean {
        if (arr[start] == 0) return true

        // 创建boolean数组来记录是否到达过当前位置
        val isVisited = BooleanArray(arr.size)
        jumpGameIIIHelper(arr, isVisited, start)
        isVisited[start] = true
        return canReach
    }

    private fun jumpGameIIIHelper(arr: IntArray, isVisited: BooleanArray, current: Int) {
        if (arr[current] == 0) {
            canReach = true
            return
        }

        // 向当前位置的左右分别进行DFS搜索
        if (current - arr[current] >= 0 && !isVisited[current - arr[current]]) {
            isVisited[current - arr[current]] = true
            jumpGameIIIHelper(arr, isVisited, current - arr[current])
            isVisited[current - arr[current]] = false
        }
        if (current + arr[current] < arr.size && !isVisited[current + arr[current]]) {
            isVisited[current + arr[current]] = true
            jumpGameIIIHelper(arr, isVisited, current + arr[current])
            isVisited[current + arr[current]] = false
        }
    }

    /**
     * 1345. Jump Game IV
     * https://leetcode.com/problems/jump-game-iv/
     *
     * 解法：BFS 建立一个Queue来保存每一步可以到达的所有位置
     * 时间O() 空间O(n)
     */
    fun minJumps(arr: IntArray): Int {
        var answer = 0

        // 用HashMap建立graph
        val graph = HashMap<Int, LinkedList<Int>>()
        for (index in arr.indices) {
            graph.computeIfAbsent(
                arr[index]
            ) { LinkedList<Int>() }.add(index)
        }

        // 用queue来进行BFS，每一步建立一个新queue
        val visited = BooleanArray(arr.size)
        var queue = LinkedList<Int>()
        queue.add(0)
        while (queue.isNotEmpty()) {
            val newQueue = LinkedList<Int>()
            for (current in queue) {
                if (current == arr.size - 1) {
                    return answer
                }

                // 搜索相同值的其他位置进行跳跃
                for (next in graph.getOrDefault(arr[current], LinkedList())) {
                    if (!visited[next]) {
                        visited[next] = true
                        newQueue.add(next)
                    }
                }
                graph[arr[current]]?.clear()

                // 向后走一格进行跳跃
                if (current - 1 >= 0 && !visited[current - 1]) {
                    visited[current - 1] = true
                    newQueue.add(current - 1)
                }

                // 向前走一格进行跳跃
                if (current + 1 < arr.size && !visited[current + 1]) {
                    visited[current + 1] = true
                    newQueue.add(current + 1)
                }
            }
            answer += 1
            queue = newQueue
        }
        return -1
    }

    /**
     * 1340. Jump Game V
     * https://leetcode.com/problems/jump-game-v/
     *
     * 解法：DFS + Memoization
     * https://leetcode.com/problems/jump-game-v/discuss/1261158/JAVA-or-8-ms-Beats-94-or-Memoization
     * 时间O(n^2)(range最大为n的情况) 空间O(n)
     */
    fun maxJumps(arr: IntArray, d: Int): Int {

        // 用mutableList存储已经DFS过并算出结果的位置，避免重复
        val maxJumpResults = MutableList(arr.size) { 0 }
        var result = 0
        for (i in arr.indices) {
            result = max(result, maxJumpsHelper(arr, d, i, maxJumpResults))
        }
        return result
    }

    private fun maxJumpsHelper(
        arr: IntArray,
        range: Int,
        current: Int,
        maxJumpResults: MutableList<Int>
    ): Int {

        // 已经查过有结果的位置直接取结果
        if (maxJumpResults[current] != 0) return maxJumpResults[current]

        var max = 1 // 自身位置算一步，至少为1

        // range内向右遍历，一旦有超过arr[current]立刻停止遍历
        for (i in 1..range) {
            val nextRight = current + i
            if (nextRight < arr.size && arr[nextRight] < arr[current]) {
                max = max(max, maxJumpsHelper(arr, range, nextRight, maxJumpResults) + 1)
            } else {
                break
            }
        }

        // range内向左遍历，一旦有超过arr[current]立刻停止遍历
        for (i in 1..range) {
            val nextLeft = current - i
            if (nextLeft >= 0 && arr[nextLeft] < arr[current]) {
                max = max(max, maxJumpsHelper(arr, range, nextLeft, maxJumpResults) + 1)
            } else {
                break
            }
        }
        maxJumpResults[current] = max
        return max
    }

    /**
     * 1696. Jump Game VI
     * https://leetcode.com/problems/jump-game-vi/
     *
     * 解法：Dynamic Programming + Sliding Window
     * 状态转移方程： dp[i] = max(dp[i - k], dp[i - k + 1],...,dp[i - 1]) + nums[i]
     * 时间O(nlgn) 空间O(n)
     */

    fun maxResult(nums: IntArray, k: Int): Int {
        val dp = MutableList(nums.size) { 0 }
        dp[0] = nums[0]

        // 用PriorityQueue来维护（dp[i-k],...dp[i-1]）的所有值
        val pq = PriorityQueue(Comparator<Int> { o1, o2 -> o2 - o1 })
        pq.add(dp[0])
        for (i in 1 until nums.size) {
            dp[i] = pq.peek() + nums[i]

            // 更新sliding window的范围和值
            pq.add(dp[i])
            if (i - k >= 0) {
                pq.remove(dp[i - k])
            }
        }
        return dp[nums.size - 1]
    }

    /**
     * 1871. Jump Game IIV
     * https://leetcode.com/problems/jump-game-vii/
     *
     * 解法：Dynamic Programming + Sliding Window
     * 状态转移方程：dp[i] = (dp[i - maxJump] || dp[i - maxJump + 1] || ... dp[i - minJump]) && (s[i] == '0')
     * 时间：O(n) 空间：O(n)
     */
    fun canReach(s: String, minJump: Int, maxJump: Int): Boolean {
        val dp = MutableList(s.length) { false }
        dp[0] = true

        // 用MutableSet来记录（dp[i-maxJump],...dp[i-minJump]）中所有可到达的位置的坐标
        val slidingWindow = mutableSetOf<Int>()
        if (minJump == 1) slidingWindow.add(0)      // base case，需要单独判断
        for (i in 1 until s.length) {
            if (s[i] == '0') {
                dp[i] = slidingWindow.isNotEmpty()  // 这里只要sliding window不为空即可
            }

            // 更新sliding window，加入i - minJump + 1，移除i - maxJump(if any)
            val newAdded = i - minJump + 1
            if (newAdded >= 0 && dp[newAdded]) slidingWindow.add(newAdded)
            slidingWindow.remove(i - maxJump)
        }
        return dp[s.length - 1]
    }

}