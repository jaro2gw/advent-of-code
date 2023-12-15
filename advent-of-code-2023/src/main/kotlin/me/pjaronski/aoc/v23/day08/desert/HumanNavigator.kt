package me.pjaronski.aoc.v23.day08.desert

import me.pjaronski.aoc.utils.Turn
import me.pjaronski.aoc.utils.infinite

data object HumanNavigator {
    fun countSteps(turns: List<Turn>, nodes: Map<String, DesertNode>): Int {
        val turnsIterator = infinite(turns).iterator()
        var steps = 0
        var current = nodes["AAA"]!!

        while (current.label != "ZZZ") {
            val turn = turnsIterator.next()
            current = current.move(turn)
            steps += 1
        }

        return steps
    }
}
