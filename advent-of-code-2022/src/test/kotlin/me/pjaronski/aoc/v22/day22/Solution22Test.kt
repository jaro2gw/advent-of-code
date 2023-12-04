package me.pjaronski.aoc.v22.day22

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.SolutionTest

class Solution22Test : SolutionTest<Int, Int>(solution = Solution22(
    transitions = Input(
        clazz = Solution22Test::class.java,
        file = "example-transitions.txt"
    )
)) {
    override val expected1 = 6032
    override val expected2 = 5031
}
