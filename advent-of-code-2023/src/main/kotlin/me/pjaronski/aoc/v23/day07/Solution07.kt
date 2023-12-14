package me.pjaronski.aoc.v23.day07

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.v23.day07.card.CardHandComparator
import me.pjaronski.aoc.v23.day07.card.JokerCardHand
import me.pjaronski.aoc.v23.day07.card.SimpleCardHand

fun main() = present(Solution07)

object Solution07 : Solution<Int, Int> {
    override fun part1(input: Input): Int = input.lines()
        .map { SimpleCardHand.parse(it) }
        .sortedWith(CardHandComparator)
        .mapIndexed { rank, hand -> hand.bid * (rank + 1) }
        .sum()

    override fun part2(input: Input): Int = input.lines()
        .map { JokerCardHand.parse(it) }
        .sortedWith(CardHandComparator)
        .mapIndexed { rank, hand -> hand.bid * (rank + 1) }
        .sum()
}
