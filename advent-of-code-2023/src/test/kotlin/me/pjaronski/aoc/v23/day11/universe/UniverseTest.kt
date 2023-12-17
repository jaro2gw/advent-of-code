package me.pjaronski.aoc.v23.day11.universe

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.v23.day11.Solution11Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory

class UniverseTest {

    @TestFactory
    fun `should calculate universe expansion correctly`(): List<DynamicTest> {
        val lines = Input(Solution11Test::class.java, "example.txt").lines().toTypedArray()
        val universe = Universe(lines)

        return listOf(1L to 374L, 9L to 1030L, 99L to 8410L).map { (years, expected) ->
            dynamicTest("Should calculate universe expansion correctly [years: $years] -> expected: $expected") {
                val actual = universe.simulateExpansion(years)
                assertEquals(expected, actual)
            }
        }
    }
}
