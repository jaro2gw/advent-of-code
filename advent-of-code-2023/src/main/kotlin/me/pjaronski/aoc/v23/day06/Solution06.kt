package me.pjaronski.aoc.v23.day06

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.v23.day06.race.Race
import me.pjaronski.aoc.v23.day06.race.RaceRecordSolver

fun main() = present(Solution06)

object Solution06 : Solution<Long, Long> {
    private val SPACE_REGEX = Regex("\\s+")
    private fun parse(input: Input): List<List<String>> = input.lines()
        .map { line ->
            line.split(SPACE_REGEX)
                .drop(1)
        }

    override fun part1(input: Input): Long {
        val (times, distances) = parse(input).map { nums -> nums.map { it.toLong() } }
        return times.zip(distances)
            .map { (time, distance) -> Race(time, distance) }
            .map { RaceRecordSolver.solve(it) }
            .map { (t0, t1) -> t1 - t0 + 1 }
            .reduce { acc, num -> acc * num }
    }

    override fun part2(input: Input): Long {
        val (time, distance) = parse(input).map { nums -> nums.joinToString(separator = "").toLong() }
        val race = Race(time, distance)
        val (t0, t1) = RaceRecordSolver.solve(race)
        return t1 - t0 + 1
    }
}
