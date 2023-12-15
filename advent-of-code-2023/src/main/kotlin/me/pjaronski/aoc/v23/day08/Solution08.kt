package me.pjaronski.aoc.v23.day08

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.utils.Turn
import me.pjaronski.aoc.utils.split
import me.pjaronski.aoc.v23.day08.desert.DesertNode
import me.pjaronski.aoc.v23.day08.desert.GhostNavigator
import me.pjaronski.aoc.v23.day08.desert.HumanNavigator

fun main() = present(Solution08)

object Solution08 : Solution<Int, Long> {
    private const val NODE_LABEL = "([A-Z0-9]{3})"
    private val NODE_REGEX = Regex("$NODE_LABEL = \\($NODE_LABEL, $NODE_LABEL\\)")

    private fun parse(input: Input): Pair<List<Turn>, Map<String, DesertNode>> {
        val (turnPart, nodePart) = split(input).toList()
        val turns = turnPart.single().map { Turn.parse(it) }

        val nodes = mutableMapOf<String, DesertNode>()

        nodePart.forEach { line ->
            val (_, label, left, right) = NODE_REGEX.find(line)!!.groupValues
            nodes[label] = DesertNode(label, left, right, nodes::getValue)
        }

        return turns to nodes
    }

    override fun part1(input: Input): Int {
        val (turns, nodes) = parse(input)
        return HumanNavigator.countSteps(turns, nodes)
    }

    override fun part2(input: Input): Long {
        val (turns, nodes) = parse(input)
        return GhostNavigator.countSteps(turns, nodes)
    }
}
