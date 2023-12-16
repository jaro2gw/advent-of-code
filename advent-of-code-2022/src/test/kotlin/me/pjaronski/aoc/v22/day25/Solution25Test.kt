package me.pjaronski.aoc.v22.day25

import me.pjaronski.aoc.SolutionTest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.TestFactory

class Solution25Test : SolutionTest<String, String?>(Solution25) {
    override val expected1 = sequenceOf("2=-1=0")
    override val expected2 = emptySequence<String?>()

    @TestFactory
    @Disabled("There is no part 2 on day 25")
    override fun part2() = super.part2()
}
