package me.pjaronski.aoc.v23.day02

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.v23.day02.game.CubeColor.*
import me.pjaronski.aoc.v23.day02.game.Game

fun main() = present(Solution02)

object Solution02 : Solution<Int, Int> {
    override fun part1(input: Input): Int = input.lines()
        .map { Game.parse(it) }
        .filter { it[red] <= 12 && it[green] <= 13 && it[blue] <= 14 }
        .sumOf { it.id }

    override fun part2(input: Input): Int = input.lines()
        .map { Game.parse(it) }
        .sumOf { it.power }
}
