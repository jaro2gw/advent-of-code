package me.pjaronski.aoc.v23.day08

import me.pjaronski.aoc.SolutionTest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Solution08Test : SolutionTest<Int, Long>(Solution08) {
    override val inputs1 = sequenceOf("example-1.txt", "example-2.txt")
    override val expected1 = sequenceOf(2, 6)

    override val inputs2 = sequenceOf("example-3.txt")
    override val expected2 = sequenceOf(6L)
}
