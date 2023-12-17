package me.pjaronski.aoc

import me.pjaronski.aoc.utils.infinite
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory

abstract class SolutionTest<P1, P2>(
    protected val solution: Solution<P1, P2>
) {
    protected open val inputs1: Sequence<String> = infinite("example.txt")
    protected abstract val expected1: Sequence<P1>

    protected open val inputs2: Sequence<String> = infinite("example.txt")
    protected abstract val expected2: Sequence<P2>

    private fun <T> tests(
        part: Int,
        inputs: Sequence<String>,
        answers: Sequence<T>,
        solve: (Input) -> T
    ) = inputs.zip(answers).toList().map { (file, expected) ->
        dynamicTest("Part $part [input: '$file', expected: '$expected']") {
            val input = Input(this::class.java, file)
            val actual = solve(input)
            assertEquals(expected, actual)
        }
    }

    @TestFactory
    open fun part1() = tests(1, inputs1, expected1, solution::part1)

    @TestFactory
    open fun part2() = tests(2, inputs2, expected2, solution::part2)
}
