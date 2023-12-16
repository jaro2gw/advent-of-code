package me.pjaronski.aoc.v23.day10

import me.pjaronski.aoc.SolutionTest

class Solution10Test : SolutionTest<Int, Int>(Solution10) {
    override val inputs1 = sequenceOf("example-1.txt", "example-2.txt")
    override val expected1 = sequenceOf(4, 8)

    override val inputs2 = sequenceOf("example-3.txt", "example-4.txt", "example-5.txt", "example-6.txt")
    override val expected2 = sequenceOf(4, 4, 8, 10)
}
