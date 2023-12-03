package me.pjaronski.aoc.v22.day08

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.v22.day08.trees.Trees
import me.pjaronski.aoc.v22.day08.trees.TreesEvaluator

fun main() = present(Solution08)

object Solution08 : Solution {
    private fun makeTreeMap(lines: Iterable<String>): Trees = lines
        .map { line ->
            line.map { char -> char - '0' }
                .toIntArray()
        }
        .toTypedArray()
        .let { Trees(it) }

    override fun part1(input: Input): String {
        val trees = makeTreeMap(input.lines())
        return TreesEvaluator().visibleTrees(trees).toString()
    }

    override fun part2(input: Input): String {
        val trees = makeTreeMap(input.lines())
        return TreesEvaluator().scenicScore(trees).toString()
    }
}
