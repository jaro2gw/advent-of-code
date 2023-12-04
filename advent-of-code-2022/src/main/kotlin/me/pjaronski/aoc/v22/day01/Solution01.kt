package me.pjaronski.aoc.v22.day01

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.utils.split

fun main() = present(Solution01)

object Solution01 : Solution<Int, Int> {
    private fun calories(input: Input) = split(input).map { it.sumOf(String::toInt) }

    override fun part1(input: Input): Int = calories(input).max()

    override fun part2(input: Input): Int = calories(input)
        .sortedDescending()
        .take(3)
        .sum()
}
