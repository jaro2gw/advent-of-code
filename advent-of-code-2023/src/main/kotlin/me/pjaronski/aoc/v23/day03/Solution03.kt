package me.pjaronski.aoc.v23.day03

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.v23.day03.engine.EngineSchematic

fun main() = present(Solution03)

object Solution03 : Solution<Int, Int> {
    override fun part1(input: Input): Int {
        val lines = input.lines().toTypedArray()
        return EngineSchematic(lines).engineParts().sum()
    }

    override fun part2(input: Input): Int {
        val lines = input.lines().toTypedArray()
        return EngineSchematic(lines).gears().sum()
    }
}
