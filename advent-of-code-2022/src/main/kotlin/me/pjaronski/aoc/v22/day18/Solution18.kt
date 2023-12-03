package me.pjaronski.aoc.v22.day18

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.utils.NUMBER_PATTERN
import me.pjaronski.aoc.v22.day18.lava.LavaDroplet
import me.pjaronski.aoc.v22.day18.lava.LavaScan

fun main() = present(Solution18)

object Solution18 : Solution {
    private val LAVA_DROPLET_REGEX = Regex("$NUMBER_PATTERN,$NUMBER_PATTERN,$NUMBER_PATTERN")

    private fun droplets(input: Input): Set<LavaDroplet> = input.lines()
        .mapNotNull { LAVA_DROPLET_REGEX.find(it) }
        .map { it.destructured }
        .map { (x, y, z) ->
            LavaDroplet(
                x = x.toInt(),
                y = y.toInt(),
                z = z.toInt(),
            )
        }
        .toSet()

    override fun part1(input: Input): String {
        val droplets = droplets(input)
        return LavaScan().sides(droplets).toString()
    }

    override fun part2(input: Input): String {
        val droplets = droplets(input)
        return LavaScan().sidesWithoutBubbles(droplets).toString()
    }
}
