package me.pjaronski.aoc.v23.day12.onsen

sealed class Onsen
data object Working : Onsen() {
    override fun toString(): String = "."
}
data class Damaged(val count: Int) : Onsen() {
    override fun toString(): String = "#".repeat(count)
}
