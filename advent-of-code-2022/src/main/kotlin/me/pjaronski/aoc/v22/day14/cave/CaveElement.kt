package me.pjaronski.aoc.v22.day14.cave

enum class CaveElement(val value: String, val free: Boolean) {
    NONE(".", true),
    LEAK("+", true),
    ROCK("#", false),
    SAND("O", false),
}
