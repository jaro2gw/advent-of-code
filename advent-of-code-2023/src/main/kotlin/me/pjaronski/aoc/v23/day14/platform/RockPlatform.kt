package me.pjaronski.aoc.v23.day14.platform

import me.pjaronski.aoc.utils.Coords
import me.pjaronski.aoc.utils.Direction
import me.pjaronski.aoc.utils.Direction.EAST
import me.pjaronski.aoc.utils.Direction.NORTH
import me.pjaronski.aoc.utils.Direction.SOUTH
import me.pjaronski.aoc.utils.Direction.WEST
import me.pjaronski.aoc.utils.contains
import me.pjaronski.aoc.utils.deepCopy
import me.pjaronski.aoc.utils.get
import me.pjaronski.aoc.utils.set

class RockPlatform(
    private val platform: Array<CharArray>
) {
    fun tilt(direction: Direction): RockPlatform {
        val platform = platform.deepCopy()

        val rows: (Array<CharArray>) -> Iterable<IndexedValue<CharArray>> = if (direction == SOUTH) {
            { it.withIndex().reversed() }
        } else {
            { it.withIndex() }
        }

        val cols: (CharArray) -> Iterable<IndexedValue<Char>> = if (direction == EAST) {
            { it.withIndex().reversed() }
        } else {
            { it.withIndex() }
        }

        rows(platform).forEach { (row, chars) ->
            cols(chars).forEach { (col, rock) ->
                if (rock == 'O') {
                    var coords = Coords(row, col) + direction.vector
                    while (coords in platform && platform[coords] == '.') {
                        coords += direction.vector
                    }

                    platform[row][col] = '.'
                    platform[coords - direction.vector] = 'O'
                }
            }
        }

        return RockPlatform(platform)
    }

    fun spin(): RockPlatform = tilt(NORTH).tilt(WEST).tilt(SOUTH).tilt(EAST)

    fun load(): Int = platform.withIndex()
        .sumOf { (row, chars) ->
            val rocks = chars.count { it == 'O' }
            val score = platform.size - row

            score * rocks
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RockPlatform

        return platform.contentDeepEquals(other.platform)
    }

    override fun hashCode(): Int = platform.contentDeepHashCode()
}
