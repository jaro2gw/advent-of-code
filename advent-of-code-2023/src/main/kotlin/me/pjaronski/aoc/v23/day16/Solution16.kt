package me.pjaronski.aoc.v23.day16

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.utils.Coords
import me.pjaronski.aoc.utils.Direction.EAST
import me.pjaronski.aoc.utils.Direction.NORTH
import me.pjaronski.aoc.utils.Direction.SOUTH
import me.pjaronski.aoc.utils.Direction.WEST
import me.pjaronski.aoc.v23.day16.beam.BeamGridSimulator
import me.pjaronski.aoc.v23.day16.beam.Tile

fun main() = present(Solution16)

object Solution16 : Solution<Int, Int> {
    private fun parse(input: Input): Array<Array<Tile>> = input.lines()
        .map { line ->
            line.map { Tile.parse(it) }.toTypedArray()
        }
        .toTypedArray()

    override fun part1(input: Input): Int {
        val grid = parse(input)
        return BeamGridSimulator.energize(grid)
    }

    override fun part2(input: Input): Int {
        val grid = parse(input)

        // check that grid is a square
        check(grid.all { it.indices == grid.indices })

        val last = grid.indices.last
        return grid.indices
            .flatMap { idx ->
                listOf(
                    Coords(row = last, col = idx) to NORTH,
                    Coords(row = idx, col = 0) to EAST,
                    Coords(row = 0, col = idx) to SOUTH,
                    Coords(row = idx, col = last) to WEST,
                )
            }
            .maxOf {
                BeamGridSimulator.energize(grid, it)
            }
    }
}
