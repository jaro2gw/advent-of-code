package me.pjaronski.aoc.v23.day01.number

import me.pjaronski.aoc.v23.day01.number.NumberWrapper.Companion.WRAPPERS_ALL
import me.pjaronski.aoc.v23.day01.number.NumberWrapper.Companion.WRAPPERS_LEX
import me.pjaronski.aoc.v23.day01.number.NumberWrapper.Companion.WRAPPERS_NUM
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertEquals

class NumberFinderTest {
    private val finder = NumberFinder()

    @TestFactory
    fun `should find single num digits`() = WRAPPERS_NUM.map { (number, string) ->
        dynamicTest("should find single num digit [string: $string]") {
            val (d1, d2) = finder.find(string, WRAPPERS_NUM)
            assertEquals(number, d1)
            assertEquals(number, d2)
        }
    }

    @TestFactory
    fun `should find single lex digits`() = WRAPPERS_LEX.map { (number, string) ->
        dynamicTest("should find single lex digit [string: $string]") {
            val (d1, d2) = finder.find(string, WRAPPERS_LEX)
            assertEquals(number, d1)
            assertEquals(number, d2)
        }
    }

    @TestFactory
    fun `should find first and last correctly`() = listOf(
        "0123456789" to (0 to 9),
        "zero one two three four five six seven eight nine" to (0 to 9),
    ).map { (line, expected) ->
        dynamicTest("should find first and last correctly [line: $line, expected: $expected]") {
            val actual = finder.find(line, WRAPPERS_ALL)
            assertEquals(expected, actual)
        }
    }
}
