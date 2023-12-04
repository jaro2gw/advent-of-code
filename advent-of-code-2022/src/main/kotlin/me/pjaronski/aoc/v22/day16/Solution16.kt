package me.pjaronski.aoc.v22.day16

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.utils.NUMBER_PATTERN
import me.pjaronski.aoc.v22.day16.volcano.Valve
import me.pjaronski.aoc.v22.day16.volcano.Volcano

fun main() = present(Solution16)

object Solution16 : Solution<Int, Int> {
    private const val VALVE_PATTERN = "([A-Z]{2})"
    private const val VALVE_TUNNELS_SEPARATOR = ", "
    private val VALVE_REGEX = Regex(
        "Valve $VALVE_PATTERN has flow rate=$NUMBER_PATTERN; tunnels? leads? to valves? ($VALVE_PATTERN($VALVE_TUNNELS_SEPARATOR$VALVE_PATTERN)*)"
    )

    private fun volcano(input: Input): Volcano {
        val tunnels = mutableMapOf<String, List<String>>()
        val valves = mutableMapOf<String, Valve>()

        input.lines().mapNotNull { VALVE_REGEX.find(it) }
            .map { it.destructured }
            .forEach { (label, flow, labels) ->
                valves[label] = Valve(label = label, flow = flow.toInt())
                tunnels[label] = labels.split(VALVE_TUNNELS_SEPARATOR)
            }

        val map: Map<Valve, List<Valve>> = tunnels.entries
            .associate { (label, labels) ->
                valves[label]!! to labels.map { valves[it]!! }
            }

        return Volcano(
            initial = Valve("AA", 0),
            tunnels = map,
        )
    }

    override fun part1(input: Input): Int = volcano(input)
        .pressure(minutes = 30)

    override fun part2(input: Input): Int = volcano(input)
        .pressure(minutes = 26, workers = 2)
}
