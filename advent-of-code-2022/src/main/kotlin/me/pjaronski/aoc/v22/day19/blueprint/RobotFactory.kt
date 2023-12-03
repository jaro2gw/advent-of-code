package me.pjaronski.aoc.v22.day19.blueprint

import me.pjaronski.aoc.v22.day19.resource.ResourceArray

data class RobotFactory(
    val id: Int,
    val blueprints: ResourceArray<RobotBlueprint>,
)
