package me.pjaronski.aoc.v22.day09

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.v22.day09.rope.Movement
import me.pjaronski.aoc.v22.day09.rope.Rope

fun main() = present(Solution09)

object Solution09 : Solution<Int, Int> {
    private fun solution(input: Input, knots: Int): Int {
        val rope = Rope.chain(knots)
        input.lines()
            .map { Movement.fromString(it) }
            .forEach { rope.move(it) }
        return rope.tails().size
    }

    override fun part1(input: Input) = solution(input, knots = 2)

    override fun part2(input: Input) = solution(input, knots = 10)
}
