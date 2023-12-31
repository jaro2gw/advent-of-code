package me.pjaronski.aoc.v22.day25

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.v22.day25.snafu.SnafuNumber

fun main() = present(Solution25)

object Solution25 : Solution<String, String?> {
    override fun part1(input: Input): String {
        val sum = input.lines().sumOf { SnafuNumber.toLong(it) }
        return SnafuNumber.fromLong(sum)
    }

    /** There is no part 2 on day 25 **/
    override fun part2(input: Input) = null
}
