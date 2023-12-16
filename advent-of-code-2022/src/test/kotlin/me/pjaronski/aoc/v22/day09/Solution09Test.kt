package me.pjaronski.aoc.v22.day09

import me.pjaronski.aoc.SolutionTest

class Solution09Test : SolutionTest<Int, Int>(Solution09) {
    override val inputs1 = sequenceOf("example-part1.txt")
    override val expected1 = sequenceOf(13)

    override val inputs2 = sequenceOf("example-part2.txt")
    override val expected2 = sequenceOf(36)
}
