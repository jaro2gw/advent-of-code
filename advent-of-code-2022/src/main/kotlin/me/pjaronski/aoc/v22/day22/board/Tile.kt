package me.pjaronski.aoc.v22.day22.board

enum class Tile(val char: Char) {
    BORDER(' '),
    EMPTY('.'),
    BLOCKED('#');

    companion object {
        fun fromChar(char: Char) = entries.single { it.char == char }
    }

    override fun toString(): String = "$char"
}
