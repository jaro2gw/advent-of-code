package me.pjaronski.aoc.v22.day19

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.SolutionTest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

fun main() = present(Solution19, Input(Solution19Test::class.java, "example.txt"))

class Solution19Test : SolutionTest<Int, Int>(Solution19) {
    override val expected1 = 33
    override val expected2 = 3472

    @Test
    @Disabled(
        """
        For some reason, part 2 test runs for a longer time with the example input than with the actual input.
        On my machine the second part with the example input runs in ~26s. With actual input it runs in ~8s.
        """
    )
    override fun part2() = super.part2()
}
