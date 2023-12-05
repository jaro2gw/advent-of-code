package me.pjaronski.aoc.v23.day03.engine

import me.pjaronski.aoc.utils.Coords
import me.pjaronski.aoc.utils.contains
import me.pjaronski.aoc.utils.get
import me.pjaronski.aoc.utils.set

class EngineSchematic(
    private val lines: Array<String>
) {
    private val isNearSymbol = Array(lines.size) { BooleanArray(lines[it].length) }

    private val potentialParts: List<EnginePart>
    private val potentialGears: List<EngineGear>

    init {
        val potentialParts = mutableListOf<EnginePart>()
        val potentialGears = mutableListOf<EngineGear>()

        lines.forEachIndexed { row, line ->
            var num = 0
            var idx = 0
            line.forEachIndexed { col, char ->
                when (char) {
                    '.' -> {
                        if (num != 0) {
                            val cols = idx..<col
                            potentialParts += EnginePart(row, cols, num)
                            num = 0
                            idx = 0
                        }
                    }

                    in '0'..'9' -> {
                        if (num == 0) idx = col
                        num *= 10
                        num += char - '0'
                    }

                    else -> {
                        if (num != 0) {
                            val cols = idx..<col
                            potentialParts += EnginePart(row, cols, num)
                            num = 0
                            idx = 0
                        }
                        if (char == '*') {
                            potentialGears += EngineGear(row, col)
                        }

                        Coords(row, col).neighbours()
                            .filter { it in lines }
                            .forEach { isNearSymbol[it] = true }
                    }
                }
            }
            if (num != 0) {
                val cols = idx..<lines.last().length
                potentialParts += EnginePart(row, cols, num)
                num = 0
                idx = 0
            }
        }

        this.potentialParts = potentialParts.toList()
        this.potentialGears = potentialGears.toList()
    }

    fun engineParts(): List<Int> = potentialParts
        .filter { (row, cols) ->
            val first = Coords(row, cols.first)
            val last = Coords(row, cols.last)
            Coords.path(first, last).any { isNearSymbol[it] }
        }
        .map { it.num }

    fun gears(): List<Int> = potentialGears
        .map { gear ->
            gear.neighbours()
                .mapNotNull { (row, col) ->
                    potentialParts.firstOrNull { row == it.row && col in it.cols }
                }
                .distinct()
                .map { it.num }
                .toList()
        }
        .filter { it.size == 2 }
        .map { (part1, part2) -> part1 * part2 }
}
