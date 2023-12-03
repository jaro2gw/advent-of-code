package me.pjaronski.aoc.v22.day22

import me.pjaronski.aoc.Input
import me.pjaronski.aoc.Presenter.present
import me.pjaronski.aoc.Solution
import me.pjaronski.aoc.utils.Coords
import me.pjaronski.aoc.utils.Direction
import me.pjaronski.aoc.utils.Direction.*
import me.pjaronski.aoc.utils.NUMBER_PATTERN
import me.pjaronski.aoc.utils.split
import me.pjaronski.aoc.v22.day22.board.Board
import me.pjaronski.aoc.v22.day22.board.Board.State
import me.pjaronski.aoc.v22.day22.board.Cube
import me.pjaronski.aoc.v22.day22.board.Tile
import me.pjaronski.aoc.v22.day22.board.Turn

fun main() = present(
    solution = Solution22(
        transitions = Input(
            clazz = Solution22::class.java,
            file = "input-transitions.txt"
        )
    )
)

class Solution22(private val transitions: Input) : Solution {
    private val MOVE_REGEX = Regex(NUMBER_PATTERN)
    private val TURN_REGEX = Regex("([LR])")
    private val DIRECTION_PATTERN = "(NORTH|EAST|SOUTH|WEST)"
    private val TRANSITION_REGEX = Regex(
        "rows: from +$NUMBER_PATTERN to +$NUMBER_PATTERN; cols: from +$NUMBER_PATTERN to +$NUMBER_PATTERN; direction: from +$DIRECTION_PATTERN to +$DIRECTION_PATTERN;"
    )

    private fun turns(line: String): List<Turn> = TURN_REGEX.findAll(line)
        .map { it.groupValues[1] }
        .map { it.single() }
        .map { Turn.fromChar(it) }
        .toList()

    private fun moves(line: String): List<Int> = MOVE_REGEX.findAll(line)
        .map { it.groupValues[1] }
        .map { it.toInt() }
        .toList()

    private fun pad(lines: List<String>): List<String> {
        val width = lines.maxOf { it.length }
        return lines.map { it.padEnd(width, ' ') }
    }

    private fun tiles(lines: List<String>): Array<Array<Tile>> = pad(lines)
        .map { line ->
            line.map { Tile.fromChar(it) }.toTypedArray()
        }
        .toTypedArray()

    private fun convert(input: Input): Triple<Array<Array<Tile>>, List<Int>, List<Turn>> {
        val (lines1, lines2) = split(input).toList()

        val tiles = tiles(lines1)
        val moves = moves(lines2.single())
        val turns = turns(lines2.single())

        return Triple(tiles, moves, turns)
    }

    private fun extractTransition(line: String): Triple<Coords, Coords, Pair<Direction, Direction>> {
        val (row1, row2, col1, col2, dir1, dir2) = TRANSITION_REGEX.find(line)!!.destructured

        val coords1 = Coords(
            row = row1.toInt(),
            col = col1.toInt(),
        )

        val coords2 = Coords(
            row = row2.toInt(),
            col = col2.toInt(),
        )

        val directions = Direction.valueOf(dir1) to Direction.valueOf(dir2)

        return Triple(coords1, coords2, directions)
    }

    private fun precomputeTransitions(): Map<State, State> {
        val map = mutableMapOf<State, State>()

        split(transitions).forEach { (line1, line2) ->
            val (coords11, coords12, directions1) = extractTransition(line1)
            val (coords21, coords22, directions2) = extractTransition(line2)

            val path1 = Coords.path(coords11, coords12)
            val path2 = Coords.path(coords21, coords22)

            path1.zip(path2).forEach { (coords1, coords2) ->
                val key1 = State(coords1, directions1.second)
                val val1 = State(coords2, directions2.second)
                map[key1] = val1

                val key2 = State(coords2, directions2.first)
                val val2 = State(coords1, directions1.first)
                map[key2] = val2
            }
        }

        return map.toMap()
    }

    private fun score(direction: Direction) = when (direction) {
        EAST -> 0
        SOUTH -> 1
        WEST -> 2
        NORTH -> 3
    }

    private fun score(coords: Coords): Int = 1000 * (coords.row + 1) + 4 * (coords.col + 1)

    private fun score(coords: Coords, direction: Direction): Int = score(coords) + score(direction)

    override fun part1(input: Input): String {
        val (tiles, moves, turns) = convert(input)
        val (coords, direction) = Board(tiles).endpoint(moves, turns)
        return score(coords, direction).toString()
    }

    override fun part2(input: Input): String {
        val (tiles, moves, turns) = convert(input)
        val transitions = precomputeTransitions()
        val (coords, direction) = Cube(tiles, transitions::getValue).endpoint(moves, turns)
        return score(coords, direction).toString()
    }
}
