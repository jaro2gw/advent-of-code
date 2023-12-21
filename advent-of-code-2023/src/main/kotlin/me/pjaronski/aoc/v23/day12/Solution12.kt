package me.pjaronski.aoc.v23.day12

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.v23.day12.onsen.OnsenValidator

fun main() = present(Solution12)

object Solution12 : Solution<Long, Long> {
    private fun extend(string: String, separator: String): String = generateSequence { string }
        .take(5)
        .joinToString(separator = separator)

    private fun extend(line: String): String {
        val (graphic, numeric) = line.split(' ')

        val gExtended = extend(graphic, "?")
        val nExtended = extend(numeric, ",")

        return "$gExtended $nExtended"
    }

    override fun part1(input: Input) = input.lines().sumOf { OnsenValidator.possibilities(it) }
    override fun part2(input: Input) = input.lines()
        .map { extend(it) }
        .sumOf { OnsenValidator.possibilities(it) }
}