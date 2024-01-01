package me.pjaronski.aoc.v23.day16.beam

enum class Tile(val symbol: Char) {
    EMPTY('.'),
    MIRROR_F('/'),
    MIRROR_B('\\'),
    SPLITTER_V('|'),
    SPLITTER_H('-');

    companion object {
        fun parse(char: Char): Tile = entries.single { char == it.symbol }
    }
}
