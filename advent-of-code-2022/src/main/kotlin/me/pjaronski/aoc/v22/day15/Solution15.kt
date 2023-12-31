package me.pjaronski.aoc.v22.day15

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.utils.Coords
import me.pjaronski.aoc.utils.NUMBER_PATTERN
import me.pjaronski.aoc.v22.day15.sensor.SensorMap
import me.pjaronski.aoc.v22.day15.sensor.SensorRow

fun main() = present(solution = Solution15(row = 2_000_000, bound = 4_000_000))

class Solution15(
    private val row: Int,
    private val bound: Int
) : Solution<Int, Long> {
    private val SENSOR_REGEX = Regex(
        "Sensor at x=$NUMBER_PATTERN, y=$NUMBER_PATTERN: closest beacon is at x=$NUMBER_PATTERN, y=$NUMBER_PATTERN"
    )

    private fun coords(input: Input): List<Pair<Coords, Coords>> = input.lines()
        .asSequence()
        .mapNotNull { SENSOR_REGEX.find(it) }
        .map { it.groupValues }
        .map { it.drop(1) }
        .map { it.map(String::toInt) }
        .map { (xs, ys, xb, yb) ->
            Coords(row = ys, col = xs) to Coords(row = yb, col = xb)
        }
        .toList()

    override fun part1(input: Input): Int {
        val row = SensorRow(row)

        coords(input).forEach { (sensor, beacon) ->
            row.add(sensor, beacon)
        }

        return row.size()
    }

    override fun part2(input: Input): Long {
        val map = SensorMap(0, bound)
        coords(input).forEach { (sensor, beacon) ->
            map.add(sensor, beacon)
        }
        val (row, col) = map.missingBeacon()
        return col * 4_000_000L + row
    }
}
