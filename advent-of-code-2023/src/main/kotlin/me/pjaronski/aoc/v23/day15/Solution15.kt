package me.pjaronski.aoc.v23.day15

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.v23.day15.lens.LensHash
import me.pjaronski.aoc.v23.day15.lens.LensMap

fun main() = present(Solution15)

object Solution15 : Solution<Int, Int> {
    override fun part1(input: Input): Int = input.lines()
        .single()
        .split(',')
        .sumOf { LensHash.of(it) }
    override fun part2(input: Input): Int {
        val lenses = LensMap()

        input.lines()
            .single()
            .split(',')
            .forEach { action ->
                if (action.endsWith('-')) {
                    val lens = action.dropLast(1)
                    lenses.remove(lens)
                }
                else {
                    val (lens, length) = action.split('=')
                    lenses.add(lens, length.toInt())
                }
            }

        return lenses.power()
    }
}
