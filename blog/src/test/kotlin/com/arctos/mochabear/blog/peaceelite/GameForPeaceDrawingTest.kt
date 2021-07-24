package com.arctos.mochabear.blog.peaceelite

import org.junit.Test

class GameForPeaceDrawingTest {

    private val drawing = PeaceEliteDrawing()

    @Test
    fun testDummyDrawing() {
        val drawingResult = drawing.dummyDrawing(4000, 648)
        println("平均花费幸运币：${drawingResult.spending}，平均获得碎片：${drawingResult.points}，性价比：${drawingResult.points / drawingResult.spending}")
    }

    @Test
    fun testAverageDummyDrawing() {
        var totalSpending = 0       // 幸运币总花费
        var totalGetPoints = 0      // 净获得的碎片数量
        var totalEqualPoints = 0    // 物品折算的总碎片数量
        val drawingTimes = 10000
        for (i in 0 until drawingTimes) {
            val drawingResult = drawing.dummyDrawing(10000, 648)    // 满足最高幸运币预算的条件即可
            print("第 $i 次傻瓜式平A，花费幸运币：${drawingResult.spending}，获得碎片：${drawingResult.points} ")
            for (egg in Egg.values()) {
                if (drawingResult.hasItemRetrieved(3, egg)) {
                    print("3星物品：${egg.description} ")
                }
            }
            println("折算碎片: ${drawingResult.points + drawingResult.retrievedItemToPoints()} ")
            totalSpending += drawingResult.spending
            totalGetPoints += drawingResult.points
            totalEqualPoints += drawingResult.points + drawingResult.retrievedItemToPoints()
        }
        val averageSpending = totalSpending.toDouble() / drawingTimes
        val averageGetPoints = totalGetPoints.toDouble() / drawingTimes
        val averageEqualPoints = totalEqualPoints.toDouble() / drawingTimes
        println("平均花费幸运币：${averageSpending}，平均获得碎片：${averageGetPoints}，平均折算碎片：${averageEqualPoints}，获得性价比：${averageGetPoints / averageSpending}，折算性价比：${averageEqualPoints / averageSpending}")
    }

    @Test
    fun testNormalAddingDrawing() {
        val drawingResult = drawing.normalAddingDrawing(2000, 648)
        print("花费：${drawingResult.spending}，获得碎片：${drawingResult.points} ")
        for (egg in Egg.values()) {
            for (level in 5..7) {
                if (drawingResult.hasItemRetrieved(level, egg)) {
                    print("${level}星物品：${egg.description} ")
                }
            }
        }
        println("折算碎片: ${drawingResult.points + drawingResult.retrievedItemToPoints()} ")
    }

    @Test
    fun testAverageNormalAddingDrawing() {
        var totalSpending = 0       // 幸运币总花费
        var totalGetPoints = 0      // 净获得的碎片数量
        var totalEqualPoints = 0    // 物品折算的总碎片数量
        val drawingTimes = 10000
        for (i in 0 until drawingTimes) {
            val drawingResult = drawing.normalAddingDrawing(60000, 6000)    // 满足最高幸运币预算的条件即可
            print("第 $i 次普通追加，花费幸运币：${drawingResult.spending}，获得碎片：${drawingResult.points} ")
            for (egg in Egg.values()) {
                for (level in 5..7) {
                    if (drawingResult.hasItemRetrieved(level, egg)) {
                        print("${level}星物品：${egg.description} ")
                    }
                }
            }
            println("折算碎片: ${drawingResult.points + drawingResult.retrievedItemToPoints()} ")
            totalSpending += drawingResult.spending
            totalGetPoints += drawingResult.points
            totalEqualPoints += drawingResult.points + drawingResult.retrievedItemToPoints()
        }
        val averageSpending = totalSpending.toDouble() / drawingTimes
        val averageGetPoints = totalGetPoints.toDouble() / drawingTimes
        val averageEqualPoints = totalEqualPoints.toDouble() / drawingTimes
        println("平均花费幸运币：${averageSpending}，平均获得碎片：${averageGetPoints}，平均折算碎片：${averageEqualPoints}，获得性价比：${averageGetPoints / averageSpending}，折算性价比：${averageEqualPoints / averageSpending}")
    }

    @Test
    fun testOptimizedNormalAddingDrawing() {
        val drawingResult = drawing.protectedAddingDrawing(2000, 648)
        print("花费：${drawingResult.spending}，获得碎片：${drawingResult.points} ")
        for (egg in Egg.values()) {
            for (level in 5..7) {
                if (drawingResult.hasItemRetrieved(level, egg)) {
                    print("${level}星物品：${egg.description} ")
                }
            }
        }
        println("折算碎片: ${drawingResult.points + drawingResult.retrievedItemToPoints()} ")
    }

