package me.pjaronski.aoc.v23.day08.desert

import me.pjaronski.aoc.utils.Turn
import me.pjaronski.aoc.utils.Turn.LEFT
import me.pjaronski.aoc.utils.Turn.RIGHT

data class DesertNode(
    val label: String,
    val left: String,
    val right: String,
    private val fetch: (String) -> DesertNode
) {
    fun move(turn: Turn): DesertNode = when (turn) {
        LEFT -> fetch(left)
        RIGHT -> fetch(right)
    }
}
