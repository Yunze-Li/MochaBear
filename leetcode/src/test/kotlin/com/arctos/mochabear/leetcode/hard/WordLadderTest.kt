package com.arctos.mochabear.leetcode.hard

import org.junit.Test

class WordLadderTest {

    @Test
    fun testWordLadderI() {
        val list = WordLadder().ladderLength(
            "leet",
            endWord = "code",
            wordList = listOf("lest", "leet", "lose", "code", "lode", "robe", "lost")
        )
        print(list)
    }

}