    @Test
    fun testAverageOptimizedNormalAddingDrawing() {
        var totalSpending = 0       // 幸运币总花费
        var totalGetPoints = 0      // 净获得的碎片数量
        var totalEqualPoints = 0    // 物品折算的总碎片数量
        val drawingTimes = 10000
        for (i in 0 until drawingTimes) {
            val drawingResult = drawing.protectedAddingDrawing(10000, 648)    // 满足最高幸运币预算的条件即可
            print("第 $i 次保护追加，花费幸运币：${drawingResult.spending}，获得碎片：${drawingResult.points} ")
            for (egg in Egg.values()) {
                for (level in 5..7) {
                    if (drawingResult.hasItemRetrieved(level, egg)) {
                        print("${level}星物品：${egg.description} ")
                    }
                }
            }
            println("折算碎片: ${drawingResult.points + drawingResult.retrievedItemToPoints()} ")
            totalSpending += drawingResult.spending
            totalGetPoints += drawingResult.points
            totalEqualPoints += drawingResult.points + drawingResult.retrievedItemToPoints()
        }
        val averageSpending = totalSpending.toDouble() / drawingTimes
        val averageGetPoints = totalGetPoints.toDouble() / drawingTimes
        val averageEqualPoints = totalEqualPoints.toDouble() / drawingTimes
        println("平均花费幸运币：${averageSpending}，平均获得碎片：${averageGetPoints}，平均折算碎片：${averageEqualPoints}，获得性价比：${averageGetPoints / averageSpending}，折算性价比：${averageEqualPoints / averageSpending}")
    }

    @Test
    fun testDifferentDrawingStrategy() {
        val dummyDrawingResult = drawing.dummyDrawing(3000, 648)
        print("傻瓜式平A，花费幸运币：${dummyDrawingResult.spending}，获得碎片：${dummyDrawingResult.points}，性价比：${dummyDrawingResult.points.toDouble() / dummyDrawingResult.spending} ")
        print("折算碎片：${dummyDrawingResult.points + dummyDrawingResult.retrievedItemToPoints()}，性价比：${(dummyDrawingResult.points + dummyDrawingResult.retrievedItemToPoints()).toDouble() / dummyDrawingResult.spending} ")
        printDrawingResult(dummyDrawingResult)
        println("")

        val normalAddingDrawingResult = drawing.normalAddingDrawing(960, 648)
        print("普通追加，花费幸运币：${normalAddingDrawingResult.spending}，获得碎片：${normalAddingDrawingResult.points}，性价比：${normalAddingDrawingResult.points.toDouble() / normalAddingDrawingResult.spending} ")
        print("折算碎片：${normalAddingDrawingResult.points + normalAddingDrawingResult.retrievedItemToPoints()}，性价比：${(normalAddingDrawingResult.points + normalAddingDrawingResult.retrievedItemToPoints()).toDouble() / normalAddingDrawingResult.spending} ")
        printDrawingResult(normalAddingDrawingResult)
        println("")
    }

    @Test
    fun testGetNextResult() {
        val countEgg = intArrayOf(0, 0, 0, 0, 0)
        val countLevel = intArrayOf(0, 0, 0)
        for (i in 0..10000) {
            val result = drawing.getNextDrawResult()
            when (result.egg) {
                Egg.EGG_1 -> countEgg[0] = countEgg[0] + 1
                Egg.EGG_2 -> countEgg[1] = countEgg[1] + 1
                Egg.EGG_3 -> countEgg[2] = countEgg[2] + 1
                Egg.EGG_4 -> countEgg[3] = countEgg[3] + 1
                Egg.EGG_5 -> countEgg[4] = countEgg[4] + 1
            }
            countLevel[result.level - 1] += 1
        }
        for (i in countEgg.indices) {
            println("扭蛋${i + 1}概率：${countEgg[i].toDouble() / 10000}")
        }
        for (i in countLevel.indices) {
            println("升${i + 1}星概率：${countLevel[i].toDouble() / 10000}")
        }
    }

    private fun printDrawingResult(result: DrawingResult) {
        for (egg in Egg.values()) {
            for (level in 3..6) {
                if (result.hasItemRetrieved(level, egg)) {
                    print("${level}星物品：${egg.description} ")
                }
            }
        }
    }
}