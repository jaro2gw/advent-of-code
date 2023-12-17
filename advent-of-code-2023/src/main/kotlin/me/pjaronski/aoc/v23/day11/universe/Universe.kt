package me.pjaronski.aoc.v23.day11.universe

import me.pjaronski.aoc.utils.Coords

class Universe(
    lines: Array<String>
) {
    private fun Array<String>.row(index: Int): Iterable<Char> = this[index].asIterable()
    private fun Array<String>.col(index: Int): Iterable<Char> = map { it[index] }

    private val emptyRows = lines.indices.filter { index -> lines.row(index).all { it == '.' } }.toSortedSet()
    private val emptyCols = lines[0].indices.filter { index -> lines.col(index).all { it == '.' } }.toSortedSet()
    private val galaxies = lines.flatMapIndexed { row, line ->
        line.mapIndexedNotNull { col, char ->
            if (char == '#') Coords(row, col)
            else null
        }
    }.toTypedArray()

    fun simulateExpansion(years: Long): Long {
        var distances = 0L
        for (g1 in galaxies) {
            for (g2 in galaxies) {
                val (minRow, maxRow) =
                    if (g1.row < g2.row) g1.row to g2.row
                    else g2.row to g1.row
                val (minCol, maxCol) =
                    if (g1.col < g2.col) g1.col to g2.col
                    else g2.col to g1.col

                val emptyRowsBetween = emptyRows.subSet(minRow, maxRow).count() * years
                val emptyColsBetween = emptyCols.subSet(minCol, maxCol).count() * years

                distances += Coords.manhattanDistance(g1, g2) + emptyRowsBetween + emptyColsBetween
            }
        }
        return distances / 2
    }
}
