package com.arctos.mochabear.leetcode.hard

import java.lang.Integer.max

/**
 * 135. Candy
 * https://leetcode.com/problems/candy/
 *
 * Greedy解法：左右可以分开比较，而且只需要与自己的左右neighbor比较并满足条件即可
 * https://leetcode.com/problems/candy/discuss/1300194/Python-O(n)-time-solution-explained
 * 时间O(n) 空间O(n)
 */

class Candy {
    fun candy(ratings: IntArray): Int {
        val results = IntArray(ratings.size) { 1 }

        // check每个rating和它左边neighbor的比较即可
        for (i in ratings.indices) {
            if (i > 0 && ratings[i - 1] < ratings[i]) {
                results[i] = results[i - 1] + 1
            }
        }

        // check每个rating和它右边neighbor的比较即可
        for (i in ratings.indices.reversed()) {
            if (i < ratings.size - 1 && ratings[i] > ratings[i + 1]) {

                // 需要同时满足左右neighbor的比较，所以取较大值
                results[i] = max(results[i], results[i + 1] + 1)
            }
        }
        return results.sum()
    }
}