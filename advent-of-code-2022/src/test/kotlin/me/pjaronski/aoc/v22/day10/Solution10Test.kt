package me.pjaronski.aoc.v22.day10

import me.pjaronski.aoc.SolutionTest
import java.lang.System.lineSeparator

class Solution10Test : SolutionTest() {
    override val solution = Solution10
    override val expected1 = "13140"
    override val expected2 = """
        ##..##..##..##..##..##..##..##..##..##..
        ###...###...###...###...###...###...###.
        ####....####....####....####....####....
        #####.....#####.....#####.....#####.....
        ######......######......######......####
        #######.......#######.......#######.....
    """.trimIndent().replace("\n", lineSeparator())
}
