package me.pjaronski.aoc.v23.day14

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.utils.Direction.NORTH
import me.pjaronski.aoc.v23.day14.platform.RockPlatform
import me.pjaronski.aoc.v23.day14.platform.RockPlatformSpinner

fun main() = present(Solution14)

object Solution14 : Solution<Int, Int> {
    private fun parse(input: Input): RockPlatform {
        val platform = input.lines()
            .map { it.toCharArray() }
            .toTypedArray()

        return RockPlatform(platform)
    }

    override fun part1(input: Input): Int = parse(input).tilt(NORTH).load()
    override fun part2(input: Input): Int = parse(input).let { RockPlatformSpinner.spin(it, 1000000000) }.load()
}
