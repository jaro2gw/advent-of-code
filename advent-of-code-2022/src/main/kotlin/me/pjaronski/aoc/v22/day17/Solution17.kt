package me.pjaronski.aoc.v22.day17

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.v22.day17.rock.RockChamber
import me.pjaronski.aoc.v22.day17.rock.RockMove
import me.pjaronski.aoc.v22.day17.rock.RockShape.Companion.shapes

fun main() = present(Solution17)

object Solution17 : Solution<Int, Long> {
    private fun moves(input: Input): List<RockMove> = input.lines()
        .first()
        .map {
            when (it) {
                '<' -> RockMove.LEFT
                '>' -> RockMove.RIGHT
                else -> throw IllegalArgumentException("Cannot convert char '$it' into a rock move")
            }
        }

    override fun part1(input: Input): Int = RockChamber(
        width = 7,
        shapes = shapes,
        moves = moves(input)
    )
        .height(2022)

    override fun part2(input: Input): Long = RockChamber(
        width = 7,
        shapes = shapes,
        moves = moves(input)
    )
        .height(1_000_000_000_000L)
}
