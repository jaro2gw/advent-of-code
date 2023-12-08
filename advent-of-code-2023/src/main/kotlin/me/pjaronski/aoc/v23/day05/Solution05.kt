package me.pjaronski.aoc.v23.day05

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.utils.head
import me.pjaronski.aoc.utils.split
import me.pjaronski.aoc.v23.day05.garden.GardenResourceMapping

fun main() = present(Solution05)

object Solution05 : Solution<Long, Long> {
    private fun parse(input: Input): Pair<List<Long>, List<GardenResourceMapping>> {
        val (seedsPart, mappingsPart) = split(input).toList().head()

        val seeds = seedsPart!!.single().split(' ').drop(1).map { it.toLong() }
        val mappings = mappingsPart.map { rulesPart ->
            val rules = rulesPart.drop(1)
                .map { rule ->
                    rule.split(' ')
                        .map { it.toLong() }
                        .let { (target, source, range) -> Triple(target, source, range) }
                }
            GardenResourceMapping(rules)
        }

        return seeds to mappings
    }

    override fun part1(input: Input): Long {
        val (seeds, mappings) = parse(input)

        return seeds.minOf { seed ->
            mappings.fold(seed) { value, mapping -> mapping.map(value) }
        }
    }

    override fun part2(input: Input): Long {
        val (seeds, mappings) = parse(input)

        val seedRanges = seeds.chunked(2) { (start, range) ->
            start until (start + range)
        }

        return mappings
            .fold(seedRanges) { ranges, mapping ->
                ranges.flatMap { mapping.map(it) }
            }
            .minOf { it.first }
    }
}
