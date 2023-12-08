package me.pjaronski.aoc.v23.day05.garden

import java.util.*

class GardenResourceMapping(rules: List<Triple<Long, Long, Long>>) {
    private data class SourceToTargetMapping(
        val sourceRange: LongRange,
        val targetStart: Long,
    ) : Comparable<SourceToTargetMapping> {
        override fun compareTo(other: SourceToTargetMapping): Int = sourceRange.first compareTo other.sourceRange.first
    }

    private val mappings = rules.associateTo(TreeMap()) { (target, source, range) ->
        source to SourceToTargetMapping(source until source + range, target)
    }

    /** translates the source [value] into a number from the target mapping */
    fun map(value: Long): Long {
        val (_, mapping) = mappings.floorEntry(value) ?: return value
        return if (value in mapping.sourceRange) mapping.targetStart + value - mapping.sourceRange.first
        else value
    }

    internal fun split(values: LongRange): List<LongRange> = mappings.asSequence()
        .map { (_, mapping) -> mapping.sourceRange }
        .plus(element = values)
        .flatMap { range -> listOf(range.first, range.last + 1) }
        .filter { it in values }
        .plus(element = values.last + 1)
        .distinct()
        .sorted()
        .zipWithNext { first, last -> first until last }
        .toList()

    /** translates the source [values] into ranges of numbers from the target mapping */
    fun map(values: LongRange): List<LongRange> = split(values).map { sourceRange ->
        val targetFirst = map(sourceRange.first)
        val targetLast = map(sourceRange.last)
        targetFirst..targetLast
    }
}
