package me.pjaronski.aoc.v23.day19.parts

import me.pjaronski.aoc.utils.EnumArray
import me.pjaronski.aoc.utils.head
import me.pjaronski.aoc.v23.day19.parts.MechanicalPart.Attribute
import me.pjaronski.aoc.v23.day19.parts.Workflow.Decision.ACCEPT
import me.pjaronski.aoc.v23.day19.parts.Workflow.Decision.REJECT
import me.pjaronski.aoc.v23.day19.parts.Workflow.Rule.Operator.GT
import me.pjaronski.aoc.v23.day19.parts.Workflow.Rule.Operator.LT
import kotlin.math.max
import kotlin.math.min

class PartSorter(
    private val workflows: Map<String, Workflow>
) {
    private val init = workflows["in"]!!

    /** returns accepted parts in the first position of the pair and the rejected ones in the second position */
    fun sort(parts: List<MechanicalPart>): Pair<List<MechanicalPart>, List<MechanicalPart>> {
        return parts.partition { decision(init, it) == ACCEPT }
    }

    private fun decision(workflow: Workflow, part: MechanicalPart): Workflow.Decision =
        when (val destination = workflow(part)) {
            is Workflow.Redirect -> decision(workflows[destination.workflow]!!, part)
            is Workflow.Decision -> destination
        }

    /* [1, 4000] */
    /** returns how many combinations in the given range ultimately get accepted and how many get rejected */
    fun combinations(lower: Int, upper: Int): Pair<Long, Long> {
        val range = Range.of(lower, upper)
        return combinations(
            EnumArray.of { range },
            init.rules,
            init.default,
        )
    }

    private fun size(ranges: EnumArray<Attribute, Range>): Long = ranges.values()
        .map { it.size.toLong() }
        .reduce { s1, s2 -> s1 * s2 }

    private fun combinations(
        ranges: EnumArray<Attribute, Range>,
        rules: List<Workflow.Rule>,
        default: Workflow.Destination
    ): Pair<Long, Long> {
        val (rule, remaining) = rules.head()
        return if (rule == null) {
            when (default) {
                ACCEPT               -> size(ranges) to 0L
                REJECT               -> 0L to size(ranges)
                is Workflow.Redirect -> {
                    val workflow = workflows[default.workflow]!!
                    combinations(ranges, workflow.rules, workflow.default)
                }
            }
        } else {
            val (attribute, operator, number, destination) = rule
            val (fulfilled, denied) = ranges[attribute].split(operator, number)

            val (fAccepted, fRejected) =
                if (fulfilled.size != 0) {
                    val fRanges = EnumArray.of<Attribute, Range> { ranges[it] }
                    fRanges[attribute] = fulfilled

                    when (destination) {
                        ACCEPT               -> size(fRanges) to 0L
                        REJECT               -> 0L to size(fRanges)
                        is Workflow.Redirect -> {
                            val workflow = workflows[destination.workflow]!!
                            combinations(fRanges, workflow.rules, workflow.default)
                        }
                    }
                } else 0L to 0L

            val (dAccepted, dRejected) =
                if (denied.size != 0) {
                    val dRanges = EnumArray.of<Attribute, Range> { ranges[it] }
                    dRanges[attribute] = denied

                    combinations(dRanges, remaining, default)
                } else 0L to 0L

            (fAccepted + dAccepted) to (fRejected + dRejected)
        }
    }

    /** [lower] inclusive, [upper] inclusive */
    internal class Range private constructor(private val lower: Int, private val upper: Int) {
        val size: Int = upper - lower + 1

        companion object {
            internal val EMPTY = Range(0, -1)

            fun of(lower: Int, upper: Int): Range =
                if (lower > upper) EMPTY
                else Range(lower, upper)
        }

        /** first range is the one that fulfills the predicate */
        fun split(operator: Workflow.Rule.Operator, number: Int): Pair<Range, Range> =
            when (operator) {
                LT -> of(lower, min(upper, number - 1)) to of(max(lower, number), upper)
                GT -> of(max(lower, number + 1), upper) to of(lower, min(upper, number))
            }
    }
}
