package me.pjaronski.aoc.v22.day12.grid

import me.pjaronski.aoc.utils.Coords
import me.pjaronski.aoc.utils.contains
import me.pjaronski.aoc.utils.get
import me.pjaronski.aoc.utils.toString2D

class Grid(
    private val elevation: Array<IntArray>,
    val start: Coords,
    val end: Coords,
) {
    companion object {
        private val alphabet = ('a'..'z').toList().toCharArray()
    }

    val rows: Int = elevation.size
    val cols: Int = elevation[0].size

    init {
        require(elevation[start] == 0)
        require(elevation[end] == 26)

        for (row in elevation) {
            require(row.size == cols)
        }
    }

    operator fun get(coords: Coords): Int = elevation[coords]

    operator fun contains(coords: Coords): Boolean = coords in elevation

    override fun toString(): String = elevation
        .mapIndexed { row, ints ->
            ints.mapIndexed { col, i ->
                when (Coords(row, col)) {
                    start -> 'S'
                    end -> 'E'
                    else -> alphabet[i]
                }
            }
        }
        .toString2D()
}
