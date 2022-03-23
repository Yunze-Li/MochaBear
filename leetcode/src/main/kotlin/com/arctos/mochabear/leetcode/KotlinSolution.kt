package com.arctos.mochabear.leetcode

import kotlin.math.min


class Solution {
    fun minPathSum(grid: Array<IntArray>): Int {
        val row = grid.size
        val column = grid[0].size

        for (rowIndex in 1 until row) {
            grid[rowIndex][0] += grid[rowIndex - 1][0]
        }
        for (columnIndex in 1 until row) {
            grid[0][columnIndex] += grid[0][columnIndex - 1]
        }
        for (rowIndex in 1 until row) {
            for (columnIndex in 1 until column) {
                grid[rowIndex][columnIndex] += min(
                    grid[rowIndex - 1][columnIndex],
                    grid[rowIndex][columnIndex - 1]
                )
            }
        }
        return grid[row - 1][column - 1]
    }
}

fun main(args: Array<String>) {
    val solution = Solution()
}
