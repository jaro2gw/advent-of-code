package me.pjaronski.aoc.v23.day19

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.utils.split
import me.pjaronski.aoc.v23.day19.parts.MechanicalPart
import me.pjaronski.aoc.v23.day19.parts.PartSorter
import me.pjaronski.aoc.v23.day19.parts.Workflow

fun main() = present(Solution19)

object Solution19 : Solution<Int, Long> {
    private fun parse(input: Input): Pair<Map<String, Workflow>, List<MechanicalPart>> {
        val (ws, ps) = split(input).toList()

        val workflows = ws.associate { w ->
            val (name, workflow) = w.split('{')
            name to Workflow.parse(workflow)
        }

        val parts = ps.map { MechanicalPart.parse(it) }

        return workflows to parts
    }

    override fun part1(input: Input): Int {
        val (workflows, parts) = parse(input)
        val (accepted, _) = PartSorter(workflows).sort(parts)
        return accepted.sumOf { (x, m, a, s) -> x + m + a + s }
    }

    override fun part2(input: Input): Long {
        val (workflows, _) = parse(input)
        val (accepted, _) = PartSorter(workflows).combinations(1, 4000)
        return accepted
    }
}
