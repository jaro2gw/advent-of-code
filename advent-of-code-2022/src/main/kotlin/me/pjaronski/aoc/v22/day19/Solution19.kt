package me.pjaronski.aoc.v22.day19

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.utils.NUMBER_PATTERN
import me.pjaronski.aoc.v22.day19.blueprint.RobotBlueprint
import me.pjaronski.aoc.v22.day19.blueprint.RobotFactory
import me.pjaronski.aoc.v22.day19.excavation.Excavation
import me.pjaronski.aoc.v22.day19.resource.Resource
import me.pjaronski.aoc.v22.day19.resource.Resource.*
import me.pjaronski.aoc.v22.day19.resource.ResourceArray

fun main() = present(Solution19)

object Solution19 : Solution {
    private val ROBOT_FACTORY_BLUEPRINT_ID_REGEX = Regex("Blueprint $NUMBER_PATTERN:")
    private val ROBOT_BLUEPRINT_REGEX = Regex(
        "Each (ore|clay|obsidian|geode) robot costs $NUMBER_PATTERN ore( and $NUMBER_PATTERN clay)?( and $NUMBER_PATTERN obsidian)?\\."
    )

    private fun factories(input: Input): Sequence<RobotFactory> = input.lines()
        .asSequence()
        .map { line ->
            val id = ROBOT_FACTORY_BLUEPRINT_ID_REGEX.find(line)!!.groupValues[1].toInt()
            val tmp = ResourceArray<RobotBlueprint?> { null }
            ROBOT_BLUEPRINT_REGEX.findAll(line).forEach {
                val resource = Resource.valueOf(it.groupValues[1])
                val required = ResourceArray { 0 }
                required[ore] = it.groupValues[2].toInt()
                required[clay] = it.groupValues[4].toIntOrNull() ?: 0
                required[obsidian] = it.groupValues[6].toIntOrNull() ?: 0
                tmp[resource] = RobotBlueprint(required)
            }
            val blueprints = ResourceArray { tmp[it]!! }
            RobotFactory(id, blueprints)
        }

    override fun part1(input: Input): String {
        val excavation = Excavation()
        return factories(input)
            .sumOf { it.id * excavation.run(it, 24) }
            .toString()
    }

    override fun part2(input: Input): String {
        val excavation = Excavation()
        return factories(input)
            .take(3)
            .map { excavation.run(it, 32) }
            .reduce(Int::times)
            .toString()
    }
}
