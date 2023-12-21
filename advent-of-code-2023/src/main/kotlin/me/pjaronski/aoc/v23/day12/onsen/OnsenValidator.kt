package me.pjaronski.aoc.v23.day12.onsen

import me.pjaronski.aoc.utils.head
import me.pjaronski.aoc.v23.day12.onsen.Geyser.DAMAGED
import me.pjaronski.aoc.v23.day12.onsen.Geyser.UNKNOWN
import me.pjaronski.aoc.v23.day12.onsen.Geyser.WORKING


object OnsenValidator {
    private val cache = HashMap<ParsingState, Long>()

    private data class ParsingState(
        val geysers: List<Geyser>,
        val numbers: List<Int>,
        val expected: Onsen
    ) {
        override fun toString(): String {
            val g = geysers.joinToString(separator = "")
            val n = numbers.joinToString(separator = ",")
            return "ParsingState($g $n; expected: $expected)"
        }
    }

    private fun cache(state: ParsingState): Long = cache.getOrPut(state) { possibilities(state) }

    private fun init(geysers: List<Geyser>, numbers: List<Int>): Sequence<ParsingState> = sequence {
        yield(
            ParsingState(
                geysers = geysers,
                numbers = numbers,
                expected = Working
            )
        )
        val (nHead, nTail) = numbers.head()
        if (nHead != null) yield(
            ParsingState(
                geysers = geysers,
                numbers = nTail,
                expected = Damaged(nHead)
            )
        )
    }

    private fun next(state: ParsingState): Sequence<ParsingState> {
        val (geysers, numbers, expected) = state
        val (gHead, gTail) = geysers.head()
        val (nHead, nTail) = numbers.head()

        check(gHead != null)

        fun working() = ParsingState(
            geysers = gTail,
            numbers = numbers,
            expected = Working
        )

        fun damaged(numbers: List<Int>, count: Int) = ParsingState(
            geysers = gTail,
            numbers = numbers,
            expected = Damaged(count = count)
        )

        return sequence {
            when (expected) {
                Working -> when (gHead) {
                    DAMAGED -> Unit
                    WORKING, UNKNOWN -> {
                        yield(working())
                        if (nHead != null) yield(damaged(numbers = nTail, count = nHead))
                    }
                }

                is Damaged -> when (gHead) {
                    WORKING -> Unit
                    DAMAGED, UNKNOWN -> {
                        if (expected.count == 1) yield(working())
                        else yield(damaged(numbers = numbers, count = expected.count - 1))
                    }
                }
            }
        }
    }

    private fun possibilities(state: ParsingState): Long {
        val (geysers, numbers, expected) = state
        return if (geysers.isEmpty()) {
            if (numbers.isEmpty()) {
                when (expected) {
                    Working -> 1L
                    is Damaged -> 0L
                }
            } else 0L
        } else next(state).sumOf { cache(it) }
    }


    fun possibilities(onsen: String): Long {
        val (graphic, numeric) = onsen.split(' ')

        val geysers = graphic.map { Geyser.parse(it) }
        val numbers = numeric.split(',').map { it.toInt() }

        return init(geysers, numbers).sumOf { cache(it) }
    }
}
