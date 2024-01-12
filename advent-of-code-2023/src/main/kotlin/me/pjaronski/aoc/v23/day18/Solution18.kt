package me.pjaronski.aoc.v23.day18

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.utils.Coords.Companion.ORIGIN
import me.pjaronski.aoc.utils.Direction
import me.pjaronski.aoc.utils.Direction.EAST
import me.pjaronski.aoc.utils.Direction.NORTH
import me.pjaronski.aoc.utils.Direction.SOUTH
import me.pjaronski.aoc.utils.Direction.WEST
import me.pjaronski.aoc.v23.day18.lagoon.LagoonAreaCalculator

fun main() = present(Solution18)

object Solution18 : Solution<Long, Long> {
    private fun parse(dirPart: String, numPart: String): Pair<Direction, Int> {
        val direction = when (dirPart) {
            "U"  -> NORTH
            "R"  -> EAST
            "D"  -> SOUTH
            "L"  -> WEST
            else -> throw IllegalArgumentException("String \"$dirPart\" does not correspond to any direction")
        }
        val steps = numPart.toInt()

        return direction to steps
    }

    private fun parse(hexPart: String): Pair<Direction, Int> {
        val numPart = hexPart.substring(2, 7)
        val dirPart = hexPart.substring(7, 8)

        check(numPart.length == 5)
        check(dirPart.length == 1)

        val direction = when (dirPart) {
            "0"  -> EAST
            "1"  -> SOUTH
            "2"  -> WEST
            "3"  -> NORTH
            else -> throw IllegalArgumentException("String \"$dirPart\" does not correspond to any direction")
        }

        val steps = numPart.toInt(16)

        return direction to steps
    }

    private fun parse(input: Input, hex: Boolean = false): List<Pair<Direction, Int>> = input.lines()
        .map { line ->
            val (dirPart, numPart, hexPart) = line.split(' ')
            if (hex) parse(hexPart)
            else parse(dirPart, numPart)
        }

    private fun solution(edges: List<Pair<Direction, Int>>): Long {
        val coords = edges.runningFold(ORIGIN) { coords, (direction, steps) ->
            coords + (direction.vector * steps)
        }
        return LagoonAreaCalculator.calculateArea(coords)
    }

    override fun part1(input: Input): Long {
        val edges = parse(input)
        return solution(edges)
    }

    override fun part2(input: Input): Long {
        val edges = parse(input, hex = true)
        return solution(edges)
    }
}
