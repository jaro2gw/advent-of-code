package me.pjaronski.aoc.v23.day01

import me.pjaronski.aoc.SolutionTest

class Solution01Test : SolutionTest<Int, Int>(Solution01) {
    override val inputs1 = sequenceOf("example-part1.txt")
    override val expected1 = sequenceOf(142)

    override val inputs2 = sequenceOf("example-part2.txt")
    override val expected2 = sequenceOf(281)
}
