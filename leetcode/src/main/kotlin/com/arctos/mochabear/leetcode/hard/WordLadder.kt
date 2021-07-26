package com.arctos.mochabear.leetcode.hard

class WordLadder {

    /**
     * 127. Word Ladder I
     * https://leetcode.com/problems/word-ladder/
     *
     * 解法：bi-BFS 从两边向中间进行BFS
     * 时间O(n) 空间O(n), n为workList的大小
     */
    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
        // 检查Corner Case
        if (!wordList.contains(endWord)) return 0

        var startSet = setOf(beginWord)     // 从前往后BFS时的搜索结果
        var endSet = setOf(endWord)      // 从后往前BFS时的搜索结果
        val availableWordSet = wordList.toMutableSet()      // 可用的word集合

        var isReversed = false      // 决定是搜索顺序的flag
        var ladderLength = 2        // 产生word ladder长度

        // 开始进行BFS
        while (startSet.isNotEmpty() && endSet.isNotEmpty()) {
            val currentSet = if (isReversed) endSet else startSet
            val targetSet = if (!isReversed) endSet else startSet
            val nextSet = mutableSetOf<String>()

            // 替换word中每个字母来确定下一个可能的word
            for (word in currentSet) {
                for (i in word.indices) {
                    for (c in 'a'..'z') {
                        if (c != word[i]) {
                            val newWord = word.substring(0, i) + c + word.substring(i + 1)

                            // 如果targetSet中存在newWord，说明ladder已连接，直接返回长度即可
                            // 否则的话就从availableWordSet中去除newWord，加入nextSet中
                            if (targetSet.contains(newWord)) {
                                return ladderLength
                            } else if (availableWordSet.contains(newWord)) {
                                nextSet.add(newWord)
                                availableWordSet.remove(newWord)
                            }
                        }
                    }
                }
            }

            // 如果nextSet为空，则BFS无法继续，直接返回0
            if (nextSet.isEmpty()) return 0

            // 更新startSet，endSet以及判断下一次BFS的顺序
            if (isReversed) {
                endSet = nextSet
            } else {
                startSet = nextSet
            }
            isReversed = startSet.size > endSet.size
            ladderLength += 1
        }
        return 0
    }
}