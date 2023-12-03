package me.pjaronski.aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

abstract class SolutionMultiTest {
    abstract val solve: (Input) -> String
    abstract val files: List<String>
    abstract val answers: List<String>

    @TestFactory
    fun tests(): List<DynamicTest> = files.zip(answers) { file, expected ->
        val input = Input(this::class.java, file)
        val actual = solve(input)

        DynamicTest.dynamicTest(file) { assertEquals(expected, actual) }
    }
}
