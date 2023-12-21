package me.pjaronski.aoc.v23.day12.onsen

enum class Geyser(private val graphic: Char) {
    WORKING('.'),
    DAMAGED('#'),
    UNKNOWN('?');

    override fun toString() = "$graphic"

    companion object {
        fun parse(char: Char): Geyser = entries.first { it.graphic == char }
    }
}
