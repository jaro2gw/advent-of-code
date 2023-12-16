package me.pjaronski.aoc.v23.day10.pipe

import me.pjaronski.aoc.utils.Direction
import me.pjaronski.aoc.utils.Direction.EAST
import me.pjaronski.aoc.utils.Direction.NORTH
import me.pjaronski.aoc.utils.Direction.SOUTH
import me.pjaronski.aoc.utils.Direction.WEST

class Pipe private constructor(
    val connections: Set<Direction>
) {
    companion object {
        private val PIPE_ENTRIES_ALL = Pipe(connections = Direction.entries.toSet())
        private val PIPE_ENTRIES_NONE = Pipe(connections = emptySet())
        private val PIPE_NS = Pipe(connections = setOf(NORTH, SOUTH))
        private val PIPE_EW = Pipe(connections = setOf(EAST, WEST))
        private val PIPE_ES = Pipe(connections = setOf(EAST, SOUTH))
        private val PIPE_NE = Pipe(connections = setOf(NORTH, EAST))
        private val PIPE_NW = Pipe(connections = setOf(NORTH, WEST))
        private val PIPE_SW = Pipe(connections = setOf(SOUTH, WEST))

        fun parse(char: Char): Pipe = when (char) {
            '|' -> PIPE_NS
            '-' -> PIPE_EW
            'F' -> PIPE_ES
            'L' -> PIPE_NE
            'J' -> PIPE_NW
            '7' -> PIPE_SW
            'S' -> PIPE_ENTRIES_ALL
            else -> PIPE_ENTRIES_NONE
        }

        fun connectable(source: Pipe, target: Pipe, direction: Direction): Boolean =
            source.connections.contains(direction) && target.connections.contains(direction.opposite)
    }
}
