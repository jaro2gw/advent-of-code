package me.pjaronski.aoc.utils

import kotlin.math.abs
import kotlin.math.sign

data class Coords(
    val row: Int,
    val col: Int,
) : Comparable<Coords> {
    companion object {
        val ORIGIN = Coords(0, 0)

        /** [end] inclusive */
        fun path(start: Coords, end: Coords) = sequence {
            val r = (end.row - start.row).sign
            val c = (end.col - start.col).sign

            var row = start.row
            var col = start.col

            var dr: Boolean
            var dc: Boolean

            do {
                val coords = Coords(row, col)
                yield(coords)
                dr = row != end.row
                dc = col != end.col
                if (dr) row += r
                if (dc) col += c
            } while (dr || dc)
        }

        fun manhattanDistance(start: Coords, end: Coords): Int {
            return abs(end.row - start.row) + abs(end.col - start.col)
        }
    }


    fun neighbours(axis: Boolean = true, diagonal: Boolean = true, self: Boolean = false): Sequence<Coords> = sequence {
        if (axis) {
            yield(copy(row = row - 1))
            yield(copy(row = row + 1))
            yield(copy(col = col - 1))
            yield(copy(col = col + 1))
        }
        if (diagonal) {
            yield(copy(row = row - 1, col = col - 1))
            yield(copy(row = row - 1, col = col + 1))
            yield(copy(row = row + 1, col = col - 1))
            yield(copy(row = row + 1, col = col + 1))
        }
        if (self) {
            yield(copy())
        }
    }

    operator fun plus(coords: Coords) = Coords(
        row = this.row + coords.row,
        col = this.col + coords.col,
    )

    operator fun plus(direction: Direction): Coords = plus(direction.vector)

    operator fun minus(coords: Coords) = Coords(
        row = this.row - coords.row,
        col = this.col - coords.col,
    )

    operator fun minus(direction: Direction): Coords = minus(direction.vector)

    operator fun times(scale: Int): Coords = Coords(row = row * scale, col = col * scale)

    override fun compareTo(other: Coords): Int {
        val comp = this.row.compareTo(other.row)
        return if (comp != 0) comp
        else this.col.compareTo(other.col)
    }

    override fun toString(): String = "(row: $row, col: $col)"
}

// get

operator fun <T> Array<Array<T>>.get(coords: Coords): T = this[coords.row][coords.col]

operator fun Array<CharArray>.get(coords: Coords): Char = this[coords.row][coords.col]

operator fun Array<IntArray>.get(coords: Coords): Int = this[coords.row][coords.col]

operator fun Array<BooleanArray>.get(coords: Coords): Boolean = this[coords.row][coords.col]

operator fun List<BooleanArray>.get(coords: Coords): Boolean = this[coords.row][coords.col]

// set

operator fun <T> Array<Array<T>>.set(coords: Coords, value: T) {
    this[coords.row][coords.col] = value
}

operator fun Array<CharArray>.set(coords: Coords, value: Char) {
    this[coords.row][coords.col] = value
}

operator fun Array<IntArray>.set(coords: Coords, value: Int) {
    this[coords.row][coords.col] = value
}

operator fun Array<BooleanArray>.set(coords: Coords, value: Boolean) {
    this[coords.row][coords.col] = value
}

operator fun List<BooleanArray>.set(coords: Coords, value: Boolean) {
    this[coords.row][coords.col] = value
}

// contains

operator fun <T> Array<Array<T>>.contains(coords: Coords): Boolean {
    return coords.row in this.indices && coords.col in this[coords.row].indices
}

operator fun Array<String>.contains(coords: Coords): Boolean {
    return coords.row in this.indices && coords.col in this[coords.row].indices
}

operator fun Array<CharArray>.contains(coords: Coords): Boolean {
    return coords.row in this.indices && coords.col in this[coords.row].indices
}

operator fun Array<IntArray>.contains(coords: Coords): Boolean {
    return coords.row in this.indices && coords.col in this[coords.row].indices
}

operator fun Array<BooleanArray>.contains(coords: Coords): Boolean {
    return coords.row in this.indices && coords.col in this[coords.row].indices
}

operator fun List<BooleanArray>.contains(coords: Coords): Boolean {
    return coords.row in this.indices && coords.col in this[coords.row].indices
}
