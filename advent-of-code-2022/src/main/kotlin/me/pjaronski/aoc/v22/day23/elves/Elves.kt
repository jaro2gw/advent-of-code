package me.pjaronski.aoc.v22.day23.elves

import me.pjaronski.aoc.utils.Coords
import me.pjaronski.aoc.utils.Direction
import me.pjaronski.aoc.utils.Direction.EAST
import me.pjaronski.aoc.utils.Direction.NORTH
import me.pjaronski.aoc.utils.Direction.SOUTH
import me.pjaronski.aoc.utils.Direction.WEST
import me.pjaronski.aoc.utils.infinite

class Elves {
    private fun neighbours(coords: Coords) = listOf(
        Coords(row = coords.row - 1, col = coords.col - 1),
        Coords(row = coords.row - 1, col = coords.col - 0),
        Coords(row = coords.row - 1, col = coords.col + 1),
        Coords(row = coords.row - 0, col = coords.col - 1),
        Coords(row = coords.row - 0, col = coords.col + 1),
        Coords(row = coords.row + 1, col = coords.col - 1),
        Coords(row = coords.row + 1, col = coords.col - 0),
        Coords(row = coords.row + 1, col = coords.col + 1),
    )

    private fun neighbours(coords: Coords, direction: Direction) = when (direction) {
        NORTH -> listOf(
            Coords(row = coords.row - 1, col = coords.col - 1),
            Coords(row = coords.row - 1, col = coords.col - 0),
            Coords(row = coords.row - 1, col = coords.col + 1),
        )

        EAST -> listOf(
            Coords(row = coords.row - 1, col = coords.col + 1),
            Coords(row = coords.row - 0, col = coords.col + 1),
            Coords(row = coords.row + 1, col = coords.col + 1),
        )

        SOUTH -> listOf(
            Coords(row = coords.row + 1, col = coords.col - 1),
            Coords(row = coords.row + 1, col = coords.col - 0),
            Coords(row = coords.row + 1, col = coords.col + 1),
        )

        WEST -> listOf(
            Coords(row = coords.row - 1, col = coords.col - 1),
            Coords(row = coords.row - 0, col = coords.col - 1),
            Coords(row = coords.row + 1, col = coords.col - 1),
        )
    }

    private fun next(elf: Coords, elves: Set<Coords>, directions: List<Direction>): Coords {
        return if (neighbours(elf).none { it in elves }) elf
        else {
            val dir = directions.firstOrNull { dir ->
                neighbours(elf, dir).none { it in elves }
            }
            if (dir != null) elf + dir.vector
            else elf
        }
    }

    private fun next(elves: Set<Coords>, directions: List<Direction>): Set<Coords>? =
        elves.groupBy { elf -> next(elf, elves, directions) }
            .entries
            .fold(emptySet<Coords>()) { set, (proposition, collisions) ->
                if (collisions.size == 1) set + proposition
                else set + collisions
            }
            .takeUnless { it == elves }

    fun simulate(elves: Set<Coords>): Sequence<Set<Coords>> {
        val directions = infinite(listOf(NORTH, SOUTH, WEST, EAST))
            .windowed(4)
            .iterator()
        return generateSequence(elves) { next(it, directions.next()) }
            .drop(1)
    }
}
