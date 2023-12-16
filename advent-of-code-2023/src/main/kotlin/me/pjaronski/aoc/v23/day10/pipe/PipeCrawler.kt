package me.pjaronski.aoc.v23.day10.pipe

import me.pjaronski.aoc.utils.Coords
import me.pjaronski.aoc.utils.Direction
import me.pjaronski.aoc.utils.Direction.EAST
import me.pjaronski.aoc.utils.Direction.NORTH
import me.pjaronski.aoc.utils.contains
import me.pjaronski.aoc.utils.get
import me.pjaronski.aoc.utils.set
import me.pjaronski.aoc.v23.day10.pipe.PipeCrawler.PipeTile.INSIDE
import me.pjaronski.aoc.v23.day10.pipe.PipeCrawler.PipeTile.OUTSIDE
import me.pjaronski.aoc.v23.day10.pipe.PipeCrawler.PipeTile.PIPELINE
import me.pjaronski.aoc.v23.day10.pipe.PipeCrawler.PipeTile.UNKNOWN

object PipeCrawler {
    private enum class PipeTile {
        UNKNOWN,
        INSIDE,
        PIPELINE,
        OUTSIDE,
    }

    fun findMaxDistance(pipes: Array<Array<Pipe>>, start: Coords): Int {
        val visited = Array(pipes.size) {
            BooleanArray(pipes[it].size)
        }
        val distance = Array(pipes.size) {
            IntArray(pipes[it].size) { Int.MAX_VALUE }
        }

        var dist = 0

        visited[start] = true
        distance[start] = 0

        val queue = mutableListOf(start)
        while (queue.isNotEmpty()) {
            val source = queue.removeFirst()
            dist = distance[source] + 1
            for (direction in Direction.entries) {
                val target = source + direction.vector
                if (
                    target in pipes &&
                    !visited[target] &&
                    Pipe.connectable(pipes[source], pipes[target], direction)
                ) {
                    visited[target] = true
                    distance[target] = dist
                    queue += target
                }
            }
        }

        return dist - 1
    }

    private fun markPipelineTiles(
        pipes: Array<Array<Pipe>>,
        start: Coords,
        tiles: Array<Array<PipeTile>>
    ) {
        tiles[start] = PIPELINE
        val queue = mutableListOf(start)
        while (queue.isNotEmpty()) {
            val source = queue.removeFirst()
            for (direction in Direction.entries) {
                val target = source + direction.vector
                if (
                    target in pipes &&
                    tiles[target] != PIPELINE &&
                    Pipe.connectable(pipes[source], pipes[target], direction)
                ) {
                    tiles[target] = PIPELINE
                    queue += target
                }
            }
        }
    }

    private fun markAllNeighbours(tiles: Array<Array<PipeTile>>, start: Coords) {
        val tile = tiles[start]
        val queue = mutableListOf(start)
        while (queue.isNotEmpty()) {
            queue.removeFirst()
                .neighbours(diagonal = false)
                .filter { it in tiles }
                .filter { tiles[it] == UNKNOWN }
                .forEach {
                    tiles[it] = tile
                    queue += it
                }
        }
    }

    private fun pipeHasConnection(pipes: Array<Array<Pipe>>, source: Coords): Boolean {
        val target = source + NORTH.vector
        return target in pipes && Pipe.connectable(pipes[source], pipes[target], NORTH)
    }

    private fun findInnerTiles(pipes: Array<Array<Pipe>>, tiles: Array<Array<PipeTile>>): Int {
        var inside = 0
        for (row in pipes.indices) {
            for (col in pipes[row].indices) {
                val coords = Coords(row, col)
                when (tiles[coords]) {
                    INSIDE -> inside += 1
                    UNKNOWN -> {
                        // https://www.geeksforgeeks.org/how-to-check-if-a-given-point-lies-inside-a-polygon/
                        val crossings = generateSequence(coords) { it + EAST.vector }
                            .takeWhile { it in pipes }
                            .filter { tiles[it] == PIPELINE }
                            .count { pipeHasConnection(pipes, it) }
                        val tile = if (crossings % 2 == 0) OUTSIDE else INSIDE
                        if (tile == INSIDE) inside += 1
                        tiles[coords] = tile
                        markAllNeighbours(tiles, coords)
                    }

                    else -> Unit
                }
            }
        }

        return inside
    }

    fun findInnerTiles(pipes: Array<Array<Pipe>>, start: Coords): Int {
        val tiles = Array(pipes.size) {
            Array(pipes[it].size) { UNKNOWN }
        }
        markPipelineTiles(pipes, start, tiles)
        return findInnerTiles(pipes, tiles)
    }
}