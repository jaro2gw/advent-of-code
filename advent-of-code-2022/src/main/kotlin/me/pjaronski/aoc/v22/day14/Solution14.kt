package me.pjaronski.aoc.v22.day14

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.utils.Coords
import me.pjaronski.aoc.v22.day14.cave.Cave

fun main() = present(Solution14)

object Solution14 : Solution<Int, Int> {
    private fun rocks(input: Input): List<List<Coords>> = input.lines()
        .map { line ->
            line.split(" -> ")
                .map { coords ->
                    val (col, row) = coords.split(",").map { it.toInt() }
                    Coords(row, col)
                }
        }

    override fun part1(input: Input): Int = Cave(
        rocks = rocks(input),
        leak = Coords(0, 500),
        bottom = false
    )
        .fill()

    override fun part2(input: Input): Int = Cave(
        rocks = rocks(input),
        leak = Coords(0, 500),
        bottom = true
    )
        .fill()
}
