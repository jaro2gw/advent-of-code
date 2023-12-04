package me.pjaronski.aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

abstract class SolutionTest<P1, P2>(
    private val solution: Solution<P1, P2>
) {
    protected open val input1 = "example.txt"
    protected open val input2 = "example.txt"

    protected abstract val expected1: P1
    protected abstract val expected2: P2

    @Test
    open fun part1() {
        assertNotNull(expected1)

        val input = Input(this::class.java, file = input1)
        val actual1 = solution.part1(input)
        assertEquals(expected1, actual1)
    }

    @Test
    open fun part2() {
        assertNotNull(expected2)

        val input = Input(this::class.java, file = input2)
        val actual2 = solution.part2(input)
        assertEquals(expected2, actual2)
    }
}
