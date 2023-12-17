package me.pjaronski.aoc.v23.day11

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.utils.Coords
import me.pjaronski.aoc.v23.day11.universe.Universe

fun main() = present(Solution11)

object Solution11 : Solution<Long, Long> {
    override fun part1(input: Input): Long {
        val lines = input.lines().toTypedArray()
        return Universe(lines).simulateExpansion(1L)
    }

    override fun part2(input: Input): Long {
        val lines = input.lines().toTypedArray()
        return Universe(lines).simulateExpansion(999_999L)
    }
}
