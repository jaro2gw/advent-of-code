package me.pjaronski.aoc.v23.day13

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.utils.split
import me.pjaronski.aoc.v23.day13.mirror.MirrorFinder.findMirror
import me.pjaronski.aoc.v23.day13.mirror.MirrorFinder.findSmudge

fun main() = present(Solution13)

object Solution13 : Solution<Int, Int> {
    private fun score(mCol: Int, mRow: Int): Int = mCol + 100 * mRow

    private fun parse(input: Input) = split(input).map { rows ->
        val cols = List(rows[0].length) { col ->
            rows.map { it[col] }
                .toCharArray()
                .concatToString()
        }

        rows to cols
    }

    override fun part1(input: Input): Int = parse(input).sumOf { (rows, cols) ->
        score(findMirror(rows) ?: 0, findMirror(cols) ?: 0)
    }

    override fun part2(input: Input): Int = parse(input).sumOf { (rows, cols) ->
        score(findSmudge(rows) ?: 0, findSmudge(cols) ?: 0)
    }
}
