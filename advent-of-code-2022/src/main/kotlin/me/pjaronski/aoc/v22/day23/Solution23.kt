package me.pjaronski.aoc.v22.day23

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.utils.Coords
import me.pjaronski.aoc.utils.MinMax
import me.pjaronski.aoc.v22.day23.elves.Elves

fun main() = present(Solution23)

object Solution23 : Solution {
    private fun elves(input: Input): Set<Coords> = input.lines()
        .flatMapIndexed { row, line ->
            line.withIndex()
                .filter { (_, char) -> char == '#' }
                .map { (col, _) ->
                    Coords(row, col)
                }
        }
        .toSet()

    private fun minmax(coords: Set<Coords>): Pair<MinMax<Int>, MinMax<Int>> = coords
        .map { (row, col) ->
            MinMax(row) to MinMax(col)
        }
        .reduce { (rows1, cols1), (rows2, cols2) ->
            (rows1 + rows2) to (cols1 + cols2)
        }

    override fun part1(input: Input): String {
        val elves = elves(input)
        val (rows, cols) = Elves().simulate(elves)
            .take(10)
            .last()
            .let { minmax(it) }
        val width = cols.max - cols.min + 1
        val height = rows.max - rows.min + 1
        val empty = width * height - elves.size
        return empty.toString()
    }

    override fun part2(input: Input): String {
        val elves = elves(input)
        val index = Elves().simulate(elves)
            .withIndex()
            .map { (index, _) -> index }
            .last()
        val round = index + 2
        return round.toString()
    }
}
