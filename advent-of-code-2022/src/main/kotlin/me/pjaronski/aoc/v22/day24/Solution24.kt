package me.pjaronski.aoc.v22.day24

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.utils.Coords
import me.pjaronski.aoc.utils.Direction
import me.pjaronski.aoc.utils.Direction.EAST
import me.pjaronski.aoc.utils.Direction.NORTH
import me.pjaronski.aoc.utils.Direction.SOUTH
import me.pjaronski.aoc.utils.Direction.WEST
import me.pjaronski.aoc.v22.day24.blizzard.Blizzard
import me.pjaronski.aoc.v22.day24.blizzard.Vortex

fun main() = present(Solution24)

object Solution24 : Solution<Int, Int> {
    private fun direction(char: Char): Direction = when (char) {
        '^' -> NORTH
        '>' -> EAST
        'v' -> SOUTH
        '<' -> WEST
        else -> throw IllegalArgumentException("Cannot convert char '$char' into a vortex")
    }

    private fun vortex(row: Int, col: Int, char: Char): Vortex {
        val coords = Coords(row, col)
        val direction = direction(char)
        return Vortex(coords, direction)
    }

    private fun convert(input: Input): Pair<Blizzard, List<Vortex>> {
        val rows = input.lines().size
        val cols = input.lines().first().length

        val vortexes = input.lines()
            .flatMapIndexed { row, line ->
                line.withIndex()
                    .filter { (_, char) -> char != '#' }
                    .filter { (_, char) -> char != '.' }
                    .map { (col, char) -> vortex(row, col, char) }
            }

        return Blizzard(rows, cols) to vortexes
    }

    override fun part1(input: Input): Int {
        val (blizzard, vortexes) = convert(input)
        val start = Coords(
            row = 0,
            col = 1,
        )
        val end = Coords(
            row = blizzard.rows - 1,
            col = blizzard.cols - 2,
        )
        val (_, minutes) = blizzard.quickestPath(vortexes, start, end)
        return minutes
    }

    override fun part2(input: Input): Int {
        val (blizzard, vortexes1) = convert(input)
        val start = Coords(
            row = 0,
            col = 1,
        )
        val end = Coords(
            row = blizzard.rows - 1,
            col = blizzard.cols - 2,
        )
        val (vortexes2, trip1) = blizzard.quickestPath(vortexes1, start, end)
        val (vortexes3, trip2) = blizzard.quickestPath(vortexes2, end, start)
        val (_, trip3) = blizzard.quickestPath(vortexes3, start, end)

        return trip1 + trip2 + trip3
    }
}
