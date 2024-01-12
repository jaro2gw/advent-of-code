package me.pjaronski.aoc.v23.day16.beam

import me.pjaronski.aoc.utils.Coords
import me.pjaronski.aoc.utils.Coords.Companion.ORIGIN
import me.pjaronski.aoc.utils.Direction
import me.pjaronski.aoc.utils.Direction.EAST
import me.pjaronski.aoc.utils.Direction.NORTH
import me.pjaronski.aoc.utils.Direction.SOUTH
import me.pjaronski.aoc.utils.Direction.WEST
import me.pjaronski.aoc.utils.contains
import me.pjaronski.aoc.utils.get
import me.pjaronski.aoc.v23.day16.beam.Tile.EMPTY
import me.pjaronski.aoc.v23.day16.beam.Tile.MIRROR_B
import me.pjaronski.aoc.v23.day16.beam.Tile.MIRROR_F
import me.pjaronski.aoc.v23.day16.beam.Tile.SPLITTER_H
import me.pjaronski.aoc.v23.day16.beam.Tile.SPLITTER_V
import java.util.EnumSet

object BeamGridSimulator {
    fun energize(grid: Array<Array<Tile>>, start: Pair<Coords, Direction> = ORIGIN to EAST): Int {
        val steady = Array(grid.size) {
            Array(grid[it].size) {
                EnumSet.noneOf(Direction::class.java)
            }
        }
        steady[start.first] += start.second

        val active = LinkedHashSet<Pair<Coords, Direction>>()
        active += start

        while (active.isNotEmpty()) {
            // current coords, towards direction
            val (coords, direction) = active.removeFirst()

            when (grid[coords]) {
                EMPTY      -> {
                    val next = coords + direction
                    if (next in grid && steady[next].add(direction)) {
                        active += next to direction
                    }
                }
                MIRROR_F   -> {
                    val reflection = when (direction) {
                        NORTH -> EAST
                        EAST  -> NORTH
                        SOUTH -> WEST
                        WEST  -> SOUTH
                    }
                    val next = coords + reflection
                    if (next in grid && steady[next].add(reflection)) {
                        active += next to reflection
                    }
                }
                MIRROR_B   -> {
                    val reflection = when (direction) {
                        NORTH -> WEST
                        EAST  -> SOUTH
                        SOUTH -> EAST
                        WEST  -> NORTH
                    }
                    val next = coords + reflection
                    if (next in grid && steady[next].add(reflection)) {
                        active += next to reflection
                    }
                }
                SPLITTER_V -> {
                    val directions = when (direction) {
                        NORTH, SOUTH -> listOf(direction)
                        else         -> listOf(NORTH, SOUTH)
                    }
                    for (dir in directions) {
                        val next = coords + dir
                        if (next in grid && steady[next].add(dir)) {
                            active += next to dir
                        }
                    }
                }
                SPLITTER_H -> {
                    val directions = when (direction) {
                        EAST, WEST -> listOf(direction)
                        else       -> listOf(EAST, WEST)
                    }
                    for (dir in directions) {
                        val next = coords + dir
                        if (next in grid && steady[next].add(dir)) {
                            active += next to dir
                        }
                    }
                }
            }
        }

        return steady.sumOf { sets -> sets.count { it.isNotEmpty() } }
    }
}
