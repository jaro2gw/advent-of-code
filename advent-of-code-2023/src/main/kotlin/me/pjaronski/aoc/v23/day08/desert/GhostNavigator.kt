package me.pjaronski.aoc.v23.day08.desert

import me.pjaronski.aoc.utils.Turn
import me.pjaronski.aoc.utils.lcm

data object GhostNavigator {
    fun countSteps(turns: List<Turn>, nodes: Map<String, DesertNode>): Long {
        return nodes.values
            .filter { it.label.endsWith('A') }
            .flatMap { NavigationCycleDetector.detectNavigationCycle(turns, it) }
            .reduce { finish1, finish2 -> lcm(finish1, finish2) }
    }
}
