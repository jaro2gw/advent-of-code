package me.pjaronski.aoc.v23.day01

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.v23.day01.number.NumberFinder
import me.pjaronski.aoc.v23.day01.number.NumberWrapper.Companion.WRAPPERS_ALL
import me.pjaronski.aoc.v23.day01.number.NumberWrapper.Companion.WRAPPERS_NUM

fun main() = present(Solution01)

object Solution01 : Solution<Int, Int> {

    private val finder = NumberFinder()

    override fun part1(input: Input): Int = input.lines()
        .sumOf { line ->
            val (d1, d2) = finder.find(line, WRAPPERS_NUM)
            10 * d1 + d2
        }

    override fun part2(input: Input): Int = input.lines()
        .sumOf { line ->
            val (d1, d2) = finder.find(line, WRAPPERS_ALL)
            10 * d1 + d2
        }
}
