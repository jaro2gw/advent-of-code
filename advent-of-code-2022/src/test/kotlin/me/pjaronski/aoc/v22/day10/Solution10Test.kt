package me.pjaronski.aoc.v22.day10

import me.pjaronski.aoc.Presenter.newline
import me.pjaronski.aoc.SolutionTest

class Solution10Test : SolutionTest<Int, String>(Solution10) {
    override val expected1 = 13140
    override val expected2 = """
        ##..##..##..##..##..##..##..##..##..##..
        ###...###...###...###...###...###...###.
        ####....####....####....####....####....
        #####.....#####.....#####.....#####.....
        ######......######......######......####
        #######.......#######.......#######.....
    """.trimIndent().replace("\n", newline)
}
