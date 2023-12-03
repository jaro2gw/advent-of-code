package me.pjaronski.aoc.v22.day21.monkey

class MonkeyWithNumber(
    name: String,
    private val number: Long,
) : Monkey(name) {
    override fun yell(): Long = number
}
