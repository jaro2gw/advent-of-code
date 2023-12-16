package me.pjaronski.aoc.v22.day09.rope

import me.pjaronski.aoc.utils.Direction
import me.pjaronski.aoc.utils.Direction.EAST
import me.pjaronski.aoc.utils.Direction.NORTH
import me.pjaronski.aoc.utils.Direction.SOUTH
import me.pjaronski.aoc.utils.Direction.WEST

data class Movement(
    val direction: Direction,
    val steps: Int,
) {
    companion object {
        private val ROPE_MOVE_REGEX = Regex("([DLRU]) (\\d+)")

        private fun direction(char: Char): Direction = when (char) {
            'D' -> SOUTH
            'L' -> WEST
            'R' -> EAST
            'U' -> NORTH
            else -> throw IllegalArgumentException("Could not convert char '$char' into a direction")
        }

        fun fromString(string: String): Movement {
            val (dir, num) = ROPE_MOVE_REGEX.find(string)!!.destructured
            val direction = direction(dir[0])
            val steps = num.toInt()
            return Movement(direction, steps)
        }
    }
}
