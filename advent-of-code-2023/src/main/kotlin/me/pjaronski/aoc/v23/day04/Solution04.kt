package me.pjaronski.aoc.v23.day04

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.v23.day04.scratchcard.ScratchCard

fun main() = present(Solution04)

object Solution04 : Solution<Int, Long> {
    override fun part1(input: Input) = input.lines()
        .map { ScratchCard.parse(it) }
        .sumOf { (_, winners) ->
            if (winners == 0) 0 else 1 shl (winners - 1)
        }

    override fun part2(input: Input): Long {
        val copies = LongArray(input.lines().size) { 1L }

        input.lines()
            .map { ScratchCard.parse(it) }
            .forEachIndexed { card, (_, winners) ->
                for (next in 1..winners) {
                    copies[card + next] += copies[card]
                }
            }

        return copies.sum()
    }
}
