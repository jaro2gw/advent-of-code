package me.pjaronski.aoc.v22.day15

import me.pjaronski.aoc.SolutionTest

class Solution15Test : SolutionTest<Int, Long>(solution = Solution15(row = 10, bound = 20)) {
    override val expected1 = sequenceOf(26)
    override val expected2 = sequenceOf(56000011L)
}
