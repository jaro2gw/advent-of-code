package me.pjaronski.aoc.v23.day09

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.v23.day09.oasis.OasisAndSandInstabilitySensor

fun main() = present(Solution09)

object Solution09 : Solution<Long, Long> {
    private fun parse(input: Input) = input.lines()
        .map { line ->
            line.split(' ')
                .map { it.toLong() }
        }

    override fun part1(input: Input): Long = parse(input)
        .sumOf { OasisAndSandInstabilitySensor.predict(it) }

    override fun part2(input: Input): Long = parse(input)
        .map { it.reversed() }
        .sumOf { OasisAndSandInstabilitySensor.predict(it) }
}
