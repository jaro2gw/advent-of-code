package me.pjaronski.aoc.v23.day10

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.utils.Coords
import me.pjaronski.aoc.v23.day10.pipe.Pipe
import me.pjaronski.aoc.v23.day10.pipe.PipeCrawler

fun main() = present(Solution10)

object Solution10 : Solution<Int, Int> {
    fun parse(input: Input): Pair<Array<Array<Pipe>>, Coords> {
        val lines = input.lines()
        val pipes: Array<Array<Pipe>> = lines
            .map { line -> line.map { Pipe.parse(it) }.toTypedArray() }
            .toTypedArray()
        val start = findStart(lines)!!
        return pipes to start
    }

    private fun findStart(lines: List<String>): Coords? {
        lines.forEachIndexed { row, line ->
            line.forEachIndexed { col, char ->
                if (char == 'S') return Coords(row, col)
            }
        }
        return null
    }

    override fun part1(input: Input): Int {
        val (pipes, start) = parse(input)
        return PipeCrawler.findMaxDistance(pipes, start)
    }

    override fun part2(input: Input): Int {
        val (pipes, start) = parse(input)
        return PipeCrawler.findInnerTiles(pipes, start)
    }
}
