package com.arctos.mochabear.blog.peaceelite

import java.lang.Integer.min
import kotlin.math.max
import kotlin.random.Random

class PeaceEliteDrawing {

    private val pointsList = listOf(2, 12, 36, 108, 320, 960, 2880, 8640)       // 碎片价值表
    private val protectDrawingSpendingList = listOf(0, 6, 17, 51, 153, 430, 827)       // 碎片价值表

    /**
     * 傻瓜式平A，直接兑换三级物品，然后兑换碎片
     * @param expectedPoints        目标碎片数
     * @param spendingBudget        幸运币预算
     */
    fun dummyDrawing(expectedPoints: Int, spendingBudget: Int): DrawingResult {
        var spending = 0
        var points = 0

        // 记录已兑换物品
        val eggItemRetrieved = mutableMapOf<Egg, MutableList<Boolean>>().apply {
            Egg.values().forEach { egg ->
                this[egg] = mutableListOf(true, true, true, false, false, false, false, false)
            }
        }

        // 当碎片数达到目标或者幸运币超出预算时停止
        while (points < expectedPoints && spending < spendingBudget) {
            spending += 6
            val result = getNextDrawResult()
            if (eggItemRetrieved[result.egg]?.get(result.level) == true) {
                points += pointsList[result.level]   // 已有兑换物品，获得全额碎片返还
            } else {
                eggItemRetrieved[result.egg]?.set(result.level, true)   // 没有当前物品，兑换该物品
            }
        }
        return DrawingResult(spending, points, eggItemRetrieved)
    }

    /**
     * 抽一次扭蛋并返回结果
     */
    internal fun getNextDrawResult(): DrawEggResult {
        // 扭蛋类型
        val resultEgg = Egg.values()[randomPick(Egg.values().size)]
        // 扭蛋级别
        return when (randomPick(100)) {
            0 -> DrawEggResult(resultEgg, 3)
            in 1 until 18 -> DrawEggResult(resultEgg, 2)
            else -> DrawEggResult(resultEgg, 1)
        }
    }

    private fun randomPick(total: Int): Int {
        return Random.nextInt(total)
    }

    /**
     * 普通追加，一旦抽并追加到五星及以上，则先兑换物品，再兑换碎片
     * @param expectedPoints        目标碎片数
     * @param spendingBudget        幸运币预算
     */
    fun normalAddingDrawing(expectedPoints: Int, spendingBudget: Int): DrawingResult {
        var spending = 0
        var points = 0

        // 记录已兑换物品
        val eggItemRetrieved = mutableMapOf<Egg, MutableList<Boolean>>().apply {
            Egg.values().forEach { egg ->
                this[egg] = mutableListOf(true, true, true, false, false, false, false, false)
            }
        }

        // 当碎片数达到目标或者幸运币超出预算时停止
        while (points < expectedPoints && spending < spendingBudget) {
            spending += 6

            var currentLevel = 0
            var currentEgg: Egg? = null
            while (true) {
                val result = getNextDrawResult()
                if (currentEgg == null) {
                    // 第一次抽取结束，准备继续追加
                    currentEgg = result.egg
                    currentLevel = result.level
                } else {
                    if (currentEgg == result.egg) {
                        // 追加成功
                        currentLevel = min(currentLevel + result.level, 7)
                        if (currentLevel > 4) {
                            if (eggItemRetrieved[result.egg]?.get(currentLevel) == true) {
                                points += pointsList[currentLevel]   // 积分100%返还
                            } else {
                                eggItemRetrieved[result.egg]?.set(currentLevel, true)   // 兑换对应物品
                            }
                            // 本轮结束，直接退出
                            break
                        }
                    } else {
                        // 追加失败，本轮结束，结算碎片并退出
                        val nextLevel = max(currentLevel - getDecreasingLevel(), 0)
                        points += pointsList[nextLevel] / (if (eggItemRetrieved[result.egg]?.get(
                                nextLevel
                            ) == true
                        ) 1 else 2)
                        break
                    }
                }
            }
        }
        return DrawingResult(spending, points, eggItemRetrieved)
    }

    /**
     * 返回随机降星的星级
     */
    private fun getDecreasingLevel(): Int {
        return when (randomPick(4)) {
            0 -> 2
            else -> 1
        }
    }

