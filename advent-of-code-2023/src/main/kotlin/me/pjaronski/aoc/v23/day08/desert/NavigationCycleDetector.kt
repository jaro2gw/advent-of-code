package me.pjaronski.aoc.v23.day08.desert

import me.pjaronski.aoc.utils.Turn
import me.pjaronski.aoc.utils.infiniteWithElementIndex

object NavigationCycleDetector {
    private data class NavigationState(
        val index: Int,
        val node: DesertNode,
        val turn: Turn,
    )

    fun detectNavigationCycle(turns: List<Turn>, node: DesertNode): List<Long> {
        val turnsIterator = infiniteWithElementIndex(turns).iterator()
        val navigationStates = mutableSetOf<NavigationState>()
        val finishes = mutableListOf<Long>()

        var current = node
        var steps = 0L
        do {
            if (current.label.endsWith('Z')) finishes += steps
            val (index, turn) = turnsIterator.next()
            val state = NavigationState(
                index,
                current,
                turn
            )
            steps += 1
            current = current.move(turn)
        } while (navigationStates.add(state))

        return finishes
    }
}