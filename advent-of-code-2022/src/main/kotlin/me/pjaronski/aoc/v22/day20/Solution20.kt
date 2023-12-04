package me.pjaronski.aoc.v22.day20

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.v22.day20.chain.Chain

fun main() = present(Solution20)

object Solution20 : Solution<Long, Long> {
    override fun part1(input: Input): Long {
        val nums = input.lines().map { it.toInt() }
        return Chain().decrypt(nums)
    }

    override fun part2(input: Input): Long {
        val nums = input.lines().map { it.toInt() }
        return Chain().decrypt(nums, key = 811589153, rounds = 10)
    }
}
