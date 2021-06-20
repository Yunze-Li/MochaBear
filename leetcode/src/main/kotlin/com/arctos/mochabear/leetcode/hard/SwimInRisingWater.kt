package com.arctos.mochabear.leetcode.hard

import java.lang.Integer.max
import java.util.PriorityQueue

/**
 * 778. Swim in Rising Water
 * https://leetcode.com/problems/swim-in-rising-water/
 *
 * BFS + Priority Queue解法
 * https://leetcode.com/problems/swim-in-rising-water/discuss/1285034/Java-Easy-BFS-solution-explained
 * 时间O(n^2logn) 空间O(n^2), n = grid边长
 */
class SwimInRisingWater {
    fun swimInWater(grid: Array<IntArray>): Int {

        val side = grid.size
        val steps = arrayOf(arrayOf(0, -1), arrayOf(0, 1), arrayOf(-1, 0), arrayOf(1, 0))
        val isVisited = Array(side) { BooleanArray(side) }

        // 用Priority Queue来保证每次取出的是能走到的用时最短的位置
        val queue = PriorityQueue<Array<Int>> { array1, array2 -> array1[2] - array2[2] }

        // Priority Queue内保存数组[i, j, value], (i,j)是位置坐标, value是到达此位置的最短用时
        queue.add(arrayOf(0, 0, grid[0][0]))
        isVisited[0][0] = true

        while (queue.isNotEmpty()) {
            val current = queue.poll()
            for (i in steps.indices) {
                val nextRow = current[0] + steps[i][0]
                val nextColumn = current[1] + steps[i][1]
                if (nextRow < 0 || nextRow >= side || nextColumn < 0 || nextColumn >= side || isVisited[nextRow][nextColumn]) {
                    continue
                }
                val result = max(grid[nextRow][nextColumn], current[2])
                if (nextRow == side - 1 && nextColumn == side - 1) {
                    return result
                }
                queue.add(arrayOf(nextRow, nextColumn, result))
                isVisited[nextRow][nextColumn] = true
            }
        }
        return 0
    }
}