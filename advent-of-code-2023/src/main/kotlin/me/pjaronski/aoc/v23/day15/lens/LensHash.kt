package me.pjaronski.aoc.v23.day15.lens

object LensHash {
    fun of(string: String): Int = string.map { it.code }
        .fold(0) { hash, code ->
            ((hash + code) * 17) % 256
        }
}