    /**
     * 保护追加，当普通追加到4，5，6星时进行保护，7星时直接兑换
     * @param expectedPoints        目标碎片数
     * @param spendingBudget        幸运币预算
     */
    fun protectedAddingDrawing(expectedPoints: Int, spendingBudget: Int): DrawingResult {
        var spending = 0
        var points = 0

        // 记录已兑换物品
        val eggItemRetrieved = mutableMapOf<Egg, MutableList<Boolean>>().apply {
            Egg.values().forEach { egg ->
                this[egg] = mutableListOf(true, true, true, false, false, false, false, false)
            }
        }

        // 当碎片数达到目标或者幸运币超出预算时停止
        while (points < expectedPoints && spending < spendingBudget) {
            spending += 6

            var currentLevel = 0
            var currentEgg: Egg? = null
            while (true) {
                val result = getNextDrawResult()
                if (currentEgg == null) {
                    // 第一次抽取结束，准备继续追加
                    currentEgg = result.egg
                    currentLevel = result.level
                } else {
                    if (currentEgg == result.egg) {
                        // 追加成功
                        currentLevel = min(currentLevel + result.level, 7)
                        if (currentLevel == 7) {
                            if (eggItemRetrieved[result.egg]?.get(currentLevel) == true) {
                                points += pointsList[currentLevel]   // 积分100%返还
                            } else {
                                eggItemRetrieved[result.egg]?.set(currentLevel, true)   // 兑换对应物品
                            }
                            // 本轮结束，直接退出
                            break
                        } else if (currentLevel >= 4) {
                            var isProtected = false
                            // 保护追加两次
                            if (!isProtected && spendingBudget - spending >= protectDrawingSpendingList[currentLevel]) {
                                spending += protectDrawingSpendingList[currentLevel]
                                val tempResult = getNextDrawResult()
                                if (tempResult.egg == currentEgg) {
                                    currentLevel = min(currentLevel + tempResult.level, 7)
                                    isProtected = true
                                }
                            }
                            // 保护追加两次
                            if (!isProtected && spendingBudget - spending >= protectDrawingSpendingList[currentLevel]) {
                                spending += protectDrawingSpendingList[currentLevel]
                                val tempResult = getNextDrawResult()
                                if (tempResult.egg == currentEgg) {
                                    currentLevel = min(currentLevel + tempResult.level, 7)
                                    isProtected = true
                                }
                            }
                            // 本轮为保护轮，扭蛋类型必相同
                            if (!isProtected) {
                                currentLevel = min(currentLevel + getNextDrawResult().level, 7)
                            }

                            // 无论什么级别，直接兑换然后退出
                            if (eggItemRetrieved[result.egg]?.get(currentLevel) == true) {
                                points += pointsList[currentLevel]   // 积分100%返还
                            } else {
                                eggItemRetrieved[result.egg]?.set(currentLevel, true)   // 兑换对应物品
                            }
                            // 本轮结束，直接退出
                            break
                        }
                    } else {
                        // 追加失败，本轮结束，结算碎片并退出
                        val nextLevel = max(currentLevel - getDecreasingLevel(), 0)
                        points += pointsList[nextLevel] / (if (eggItemRetrieved[result.egg]?.get(
                                nextLevel
                            ) == true
                        ) 1 else 2)
                        break
                    }
                }
            }
        }
        return DrawingResult(spending, points, eggItemRetrieved)
    }
}

/************************
 *      辅助类
 ************************/

/**
 * 单次抽扭蛋的结果类
 */
data class DrawEggResult(
    val egg: Egg,   // 扭蛋类型
    val level: Int  // 星级
)

/**
 * 不同策略下抽扭蛋的最终结果类
 */
data class DrawingResult(
    val spending: Int,      // 幸运币总花销
    val points: Int,        // 获得碎片总数

    // 已兑换物品记录
    val eggItems: Map<Egg, MutableList<Boolean>> = mutableMapOf<Egg, MutableList<Boolean>>().apply {
        Egg.values().forEach { egg ->
            this[egg] = mutableListOf(true, true, true, false, false, false, false, false)
        }
    }
)

/**
 * 定义扭蛋的枚举类
 */
enum class Egg(val description: String) {
    EGG_1("苍蓝魅影"),
    EGG_2("翡翠骑士"),
    EGG_3("绚金战神"),
    EGG_4("炫彩锋芒"),
    EGG_5("幻彩光梭")
}


/************************
 *     Kotlin扩展方法
 ************************/

fun DrawingResult.hasItemRetrieved(level: Int, egg: Egg): Boolean {
    return eggItems[egg]?.get(level) ?: false
}

fun DrawingResult.retrievedItemToPoints(): Int {
    var points = 0
    val pointsMap = listOf(2, 12, 36, 108, 320, 960, 2880, 8640)
    for ((_, retrieved) in eggItems) {
        for (level in 3..7) {
            if (retrieved[level]) {
                points += pointsMap[level]
            }
        }
    }
    return points
}