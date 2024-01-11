package me.pjaronski.aoc.v23.day17

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.v23.day17.heat.HeatMapNavigator

fun main() = present(Solution17)

object Solution17 : Solution<Int, Int> {
    private fun parse(input: Input): Array<IntArray> = input.lines()
        .map { line ->
            line.map { it.digitToInt() }
                .toIntArray()
        }
        .toTypedArray()

    override fun part1(input: Input): Int {
        val heatMap = parse(input)
        return HeatMapNavigator.minHeatLoss(heatMap)
    }

    override fun part2(input: Input): Int {
        val heatMap = parse(input)
        return HeatMapNavigator.minHeatLoss(heatMap, ultra = true)
    }
}
