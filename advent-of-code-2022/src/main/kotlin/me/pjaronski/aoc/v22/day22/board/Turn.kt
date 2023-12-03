package me.pjaronski.aoc.v22.day22.board

import me.pjaronski.aoc.utils.Direction

enum class Turn(val char: Char, private val diff: Int) : (Direction) -> Direction {
    LEFT('L', -1),
    RIGHT('R', +1);

    companion object {
        fun fromChar(char: Char) = entries.single { it.char == char }
    }

    override fun invoke(direction: Direction): Direction {
        val index = (direction.ordinal + diff + 4) % 4
        return Direction.entries[index]
    }

    override fun toString(): String = "$char"
}
